import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class WindowViewALLItems extends JFrame implements ActionListener{
    public static final int WIDTH = 350;
    public static final int HEIGHT = 750;
    Stock.StockType inputType;
    private ActionEvent e;

    public WindowViewALLItems(Stock.StockType type) throws ParseException {
        inputType = type;
        DefaultUI ui = new DefaultUI("Stock", this);
        setVisible(true);

        // 2. Bottom Bar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,4));
        buttonPanel.setBackground(WindowNotice.MENU_BACKGROUND);
        buildBottomIcon(buttonPanel, "add", "add");
        buildBottomIcon(buttonPanel, "expired", "notification");
        buildBottomIcon(buttonPanel, "stock_g", "stock");
        buildBottomIcon(buttonPanel, "recipe", "recipe");
        add(buttonPanel, BorderLayout.SOUTH);

        // 3. Center Items
        Stock stock = new Stock();
        ArrayList<FoodItem> showinglist = stock.getSpecificItems(type);
        int size = showinglist.size();
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(12, 1));
        centerPanel.setBackground(WindowNotice.MAIN_BACKGROUND);

        for (int i = 0; i < size; i++) {
            JPanel itemPanel = new JPanel(new GridLayout(1, 4));
            JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel middle1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel middle2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel rightSide = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            left.setBackground(WindowNotice.WHITE_COLOR);
            middle1.setBackground(WindowNotice.WHITE_COLOR);
            middle2.setBackground(WindowNotice.WHITE_COLOR);
            rightSide.setBackground(WindowNotice.WHITE_COLOR);
//            item.setLayout(new GridLayout(1, 6));

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
            removeButton.setBackground(WindowNotice.WHITE_COLOR);
            left.add(removeButton);

            // b. Icon
            String picType = showinglist.get(i).getType().toString().toLowerCase();
            newIcon = new ImageIcon("./stockIcons/subPanel_" + picType +".png");
            img = newIcon.getImage();
            newImg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            ImageIcon typeIcon = new ImageIcon(newImg);
            JButton typeButton = new JButton(typeIcon);
            typeButton.setOpaque(true);
            typeButton.setBorderPainted(false);
            typeButton.setBackground(WindowNotice.WHITE_COLOR);
            left.add(typeButton);

            // c. Name
            JButton nameButton = new JButton(showinglist.get(i).getName());
            nameButton.setActionCommand("View" + i);
            nameButton.setFont(new Font(WindowNotice.TITLE_FONT, Font.BOLD, 13));
            nameButton.setHorizontalAlignment(SwingConstants.LEFT);
            nameButton.setOpaque(true);
            nameButton.setBorderPainted(false);
            nameButton.setBackground(WindowNotice.WHITE_COLOR);
            middle1.add(nameButton);

            // d. Quantity
            JLabel qtyLabel = new JLabel(String.valueOf(showinglist.get(i).getQuantity()));
            qtyLabel.setFont(new Font(WindowNotice.TITLE_FONT, Font.PLAIN, 11));
            middle2.add(qtyLabel);

            // e. Expiration
            JLabel expirationLabel = new JLabel(showinglist.get(i).getExpirationToString());
            expirationLabel.setFont(new Font(WindowNotice.TITLE_FONT, Font.PLAIN, 11));
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
            editButton.setBackground(WindowNotice.WHITE_COLOR);
            rightSide.add(editButton);

            itemPanel.add(left);
            itemPanel.add(middle1);
            itemPanel.add(middle2);
            itemPanel.add(rightSide);
            centerPanel.add(itemPanel);
        }

        add(centerPanel);
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
                WindowNotice aNewWindow = new WindowNotice();
                aNewWindow.setVisible(true);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (actionCommand.equals("recipe")) {
            setVisible(false);
            try {
                WindowRecipe aNewWindow = new WindowRecipe();
                aNewWindow.setVisible(true);
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
        else if (actionCommand.startsWith("remove")) {
            int removeNum = Integer.parseInt(actionCommand.replaceAll("[\\D]", ""));
            Stock stock = new Stock();
            ArrayList<FoodItem> showinglist = stock.getSpecificItems(inputType);
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

        }
        else if (actionCommand.startsWith("edit")) {
            int editNum = Integer.parseInt(actionCommand.replaceAll("[\\D]", ""));
            Stock stock = new Stock();
            ArrayList<FoodItem> showinglist = stock.getSpecificItems(inputType);
            FoodItem editItem = showinglist.get(editNum);

            setVisible(false);
            // 这个window还没写，用于edit指定item。写好后把下面代码解除注释，即可使用。
            // WindowViewALLItems window = new WindowViewALLItems(editItem);
            // window.setVisible(true);
        }
        else if (actionCommand.startsWith("view")) {
            int viewNum = Integer.parseInt(actionCommand.replaceAll("[\\D]", ""));
            Stock stock = new Stock();
            ArrayList<FoodItem> showinglist = stock.getSpecificItems(inputType);
            FoodItem viewItem = showinglist.get(viewNum);

            setVisible(false);
            // 这个window还没写，用于查看指定item的详情。写好后把下面代码解除注释，即可使用。
            // WindowItemDetails window = new WindowItemDetails(viewItem);
            // window.setVisible(true);
        }
        else
            System.out.println("Unexpected error.");
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

}