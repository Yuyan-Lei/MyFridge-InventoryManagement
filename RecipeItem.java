public class RecipeItem {
    private String name;
    private String ingredient;
    private String detailsOfIngredient;
    private String method;
    private String serving;
    private String cookTime;
    private String calories;


    public RecipeItem(String name, String ingredient, String detailsOfIngredient, String method, String serving, String cookTime, String calories) {
        this.name = name;
        this.ingredient = ingredient;
        this.detailsOfIngredient = detailsOfIngredient;
        this.method = method;
        this.serving = serving;
        this.cookTime = cookTime;
        this.calories = calories;
    }


    @Override
    public String toString() {
        return "***Recipe***\n" +
                name + "/// \n" +
                ingredient + "/// \n"+
                detailsOfIngredient + "/// \n" +
                method + "/// \n" +
                serving + "/// \n" +
                cookTime + "/// \n" +
                calories + "\n" +
                "******" + "\n";
    }
}
