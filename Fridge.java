import java.text.SimpleDateFormat;
import java.util.Date;

public class Fridge {
    public static void main(String[] args) {

        // get the current date in the object 'todayDate'
//        Date todayDate = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
//        System.out.println(formatter.format(todayDate));

        Date expireDate = new Date();
        Stock fridgeStock = new Stock("Big Cake", 1, expireDate, FoodItem.FoodType.OTHER, FoodItem.PlaceLocation.REFRIGERATED);
//        fridgeStock.updateItem();
        fridgeStock.printItemInfo();
    }
}
