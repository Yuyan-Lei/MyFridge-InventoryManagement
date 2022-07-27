import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeDatabase {
    private ArrayList<RecipeItem> recipeList;

    private RecipeDatabase() {
        loadRecipeList();
    }

    // Private methods for inner operations over the stock list & database
    private void loadRecipeList() {
        // Store the items in an arraylist.
        recipeList = new ArrayList<RecipeItem>();

        // Read from the txt file
        try {
            BufferedReader br = new BufferedReader(new FileReader("./RecipeDatabase.txt"));
            String s;
            StringBuilder recipeDetail = new StringBuilder();
            String name;
            String ingredient;
            String detailsOfIngredient;
            String method;
            String serving;
            String cookTime;
            String calories;

            while ((s = br.readLine()) != null) {
                // 1. Extract information from text
                if (s.equals("***Recipe***")) {
                    continue;
                }
                else if (s.equals("******")) {
                    String[] arrOfStr = recipeDetail.toString().split("/// \n", 0);
                    name = arrOfStr[0];
                    ingredient = arrOfStr[1];
                    detailsOfIngredient = arrOfStr[2];
                    method = arrOfStr[3];
                    serving = arrOfStr[4];
                    cookTime = arrOfStr[5];
                    calories = arrOfStr[6];
                    // 2. Create objects with the info
                    RecipeItem newItem = new RecipeItem(name, ingredient, detailsOfIngredient, method, serving, cookTime, calories);
                    recipeList.add(newItem);
                    recipeDetail = new StringBuilder();
                }
                else {
                    recipeDetail.append(s);
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveRecipeList() throws IOException {
        FileWriter fw;
        try {
            fw = new FileWriter("./RecipeDatabase.txt", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (RecipeItem itemInList : recipeList) {
            String text = itemInList.toString();
            fw.write(text);
        }
        fw.close();
    }


    // Add
    private void addItem(String name, String ingredient, String detailsOfIngredient, String method, String serving, String cookTime, String calories) throws IOException {
        // 1. Create a new object
        RecipeItem newItem = new RecipeItem(name, ingredient, detailsOfIngredient, method, serving,cookTime, calories);
        recipeList.add(newItem);

        // 2. Add to the txt
        String text = newItem.toString();
        FileWriter fw;
        try {
            fw = new FileWriter("./RecipeDatabase.txt", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fw.write(text);
        fw.close();
    }

    private void main(String[] args) throws IOException {
        String name;
        String ingredient;
        String detailsOfIngredient;
        String method;
        String serving;
        String cookTime;
        String calories;

        String exit = "no";

        while (!exit.equals("exit")) {
            System.out.println("---------------------------");
            System.out.println("Enter your new recipe here: ");
            Scanner input = new Scanner(System.in);
            System.out.println("Name: \n");
            name = input.nextLine();
            System.out.println("Ingredient: \n");
            ingredient = input.nextLine();
            System.out.println("Details Of Ingredient: \n");
            detailsOfIngredient = input.nextLine();
            System.out.println("Method: \n");
            method = input.nextLine();
            System.out.println("Serving: \n");
            serving = input.nextLine();
            System.out.println("Cook time: \n");
            cookTime = input.nextLine();
            System.out.println("Calories: \n");
            calories = input.nextLine();

            RecipeDatabase newRecipeDatabase = new RecipeDatabase();
            newRecipeDatabase.addItem(name, ingredient, detailsOfIngredient, method, serving, cookTime, calories);
            System.out.println("Saved!\n Exit?");
            exit = input.nextLine();
        }
    }
}

