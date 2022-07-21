import java.util.Date;

public class FoodItem {
    private String name;
    private int quantity;
    private Date expiration;
    public enum FoodType {VEGETABLE, MEAT, FRUIT, OTHER} //milk and juice?
    private FoodType type;
    public enum PlaceLocation {FROZEN,REFRIGERATED}
    private PlaceLocation location;

    public FoodItem(String name){ //for checking the specific item?
        setName(name);
    }

    public FoodItem(String name, int quantity, Date expiration, FoodType type, PlaceLocation location) {
        setName(name);
        setQuantity(quantity);
        setExpiration(expiration);
        setType(type);
        setLocation(location);
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) throws IllegalArgumentException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
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

}
