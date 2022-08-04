package model;

public class RecipeItem {
    private int id;
    private String name;
    private String[] ingredient;
    private String detailsOfIngredient;
    private String method;
    private String serving;
    private String cookTime;


    public RecipeItem(int id, String name, String[] ingredient, String detailsOfIngredient, String method, String serving, String cookTime) {
        this.id = id;
        this.name = name;
        this.ingredient = ingredient;
        this.detailsOfIngredient = detailsOfIngredient;
        this.method = method;
        this.serving = serving;
        this.cookTime = cookTime;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getIngredient() {
        return ingredient;
    }

    public String getDetailsOfIngredient() {
        return detailsOfIngredient;
    }
    public String getMethod() {
        return method;
    }

    public String getServing() {
        return serving;
    }

    public String getCookTime() {
        return cookTime;
    }
}
