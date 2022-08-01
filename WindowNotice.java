import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * This GUI is the notice window
 */
public class WindowNotice extends JFrame implements ActionListener  {
    private String itemName;
    private ArrayList<FoodItem> showinglist;
    private ArrayList<FoodItem> lowStockList;
    private ArrayList<FoodItem> expiredList;
    private ArrayList<FoodItem> expiringList;
    private String panelName = "";
    private ActionEvent e;
    public WindowNotice() throws ParseException {
        DefaultUI ui = new DefaultUI("Notice", this);
        setVisible(true);

        // Center stock info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3,1));

        //remove icon
        ImageIcon newIcon = new ImageIcon("./icons/delete_g.png");
        Image img = newIcon.getImage();
        Image newImg = img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon removeIcon = new ImageIcon(newImg);

        // 3-1 Low Stock Panel
        JPanel lowStock = new JPanel(new BorderLayout());
        // show title
        panelName = "Low Stock";
        JLabel titleLow = new JLabel(panelName);
        titleLow.setFont(new Font(DefaultUI.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, DefaultUI.TITLE_SIZE));
        titleLow.setForeground(DefaultUI.TITLE_COLOR);
        lowStock.setBackground(DefaultUI.MAIN_BACKGROUND);
        lowStock.add(titleLow, BorderLayout.NORTH);
        lowStock.setBorder(BorderFactory.createLineBorder(DefaultUI.GREEN_THEME));
        // show items
        Stock newStock = new Stock();
        lowStockList = newStock.getLowStockItems(Stock.StockType.ALL);
        int sizeOfLowStockItems = lowStockList.size();

        lowStock.setLayout(new GridLayout(sizeOfLowStockItems + 1, 1));
        lowStock.setBackground(DefaultUI.WHITE_COLOR);
//        JPanel[] lowStockItemPanel = new JPanel[sizeOfLowStockItems];
        for (int i = 0; i < sizeOfLowStockItems; i++) {
            String itemNum = String.valueOf(lowStockList.get(i).getQuantity());
            String itemDate = lowStockList.get(i).getExpirationToString();
            itemName = lowStockList.get(i).getName();

            //whole item
            JPanel bigItemPanel = new JPanel();
            bigItemPanel.setLayout(new BorderLayout());
            bigItemPanel.setBackground(DefaultUI.WHITE_COLOR);

            //delete button and itemName button
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.LINE_AXIS));
            itemPanel.setBackground(DefaultUI.WHITE_COLOR);

            JButton deleteButton = new JButton(removeIcon);
            deleteButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
            deleteButton.setActionCommand("Delete" + panelName + i);

            JButton nameB = new JButton("  " + itemName);
            nameB.setFont(new Font(DefaultUI.TITLE_FONT, Font.BOLD, DefaultUI.TEXT_SIZE - 3));
            nameB.setOpaque(true);
            nameB.setBorderPainted(false);
            nameB.setBackground(DefaultUI.WHITE_COLOR);
            nameB.setActionCommand("View" + panelName + i);

            JLabel expireDate = new JLabel(itemDate);
            expireDate.setFont(new Font(DefaultUI.TEXT_FONT, Font.PLAIN, DefaultUI.TEXT_SIZE - 4));
            expireDate.setForeground(Color.gray);

            JPanel textPanel = new JPanel();
            textPanel.setBackground(DefaultUI.WHITE_COLOR);

            JLabel only = new JLabel("      Only  ");
            only.setForeground(Color.gray);

            JLabel num = new JLabel(itemNum);
            num.setFont(new Font(DefaultUI.TEXT_FONT, Font.BOLD, DefaultUI.TITLE_SIZE));
            num.setForeground(DefaultUI.RED_COLOR);

            JLabel left = new JLabel("  left");
            left.setForeground(Color.gray);

            itemPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
            itemPanel.add(deleteButton);
            itemPanel.add(nameB);
            textPanel.add(expireDate);
            textPanel.add(only);
            textPanel.add(num);
            textPanel.add(left);
            bigItemPanel.add(itemPanel, BorderLayout.WEST);
            bigItemPanel.add(textPanel,BorderLayout.EAST);
            lowStock.add(bigItemPanel);

        }
        infoPanel.add(lowStock);

        // 3-2&3 Expired Panel & Expiring Panel
        int startIndex = sizeOfLowStockItems;
        for(int p=1; p<3; p++) {
            //separate two cases
            String word = "";
//            int endIndex = 0;
            ArrayList<FoodItem> list = newStock.getExpiredItems(Stock.StockType.ALL);
            if (p == 1) {
                panelName = "Expired";
                word = "       Expired  ";
//                startIndex = sizeOfLowStockItems;
                list = newStock.getExpiredItems(Stock.StockType.ALL);
                expiredList = newStock.getExpiredItems(Stock.StockType.ALL);
//                endIndex = sizeOfLowStockItems + expiredList.size();
            } else {
                panelName = "Expiring";
                word = "    Expiring in  ";
//                startIndex = sizeOfLowStockItems + expiredList.size();
                list = newStock.getAlmostExpiredItems(Stock.StockType.ALL);
                expiringList = newStock.getAlmostExpiredItems(Stock.StockType.ALL);
//                endIndex = sizeOfLowStockItems + expiredList.size() + expiringList.size();
            }
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
            int rowsNum = 0;
            if(sizeOfListItems > 2){
                rowsNum = sizeOfListItems + 1;
            }
            else{
                rowsNum = sizeOfListItems + 2;
            }
            expired.setLayout(new GridLayout(rowsNum, 1));
            expired.setBackground(DefaultUI.WHITE_COLOR);


            for (int i = 0; i < sizeOfListItems; i++) {
                String itemNum = String.valueOf(list.get(i).getQuantity());
                String itemDate = String.valueOf(list.get(i).getDays());
                itemName = list.get(i).getName();

                //whole item
                JPanel bigItemPanel = new JPanel();
                bigItemPanel.setLayout(new BorderLayout());
                bigItemPanel.setBackground(DefaultUI.WHITE_COLOR);

                //delete button and itemName button
                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.LINE_AXIS));
                itemPanel.setBackground(DefaultUI.WHITE_COLOR);

                JButton deleteButton = new JButton(removeIcon);
                deleteButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                deleteButton.setActionCommand("Delete" + panelName + startIndex);
                JButton nameB = new JButton("  " + itemName);
                nameB.setFont(new Font(DefaultUI.TITLE_FONT, Font.BOLD, DefaultUI.TEXT_SIZE - 3));
                nameB.setOpaque(true);
                nameB.setBorderPainted(false);
                nameB.setBackground(DefaultUI.WHITE_COLOR);
                nameB.setActionCommand("View" + panelName + i);

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

//                startIndex++;
                itemPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                itemPanel.add(deleteButton);
                itemPanel.add(nameB);

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
        if(panelName.equals("Low Stock")){
            showinglist = lowStockList;
        }
        else if (panelName.equals("Expired")){
            showinglist = expiredList;
        }
        else if (panelName.equals("Expiring")){
            showinglist = expiringList;
        }

        if (actionCommand.startsWith("Delete")) {
            int removeNum = Integer.parseInt(actionCommand.replaceAll("[\\D]", ""));
            Stock stock = new Stock();
            try {
                stock.removeItem(showinglist.get(removeNum));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            setVisible(false);
            WindowNotice window = null;
            try {
                window = new WindowNotice();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            window.setVisible(true);
        }

        else if (actionCommand.startsWith("View")) {
            int viewNum = Integer.parseInt(actionCommand.replaceAll("[\\D]", ""));
            FoodItem viewItem = showinglist.get(viewNum);

            setVisible(false);
            WindowItemDetails window = null;
            try {
                window = new WindowItemDetails(viewItem);
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