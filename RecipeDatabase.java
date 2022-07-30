import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

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

            while ((s = br.readLine()) != null) {
                // 1. Extract information from text
                if (s.equals("***Recipe***")) {
                    continue;
                }
                else if (s.equals("******")) {
                    String[] arrOfStr = recipeDetail.toString().split("///\n", 0);

                    name = arrOfStr[0];

                    String ingredientString = arrOfStr[1];
                    ingredient = ingredientString.split(", ", 0);

                    detailsOfIngredient = arrOfStr[2];
                    method = arrOfStr[3];
                    serving = arrOfStr[4];
                    cookTime = arrOfStr[5];

                    // 2. Create objects with the info
                    RecipeItem newItem = new RecipeItem(recipeList.size() + 1, name, ingredient, detailsOfIngredient, method, serving, cookTime);
                    recipeList.add(newItem);
                    recipeDetail = new StringBuilder();
                }
                else {
                    recipeDetail.append(s).append("\n");
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Add
    private void addItem(String name, String[] ingredient, String detailsOfIngredient, String method, String serving, String cookTime) throws IOException {
        // 1. Create a new object
        RecipeItem newItem = new RecipeItem(recipeList.size() + 1, name, ingredient, detailsOfIngredient, method, serving,cookTime);
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

    // Get a list of all recipes in the database
    public ArrayList<RecipeItem> getAllRecipes() {
        return recipeList;
    }

    // Get a list of recipes that contains the input ingredients.
    public ArrayList<RecipeItem> getSpecificRecipeList(ArrayList<String> inputIngredients, int range) {
        ArrayList<RecipeItem> list = new ArrayList<RecipeItem>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        // Check every the input ingredients in every recipe.
        for (String ingredient : inputIngredients) {
            for (RecipeItem item : recipeList) {
                // if this recipe contains the specific ingredient, then map value + 1
                if (Arrays.asList(item.getIngredient()).contains(ingredient)) {
                    map.put(item.getId(), map.getOrDefault(item.getId(), 0) + 1);
                }
            }
        }
        HashMap<Integer, ArrayList<Integer>> hitMap = new HashMap<Integer, ArrayList<Integer>>();
        for (int id : map.keySet()) {
            int hitting = map.get(id);
            if (hitMap.containsKey(hitting)) {
                ArrayList<Integer> value = hitMap.get(hitting);
                value.add(id);
                hitMap.put(hitting, value);
            }
            else {
                ArrayList<Integer> value = new ArrayList<Integer>();
                value.add(id);
                hitMap.put(hitting, value);
            }
        }

        // Return a list of recipe containing the input ingredients (By the rank of hitting)
        Object[] keySet = hitMap.keySet().toArray();
        Arrays.sort(keySet, Collections.reverseOrder());
        for (Object hitting : keySet) {
            int hitCount = (int)hitting;
            for (int id : hitMap.get(hitCount)) {
                list.add(recipeList.get(id - 1));
            }
        }

        // Only return the recipes in the given range
        ArrayList<RecipeItem> result = new ArrayList<RecipeItem>();
        for (int i = 0; i < range; i++) {
            result.add(list.get(i));
        }
        return result;
    }


    // Only for testing
    public String printRecipe() {
        StringBuilder list = new StringBuilder();
        for (RecipeItem item : recipeList) {
            String temp = "***Recipe***\n" +
                        "Name:" + item.getName() + "\n\n" +
                        "Ingredients:" + item.ingredientsToString() + "\n\n"+
                        "Ingredient Details: " + item.detailsOfIngredientToString() + "\n\n" +
                        "Method: " + item.getMethod() + "\n\n" +
                        "Sering: " + item.getServing() + "\n\n" +
                        "Cook Time: " + item.getCookTime() + "\n\n" +
                        "******" + "\n\n";
            list.append(temp);
        }
        return list.toString();
    }

    public static void main(String[] args) throws IOException {
        // 1. View all the list
//        RecipeDatabase recipeDatabase = new RecipeDatabase();
//
//        String[] inputIngredients = {"onion", "lemon", "sugar", "avocado", "potato"};
//
//        System.out.println(recipeDatabase.getSpecificRecipeList(inputIngredients, 10));

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

