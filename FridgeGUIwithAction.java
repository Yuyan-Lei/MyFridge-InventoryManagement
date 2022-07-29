import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * This GUI is the basic menu
 */
public class FridgeGUIwithAction extends JFrame implements ActionListener  {
    public static final int WIDTH = 350;
    public static final int HEIGHT = 750;
    private ActionEvent e;

    // Colors
    public static final Color GREEN_THEME = new Color(122, 156, 87);
//  public static final Color MAIN_BACKGROUND = new Color(242, 245, 237);
    public static final Color MAIN_BACKGROUND = new Color(255, 255, 255);
    public static final Color MENU_BACKGROUND = new Color(232, 240, 213);
    public static final Color WHITE_COLOR = new Color(255, 255, 255);
    public static final Color TITLE_COLOR = new Color(122, 156, 87);
    public static final Color RED_COLOR = new Color(223, 185, 182);

    // Fonts
    public static final String TITLE_FONT = "San Francisco";
    public static final String TEXT_FONT = "Arial";

    // Sizes
    public static final int TOP_BAR_SIZE = 24;
    public static final int TITLE_SIZE = 18;
    public static final int TEXT_SIZE = 14;
    public static final int MENU_SIZE = 12;
    public static final Font MENU_FORMAT = new Font(TITLE_FONT, Font.PLAIN, MENU_SIZE);
    public static final int ICON_SIZE = 44;


    public FridgeGUIwithAction() throws ParseException {
//        super("My Fridge"); //title name
//        setSize(WIDTH, HEIGHT);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
        DefaultUI ui = new DefaultUI("Notifications", this);

        // 1. Top bar
//        JPanel topPanel = new JPanel();
//        topPanel.setBackground(GREEN_THEME);
//        JLabel topText = new JLabel("Notifications");
//        topText.setFont(new Font(TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, TOP_BAR_SIZE));
//        topText.setForeground(WHITE_COLOR);
//        topPanel.add(topText);
//        add(topPanel, BorderLayout.NORTH);

        // 2. Bottom Bar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,4));
        buttonPanel.setBackground(MENU_BACKGROUND);

        ImageIcon addIcon = new ImageIcon("./icons/add.png");
        Image img = addIcon.getImage();
        Image newImg = img.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        addIcon = new ImageIcon(newImg);
        JButton addButton = new JButton(addIcon);
        addButton.setActionCommand("add");
        addButton.setBackground(MENU_BACKGROUND);
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        ImageIcon notificationIcon = new ImageIcon("./icons/expired_g.png");
        img = notificationIcon.getImage();
        newImg = img.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        notificationIcon = new ImageIcon(newImg);
        JButton notificationButton = new JButton(notificationIcon);
        notificationButton.setActionCommand("notification");
        notificationButton.setBackground(MENU_BACKGROUND);
        notificationButton.setOpaque(true);
        notificationButton.setBorderPainted(false);
        notificationButton.addActionListener(this);
        buttonPanel.add(notificationButton);

        ImageIcon viewIcon = new ImageIcon("./icons/stock.png");
        img = viewIcon.getImage();
        newImg = img.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        viewIcon = new ImageIcon(newImg);
        JButton viewButton = new JButton(viewIcon);
        viewButton.setActionCommand("stock");
        viewButton.setBackground(MENU_BACKGROUND);
        viewButton.setOpaque(true);
        viewButton.setBorderPainted(false);
        viewButton.addActionListener(this);
        buttonPanel.add(viewButton);

        ImageIcon recipeIcon = new ImageIcon("./icons/recipe.png");
        img = recipeIcon.getImage();
        newImg = img.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        recipeIcon = new ImageIcon(newImg);
        JButton recipeButton = new JButton(recipeIcon);
        recipeButton.setActionCommand("recipe");
        recipeButton.setBackground(MENU_BACKGROUND);
        recipeButton.setOpaque(true);
        recipeButton.setBorderPainted(false);
        recipeButton.addActionListener(this);
        buttonPanel.add(recipeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // 3. center stock info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3,1));

        // 3-1 Low Stock Panel
        JPanel lowStock = new JPanel();
        // show title
        JLabel titleLow = new JLabel(" Low Stock");
        titleLow.setFont(new Font(TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, TITLE_SIZE));
        titleLow.setForeground(TITLE_COLOR);
        lowStock.setBackground(MAIN_BACKGROUND);
        lowStock.add(titleLow, BorderLayout.NORTH);
        lowStock.setBorder(BorderFactory.createLineBorder(GREEN_THEME));
        // show items
        Stock newStock = new Stock();
        ArrayList<FoodItem> lowStockList = newStock.getLowStockItems(Stock.StockType.ALL);
        int sizeOfLowStockItems = lowStockList.size();
        lowStock.setLayout(new GridLayout(sizeOfLowStockItems + 1, 1));
        JButton[] lowStockItemButtons = new JButton[sizeOfLowStockItems];
        for (int i = 0; i < sizeOfLowStockItems; i++) {
            String itemText = lowStockList.get(i).toString();
            lowStockItemButtons[i] = new JButton(itemText);
            lowStock.add(lowStockItemButtons[i]);
        }
        infoPanel.add(lowStock);

        // 3-2 Expired Panel
        JPanel expired = new JPanel();
        // show title
        JLabel titleExpired = new JLabel(" Expired");
        titleExpired.setFont(new Font(TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, TITLE_SIZE));
        titleExpired.setForeground(TITLE_COLOR);
        expired.setBackground(MAIN_BACKGROUND);
        expired.add(titleExpired, BorderLayout.NORTH);
        expired.setBorder(BorderFactory.createLineBorder(GREEN_THEME));
        // show items
        ArrayList<FoodItem> expiredList = newStock.getExpiredItems(Stock.StockType.ALL);
        int sizeOfExpiredItems = expiredList.size();
        expired.setLayout(new GridLayout(sizeOfExpiredItems + 1, 1));
        JButton[] expiredItemButtons = new JButton[sizeOfExpiredItems];
        for (int i = 0; i < sizeOfExpiredItems; i++) {
            String itemText = expiredList.get(i).toString();
            expiredItemButtons[i] = new JButton(itemText);
            expired.add(expiredItemButtons[i]);
        }
        infoPanel.add(expired);

        // 3-3 Expiring Panel
        JPanel expiring = new JPanel();
        // show title
        JLabel titleExpiring = new JLabel(" Expiring soon");
        titleExpiring.setFont(new Font(TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, TITLE_SIZE));
        titleExpiring.setForeground(TITLE_COLOR);
        expiring.setBackground(MAIN_BACKGROUND);
        expiring.add(titleExpiring, BorderLayout.NORTH);
        expiring.setBorder(BorderFactory.createLineBorder(GREEN_THEME));
        // show items
        ArrayList<FoodItem> expiringList = newStock.getAlmostExpiredItems(Stock.StockType.ALL);
        int sizeOfExpiringItems = expiringList.size();
        expiring.setLayout(new GridLayout(sizeOfExpiringItems + 1, 1));
        JButton[] expiringItemButtons = new JButton[sizeOfExpiringItems];
        for (int i = 0; i < sizeOfExpiringItems; i++) {
            String itemText = expiringList.get(i).toString();
            expiringItemButtons[i] = new JButton(itemText);
            expiring.add(expiringItemButtons[i]);
        }
        infoPanel.add(expiring);

        add(infoPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("add")) {
            setVisible(false); //can keep the new window opened only (looks like close the previous window)
            try {
                AddWindow aNewWindow = new AddWindow();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (actionCommand.equals("stock")) {
            setVisible(false);
            try {
                StockWindow aNewWindow = new StockWindow();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (actionCommand.equals("recipe")) {
            setVisible(false);
            try {
                RecipeWindow aNewWindow = new RecipeWindow();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else
            System.out.println("Unexpected error.");
    }

}