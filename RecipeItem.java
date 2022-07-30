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
                "******" + "\n";
    }
}
