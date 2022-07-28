import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodItem {
    private int id;
    private String name;
    private int quantity;
    private Date expiration;
    public enum FoodType {VEGETABLE, MEAT, FRUIT, DRINK, OTHER}
    private FoodType type;
    public enum PlaceLocation {FROZEN,REFRIGERATED}
    private PlaceLocation location;

    public FoodItem(int id, String name, int quantity, Date expiration, FoodType type, PlaceLocation location) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.expiration = expiration;
        this.type = type;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) throws IllegalArgumentException {
        this.quantity = quantity;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public void setType(FoodType type) {
        this.type = type;
    }

    public void setLocation(PlaceLocation location) {
        this.location = location;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getExpiration() {
        return expiration;
    }

    public FoodType getType() {
        return type;
    }

    public PlaceLocation getLocation() {
        return location;
    }

    // Two kinds of URLs: Whole Foods / Amazon Fresh
    public String getWFURL() {
        return "https://www.amazon.com/s?k=" + name + "&i=wholefoods";
    }

    public String getAFURL() {
        return "https://www.amazon.com/s?k=" + name + "&i=amazonfresh";
    }

    @Override
    public String toString() {
        // Format: name, quantity, expiration date, type, location
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return getName() + ", "  + getQuantity() + ", "+ formatter.format(getExpiration()) + ", " + getType() + ", " + getLocation() + "\n";
    }
}
