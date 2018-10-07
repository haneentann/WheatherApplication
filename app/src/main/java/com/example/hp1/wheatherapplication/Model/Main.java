package com.example.hp1.wheatherapplication.Model;

/**
 * Created by Haneen on 10/7/2018.
 */

public class Main {
    private double tmep;
    private double pressure;
    private int humidity;
    private double temp_min;
    private double temp_max;

    public Main(double tmep, double pressure, int humidity, double temp_min, double temp_max) {
        this.tmep = tmep;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public double getTmep() {
        return tmep;
    }

    public void setTmep(double tmep) {
        this.tmep = tmep;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }
}
