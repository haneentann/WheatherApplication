package com.example.hp1.wheatherapplication.Common;

/**
 * Created by Hp1 on 05/10/2018.
 */

public class Common {
    public static String API_KEY="";
    public static String API_LINK="http://api.openweathermap.org/data/2.5/weather";

    public static String apiRequest(String lat, String lon){

        StringBuilder stringBuilder = new StringBuilder(API_LINK);
        stringBuilder.append(String.format("?lat-%s&lon-%s&APPID-%s&units-metric",lat, lon,API_KEY));
        return stringBuilder.toString();
    }
}
