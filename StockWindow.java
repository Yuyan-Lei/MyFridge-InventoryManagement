import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

public class StockWindow implements ActionListener {
    JFrame frame = new JFrame();

    StockWindow() throws ParseException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());


        // 1. Top bar
        JPanel topPanel = new JPanel();
        topPanel.setBackground(GREEN_THEME);
        JLabel topText = new JLabel("Stock");
        topText.setFont(new Font(TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, TOP_BAR_SIZE));
        topText.setForeground(WHITE_COLOR);
        topPanel.add(topText);
        add(topPanel, BorderLayout.NORTH);

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

        Stock newStock = new Stock();
        ArrayList<FoodItem> itemList = newStock.getItems();
        int sizeOfItems = itemList.size();
        infoPanel.setLayout(new GridLayout(sizeOfItems, 1));
        JButton[] itemButtons = new JButton[sizeOfItems];
        for (int i = 0; i < sizeOfItems; i++) {
            String itemText = itemList.get(i).toString();
            itemButtons[i] = new JButton(itemText);
            expiring.add(itemButtons[i]);
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
                StockWindow aNewWindow = new StockWindow();
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
