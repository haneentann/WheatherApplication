package com.example.hp1.wheatherapplication.Common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public static String  unixTimeStampToDateTime(double unixTimeStamp){
        DateFormat dateFormat= new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setTime((long)unixTimeStamp*1000);
        return dateFormat.format(date);
    }
    public static String getImage(String icon){
        return String.format("http://openweathermap.org/img/w/ts.png",icon);
    }
    public String getDataNow(){
        DateFormat dateFormat = new SimpleDateFormat("dd HHHH yyyy HH:mm");
        Date date = new Date();
        return  dateFormat.format(date);
    }

}
