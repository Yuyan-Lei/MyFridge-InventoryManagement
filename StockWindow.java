import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

public class StockWindow extends JFrame implements ActionListener {

    StockWindow() throws ParseException {
        DefaultUI ui = new DefaultUI();
//        super("My Fridge");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(FridgeGUIwithAction.WIDTH, FridgeGUIwithAction.HEIGHT);
        setVisible(true);
//        setLayout(new BorderLayout());



        // 1. Top bar
        JPanel topPanel = new JPanel();
        topPanel.setBackground(FridgeGUIwithAction.GREEN_THEME);
        JLabel topText = new JLabel("Stock");
        topText.setFont(new Font(FridgeGUIwithAction.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, FridgeGUIwithAction.TOP_BAR_SIZE));
        topText.setForeground(FridgeGUIwithAction.WHITE_COLOR);
        topPanel.add(topText);
        add(topPanel, BorderLayout.NORTH);

        // 2. Bottom Bar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,4));
        buttonPanel.setBackground(FridgeGUIwithAction.MENU_BACKGROUND);

        ImageIcon addIcon = new ImageIcon("./icons/add.png");
        Image img = addIcon.getImage();
        Image newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        addIcon = new ImageIcon(newImg);
        JButton addButton = new JButton(addIcon);
        addButton.setActionCommand("add");
        addButton.setBackground(FridgeGUIwithAction.MENU_BACKGROUND);
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        ImageIcon notificationIcon = new ImageIcon("./icons/expired.png");
        img = notificationIcon.getImage();
        newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        notificationIcon = new ImageIcon(newImg);
        JButton notificationButton = new JButton(notificationIcon);
        notificationButton.setActionCommand("notification");
        notificationButton.setBackground(FridgeGUIwithAction.MENU_BACKGROUND);
        notificationButton.setOpaque(true);
        notificationButton.setBorderPainted(false);
        notificationButton.addActionListener(this);
        buttonPanel.add(notificationButton);

        ImageIcon viewIcon = new ImageIcon("./icons/stock_g.png");
        img = viewIcon.getImage();
        newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        viewIcon = new ImageIcon(newImg);
        JButton viewButton = new JButton(viewIcon);
        viewButton.setActionCommand("stock");
        viewButton.setBackground(FridgeGUIwithAction.MENU_BACKGROUND);
        viewButton.setOpaque(true);
        viewButton.setBorderPainted(false);
        viewButton.addActionListener(this);
        buttonPanel.add(viewButton);

        ImageIcon recipeIcon = new ImageIcon("./icons/recipe.png");
        img = recipeIcon.getImage();
        newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        recipeIcon = new ImageIcon(newImg);
        JButton recipeButton = new JButton(recipeIcon);
        recipeButton.setActionCommand("recipe");
        recipeButton.setBackground(FridgeGUIwithAction.MENU_BACKGROUND);
        recipeButton.setOpaque(true);
        recipeButton.setBorderPainted(false);
        recipeButton.addActionListener(this);
        buttonPanel.add(recipeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // 3. center stock info
        JPanel infoPanel = new JPanel();

        Stock newStock = new Stock();
        ArrayList<FoodItem> itemList = newStock.getItems();
        int sizeOfItems = itemList.size();
        infoPanel.setLayout(new GridLayout(sizeOfItems, 1));
        JButton[] itemButtons = new JButton[sizeOfItems];
        for (int i = 0; i < sizeOfItems; i++) {
            String itemText = itemList.get(i).toString();
            itemButtons[i] = new JButton(itemText);
            infoPanel.add(itemButtons[i]);
        }

        add(infoPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("add")) {
            setVisible(false); //can keep the new window opened only (looks like close the previous window)
            AddWindow aNewWindow = new AddWindow();
        }
        else if (actionCommand.equals("notification")) {
            setVisible(false);
            try {
                FridgeGUIwithAction aNewWindow = new FridgeGUIwithAction();
                aNewWindow.setVisible(true);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (actionCommand.equals("recipe")) {
            setVisible(false);
            RecipeWindow aNewWindow = new RecipeWindow();
        }
        else
            System.out.println("Unexpected error.");
    }
}
