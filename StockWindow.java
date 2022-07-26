import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

public class StockWindow {
    JFrame frame = new JFrame();

    StockWindow() throws ParseException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,1000);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.setLayout(new BorderLayout());

        // Title
        JLabel label = new JLabel("Here is all your stock:");
        label.setBounds(0,0,200,50);
        frame.add(label, BorderLayout.NORTH);


        // Stocks
        JPanel stockPanel = new JPanel();
        stockPanel.setLayout(new GridLayout(3,1));

        Fridge stock = new Fridge();
        ArrayList<FoodItem> stockList = stock.getStockList();
        ArrayList<FoodItem> expiredList = stock.showExpiredItems();
        ArrayList<FoodItem> almostExpiredList = stock.showAlmostExpiredItems();

        JPanel expiredItems = new JPanel();
        JLabel title1 = new JLabel("----- Expired Items -----\n");
        expiredItems.add(title1);
        for (FoodItem item : expiredList) {
            JLabel itemsText = new JLabel(item.toString());
            expiredItems.add(itemsText);
        }
        stockPanel.add(expiredItems);

        JPanel almostExpiredItems = new JPanel();
        JLabel title2 = new JLabel("----- Items About To Expire-----\n");
        almostExpiredItems.add(title2);
        for (FoodItem item : almostExpiredList) {
            JLabel itemsText = new JLabel(item.toString());
            almostExpiredItems.add(itemsText);
        }
        stockPanel.add(almostExpiredItems);

        JPanel allItems = new JPanel();
        JLabel title3 = new JLabel("----- All Items -----\n");
        allItems.add(title3);
        for (FoodItem item : stockList) {
            JLabel itemsText = new JLabel(item.toString());
            allItems.add(itemsText);
        }
        stockPanel.add(allItems);

        frame.add(stockPanel, BorderLayout.CENTER);
    }
}
