package com.example.finalexam;

import com.example.finalexam.Model.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.NumberFormat;
import java.util.*;

public class MainController implements Initializable {


    @FXML
    private TableView<Car> CarTableView;
    @FXML
    private Label header;

    @FXML
    private Label addressLabel;

    @FXML
    private Label averageEngineSizeLabel;

    @FXML
    private Label totalValueLabel;

    @FXML
    private Label numberOfCarsLabel;

    @FXML
    private ComboBox<String> filterDropdown;

    @FXML
    private TextField filterTextBox;

    @FXML
    private TableColumn<Car, String> TransmissionSizeColumn;
    @FXML
    private TableColumn<Car, Double> engineSizeColumn;



    @FXML
    private TableColumn<Car, String> fueltypeColumn;



    @FXML
    private TableColumn<Car, String> modelColumn;

    @FXML
    private TableColumn<Car, Double> mpgColumn;



    @FXML
    private TableColumn<Car, Double> priceColumn;



    @FXML
    private TableColumn<Car, Integer> yearColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataAccessLayer.loadJsonToMemory("src/main/audi.json");

        TransmissionSizeColumn.setCellValueFactory(new PropertyValueFactory<Car,String>("transmission"));
        engineSizeColumn.setCellValueFactory(new PropertyValueFactory<Car,Double>("EngineSize"));
        mpgColumn.setCellValueFactory(new PropertyValueFactory<Car,Double>("mpg"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Car,String>("MODEL"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Car,Double>("price"));
        fueltypeColumn.setCellValueFactory(new PropertyValueFactory<Car,String>("fuelType"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("year"));
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        priceColumn.setCellFactory(tc -> new TableCell<Car, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty || price == null) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(price)); // Format as currency
                }
            }
        });

        try {
            DataAccessLayer.getReadThread().join(); //date is loaded into memory
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Car> AllCars = DataAccessLayer.getInventoryData().getInventory();
        ObservableList<Car> CarsInObList = FXCollections.observableArrayList();

        CarsInObList.addAll(AllCars);
        CarTableView.setItems(CarsInObList);
        header.setText(DataAccessLayer.getInventoryData().getDealer());
        addressLabel.setText(DataAccessLayer.getInventoryData().getAddress());
        Integer numberOfCars = DataAccessLayer.getInventoryData().getInventory().stream().toList().size();
        Double totalPrice = DataAccessLayer.getInventoryData().getInventory().stream()
                .mapToDouble(Car::getPrice) // Assuming getPrice returns a double
                .sum();
        OptionalDouble averageEngineSize = DataAccessLayer.getInventoryData().getInventory().stream()
                .mapToDouble(Car::getEngineSize)
                .average();
        updateNumberOfCars(numberOfCars,totalPrice,averageEngineSize);

        filterTextBox.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
            filterCar(newValue);
        });
        Set<String> DropdownPopulator = new HashSet<>();
        for(Car toCheck : DataAccessLayer.getInventoryData().getInventory().stream().sorted(Comparator.comparing(Car::getMODEL)).toList())
        {
            DropdownPopulator.add(toCheck.getMODEL());
        }
        filterDropdown.getItems().addAll(DropdownPopulator.stream().toList());
    }
    private void updateNumberOfCars(Integer numberOfCars, Double TotalValue,OptionalDouble averageEngineSize)
    {
        numberOfCarsLabel.setText("Number of cars: "+ numberOfCars.toString());
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        totalValueLabel.setText("Value of Cars in table: " + currencyFormat.format(TotalValue));
        Double averageEng = averageEngineSize.getAsDouble();
        averageEngineSizeLabel.setText("Average Engine Size: "+averageEng.toString() );
    }
    private void filterCar(String newVale)
    {
        CarTableView.getItems().clear();
        CarTableView.getItems().addAll(
                DataAccessLayer.getInventoryData().getInventory().stream()
                        .filter(Car -> Car.contains(newVale)).toList());
    }
}