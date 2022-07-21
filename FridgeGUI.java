import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * This GUI is the basic menu
 */
public class FridgeGUI extends JFrame  {
    //String name, int quantity, Date expiration, FoodItem.FoodType type, PlaceLocation location
//    private String name;
//    private int quantity;
//    private Date expiration;
//    public enum FoodType {VEGETABLE, MEAT, FRUIT, OTHER} //milk and juice?
//    private FoodItem.FoodType type;
//    public enum PlaceLocation {FROZEN,REFRIGERATED}
//    private FoodItem.PlaceLocation location;
    public static final int WIDTH = 200;
    public static final int HEIGHT = 350;


    public FridgeGUI(){
        super("My Fridge"); //title name
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,1));

        JButton addButton = new JButton("Add item");
        addButton.setBackground(Color.PINK);
        buttonPanel.add(addButton);

        JButton viewButton = new JButton("View stock");
        viewButton.setBackground(Color.CYAN);
        buttonPanel.add(viewButton);

        JButton recipeButton = new JButton("Recommend recipe");
        recipeButton.setBackground(Color.ORANGE);
        buttonPanel.add(recipeButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        FridgeGUI gui = new FridgeGUI();
        gui.setVisible(true);
    }
}
