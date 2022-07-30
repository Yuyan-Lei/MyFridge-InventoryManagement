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
        setVisible(true);

        // 2. Bottom Bar
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new GridLayout(1,4));
//        buttonPanel.setBackground(WindowNotice.MENU_BACKGROUND);
//
//        buildBottomIcon(buttonPanel, "add", "add"); // 看这里，给button们建立method了
//        buildBottomIcon(buttonPanel, "expired", "notification");
//        buildBottomIcon(buttonPanel, "stock_g", "stock");
//        buildBottomIcon(buttonPanel, "recipe", "recipe");
//
//        add(buttonPanel, BorderLayout.SOUTH);

        // 3. center stock info
        Stock newStock = new Stock();
        ArrayList<FoodItem> itemList = newStock.getAllItems();
        ArrayList<FoodItem> fruitList = newStock.getSpecificItems(Stock.StockType.FRUIT);
        ArrayList<FoodItem> vegetableList = newStock.getSpecificItems(Stock.StockType.VEGETABLE);
        ArrayList<FoodItem> drinkList = newStock.getSpecificItems(Stock.StockType.DRINK);
        ArrayList<FoodItem> meatList = newStock.getSpecificItems(Stock.StockType.MEAT);
        ArrayList<FoodItem> otherList = newStock.getSpecificItems(Stock.StockType.OTHER);
        ArrayList<FoodItem> frozenList = newStock.getSpecificItems(Stock.StockType.FROZEN);
        ArrayList<FoodItem> refrigeratedList = newStock.getSpecificItems(Stock.StockType.REFRIGERATED);
        int sizeOfAllItems = itemList.size();
        int sizeOfFruit = fruitList.size();
        int sizeOfVegetable = vegetableList.size();
        int sizeOfDrink = drinkList.size();
        int sizeOfMeat = meatList.size();
        int sizeOfOther = otherList.size();
        int sizeOfFrozen = frozenList.size();
        int sizeOfFridge = refrigeratedList.size();

        JPanel bigPanel = new JPanel();
        bigPanel.setLayout(new BorderLayout());
        bigPanel.setBackground(DefaultUI.WHITE_COLOR);

        JPanel allItemsPanel = subPanel(bigPanel, "All", sizeOfFridge);

        //cut line
        JLabel cutLine = new JLabel("Types");
        cutLine.setBackground(DefaultUI.MENU_BACKGROUND);
        cutLine.setForeground(Color.GRAY);
        cutLine.setPreferredSize(new Dimension(300, 150));
        cutLine.setBorder(BorderFactory.createLineBorder(WindowNotice.GREEN_THEME));

        //subgroup part (7,1)
        JPanel subgroupPanel = new JPanel();
        subgroupPanel.setLayout(new GridLayout(7,1));
        subgroupPanel.setBackground(DefaultUI.WHITE_COLOR);

        // 看这里，给panels建立method了
        // Build Panels of types
        subgroupPanel.add(subPanel(subgroupPanel, "Fridge", sizeOfFridge));
        subgroupPanel.add(subPanel(subgroupPanel, "Freezer", sizeOfFrozen));
        subgroupPanel.add(subPanel(subgroupPanel, "Fruit", sizeOfFruit));
        subgroupPanel.add(subPanel(subgroupPanel, "Vegetable", sizeOfVegetable));
        subgroupPanel.add(subPanel(subgroupPanel, "Meat", sizeOfMeat));
        subgroupPanel.add(subPanel(subgroupPanel, "Drink", sizeOfDrink));
        subgroupPanel.add(subPanel(subgroupPanel, "Other", sizeOfOther));

        bigPanel.add(allItemsPanel, BorderLayout.NORTH);
        bigPanel.add(cutLine, BorderLayout.CENTER);
        bigPanel.add(subgroupPanel, BorderLayout.SOUTH);
        add(bigPanel,BorderLayout.CENTER);
    }

    public void buildBottomIcon(JPanel buttonPanel, String iconPath, String command) {
        ImageIcon addIcon = new ImageIcon("./icons/" + iconPath + ".png");
        Image img = addIcon.getImage();
        Image newImg = img.getScaledInstance(WindowNotice.ICON_SIZE, WindowNotice.ICON_SIZE, Image.SCALE_SMOOTH);
        addIcon = new ImageIcon(newImg);
        JButton addButton = new JButton(addIcon);
        addButton.setActionCommand(command);
        addButton.setBackground(WindowNotice.MENU_BACKGROUND);
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        addButton.addActionListener(this);
        buttonPanel.add(addButton);
    }

    public JPanel subPanel(JPanel subgroupPanel, String name, int size) {
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(1,4));
        subPanel.setBackground(DefaultUI.WHITE_COLOR);
        ImageIcon fridgeIcon = new ImageIcon("./stockIcons/subPanel_" + name.toLowerCase() +".png");
        Image img = fridgeIcon.getImage();
        Image newImg = img.getScaledInstance(WindowNotice.ICON_SIZE, WindowNotice.ICON_SIZE, Image.SCALE_SMOOTH);
        fridgeIcon = new ImageIcon(newImg);
        subPanel.add(new JLabel(fridgeIcon)); //add icon

        JPanel fridgeTextPanel = new JPanel();
        fridgeTextPanel.setLayout(new GridLayout(2,1));
        fridgeTextPanel.setBackground(DefaultUI.WHITE_COLOR);
        fridgeTextPanel.add(new JLabel(name)); //add title text
        fridgeTextPanel.setFont(new Font(WindowNotice.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, WindowNotice.TOP_BAR_SIZE));
        subPanel.add(fridgeTextPanel);
        JPanel fridgeNumPanel = new JPanel();
        fridgeNumPanel.setLayout(new GridLayout(2,1));
        fridgeNumPanel.setBackground(DefaultUI.WHITE_COLOR);
        fridgeNumPanel.add(new JLabel(String.valueOf(size)));
        fridgeNumPanel.add(new JLabel("items"));
        subPanel.add(fridgeNumPanel); //add items num

        // Build an Open Button
        ImageIcon openIcon = new ImageIcon("./stockIcons/open.png");
        img = openIcon.getImage();
        newImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        openIcon = new ImageIcon(newImg);

        JButton openButton = new JButton(openIcon);
        openButton.setActionCommand(name);
        openButton.setOpaque(true);
        openButton.setBorderPainted(false);
        openButton.addActionListener(this);
        openButton.setBackground(WindowNotice.WHITE_COLOR);
        subPanel.add(openButton);

        subPanel.setBorder(BorderFactory.createLineBorder(WindowNotice.MENU_BACKGROUND));

        return subPanel;
    }

    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
//        if (actionCommand.equals("add")) {
//            setVisible(false); //can keep the new window opened only (looks like close the previous window)
//            try {
//                AddWindow aNewWindow = new AddWindow();
//            } catch (ParseException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//        else if (actionCommand.equals("notification")) {
//            setVisible(false);
//            try {
//                WindowNotice aNewWindow = new WindowNotice();
//                aNewWindow.setVisible(true);
//            } catch (ParseException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//        else if (actionCommand.equals("recipe")) {
//            setVisible(false);
//            try {
//                WindowRecipe aNewWindow = new WindowRecipe();
//            } catch (ParseException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
        if (actionCommand.equals("Fridge")) {
            setVisible(false);
            WindowViewALLItems window = null;
            try {
                window = new WindowViewALLItems(Stock.StockType.REFRIGERATED);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            window.setVisible(true);
        }
        else if (actionCommand.equals("Freezer")) {
            setVisible(false);
            WindowViewALLItems window = null;
            try {
                window = new WindowViewALLItems(Stock.StockType.FROZEN);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            window.setVisible(true);
        }
        else if (actionCommand.equals("All") || actionCommand.equals("Fruit") || actionCommand.equals("Meat") || actionCommand.equals("Vegetable")
                || actionCommand.equals("Other") || actionCommand.equals("Drink")) {
            setVisible(false);
            WindowViewALLItems window = null;
            try {
                window = new WindowViewALLItems(Stock.StockType.valueOf(actionCommand.toUpperCase()));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            window.setVisible(true);
        }
        else
            System.out.println("Unexpected error.");
    }
}
