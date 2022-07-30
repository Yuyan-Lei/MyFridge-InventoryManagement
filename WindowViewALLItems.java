import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class WindowViewALLItems extends JFrame implements ActionListener {
    private Stock.StockType inputType;
    private ArrayList<FoodItem> showinglist;
    private ActionEvent e;

    public WindowViewALLItems(Stock.StockType type) throws ParseException {
        inputType = type;
        DefaultUI ui = new DefaultUI("Stock", this);
        setVisible(true);

        // Center Items
        Stock stock = new Stock();
        showinglist = stock.getSpecificItems(type);
        int size = showinglist.size();
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(12, 1));
        centerPanel.setBackground(DefaultUI.MAIN_BACKGROUND);

        for (int i = 0; i < size; i++) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            JPanel left = new JPanel();
            JPanel middle = new JPanel(new GridLayout(1, 2));
            JPanel middle1 = new JPanel();
            JPanel middle2 = new JPanel();
            JPanel rightSide = new JPanel();
            left.setBackground(DefaultUI.WHITE_COLOR);
            middle.setBackground(DefaultUI.WHITE_COLOR);
            middle1.setBackground(DefaultUI.WHITE_COLOR);
            middle2.setBackground(DefaultUI.WHITE_COLOR);
            rightSide.setBackground(DefaultUI.WHITE_COLOR);

            // a. remove
            ImageIcon newIcon = new ImageIcon("./icons/delete_g.png");
            Image img = newIcon.getImage();
            Image newImg = img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon removeIcon = new ImageIcon(newImg);

            JButton removeButton = new JButton(removeIcon);
            removeButton.setActionCommand("remove" + i);
            removeButton.setOpaque(true);
            removeButton.setBorderPainted(false);
            removeButton.addActionListener(this);
            removeButton.setBackground(DefaultUI.WHITE_COLOR);
            left.add(removeButton);

            // b. Icon
            String picType = showinglist.get(i).getType().toString().toLowerCase();
            newIcon = new ImageIcon("./stockIcons/subPanel_" + picType + ".png");
            img = newIcon.getImage();
            newImg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon typeIcon = new ImageIcon(newImg);
            JButton typeButton = new JButton(typeIcon);
            typeButton.setOpaque(true);
            typeButton.setBorderPainted(false);
            typeButton.setBackground(DefaultUI.WHITE_COLOR);
            left.add(typeButton);

            // c. Name
            JButton nameButton = new JButton(showinglist.get(i).getName());
            nameButton.setActionCommand("view" + i);
            nameButton.setFont(new Font(DefaultUI.TITLE_FONT, Font.BOLD, 13));
            nameButton.setOpaque(true);
            nameButton.setBorderPainted(false);
            nameButton.addActionListener(this);
            nameButton.setBackground(DefaultUI.WHITE_COLOR);
            middle1.add(nameButton);

            // d. Quantity
            JLabel qtyLabel = new JLabel(String.valueOf(showinglist.get(i).getQuantity()));
            qtyLabel.setFont(new Font(DefaultUI.TITLE_FONT, Font.PLAIN, 11));
            middle2.add(qtyLabel);

            // e. Expiration
            JLabel expirationLabel = new JLabel(showinglist.get(i).getExpirationToString());
            expirationLabel.setFont(new Font(DefaultUI.TITLE_FONT, Font.PLAIN, 11));
            middle2.add(expirationLabel);

            // f. Edit
            newIcon = new ImageIcon("./icons/edit_g.png");
            img = newIcon.getImage();
            newImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            ImageIcon editIcon = new ImageIcon(newImg);

            JButton editButton = new JButton(editIcon);
            editButton.setActionCommand("edit" + i);
            editButton.setOpaque(true);
            editButton.setBorderPainted(false);
            editButton.addActionListener(this);
            editButton.setBackground(DefaultUI.WHITE_COLOR);
            rightSide.add(editButton);

            itemPanel.add(left, BorderLayout.WEST);
            middle.add(middle1);
            middle.add(middle2);
            itemPanel.add(middle, BorderLayout.CENTER);
            itemPanel.add(rightSide, BorderLayout.EAST);
            centerPanel.add(itemPanel);
        }

        add(centerPanel);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.startsWith("remove")) {
            int removeNum = Integer.parseInt(actionCommand.replaceAll("[\\D]", ""));
            Stock stock = new Stock();
            try {
                stock.removeItem(showinglist.get(removeNum));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            setVisible(false);
            WindowViewALLItems window = null;
            try {
                window = new WindowViewALLItems(inputType);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            window.setVisible(true);

        } else if (actionCommand.startsWith("edit")) {
            int editNum = Integer.parseInt(actionCommand.replaceAll("[\\D]", ""));
            FoodItem editItem = showinglist.get(editNum);

            setVisible(false);
            // 这个window还没写，用于edit指定item。写好后把下面代码解除注释，即可使用。
            // WindowEditItem window = new WindowEditItem(editItem);
            // window.setVisible(true);
        } else if (actionCommand.startsWith("view")) {
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
        } else
            System.out.println("Unexpected error.");
    }
}