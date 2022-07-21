import java.io.*;
import java.util.Date;

public class Stock extends FoodItem{

    public Stock(String name){
        super(name);
    }

    public Stock(String name, int quantity, Date expiration, FoodType type, PlaceLocation location){
        super(name, quantity, expiration, type, location);
    }

    //below is to place the item in the txt
    public void updateItem(){      //need to check if it can work
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("FridgeItem.txt"));
            bw.write(getName() + getExpiration() + getLocation() + getQuantity() + getType() + "\n");
        }
        catch (IOException e) {
            return;
        }
    }

    //read the item
//    public String readItemInfo(){
//        try{
//            BufferedReader br = new BufferedReader(new FileReader("FridgeItem.txt"));
//            String s;
//            while((s = br.readLine()) != null){
//                System.out.println(s);
//            }
//            br.close();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
