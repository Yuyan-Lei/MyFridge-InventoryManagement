import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This GUI is the basic menu
 */
public class FridgeGUIwithAction extends JFrame implements ActionListener  {
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
    private ActionEvent e;


    public FridgeGUIwithAction(){
        super("My Fridge"); //title name
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,1));

        JButton addButton = new JButton("Add item");
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        JButton viewButton = new JButton("View stock");
        viewButton.addActionListener(this);
        buttonPanel.add(viewButton);

        JButton recipeButton = new JButton("Recommend recipe");
        recipeButton.addActionListener(this);
        buttonPanel.add(recipeButton);

        add(buttonPanel, BorderLayout.CENTER);

    }

    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Add item")) {
            //System.out.println("add item");
            setVisible(false);
            AddWindow aNewWindow = new AddWindow();
        }
        else if (actionCommand.equals("View stock")) {
            setVisible(false);
            StockWindow aNewWindow = new StockWindow();
        }
            //System.out.println("view item");
        else if (actionCommand.equals("Recommend recipe")) {
            setVisible(false);
            RecipeWindow aNewWindow = new RecipeWindow();
        }
            //System.out.println("recipes");
        else
            System.out.println("Unexpected error.");
    }


    public static void main(String[] args) {
        FridgeGUIwithAction gui = new FridgeGUIwithAction();
        gui.setVisible(true);
    }
}