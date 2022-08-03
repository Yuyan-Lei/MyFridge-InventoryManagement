import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Stock {
    private Date todayDate;
    private ArrayList<FoodItem> stockList;
    public enum StockType {ALL,FROZEN,REFRIGERATED, VEGETABLE, MEAT, FRUIT, DRINK, OTHER}

    public Stock() {
        todayDate = new Date();
        loadStockList();
    }

    // Private methods for inner operations over the stock list & database
    private void loadStockList() {
        // Store the items in an arraylist.
        stockList = new ArrayList<FoodItem>();

        // Read from the txt file
        try{
            BufferedReader br = new BufferedReader(new FileReader("DatabaseOfStock.txt"));
            String s;
            while((s = br.readLine()) != null) {
                String name;
                int quantity;
                Date expiration;
                FoodItem.FoodType type;
                FoodItem.PlaceLocation location;

                // 1. Extract information from text
                String[] arrOfStr = s.split(", ", 0);
                name = arrOfStr[0];
                quantity = Integer.parseInt(arrOfStr[1]);

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                expiration = formatter.parse(arrOfStr[2]);

                type = FoodItem.FoodType.valueOf(arrOfStr[3]);
                location = FoodItem.PlaceLocation.valueOf(arrOfStr[4]);

                // 2. Create objects with the info
                int newId = stockList.size();
                FoodItem newItem = new FoodItem(newId, name, quantity, expiration, type, location);
                stockList.add(newItem);
            }
            br.close();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveStockList() throws IOException {
        FileWriter fw;
        try {
            fw = new FileWriter("DatabaseOfStock.txt", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (FoodItem itemInList : stockList) {
            String text = itemInList.toString();
            fw.write(text);
        }
        fw.close();
    }


    // Add
    public void addItem(String name, int quantity, Date expiration, FoodItem.FoodType type, FoodItem.PlaceLocation location) throws IOException {
        // 1. Create a new object
        int newId = stockList.size();
        FoodItem newItem = new FoodItem(newId, name, quantity, expiration, type, location);
        stockList.add(newItem);

        // 2. Add to the txt
        String text = newItem.toString();
        FileWriter fw;
        try {
            fw = new FileWriter("DatabaseOfStock.txt", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fw.write(text);
        fw.close();
    }


    // Edit
    public void updateItem(FoodItem item, String name, int quantity, Date expireDate, FoodItem.FoodType type, FoodItem.PlaceLocation location) throws IOException {
        // Edit the stockList
        for (FoodItem itemInList : stockList) {
            if (itemInList.getId() == item.getId()) {
                itemInList.setName(name);
                itemInList.setQuantity(quantity);
                itemInList.setExpiration(expireDate);
                itemInList.setType(type);
                itemInList.setLocation(location);
            }
        }

        // Save the txt file
        saveStockList();
    }


    // Remove
    public void removeItem(FoodItem item) throws IOException {
        // Edit the stockList
        stockList.removeIf(itemInList -> itemInList.getId() == item.getId());

        // Save the txt file
        saveStockList();
    }

    // View 1 : Show specific types of items in a list
    public ArrayList<FoodItem> getItems(StockType stockType) {
        ArrayList<FoodItem> showingList;
        if (stockType == StockType.ALL) {
            showingList = stockList;
        }
        else if (stockType == StockType.FROZEN || stockType == StockType.REFRIGERATED) {
            showingList = getItemsByLocation(FoodItem.PlaceLocation.valueOf(stockType.toString()));
        }
        else {
            showingList = getItemsByType(FoodItem.FoodType.valueOf(stockType.toString()));
        }
        return showingList;
    }

    // View 2: Show items that have already been expired in a list
    public ArrayList<FoodItem> getExpiredItems() {
        ArrayList<FoodItem> expiredItems = new ArrayList<FoodItem>();
        for (FoodItem item : stockList)  {
            if (item.getExpiration().before(todayDate)) {
                expiredItems.add(item);
            }
        }
        return expiredItems;
    }

    // View 3: Show items that are going to be expired in 3 days
    public ArrayList<FoodItem> getAlmostExpiredItems() {
        ArrayList<FoodItem> almostExpiredItems = new ArrayList<FoodItem>();
        for (FoodItem item : stockList) {
            // get the number of days between today and the expiration date
            long daysBetween = TimeUnit.DAYS.convert(item.getExpiration().getTime() - todayDate.getTime(), TimeUnit.MILLISECONDS);
            if (item.getExpiration().after(todayDate) && daysBetween <= 3) {
                almostExpiredItems.add(item);
            }
        }
        return almostExpiredItems;
    }

    // View 4: Show items that are of low stock
    public ArrayList<FoodItem> getLowStockItems(){
        ArrayList<FoodItem> lowStockItems = new ArrayList<FoodItem>();
        for (FoodItem item : stockList) {
            if (item.getQuantity() <= 3) {
                lowStockItems.add(item);
            }
        }
        return lowStockItems;
    }

    // Filter: By Type / By Location
    private ArrayList<FoodItem> getItemsByType(FoodItem.FoodType type) {
        ArrayList<FoodItem> itemsByType = new ArrayList<FoodItem>();
        for (FoodItem item : stockList) {
            if (item.getType().equals(type)) {
                itemsByType.add(item);
            }
        }
        return itemsByType;
    }

    private ArrayList<FoodItem> getItemsByLocation(FoodItem.PlaceLocation location) {
        ArrayList<FoodItem> itemsByLocation = new ArrayList<FoodItem>();
        for (FoodItem item : stockList) {
            if (item.getLocation().equals(location)) {
                itemsByLocation.add(item);
            }
        }
        return itemsByLocation;
    }


    // For recipe usage
    public ArrayList<String> getIngredientList() {
        ArrayList<String> list = new ArrayList<String>();
        for (FoodItem item : stockList) {
            String ingredient = item.getName().toLowerCase();
            list.add(ingredient);
        }
        return list;
    }
}
