package org.example;

import java.util.Map;

public class Ingredient {

    private static final Map<String, Double> DEFAULT_RATIOS = Map.ofEntries(
            Map.entry("flour", 120.0),
            Map.entry("water", 237.0),
            Map.entry("salt", 288.0),
            Map.entry("starter", 240.0),
            Map.entry("sugar", 200.0),
            Map.entry("butter", 350.0),
            Map.entry("chocolate", 170.0),
            Map.entry("honey", 340.0),
            Map.entry("yeast", 144.0),
            Map.entry("cheese", 113.0),
            Map.entry("olives", 120.0),
            Map.entry("milk", 240.0),
            Map.entry("egg", 243.0),
            Map.entry("olive oil", 220.0),
            Map.entry("whole wheat flour", 125.0)
    );

    //Fields
    private final String name;

    private double cups;

    private double grams;

    //Constructor
    public Ingredient(String name, String measurement, double amount){
        this.name = name;
        Double gramsToCupRatio = DEFAULT_RATIOS.get(name.toLowerCase());

        if (gramsToCupRatio == null) {
            throw new IllegalArgumentException("Unknown ingredient ratio");
        }

        if (measurement.equalsIgnoreCase("cups")){
            this.cups = amount;
            this.grams = Math.round(amount* gramsToCupRatio * 100.0) / 100.0;

        } else if (measurement.equalsIgnoreCase("grams")){
            this.grams = amount;
            this.cups = Math.round(amount/ gramsToCupRatio * 100.0) / 100.0;
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

    public String getName(){
        return this.name;
    }
}
