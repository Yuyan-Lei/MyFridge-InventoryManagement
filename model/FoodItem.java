package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    public int getId() {
        return id;
    }

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

    public String getExpirationToString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(getExpiration());
    }

    public String getLocationToString() {
        if (location == PlaceLocation.REFRIGERATED) {
            return "Fridge";
        }
        else {
            return "Freezer";
        }
    }

    public String getTypeToString() {
        String typeName = type.toString().toLowerCase();
        String s1 = typeName.substring(0, 1).toUpperCase();
        return s1 + typeName.substring(1);
    }

    public int getDays() {
        Date todayDate = new Date();
        int daysBetween = (int) TimeUnit.DAYS.convert(expiration.getTime() - todayDate.getTime(), TimeUnit.MILLISECONDS);
        return Math.abs(daysBetween);
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
        return getName() + ", "  + getQuantity() + ", "+ getExpirationToString() + ", " + getType() + ", " + getLocation() + "\n";
    }
}
