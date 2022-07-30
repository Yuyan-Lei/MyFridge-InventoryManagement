import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;


public class WindowItemDetails extends JFrame implements ActionListener {

    WindowItemDetails(FoodItem foodToView) throws ParseException {
        DefaultUI ui = new DefaultUI("Recipes", this);
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
        add(buttonPanel, BorderLayout.SOUTH);

        // 3. Center Items
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 1));
        centerPanel.setBackground(DefaultUI.WHITE_COLOR);

        // a. Recipe name - top
        JTextPane nameLabel = new JTextPane();
        nameLabel.setMargin(new Insets(75, 15, 15, 15));
        StyledDocument nameDoc = nameLabel.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        nameDoc.setParagraphAttributes(0, nameDoc.getLength(), center, false);
        try {
            nameDoc.insertString(nameDoc.getLength(), foodToView.getName(), center);
        } catch (BadLocationException e) {
            System.out.println(e.getMessage());
        }
        nameLabel.setFont(new Font(WindowNotice.TITLE_FONT, Font.BOLD, 15));
        nameLabel.setForeground(DefaultUI.GREEN_THEME);
        nameLabel.setBackground(WindowNotice.WHITE_COLOR);

        centerPanel.add(nameLabel);

        // b. Other information - Center
        subPanel(centerPanel, " Quantity", "quantity", String.valueOf(foodToView.getQuantity()) + " items");
        subPanel(centerPanel, " Expiration", "expiration", foodToView.getExpirationToString());
        subPanel(centerPanel, " Type", "type", foodToView.getTypeToString());
        subPanel(centerPanel, " Location", "location", foodToView.getLocationToString());
        String urlText = "Whole Foods: \n" + foodToView.getWFURL();
        urlText += "\n\nAmazon Fresh: \n" + foodToView.getAFURL();
        subPanel(centerPanel, "Order Online", "url", urlText);
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
        else if (actionCommand.equals("stock")) {
            setVisible(false);
            try {
                StockWindow aNewWindow = new StockWindow();
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

    public void subPanel(JPanel thePanel, String title, String iconPath, String text) {
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        subPanel.setBackground(DefaultUI.WHITE_COLOR);

        // 1. North title part
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(DefaultUI.WHITE_COLOR);

        // a. Add icon
        ImageIcon newIcon = new ImageIcon("./itemIcons/" + iconPath +".png");
        Image img = newIcon.getImage();
        Image newImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        newIcon = new ImageIcon(newImg);
        JLabel newLabel = new JLabel(newIcon);
        newLabel.setBorder(new EmptyBorder(0, 10, 0 ,0));
        titlePanel.add(newLabel, BorderLayout.WEST);

        // b. Add Title
        JTextArea nameLabel = new JTextArea(title);
        nameLabel.setFont(new Font(WindowNotice.TITLE_FONT, Font.BOLD, 13));
        nameLabel.setForeground(DefaultUI.GREEN_THEME);
        nameLabel.setBackground(WindowNotice.WHITE_COLOR);
        titlePanel.add(nameLabel, BorderLayout.CENTER);

        subPanel.add(titlePanel, BorderLayout.NORTH);

        // 2. Center Text Field Part
        JTextPane textPane = new JTextPane();
        textPane.setMargin(new Insets(10, 34, 0, 15));
        StyledDocument textDoc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
        textDoc.setParagraphAttributes(0, textDoc.getLength(), center, false);
        try {
            textDoc.insertString(textDoc.getLength(), text, center);
        } catch (BadLocationException e) {
            System.out.println(e.getMessage());
        }
        subPanel.add(textPane, BorderLayout.CENTER);

        thePanel.add(subPanel);
    }


    public static void main(String[] args) throws ParseException {
        FoodItem item = new FoodItem(0, "Apples", 9, new Date(), FoodItem.FoodType.FRUIT, FoodItem.PlaceLocation.FROZEN);
        WindowItemDetails newWindow = new WindowItemDetails(item);
        newWindow.setVisible(true);
    }
}