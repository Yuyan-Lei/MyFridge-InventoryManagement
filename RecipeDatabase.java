import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeDatabase {
    private ArrayList<RecipeItem> recipeList;

    public RecipeDatabase() {
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
            String[] ingredient;
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
                    String[] arrOfStr = recipeDetail.toString().split("///", 0);

                    name = arrOfStr[0];

                    String ingredientString = arrOfStr[1];
                    ingredient = ingredientString.split(", ", 0);

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

    // Add
    private void addItem(String name, String[] ingredient, String detailsOfIngredient, String method, String serving, String cookTime, String calories) throws IOException {
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



    // Only for testing
    public String getRecipe() {
        StringBuilder list = new StringBuilder();
        for (RecipeItem item : recipeList) {
            String temp = "***Recipe***\n" +
                        "Name:" + item.getName() + "\n\n" +
                        "Ingredients:" + item.ingredientsToString() + "\n\n"+
                        "Ingredient Details: " + item.detailsOfIngredientToString() + "\n\n" +
                        "Method: " + item.getMethod() + "\n\n" +
                        "Sering: " + item.getServing() + "\n\n" +
                        "Cook Time: " + item.getCookTime() + "\n\n" +
                        "Calories: " + item.getCalories() + "\n\n" +
                        "******" + "\n\n";
            list.append(temp);
        }
        return list.toString();
    }

    public static void main(String[] args) throws IOException {
        // 1. View all the list
        RecipeDatabase recipeDatabase = new RecipeDatabase();
        System.out.println(recipeDatabase.getRecipe());

        // 2. Add a new recipe
//        String name;
//        String[] ingredient;
//        String detailsOfIngredient;
//        String method;
//        String serving;
//        String cookTime;
//        String calories;
//
//        String exit = "no";
//
//        while (!exit.equals("exit")) {
//            System.out.println("---------------------------");
//            System.out.println("Enter your new recipe here: ");
//            Scanner input = new Scanner(System.in);
//            System.out.println("Name: \n");
//            name = input.nextLine();
//            System.out.println("Ingredient: \n");
//            String ingredientString = input.nextLine();
//            ingredient = ingredientString.split(", ", 0);
//            System.out.println("Details Of Ingredient: \n");
//            detailsOfIngredient = input.nextLine();
//            System.out.println("Method: \n");
//            method = input.nextLine();
//            System.out.println("Serving: \n");
//            serving = input.nextLine();
//            System.out.println("Cook time: \n");
//            cookTime = input.nextLine();
//            System.out.println("Calories: \n");
//            calories = input.nextLine();
//
//            RecipeDatabase newRecipeDatabase = new RecipeDatabase();
//            newRecipeDatabase.addItem(name, ingredient, detailsOfIngredient, method, serving, cookTime, calories);
//            System.out.println("Saved!\n Exit?");
//            exit = input.nextLine();

    }
}

