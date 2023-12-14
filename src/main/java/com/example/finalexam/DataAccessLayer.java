package com.example.finalexam;

import com.example.finalexam.Model.Car;
import com.example.finalexam.Model.Dealership;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class DataAccessLayer {
    private static Thread readThread;
    private static Dealership inventoryData;
    public static void main(String[] args) throws InterruptedException {
        loadJsonToMemory("src/main/audi.json");
        readThread.join();
    }
    public static void loadJsonToMemory(String fileToLoad)
    {
        readThread = new Thread(() -> {
            Gson gson = new Gson();

            try (FileReader reader = new FileReader(fileToLoad)) {
                // Assuming the JSON data is in a file named "carData.json"
                inventoryData = gson.fromJson(reader, Dealership.class);

                // Access the loaded data
                System.out.println("Dealership: " + inventoryData.getDealer());
                System.out.println("Address: " + inventoryData.getAddress());
                System.out.println("Inventory:");

                for (Car car : inventoryData.getInventory()) {

                    System.out.println("Year: " + car.getYear());
                    System.out.println("Model: " + car.getMODEL());
                    System.out.println("Price: " + car.getPrice());
                    System.out.println("----------------------------");
                }
            } catch ( IOException e) {
                e.printStackTrace();
            }
        });
        readThread.start();
    }
}
