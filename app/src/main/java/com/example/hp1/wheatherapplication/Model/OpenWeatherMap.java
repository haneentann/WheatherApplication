package com.example.hp1.wheatherapplication.Model;

import java.util.List;

/**
 * Created by Haneen on 10/7/2018.
 */

public class OpenWeatherMap {
    private Coord coord;
    private List<Weather> weatherList;
    private String base;
    private Main main;
    private Wind wind;
    private Sys sys;
    private Clouds clouds;
    private int dt;
    private int id;
    private String name;
    private int cod;

    public OpenWeatherMap() {
    }

    public OpenWeatherMap(Coord coord, List<Weather> weatherList, String base, Main main, Wind wind, Sys sys, Clouds clouds, int dt, int id, String name, int cod) {
        this.coord = coord;
        this.weatherList = weatherList;
        this.base = base;
        this.main = main;
        this.wind = wind;
        this.sys = sys;
        this.clouds = clouds;
        this.dt = dt;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }
}
