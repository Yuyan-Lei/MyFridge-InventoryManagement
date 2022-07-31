import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * This GUI is the notice window
 */
public class WindowNotice extends JFrame implements ActionListener  {
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
        JLabel titleLow = new JLabel(" Low Stock");
        titleLow.setFont(new Font(DefaultUI.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, DefaultUI.TITLE_SIZE));
        titleLow.setForeground(DefaultUI.TITLE_COLOR);
        lowStock.setBackground(DefaultUI.MAIN_BACKGROUND);
        lowStock.add(titleLow, BorderLayout.NORTH);
        lowStock.setBorder(BorderFactory.createLineBorder(DefaultUI.GREEN_THEME));
        // show items
        Stock newStock = new Stock();
        ArrayList<FoodItem> lowStockList = newStock.getLowStockItems(Stock.StockType.ALL);
        int sizeOfLowStockItems = lowStockList.size();

        lowStock.setLayout(new GridLayout(sizeOfLowStockItems + 1, 1));
        lowStock.setBackground(DefaultUI.WHITE_COLOR);
//        JPanel[] lowStockItemPanel = new JPanel[sizeOfLowStockItems];
        for (int i = 0; i < sizeOfLowStockItems; i++) {
            String itemNum = String.valueOf(lowStockList.get(i).getQuantity());
            String itemDate = lowStockList.get(i).getExpirationToString();
            String itemName = lowStockList.get(i).getName();

            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.LINE_AXIS));
//            itemPanel.setLayout(new GridLayout(1,5));
            itemPanel.setBackground(DefaultUI.WHITE_COLOR);

            JButton deleteButton = new JButton(removeIcon);
            deleteButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
            //need to add action
            JButton nameB = new JButton(itemName);
            nameB.setFont(new Font(DefaultUI.TITLE_FONT, Font.BOLD, DefaultUI.TEXT_SIZE - 3));


//            JLabel expireDate = new JLabel(itemDate);
//            expireDate.setFont(new Font(DefaultUI.TEXT_FONT, Font.PLAIN, DefaultUI.TEXT_SIZE - 4));
//            JPanel expire = new JPanel();
//            expire.add(expireDate);
//            expire.setBackground(new Color(230,235,220));
//            expireDate.setForeground(Color.white);


            JLabel only = new JLabel("   Only  ");
            only.setForeground(Color.gray);

            JLabel num = new JLabel(itemNum);
            num.setFont(new Font(DefaultUI.TEXT_FONT, Font.BOLD, DefaultUI.TITLE_SIZE));
            num.setForeground(DefaultUI.RED_COLOR);

            JLabel left = new JLabel("  left");
            left.setForeground(Color.gray);

            itemPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
            itemPanel.add(deleteButton);
            itemPanel.add(nameB);
//            itemPanel.add(expire);
//            itemPanel.add(new JLabel());
            itemPanel.add(only);
            itemPanel.add(num);
            itemPanel.add(left);
            lowStock.add(itemPanel);
        }
        infoPanel.add(lowStock);


        // 3-2 Expired Panel
        for(int p=0; p<2; p++) {
            //separate two cases
            String titleName = "";
            String word = "";
            ArrayList<FoodItem> expiredList = newStock.getExpiredItems(Stock.StockType.ALL);
            if (p == 0) {
                titleName = " Expired";
                word = "       Expired  ";
                expiredList = newStock.getExpiredItems(Stock.StockType.ALL);
            } else {
                titleName = " Expiring";
                word = "    Expiring in  ";
                expiredList = newStock.getAlmostExpiredItems(Stock.StockType.ALL);
            }
            JPanel expired = new JPanel();
            // show title
            JLabel titleExpired = new JLabel(titleName);
            titleExpired.setFont(new Font(DefaultUI.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, DefaultUI.TITLE_SIZE));
            titleExpired.setForeground(DefaultUI.TITLE_COLOR);
            expired.setBackground(DefaultUI.MAIN_BACKGROUND);
            expired.add(titleExpired, BorderLayout.NORTH);
            expired.setBorder(BorderFactory.createLineBorder(DefaultUI.GREEN_THEME));
            // show items

            int sizeOfExpiredItems = expiredList.size();
            expired.setLayout(new GridLayout(sizeOfExpiredItems + 1, 1));
            expired.setBackground(DefaultUI.WHITE_COLOR);


            for (int i = 0; i < sizeOfExpiredItems; i++) {
                String itemNum = String.valueOf(expiredList.get(i).getQuantity());
                String itemDate = String.valueOf(expiredList.get(i).getDays());
                String itemName = expiredList.get(i).getName();

                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.LINE_AXIS));
//            itemPanel.setLayout(new GridLayout(1,5));
                itemPanel.setBackground(DefaultUI.WHITE_COLOR);

                JButton deleteButton = new JButton(removeIcon);
                deleteButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                //need to add action
                JButton nameB = new JButton(itemName);
                nameB.setFont(new Font(DefaultUI.TITLE_FONT, Font.BOLD, DefaultUI.TEXT_SIZE - 3));

                JLabel numLabel = new JLabel(itemNum);
                numLabel.setForeground(Color.gray);


                JLabel expiredLabel = new JLabel(word);
                expiredLabel.setForeground(Color.gray);

                JLabel num = new JLabel(itemDate);
                num.setFont(new Font(DefaultUI.TEXT_FONT, Font.BOLD, DefaultUI.TITLE_SIZE));
                num.setForeground(DefaultUI.RED_COLOR);

                JLabel days = new JLabel("  Days");
                days.setForeground(Color.gray);

                itemPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
                itemPanel.add(deleteButton);
                itemPanel.add(nameB);
                itemPanel.add(numLabel);
                itemPanel.add(expiredLabel);
                itemPanel.add(num);
                itemPanel.add(days);
                expired.add(itemPanel);
            }
            infoPanel.add(expired);
        }

//        // 3-3 Expiring Panel
//        JPanel expiring = new JPanel();
//
//        // show title
//        JLabel titleExpiring = new JLabel(" Expiring soon");
//        titleExpiring.setFont(new Font(DefaultUI.TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, DefaultUI.TITLE_SIZE));
//        titleExpiring.setForeground(DefaultUI.TITLE_COLOR);
//        expiring.setBackground(DefaultUI.MAIN_BACKGROUND);
//        expiring.add(titleExpiring, BorderLayout.NORTH);
//        expiring.setBorder(BorderFactory.createLineBorder(DefaultUI.GREEN_THEME));
//
//        // show items
//        Stock stock = new Stock();
//        ArrayList<FoodItem> showinglist = stock.getAlmostExpiredItems(Stock.StockType.ALL);
//        int size = showinglist.size();
//
//        JPanel expiringItemsPanel = new JPanel();
//        expiringItemsPanel.setLayout(new GridLayout(5, 1));
//        expiringItemsPanel.setBackground(DefaultUI.MAIN_BACKGROUND);
//        for (int i = 0; i < size; i++) {
//            JPanel itemPanel = new JPanel(new GridLayout(1, 4));
//            itemPanel.setBackground(DefaultUI.WHITE_COLOR);
//            // a. remove
////            newIcon = new ImageIcon("./icons/delete_g.png");
////            img = newIcon.getImage();
////            newImg = img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
////            removeIcon = new ImageIcon(newImg);
//
//            JButton removeButton = new JButton(removeIcon);
//            removeButton.setActionCommand("remove" + i);
//            removeButton.setOpaque(true);
//            removeButton.setBorderPainted(false);
//            removeButton.addActionListener(this);
//            removeButton.setBackground(DefaultUI.WHITE_COLOR);
//            itemPanel.add(removeButton);
//
//            // b. Name
//            JButton nameButton = new JButton(showinglist.get(i).getName());
//            nameButton.setActionCommand("View" + i);
//            nameButton.setFont(new Font(DefaultUI.TITLE_FONT, Font.BOLD, 13));
//            nameButton.setHorizontalAlignment(SwingConstants.LEFT);
//            nameButton.setOpaque(true);
//            nameButton.setBorderPainted(false);
//            nameButton.setBackground(DefaultUI.WHITE_COLOR);
//            itemPanel.add(nameButton);
//
//            // c. Quantity
//            JLabel qtyLabel = new JLabel(String.valueOf(showinglist.get(i).getQuantity()));
//            qtyLabel.setFont(new Font(DefaultUI.TITLE_FONT, Font.PLAIN, 11));
//            itemPanel.add(qtyLabel);
//
//            // d. Expiration
//            JLabel expirationLabel = new JLabel(showinglist.get(i).getExpirationToString());
//            expirationLabel.setFont(new Font(DefaultUI.TITLE_FONT, Font.PLAIN, 11));
//            itemPanel.add(expirationLabel);
//
//            expiringItemsPanel.add(itemPanel);
//        }
//        expiring.add(expiringItemsPanel);
//
//        infoPanel.add(expiring);

        add(infoPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
        if (actionCommand.startsWith("item")) {
            setVisible(false);
            try {
                AddWindow aNewWindow = new AddWindow();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else
            System.out.println("Unexpected error.");
    }
}