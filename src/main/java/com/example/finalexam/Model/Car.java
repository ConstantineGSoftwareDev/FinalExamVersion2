package com.example.finalexam.Model;

public class Car {
    private Integer Year;
    private String MODEL;
    private String fuelType;
    private Double Price;
    private Double mpg;
    private Double EngineSize;
    private String transmission;

    public Integer getYear() {
        return Year;
    }

    public void setYear(Integer year) {
        Year = year;
    }

    public String getMODEL() {
        return MODEL;
    }

    public void setMODEL(String MODEL) {
        this.MODEL = MODEL;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Double getMpg() {
        return mpg;
    }

    public void setMpg(Double mpg) {
        this.mpg = mpg;
    }

    public Double getEngineSize() {
        return EngineSize;
    }

    public void setEngineSize(Double engineSize) {
        EngineSize = engineSize;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
    public boolean contains(String searchTerm) {
        String searchTermLower = searchTerm.toLowerCase();
        Boolean returnVal = false;
        returnVal = Year.toString().toLowerCase().contains(searchTermLower)
                || MODEL.toLowerCase().contains(searchTermLower)
                || fuelType.toLowerCase().contains(searchTermLower)
                || Price.toString().toLowerCase().contains(searchTermLower)
                || transmission.toLowerCase().contains(searchTermLower);
        return  returnVal;
    }
}
