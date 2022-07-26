import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Fridge {
    private Date todayDate;
    private ArrayList<FoodItem> stockList;

    public Fridge() {
        todayDate = new Date();
        setStockList();
    }

    public void setStockList() {
        // Store the items in an arraylist.
        stockList = new ArrayList<FoodItem>();

        // Read from the txt file
        try{
            BufferedReader br = new BufferedReader(new FileReader("./FridgeItem.txt"));
            String s;
            while((s = br.readLine()) != null){
                String name;
                int quantity;
                Date expiration;
                FoodItem.FoodType type;
                FoodItem.PlaceLocation location;

                // 1. Extract information from text
                Scanner input = new Scanner(s);
                name = input.next();
                quantity = input.nextInt();

                int year = input.nextInt();
                int month = input.nextInt();
                int day = input.nextInt();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String date = year + "-" + month + "-" + day;
                expiration = formatter.parse(date);

                String typeString = input.next();
                type = FoodItem.FoodType.valueOf(typeString);
                String locationString = input.next();
                location = FoodItem.PlaceLocation.valueOf(locationString);

                // 2. Create objects with the info
                FoodItem newItem = new FoodItem(name, quantity, expiration, type, location);
                stockList.add(newItem);
            }
            br.close();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public void addItem(String name, int quantity, Date expiration, FoodItem.FoodType type, FoodItem.PlaceLocation location) throws IOException {
        // 1. Create a new object
        FoodItem newItem = new FoodItem(name, quantity, expiration, type, location);
        stockList.add(newItem);

        // 2. Add to the txt
        String text = newItem.toString();
        FileWriter fw;
        try {
            fw = new FileWriter("./FridgeItem.txt", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fw.write(text);
        fw.close();
    }

    // View 1: Show all the items in a list
    public ArrayList<FoodItem> getStockList() {
        return stockList;
    }

    // View 2: Show items that have already been expired in a list
    public ArrayList<FoodItem>  showExpiredItems() {
        ArrayList<FoodItem> expiredItems = new ArrayList<FoodItem>();
        for (FoodItem item : stockList) {
            if (item.getExpiration().before(todayDate)) {
                expiredItems.add(item);
            }
        }
        return expiredItems;
    }

    // View 3: Show items that are going to be expired in 3 days
    public ArrayList<FoodItem>  showAlmostExpiredItems() throws ParseException {
        ArrayList<FoodItem> almostExpiredItems = new ArrayList<FoodItem>();
        for (FoodItem item : stockList) {
            // get the number of days between today and the expiration date
            long daysBetween = (item.getExpiration().getTime() - todayDate.getTime()) / (24 * 60 * 60 * 1000);
            if (daysBetween <= 3 && daysBetween >= 0) {
                almostExpiredItems.add(item);
            }
        }
        return almostExpiredItems;
    }

    // methods below are only used for testing
    private void viewItems() {
        for (FoodItem item : stockList) {
            System.out.println(item);
        }
    }

    private void viewExpiredItems() {
        for (FoodItem item : this.showExpiredItems()) {
            System.out.println(item);
        }
    }

    private void viewAlmostExpiredItems() throws ParseException {
        for (FoodItem item : this.showAlmostExpiredItems()) {
            System.out.println(item);
        }
    }

    // only for testing
    public static void main(String[] args) throws IOException, ParseException {

        Fridge newFridge = new Fridge();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = 2022 + "-" + 7 + "-" + 28;
        Date expiration = formatter.parse(date);

        System.out.println("---- View all items -----");
        newFridge.viewItems();

        System.out.println("---- After adding a new item -----");
        newFridge.addItem("Biscuit", 1, expiration, FoodItem.FoodType.OTHER, FoodItem.PlaceLocation.REFRIGERATED);

        newFridge.viewItems();

        System.out.println("---- View expired items -----");
        newFridge.viewExpiredItems();

        System.out.println("---- View items expires in 3 days-----");
        newFridge.viewAlmostExpiredItems();
    }
}
