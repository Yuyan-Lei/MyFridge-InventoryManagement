package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
            BufferedReader br = new BufferedReader(new FileReader("database/DatabaseOfRecipes.txt"));
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
                if (s.equals("******")) {
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
                else if (!s.equals("***Recipe***")) {
                    recipeDetail.append(s).append("\n");
                }
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
}

