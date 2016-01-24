package com.taro.tusk.Tools;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Tina on 15-08-19.
 */
public class locationGet {

    private static List<String> countriesList= Arrays.asList("Canada");
    private static List<String> listCanada= Arrays.asList("Alberta", "British Columbia", "Manitoba", "New Brunswick", "Newfoundland and Labrador", "Northwest Territories", "Nova Scotia", "Nunavut", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan", "Yukon");
    private static List<String> listOntario= Arrays.asList("Hamilton", "Toronto", "Waterloo");

    private locationGet(){

    }
    public static List<String> getCountry(){
        return countriesList;
    }
    public static List<String> getProvinces(String country){
        return listCanada;
    }
    public static List<String> getCity(String prov){
        return listOntario;
    }
}

