package org.example.Ingredient;

public class AbstractIngredient implements Ingredient{
    protected String name;
    protected double cups;
    protected double grams;
    protected double gramsPerCup;
    @Override
    public String getName() {return name;}

    @Override
    public double getCups() {return cups;}

    @Override
    public double getGrams() {return grams;}

    @Override
    public double convertGramsToCups(double grams) {return grams/gramsPerCup;}

    @Override
    public double convertCupsToGrams(double cups) {return cups*gramsPerCup;}
}
