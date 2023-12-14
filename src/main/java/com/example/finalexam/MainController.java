package com.example.finalexam;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {



    @FXML
    private Label addressLabel;

    @FXML
    private Label averageEngineSizeLabel;

    @FXML
    private Label totalValueLabel;

    @FXML
    private Label numberOfCarsLabel;

    @FXML
    private TableColumn<?, ?> TransmissionSizeColumn;
    @FXML
    private TableColumn<?, ?> engineSizeColumn;

    @FXML
    private ComboBox<?> filterDropdown;

    @FXML
    private TextField filterTextBox;

    @FXML
    private TableColumn<?, ?> fueltypeColumn;

    @FXML
    private Label header;

    @FXML
    private TableColumn<?, ?> modelColumn;

    @FXML
    private TableColumn<?, ?> mpgColumn;



    @FXML
    private TableColumn<?, ?> priceColumn;



    @FXML
    private TableColumn<?, ?> yearColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataAccessLayer.loadJsonToMemory("src/main/audi.json");
        try {
            DataAccessLayer.getReadThread().join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}