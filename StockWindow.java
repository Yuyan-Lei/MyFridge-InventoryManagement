import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

public class StockWindow extends JFrame implements ActionListener {

    StockWindow() throws ParseException {
        DefaultUI ui = new DefaultUI("Stock", this);
//        super("My Fridge");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(FridgeGUIwithAction.WIDTH, FridgeGUIwithAction.HEIGHT);
        setVisible(true);
//        setLayout(new BorderLayout());



        // 1. Top bar
//        JPanel topPanel = new JPanel();
//        topPanel.setBackground(FridgeGUIwithAction.GREEN_THEME);
//        JLabel topText = new JLabel("Stock");
//        topText.setFont(new Font(FridgeGUIwithAction.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, FridgeGUIwithAction.TOP_BAR_SIZE));
//        topText.setForeground(FridgeGUIwithAction.WHITE_COLOR);
//        topPanel.add(topText);
//        add(topPanel, BorderLayout.NORTH);

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
//        JPanel infoPanel = new JPanel();
//
//        Stock newStock = new Stock();
//        ArrayList<FoodItem> itemList = newStock.getItems();
//        int sizeOfItems = itemList.size();
//        infoPanel.setLayout(new GridLayout(sizeOfItems, 1));
//        JButton[] itemButtons = new JButton[sizeOfItems];
//        for (int i = 0; i < sizeOfItems; i++) {
//            String itemText = itemList.get(i).toString();
//            itemButtons[i] = new JButton(itemText);
//            infoPanel.add(itemButtons[i]);
//        }

//        add(infoPanel, BorderLayout.CENTER);
        JPanel bigPanel = new JPanel();
        bigPanel.setLayout(new BorderLayout());
        bigPanel.setBackground(DefaultUI.WHITE_COLOR);

        JPanel allItemsPanel = new JPanel();
        allItemsPanel.setLayout(new GridLayout(1,4));
        allItemsPanel.setBackground(DefaultUI.WHITE_COLOR);
        ImageIcon allItemIcon = new ImageIcon("./stockIcons/all_items.png");
        img = allItemIcon.getImage();
        newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        allItemIcon = new ImageIcon(newImg);
        allItemsPanel.add(new JLabel(allItemIcon)); //add icon

        JPanel textPanel = new JPanel();
        textPanel.setBackground(DefaultUI.WHITE_COLOR);
        textPanel.setLayout(new GridLayout(2,1));
        textPanel.add(new JLabel("All Items")); //add title text
        textPanel.setFont(new Font(FridgeGUIwithAction.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, FridgeGUIwithAction.TOP_BAR_SIZE));
        allItemsPanel.add(textPanel);
        JPanel numPanel = new JPanel();
        numPanel.setBackground(DefaultUI.WHITE_COLOR);
        numPanel.setLayout(new GridLayout(2,1));
        numPanel.add(new JLabel("16")); //need to link the function to get total items
        numPanel.add(new JLabel("items"));
        allItemsPanel.add(numPanel); //add items num

        //open icon
        ImageIcon openIcon = new ImageIcon("./stockIcons/open.png");
        img = openIcon.getImage();
        newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        openIcon = new ImageIcon(newImg);
        allItemsPanel.add(new JLabel(openIcon));

        //cut line
        JLabel cutLine = new JLabel("  MY SPACES");
        cutLine.setBackground(DefaultUI.MENU_BACKGROUND);
        cutLine.setForeground(Color.GRAY);
        cutLine.setPreferredSize(new Dimension(10, 3));
        cutLine.setBorder(BorderFactory.createLineBorder(FridgeGUIwithAction.GREEN_THEME));

        //subgroup part (7,1)
        JPanel subgroupPanel = new JPanel();
        subgroupPanel.setLayout(new GridLayout(7,1));
        subgroupPanel.setBackground(DefaultUI.WHITE_COLOR);

        //Fridge
        JPanel fridgePanel = new JPanel();
        fridgePanel.setLayout(new GridLayout(1,4));
        fridgePanel.setBackground(DefaultUI.WHITE_COLOR);
        ImageIcon fridgeIcon = new ImageIcon("./stockIcons/location_fridge.png");
        img = fridgeIcon.getImage();
        newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        fridgeIcon = new ImageIcon(newImg);
        fridgePanel.add(new JLabel(fridgeIcon)); //add icon

        JPanel fridgeTextPanel = new JPanel();
        fridgeTextPanel.setLayout(new GridLayout(2,1));
        fridgeTextPanel.setBackground(DefaultUI.WHITE_COLOR);
        fridgeTextPanel.add(new JLabel("Fridge")); //add title text
        fridgeTextPanel.setFont(new Font(FridgeGUIwithAction.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, FridgeGUIwithAction.TOP_BAR_SIZE));
        fridgePanel.add(fridgeTextPanel);
        JPanel fridgeNumPanel = new JPanel();
        fridgeNumPanel.setLayout(new GridLayout(2,1));
        fridgeNumPanel.setBackground(DefaultUI.WHITE_COLOR);
        fridgeNumPanel.add(new JLabel("16")); //need to link the function to get total items
        fridgeNumPanel.add(new JLabel("items"));
        fridgePanel.add(fridgeNumPanel); //add items num
        fridgePanel.add(new JLabel(openIcon));
        fridgePanel.setBorder(BorderFactory.createLineBorder(FridgeGUIwithAction.MENU_BACKGROUND));

        //Freezer
        JPanel freezerPanel = new JPanel();
        freezerPanel.setLayout(new GridLayout(1,4));
        freezerPanel.setBackground(DefaultUI.WHITE_COLOR);
        ImageIcon freezerIcon = new ImageIcon("./stockIcons/location_frozen.png");
        img = freezerIcon.getImage();
        newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        freezerIcon = new ImageIcon(newImg);
        freezerPanel.add(new JLabel(freezerIcon)); //add icon

        JPanel freezerTextPanel = new JPanel();
        freezerTextPanel.setBackground(DefaultUI.WHITE_COLOR);
        freezerTextPanel.setLayout(new GridLayout(2,1));
        freezerTextPanel.add(new JLabel("Freezer")); //add title text
        freezerTextPanel.setFont(new Font(FridgeGUIwithAction.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, FridgeGUIwithAction.TOP_BAR_SIZE));
        freezerPanel.add(freezerTextPanel);
        JPanel freezerNumPanel = new JPanel();
        freezerNumPanel.setBackground(DefaultUI.WHITE_COLOR);
        freezerNumPanel.setLayout(new GridLayout(2,1));
        freezerNumPanel.add(new JLabel("16")); //need to link the function to get total items
        freezerNumPanel.add(new JLabel("items"));
        freezerPanel.add(freezerNumPanel); //add items num
        freezerPanel.add(new JLabel(openIcon));
        freezerPanel.setBorder(BorderFactory.createLineBorder(FridgeGUIwithAction.MENU_BACKGROUND));

        //VEGETABLE
        JPanel vegePanel = new JPanel();
        vegePanel.setBackground(DefaultUI.WHITE_COLOR);
        vegePanel.setLayout(new GridLayout(1,4));
        ImageIcon vegeIcon = new ImageIcon("./stockIcons/type_vegetable.png");
        img = vegeIcon.getImage();
        newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        vegeIcon = new ImageIcon(newImg);
        vegePanel.add(new JLabel(vegeIcon)); //add icon

        JPanel vegeTextPanel = new JPanel();
        vegeTextPanel.setLayout(new GridLayout(2,1));
        vegeTextPanel.setBackground(DefaultUI.WHITE_COLOR);
        vegeTextPanel.add(new JLabel("Vegetable")); //add title text
        vegeTextPanel.setFont(new Font(FridgeGUIwithAction.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, FridgeGUIwithAction.TOP_BAR_SIZE));
        vegePanel.add(vegeTextPanel);
        JPanel vegeNumPanel = new JPanel();
        vegeNumPanel.setLayout(new GridLayout(2,1));
        vegeNumPanel.add(new JLabel("16")); //need to link the function to get total items
        vegeNumPanel.add(new JLabel("items"));
        vegeNumPanel.setBackground(DefaultUI.WHITE_COLOR);
        vegePanel.add(vegeNumPanel); //add items num
        vegePanel.add(new JLabel(openIcon));
        vegePanel.setBorder(BorderFactory.createLineBorder(FridgeGUIwithAction.MENU_BACKGROUND));

        //MEAT
        JPanel meatPanel = new JPanel();
        meatPanel.setLayout(new GridLayout(1,4));
        meatPanel.setBackground(DefaultUI.WHITE_COLOR);
        ImageIcon meatIcon = new ImageIcon("./stockIcons/type_meat.png");
        img = meatIcon.getImage();
        newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        meatIcon = new ImageIcon(newImg);
        meatPanel.add(new JLabel(meatIcon)); //add icon

        JPanel meatTextPanel = new JPanel();
        meatTextPanel.setLayout(new GridLayout(2,1));
        meatTextPanel.setBackground(DefaultUI.WHITE_COLOR);
        meatTextPanel.add(new JLabel("Meat")); //add title text
        meatTextPanel.setFont(new Font(FridgeGUIwithAction.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, FridgeGUIwithAction.TOP_BAR_SIZE));
        meatPanel.add(meatTextPanel);
        JPanel meatNumPanel = new JPanel();
        meatNumPanel.setLayout(new GridLayout(2,1));
        meatNumPanel.setBackground(DefaultUI.WHITE_COLOR);
        meatNumPanel.add(new JLabel("16")); //need to link the function to get total items
        meatNumPanel.add(new JLabel("items"));
        meatPanel.add(meatNumPanel); //add items num
        meatPanel.add(new JLabel(openIcon));
        meatPanel.setBorder(BorderFactory.createLineBorder(FridgeGUIwithAction.MENU_BACKGROUND));

        //FRUIT
        JPanel fruitPanel = new JPanel();
        fruitPanel.setBackground(DefaultUI.WHITE_COLOR);
        fruitPanel.setLayout(new GridLayout(1,4));
        ImageIcon fruitIcon = new ImageIcon("./stockIcons/type_fruits.png");
        img = fruitIcon.getImage();
        newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        fruitIcon = new ImageIcon(newImg);
        fruitPanel.add(new JLabel(fruitIcon)); //add icon

        JPanel fruitTextPanel = new JPanel();
        fruitTextPanel.setLayout(new GridLayout(2,1));
        fruitTextPanel.setBackground(DefaultUI.WHITE_COLOR);
        fruitTextPanel.add(new JLabel("Fruits")); //add title text
        fruitTextPanel.setFont(new Font(FridgeGUIwithAction.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, FridgeGUIwithAction.TOP_BAR_SIZE));
        fruitPanel.add(fruitTextPanel);
        JPanel fruitNumPanel = new JPanel();
        fruitNumPanel.setLayout(new GridLayout(2,1));
        fruitNumPanel.add(new JLabel("16")); //need to link the function to get total items
        fruitNumPanel.add(new JLabel("items"));
        fruitNumPanel.setBackground(DefaultUI.WHITE_COLOR);
        fruitPanel.add(fruitNumPanel); //add items num
        fruitPanel.add(new JLabel(openIcon));
        fruitPanel.setBorder(BorderFactory.createLineBorder(FridgeGUIwithAction.MENU_BACKGROUND));

        //DRINK
        JPanel drinkPanel = new JPanel();
        drinkPanel.setLayout(new GridLayout(1,4));
        drinkPanel.setBackground(DefaultUI.WHITE_COLOR);
        ImageIcon drinkIcon = new ImageIcon("./stockIcons/type_drinks.png");
        img = drinkIcon.getImage();
        newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        drinkIcon = new ImageIcon(newImg);
        drinkPanel.add(new JLabel(drinkIcon)); //add icon

        JPanel drinkTextPanel = new JPanel();
        drinkTextPanel.setLayout(new GridLayout(2,1));
        drinkTextPanel.setBackground(DefaultUI.WHITE_COLOR);
        drinkTextPanel.add(new JLabel("Drinks")); //add title text
        drinkTextPanel.setFont(new Font(FridgeGUIwithAction.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, FridgeGUIwithAction.TOP_BAR_SIZE));
        drinkPanel.add(drinkTextPanel);
        JPanel drinkNumPanel = new JPanel();
        drinkNumPanel.setLayout(new GridLayout(2,1));
        drinkNumPanel.add(new JLabel("16")); //need to link the function to get total items
        drinkNumPanel.add(new JLabel("items"));
        drinkNumPanel.setBackground(DefaultUI.WHITE_COLOR);
        drinkPanel.add(drinkNumPanel); //add items num
        drinkPanel.add(new JLabel(openIcon));
        drinkPanel.setBorder(BorderFactory.createLineBorder(FridgeGUIwithAction.MENU_BACKGROUND));

        //OTHER
        JPanel otherPanel = new JPanel();
        otherPanel.setLayout(new GridLayout(1,4));
        otherPanel.setBackground(DefaultUI.WHITE_COLOR);
        ImageIcon otherIcon = new ImageIcon("./stockIcons/type_other.png");
        img = otherIcon.getImage();
        newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        otherIcon = new ImageIcon(newImg);
        otherPanel.add(new JLabel(otherIcon)); //add icon

        JPanel otherTextPanel = new JPanel();
        otherTextPanel.setLayout(new GridLayout(2,1));
        otherTextPanel.setBackground(DefaultUI.WHITE_COLOR);
        otherTextPanel.add(new JLabel("Other")); //add title text
        otherTextPanel.setFont(new Font(FridgeGUIwithAction.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, FridgeGUIwithAction.TOP_BAR_SIZE));
        otherPanel.add(otherTextPanel);
        JPanel otherNumPanel = new JPanel();
        otherNumPanel.setLayout(new GridLayout(2,1));
        otherNumPanel.setBackground(DefaultUI.WHITE_COLOR);
        otherNumPanel.add(new JLabel("16")); //need to link the function to get total items
        otherNumPanel.add(new JLabel("items"));
        otherPanel.add(otherNumPanel); //add items num
        otherPanel.add(new JLabel(openIcon));
        otherPanel.setBorder(BorderFactory.createLineBorder(FridgeGUIwithAction.MENU_BACKGROUND));

        subgroupPanel.add(fridgePanel);
        subgroupPanel.add(freezerPanel);
        subgroupPanel.add(vegePanel);
        subgroupPanel.add(meatPanel);
        subgroupPanel.add(fruitPanel);
        subgroupPanel.add(drinkPanel);
        subgroupPanel.add(otherPanel);
        bigPanel.add(allItemsPanel, BorderLayout.NORTH);
        bigPanel.add(cutLine, BorderLayout.CENTER);
        bigPanel.add(subgroupPanel, BorderLayout.SOUTH);
        add(bigPanel,BorderLayout.CENTER);
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
