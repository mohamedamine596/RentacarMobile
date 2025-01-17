package com.example.rentacar.database.models;

public class Vehicle {
    private int id;
    private String name;
    private String type;
    private double dailyRate;
    private boolean isAvailable;

    // Constructor
    public Vehicle(int id, String name, String type, double dailyRate, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.dailyRate = dailyRate;
        this.isAvailable = isAvailable;
    }

    public Vehicle(String vehicleName, String vehicleModel, double vehiclePrice) {

    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getDailyRate() { return dailyRate; }
    public void setDailyRate(double dailyRate) { this.dailyRate = dailyRate; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}