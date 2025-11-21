package org.example;

import org.example.Repository.IRepository;
import org.example.Repository.JSONRepository;
import org.example.Service.RecipeBookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        Scanner scanner = new Scanner(System.in);

        IO.println("Please enter a filename: ");
        String filename = validateFilename(scanner.nextLine());

        IRepository repo = new JSONRepository(filename);
        RecipeBookService service = new RecipeBookService(repo);
        service.loadBook();
        IO.println("Enter your recipe book name: ");
        String bookName = scanner.nextLine();
        RecipeBook book = new RecipeBook(bookName);


        boolean running = true;
        while(running){
            IO.println("\n -------" + book.getTitle()+ "------");
            IO.println("1: View all pages");
            IO.println("2: View a recipe by page");
            IO.println("3: Add a new recipe");
            IO.println("4: Save recipe book");
            IO.println("0: Exit");
            IO.println("Please enter an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    service.getPages().forEach(IO::println);
                    break;
                case "2":
                    int pageNum;
                    while (true) {
                        IO.println("Please enter a page number you would like to view: ");
                        String input = scanner.nextLine();
                        try {
                            pageNum = Integer.parseInt(input);
                            break;
                        } catch (NumberFormatException e) {
                            IO.println("Please enter a valid number.");
                        }
                    }
                    try {
                        Recipe recipe = service.getRecipe(pageNum);
                        if (recipe == null) {
                            IO.println("No recipe found aat that page.");
                        } else {
                            IO.println(recipe);
                        }

                    } catch (IllegalArgumentException e) {
                        IO.println(e.getMessage());
                    }
                    break;
                case "3":
                    IO.println("Please enter a recipe title: ");
                    String title = scanner.nextLine();

                    List<Ingredient> ingredients = new ArrayList<>();
                    IO.println("Add your ingredients or type DONE to finish: ");
                    while (true) {
                        IO.println("Enter the ingredient name: ");
                        String ingredientName = scanner.nextLine();

                        if (ingredientName.equalsIgnoreCase("DONE")) {
                            break;
                        }
                        if (ingredientName.isBlank()) {
                            System.out.println("Ingredient name cannot be empty.");
                            continue;
                        }
                        String measurementType;
                        while (true) {
                            IO.println("Please enter the measurement type (cups or grams)");
                            measurementType = scanner.nextLine().toLowerCase();
                            if (measurementType.equalsIgnoreCase("grams") || measurementType.equalsIgnoreCase("cups")) {
                                break;
                            } else {
                                IO.println("Invalid measurement type.");
                            }
                        }
                        double amount;
                        while (true) {
                            IO.println("Please enter the amount: ");
                            String amt = scanner.nextLine();
                            try {
                                amount = Double.parseDouble(amt);
                                break;
                            } catch (NumberFormatException e) {
                                IO.println("Please enter a valid number.");
                            }
                        }
                        ingredients.add(new Ingredient(ingredientName, measurementType, amount));
                    }
                    IO.println("Please enter the instructions");
                    StringBuilder instructions = new StringBuilder();

                    System.out.println("Enter multiple lines of text (type 'DONE' on a new line to finish):");

                    String line;
                    while (scanner.hasNextLine()) { // Check if there's another line
                        line = scanner.nextLine();
                        if (line.equalsIgnoreCase("DONE")) { // User signals end of input
                            break;
                        }
                        instructions.append(line).append("\n"); // Append line and a newline character
                    }


                    Recipe recipe = new Recipe(title, ingredients, instructions.toString());


                    service.addRecipe(recipe);

                    IO.println("Recipe added successfully!");
                    break;

                case "4":
                    service.saveBook();
                    IO.println("Recipe book saved");
                    break;

                case "0":
                    running = false;
                    IO.println("See you next time!");
                    break;

                default:
                    IO.println("Invalid option, please try again");

            }


        }

    }
    public static String validateFilename(String filename) {
        // 1. Cannot be empty
        if (filename == null || filename.isBlank()) {
            System.out.println("Filename cannot be empty. Using default 'recipes.json'.");
            return "recipes.json";
        }

        // 2. Remove leading/trailing spaces
        filename = filename.trim();

        // 3. Ensure it ends with .json
        if (!filename.toLowerCase().endsWith(".json")) {
            System.out.println("Filename must end with .json. Adding extension automatically.");
            filename = filename + ".json";
        }

        // 4. Disallow illegal characters (Windows & Mac safe list)
        String illegal = "[\\\\/:*?\"<>|]";
        if (filename.matches(".*" + illegal + ".*")) {
            System.out.println("Filename contains invalid characters. Using default 'recipes.json'.");
            return "recipes.json";
        }

        return filename;
    }

}
