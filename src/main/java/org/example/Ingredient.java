package org.example;

import java.util.Map;

public class Ingredient {

    private static final Map<String, Double> DEFAULT_RATIOS = Map.of(
            "flour", 120.0,
            "water", 237.0,
            "salt", 288.0,
            "starter", 240.0,
            "sugar", 200.0
    );

    //Fields
    private final String name;

    private double cups;

    private double grams;

    //Constructor
    public Ingredient(String name, String measurement, double amount){
        this.name = name;
        double gramsToCupRatio = DEFAULT_RATIOS.get(name.toLowerCase());

        if (measurement.equalsIgnoreCase("cups")){
            this.cups = amount;
            this.grams =  amount* gramsToCupRatio;
        } else if (measurement.equalsIgnoreCase("grams")){
            this.grams = amount;
            this.cups = amount/ gramsToCupRatio;
        }
    }

    //Methods

    public String toString(){
        String HedgeCase = this.name.substring(0,1).toUpperCase() + this.name.substring(1).toLowerCase();
        return HedgeCase + ": " + this.cups +"c, " + (int) this.grams + "g";
    }

    public double getCups(){
        return this.cups;
    }

    public double getGrams(){
        return this.grams;
    }
}
