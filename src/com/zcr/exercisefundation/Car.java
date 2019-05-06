package com.zcr.exercisefundation;

/**
 * @author zcr
 * @date 2019/5/6-11:25
 */
public class Car{
    private String make;
    private String model;
    private int year;
    private double odometer_reading;

    public Car() {
    }

    public Car(String make, String model, int year, double odometer_reading) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.odometer_reading = odometer_reading;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getOdometer_reading() {
        return odometer_reading;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setOdometer_reading(double odometer_reading) {
        this.odometer_reading = odometer_reading;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", odometer_reading=" + odometer_reading +
                '}';
    }

    public void propertity(){
        System.out.println("我有四个轮胎");
    }
}
