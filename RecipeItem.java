import java.util.Arrays;

public class RecipeItem {
    private String name;
    private String[] ingredient;
    private String detailsOfIngredient;
    private String method;
    private String serving;
    private String cookTime;
    private String calories;


    public RecipeItem(String name, String[] ingredient, String detailsOfIngredient, String method, String serving, String cookTime, String calories) {
        this.name = name;
        this.ingredient = ingredient;
        this.detailsOfIngredient = detailsOfIngredient;
        this.method = method;
        this.serving = serving;
        this.cookTime = cookTime;
        this.calories = calories;
    }

    // Getters
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

    public String getCalories() {
        return calories;
    }

    // Used for showing
    public String ingredientsToString() {
        StringBuilder sb = new StringBuilder();
        for (String item : ingredient) {
            sb.append(item).append(", ");
        }
        sb.setLength(sb.length() - 2);

        return sb.toString();
    }

    public String detailsOfIngredientToString() {
        StringBuilder sb = new StringBuilder();
        String[] s = detailsOfIngredient.split("; ", 0);
        for (String item : s) {
            sb.append(item).append("\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public String toString() {
        return "***Recipe***\n" +
                name + "///\n" +
                ingredientsToString() + "///\n"+
                detailsOfIngredient + "///\n" +
                method + "///\n" +
                serving + "///\n" +
                cookTime + "///\n" +
                calories + "\n" +
                "******" + "\n";
    }
}
