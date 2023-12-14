package com.example.finalexam.Model;

import java.util.List;

public class Dealership {
    private String Dealer;
    private String Address;

    private List<Car> Audis;
    public String getDealer() {
        return Dealer;
    }

    public void setDealer(String dealer) {
        Dealer = dealer;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public List<Car> getInventory() {
        return Audis;
    }

    public void setInventory(List<Car> inventory) {
        Audis = inventory;
    }
}
