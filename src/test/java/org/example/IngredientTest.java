package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @Test
    void testToStringCups(){
        Ingredient ingredient = new Ingredient("flour", "cups", 2.5);

        assertEquals("Flour: 2.5c, 300g", ingredient.toString());
    }

    @Test
    void getCupsProvided() {
        Ingredient ingredient = new Ingredient("flour", "cups", 2.5);
        assertEquals(2.5, ingredient.getCups());
    }

    @Test
    void getCupsNotProvided() {
        Ingredient ingredient = new Ingredient("flour", "grams", 270);
        assertEquals(2.25, ingredient.getCups());
    }

    @Test
    void getGramsProvided() {
        Ingredient ingredient = new Ingredient("flour", "grams", 270);
        assertEquals(270, ingredient.getGrams());
    }

    @Test
    void getGramsNotProvided(){
        Ingredient ingredient = new Ingredient("flour", "cups", 2.5);
        assertEquals(300, ingredient.getGrams());
    }

}
