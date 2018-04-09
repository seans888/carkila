package com.prototype.carkila.Vehicle;

/**
 * Created by Martha on 03/04/2018.
 */

public class Vehicle {
    private int id;
    private String make;
    private String model;
    private String year;
    private String seatingCapacity;
    private String rentalRate;
    private String image;

    public Vehicle(int id, String make, String model, String year, String seatingCapacity, String rentalRate, String image) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.seatingCapacity = seatingCapacity;
        this.rentalRate = rentalRate;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear(){
        return year;
    }

    public String getSeatingCapacity() {
        return seatingCapacity;
    }

    public String getRentalRate(){
        return rentalRate;
    }

    public String getImage() {
        return image;

    }
}
