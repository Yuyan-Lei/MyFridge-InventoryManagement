package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import model.FoodItem;
import model.Stock;

public class StockWindow extends JFrame implements ActionListener {

    StockWindow() throws ParseException {
        new DefaultUI("Stock", this);
        setVisible(true);

        // Center stock info
        Stock newStock = new Stock();
        ArrayList<FoodItem> itemList = newStock.getItems(Stock.StockType.ALL);
        ArrayList<FoodItem> fruitList = newStock.getItems(Stock.StockType.FRUIT);
        ArrayList<FoodItem> vegetableList = newStock.getItems(Stock.StockType.VEGETABLE);
        ArrayList<FoodItem> drinkList = newStock.getItems(Stock.StockType.DRINK);
        ArrayList<FoodItem> meatList = newStock.getItems(Stock.StockType.MEAT);
        ArrayList<FoodItem> otherList = newStock.getItems(Stock.StockType.OTHER);
        ArrayList<FoodItem> frozenList = newStock.getItems(Stock.StockType.FROZEN);
        ArrayList<FoodItem> refrigeratedList = newStock.getItems(Stock.StockType.REFRIGERATED);
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

        JPanel allItemsPanel = subPanel(bigPanel, "All", sizeOfAllItems);
        allItemsPanel.setPreferredSize(new Dimension(20,90));

        //cut line
        JPanel cutPanel = new JPanel();
        JLabel cutLine = new JLabel("Types                                            ");
        cutLine.setFont(new Font(DefaultUI.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, DefaultUI.TITLE_SIZE));
        cutPanel.setBackground(new Color(230,235,220));
        cutLine.setForeground(Color.GRAY);
//        cutLine.setPreferredSize(new Dimension(300, 150));
        cutPanel.add(cutLine);

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
        bigPanel.add(cutPanel, BorderLayout.CENTER);
        bigPanel.add(subgroupPanel, BorderLayout.SOUTH);
        add(bigPanel,BorderLayout.CENTER);
    }

    public void buildBottomIcon(JPanel buttonPanel, String iconPath, String command) {
        ImageIcon addIcon = new ImageIcon("./icons/basicIcons" + iconPath + ".png");
        Image img = addIcon.getImage();
        Image newImg = img.getScaledInstance(DefaultUI.ICON_SIZE, DefaultUI.ICON_SIZE, Image.SCALE_SMOOTH);
        addIcon = new ImageIcon(newImg);
        JButton addButton = new JButton(addIcon);
        addButton.setActionCommand(command);
        addButton.setBackground(DefaultUI.MENU_BACKGROUND);
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        addButton.addActionListener(this);
        addButton.setFocusPainted(false);
        buttonPanel.add(addButton);
    }

    public JPanel subPanel(JPanel subgroupPanel, String name, int size) {
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(1,5));
        subPanel.setBackground(DefaultUI.WHITE_COLOR);
        subgroupPanel.setPreferredSize(new Dimension(100,500));
        ImageIcon fridgeIcon = new ImageIcon("./icons/stockIcons/subPanel_" + name.toLowerCase() +".png");
        Image img = fridgeIcon.getImage();
        Image newImg = img.getScaledInstance(DefaultUI.ICON_SIZE, DefaultUI.ICON_SIZE, Image.SCALE_SMOOTH);
        fridgeIcon = new ImageIcon(newImg);
        subPanel.add(new JLabel(fridgeIcon)); //add icon

        JPanel fridgeTextPanel = new JPanel();
        fridgeTextPanel.setLayout(new GridLayout(2,1));
        fridgeTextPanel.setBackground(DefaultUI.WHITE_COLOR);
        JLabel typeName = new JLabel(name);
        typeName.setFont(new Font(DefaultUI.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, DefaultUI.TEXT_SIZE));
        fridgeTextPanel.add(typeName); //add title text
        subPanel.add(fridgeTextPanel);
        JPanel fridgeNumPanel = new JPanel();
        fridgeNumPanel.setLayout(new GridLayout(2,1));
        fridgeNumPanel.setBackground(DefaultUI.WHITE_COLOR);

        JPanel blank = new JPanel();
        blank.setBackground(DefaultUI.WHITE_COLOR);
        subPanel.add(blank);

        JLabel num = new JLabel(String.valueOf(size), SwingConstants.CENTER);
        num.setFont(new Font(DefaultUI.TITLE_FONT, Font.PLAIN, DefaultUI.TITLE_SIZE));
        fridgeNumPanel.add(num);
        JLabel items = new JLabel("items", SwingConstants.CENTER);
        items.setForeground(Color.gray);
        fridgeNumPanel.add(items);
        subPanel.add(fridgeNumPanel); //add items num

        // Build an Open Button
        ImageIcon openIcon = new ImageIcon("./icons/stockIcons/open.png");
        img = openIcon.getImage();
        newImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        openIcon = new ImageIcon(newImg);

        JButton openButton = new JButton(openIcon);
        openButton.setActionCommand(name);
        openButton.setOpaque(true);
        openButton.setBorderPainted(false);
        openButton.addActionListener(this);
        openButton.setBackground(DefaultUI.WHITE_COLOR);
        openButton.setFocusPainted(false);
        subPanel.add(openButton);

        subPanel.setBorder(BorderFactory.createLineBorder(DefaultUI.MENU_BACKGROUND));

        return subPanel;
    }

    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Fridge")) {
            setVisible(false);
            StockListWindow window = null;
            try {
                window = new StockListWindow(Stock.StockType.REFRIGERATED);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            window.setVisible(true);
        }
        else if (actionCommand.equals("Freezer")) {
            setVisible(false);
            StockListWindow window = null;
            try {
                window = new StockListWindow(Stock.StockType.FROZEN);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            window.setVisible(true);
        }
        else if (actionCommand.equals("All") || actionCommand.equals("Fruit") || actionCommand.equals("Meat") || actionCommand.equals("Vegetable")
                || actionCommand.equals("Other") || actionCommand.equals("Drink")) {
            setVisible(false);
            StockListWindow window = null;
            try {
                window = new StockListWindow(Stock.StockType.valueOf(actionCommand.toUpperCase()));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            window.setVisible(true);
        }
        else
            System.out.println("Unexpected error.");
    }
}
