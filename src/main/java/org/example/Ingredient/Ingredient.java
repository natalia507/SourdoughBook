package org.example.Ingredient;

public interface Ingredient {
    String getName();

    double getCups();

    double getGrams();

    double convertGramsToCups(double grams);

    double convertCupsToGrams(double cups);
}
