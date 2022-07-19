import java.text.SimpleDateFormat;
import java.util.Date;

public class Fridge {
    public static void main(String[] args) {

        // get the current date in the object 'todayDate'
        Date todayDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        System.out.println(formatter.format(todayDate));
    }
}
