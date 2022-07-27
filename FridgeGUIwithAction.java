import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/**
 * This GUI is the basic menu
 */
public class FridgeGUIwithAction extends JFrame implements ActionListener  {
    public static final int WIDTH = 350;
    public static final int HEIGHT = 750;
    private ActionEvent e;


    public FridgeGUIwithAction(){
        super("My Fridge"); //title name
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Color menuColor = new Color(107,174,120);

        //top menu
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3));
        buttonPanel.setBackground(menuColor);

        JButton addButton = new JButton("➕ Add");
        addButton.setBackground(menuColor);
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        JButton viewButton = new JButton("👁 Stock");
        viewButton.setBackground(menuColor);
        viewButton.setOpaque(true);
        viewButton.setBorderPainted(false);
        viewButton.addActionListener(this);
        buttonPanel.add(viewButton);

        JButton recipeButton = new JButton("📖 Recipe");
        recipeButton.setBackground(menuColor);
        recipeButton.setOpaque(true);
        recipeButton.setBorderPainted(false);
        recipeButton.addActionListener(this);
        buttonPanel.add(recipeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        //center stock info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3,1));

        JPanel lowStock = new JPanel();
        lowStock.setLayout(new BorderLayout());
        JLabel titleLow = new JLabel(" Low Stock");
        titleLow.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        titleLow.setForeground(new Color(15,118,36));
        lowStock.add(titleLow, BorderLayout.NORTH);
        //details put in center/bottom -- not finished yet
        lowStock.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        infoPanel.add(lowStock);

        JPanel expired = new JPanel();
        expired.setLayout(new BorderLayout());
        JLabel titleExpired = new JLabel(" Expired");
        titleExpired.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        titleExpired.setForeground(new Color(15,118,36));
        expired.add(titleExpired, BorderLayout.NORTH);
        //details put in center/bottom -- not finished yet
        expired.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        infoPanel.add(expired);

        JPanel expiring = new JPanel();
        expiring.setLayout(new BorderLayout());
        JLabel titleExpiring = new JLabel(" Expiring soon");
        titleExpiring.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 18));
        titleExpiring.setForeground(new Color(15,118,36));
        expiring.add(titleExpiring, BorderLayout.NORTH);
        //details put in center/bottom -- not finished yet
        expiring.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        infoPanel.add(expiring);

        add(infoPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("➕ Add")) {
            //System.out.println("add item");
            setVisible(false); //can keep the new window opened only (looks like close the previous window)
            AddWindow aNewWindow = new AddWindow();
        }
        else if (actionCommand.equals("👁 Stock")) {
            setVisible(false);
            try {
                StockWindow aNewWindow = new StockWindow();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        //System.out.println("view item");
        else if (actionCommand.equals("📖 Recipe")) {
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