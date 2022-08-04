package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import model.FoodItem;
import model.Stock;

/**
 * This GUI is the notice window
 */
public class NoticeWindow extends JFrame implements ActionListener  {
    private String itemName;
    private ArrayList<FoodItem> showinglist;
    private ArrayList<FoodItem> lowStockList;
    private ArrayList<FoodItem> expiredList;
    private ArrayList<FoodItem> expiringList;
    private String panelName;
    private ActionEvent e;
    public NoticeWindow() throws ParseException {
        DefaultUI ui = new DefaultUI("Notice", this);
        setVisible(true);

        // Center stock info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3,1));

        // remove icon
        ImageIcon newIcon = new ImageIcon("./icons/delete_g.png");
        Image img = newIcon.getImage();
        Image newImg = img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon removeIcon = new ImageIcon(newImg);

        // 3-1 Low model.Stock Panel
        JPanel lowStock = new JPanel(new BorderLayout());

        // show title
        panelName = "Low Stock";
        JLabel titleLow = new JLabel(panelName);
        titleLow.setFont(new Font(DefaultUI.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, DefaultUI.TITLE_SIZE));
        titleLow.setForeground(DefaultUI.TITLE_COLOR);
        lowStock.setBackground(DefaultUI.MAIN_BACKGROUND);
        lowStock.add(titleLow, BorderLayout.NORTH);
        lowStock.setBorder(BorderFactory.createLineBorder(DefaultUI.GREEN_THEME));

        // get items' list
        Stock newStock = new Stock();
        lowStockList = newStock.getLowStockItems();
        int sizeOfLowStockItems = lowStockList.size();

        lowStock.setLayout(new GridLayout(sizeOfLowStockItems + 1, 1));
        lowStock.setBackground(DefaultUI.WHITE_COLOR);

        for (int i = 0; i < sizeOfLowStockItems; i++) {
            String itemNum = String.valueOf(lowStockList.get(i).getQuantity());
            String itemDate = lowStockList.get(i).getExpirationToString();
            itemName = lowStockList.get(i).getName();

            // whole item
            JPanel bigItemPanel = new JPanel();
            bigItemPanel.setLayout(new BorderLayout());
            bigItemPanel.setBackground(DefaultUI.WHITE_COLOR);

            // delete button and itemName button
            // for placing to the west of border layout
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.LINE_AXIS));
            itemPanel.setBackground(DefaultUI.WHITE_COLOR);

            JButton deleteButton = new JButton(removeIcon);
            deleteButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
            deleteButton.setActionCommand("Delete " + panelName + i);
            deleteButton.addActionListener(this);

            JButton nameB = new JButton("  " + itemName);
            nameB.setFont(new Font(DefaultUI.TITLE_FONT, Font.BOLD, DefaultUI.TEXT_SIZE - 3));
            nameB.setOpaque(true);
            nameB.setBorderPainted(false);
            nameB.setBackground(DefaultUI.WHITE_COLOR);
            nameB.setActionCommand("View " + panelName + i);
            nameB.addActionListener(this);

            // text panel for storing all east side text
            JPanel textPanel = new JPanel();
            textPanel.setBackground(DefaultUI.WHITE_COLOR);

            JLabel expireDate = new JLabel(itemDate);
            expireDate.setFont(new Font(DefaultUI.TEXT_FONT, Font.PLAIN, DefaultUI.TEXT_SIZE - 4));
            expireDate.setForeground(Color.gray);

            JLabel only = new JLabel("      Only  ");
            only.setForeground(Color.gray);

            JLabel num = new JLabel(itemNum);
            num.setFont(new Font(DefaultUI.TEXT_FONT, Font.BOLD, DefaultUI.TITLE_SIZE));
            num.setForeground(DefaultUI.RED_COLOR);

            JLabel left = new JLabel("  left");
            left.setForeground(Color.gray);

            // button panel
            itemPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
            itemPanel.add(deleteButton);
            itemPanel.add(nameB);

            // text panel
            textPanel.add(expireDate);
            textPanel.add(only);
            textPanel.add(num);
            textPanel.add(left);

            // put two panels into bigItem panel -> one item panel done
            bigItemPanel.add(itemPanel, BorderLayout.WEST);
            bigItemPanel.add(textPanel,BorderLayout.EAST);

            // put each item panel into lowStock panel
            lowStock.add(bigItemPanel);

        }
        infoPanel.add(lowStock);

        // 3-2&3 Expired Panel & Expiring Panel
        for (int p=1; p<3; p++) {
            // separate two cases
            String word = "";
            ArrayList<FoodItem> list = newStock.getExpiredItems();
            // Expired panel
            if (p == 1) {
                panelName = "Expired";
                word = "       Expired  ";
                expiredList = newStock.getExpiredItems();
                list = expiredList;
            }
            // Expiring panel
            else {
                panelName = "Expiring";
                word = "    Expiring in  ";
                expiringList = newStock.getAlmostExpiredItems();
                list = expiringList;
            }

            // create a big panel for storing all items in one expired / expiring panel
            JPanel expired = new JPanel();

            // show title
            JLabel titleExpired = new JLabel(panelName);
            titleExpired.setFont(new Font(DefaultUI.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, DefaultUI.TITLE_SIZE));
            titleExpired.setForeground(DefaultUI.TITLE_COLOR);
            expired.setBackground(DefaultUI.MAIN_BACKGROUND);
            expired.add(titleExpired, BorderLayout.NORTH);
            expired.setBorder(BorderFactory.createLineBorder(DefaultUI.GREEN_THEME));

            // show items
            int sizeOfListItems = list.size();
            int rowsNum;

            if(sizeOfListItems > 2){
                rowsNum = sizeOfListItems + 1;
            }
            // leave some space - make it more horizontally clear
            else{
                rowsNum = sizeOfListItems + 3;
            }
            expired.setLayout(new GridLayout(rowsNum, 1));
            expired.setBackground(DefaultUI.WHITE_COLOR);

            for (int i = 0; i < sizeOfListItems; i++) {
                String itemNum = String.valueOf(list.get(i).getQuantity());
                int date = list.get(i).getDays();
                if(p==2){
                    date += 1;
                }
                String itemDate = String.valueOf(date);
                itemName = list.get(i).getName();

                //whole expired / expiring panel
                JPanel bigItemPanel = new JPanel();
                bigItemPanel.setLayout(new BorderLayout());
                bigItemPanel.setBackground(DefaultUI.WHITE_COLOR);

                // delete button and itemName button panel
                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.LINE_AXIS));
                itemPanel.setBackground(DefaultUI.WHITE_COLOR);

                // delete button
                JButton deleteButton = new JButton(removeIcon);
                deleteButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                deleteButton.setActionCommand("Delete " + panelName + i);
                deleteButton.addActionListener(this);

                // name button
                JButton nameB = new JButton("  " + itemName);
                nameB.setFont(new Font(DefaultUI.TITLE_FONT, Font.BOLD, DefaultUI.TEXT_SIZE - 3));
                nameB.setOpaque(true);
                nameB.setBorderPainted(false);
                nameB.setBackground(DefaultUI.WHITE_COLOR);
                nameB.setActionCommand("View " + panelName + i);
                nameB.addActionListener(this);

                // text panel for storing all text
                JPanel textPanel = new JPanel();
                textPanel.setBackground(DefaultUI.WHITE_COLOR);

                JLabel numLabel = new JLabel("    "+ itemNum);
                numLabel.setForeground(Color.gray);

                JLabel expiredLabel = new JLabel(word);
                expiredLabel.setForeground(Color.gray);

                JLabel num = new JLabel(itemDate);
                num.setFont(new Font(DefaultUI.TEXT_FONT, Font.BOLD, DefaultUI.TITLE_SIZE));
                num.setForeground(DefaultUI.RED_COLOR);

                JLabel days = new JLabel("  Days");
                days.setForeground(Color.gray);

                // itemPanel with two buttons -> place to West
                itemPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                itemPanel.add(deleteButton);
                itemPanel.add(nameB);

                //textPanel -> place to East
                textPanel.add(numLabel);
                textPanel.add(expiredLabel);
                textPanel.add(num);
                textPanel.add(days);

                bigItemPanel.add(itemPanel, BorderLayout.WEST);
                bigItemPanel.add(textPanel,BorderLayout.EAST);
                expired.add(bigItemPanel);
            }
            infoPanel.add(expired);
        }
        add(infoPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();

        if (actionCommand.startsWith("Delete")) {
            if(actionCommand.startsWith("Delete Low Stock")){
                showinglist = lowStockList;
            }
            else if (actionCommand.startsWith("Delete Expired")){
                showinglist = expiredList;
            }
            else if (actionCommand.startsWith("Delete Expiring")){
                showinglist = expiringList;
            }
            int removeNum = Integer.parseInt(actionCommand.replaceAll("[\\D]", ""));
            Stock stock = new Stock();
            try {
                stock.removeItem(showinglist.get(removeNum));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            setVisible(false);

            NoticeWindow window = null;
            try {
                window = new NoticeWindow();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            window.setVisible(true);
        }

        else if (actionCommand.startsWith("View")) {
            if(actionCommand.startsWith("View Low Stock")){
                showinglist = lowStockList;
            }
            else if (actionCommand.startsWith("View Expired")){
                showinglist = expiredList;
            }
            else if (actionCommand.startsWith("View Expiring")){
                showinglist = expiringList;
            }

            int viewNum = Integer.parseInt(actionCommand.replaceAll("[\\D]", ""));
            FoodItem viewItem = showinglist.get(viewNum);

            setVisible(false);
            ItemDetailsWindow window;
            try {
                window = new ItemDetailsWindow(viewItem);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            window.setVisible(true);
        }
        else {
                System.out.println("Unexpected error.");
            }
    }
}