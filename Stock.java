import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

/**
 * This class is for storing and adding the info.
 */
public class Stock extends FoodItem{

    public Stock(){
        super();
    }
    public Stock(String name){
        super(name);
    }

    public Stock(String name, int quantity, Date expiration, FoodType type, PlaceLocation location){
        super(name, quantity, expiration, type, location);
    }

    //below is to place the item in the txt
    public void updateItem(){      //need to check if it can work
        try{
            String text = getName() + ", " + getExpiration() + ", " + getLocation() + ", " + getQuantity() + ", " + getType() + "\n";
            FileWriter fw = new FileWriter("../5004-Final-Project/FridgeItem.txt", true);
            fw.write(text);
            fw.close();
        }
        catch (IOException e) {
            return;
        }
    }

    //read the item
    public void printItemInfo(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("../5004-Final-Project/FridgeItem.txt"));
            String s;
            while((s = br.readLine()) != null){
                System.out.println(s);
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
