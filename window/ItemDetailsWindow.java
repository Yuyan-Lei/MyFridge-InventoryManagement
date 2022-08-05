package window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Objects;
import model.FoodItem;


public class ItemDetailsWindow extends JFrame implements ActionListener {
    private final String urlWholeFoods;
    private final String urlAmazonFresh;
    FoodItem theItem;

    ItemDetailsWindow(FoodItem foodToView) throws ParseException {
        new DefaultUI("Item Details", this);
        setVisible(true);
        theItem = foodToView;

        // Center Items
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(DefaultUI.WHITE_COLOR);

        // a. Item name - top
        JTextPane nameLabel = new JTextPane();
        nameLabel.setEditable(false);
        nameLabel.setMargin(new Insets(25, 15, 0, 15));
        StyledDocument nameDoc = nameLabel.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        nameDoc.setParagraphAttributes(0, nameDoc.getLength(), center, false);
        try {
            nameDoc.insertString(nameDoc.getLength(), foodToView.getName(), center);
        } catch (BadLocationException e) {
            System.out.println(e.getMessage());
        }
        nameLabel.setFont(new Font(DefaultUI.TITLE_FONT, Font.BOLD, 15));
        nameLabel.setForeground(DefaultUI.GREEN_THEME);
        nameLabel.setBackground(DefaultUI.WHITE_COLOR);

        centerPanel.add(nameLabel);

        // b. Other information - Center
        subPanel(centerPanel, " Quantity", "quantity", foodToView.getQuantity() + " items");
        subPanel(centerPanel, " Expiration", "expiration", foodToView.getExpirationToString());
        subPanel(centerPanel, " Type", "type", foodToView.getTypeToString());
        subPanel(centerPanel, " Location", "location", foodToView.getLocationToString());
        urlWholeFoods = foodToView.getWFURL();
        urlAmazonFresh = foodToView.getAFURL();
        subPanel(centerPanel, "Order Online", "url", "url");
        add(centerPanel);

        // c. Edit
        JPanel editButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(this);
        editButtonPanel.add(editButton);
        editButton.setBorderPainted(false);
        editButton.setOpaque(true);
        editButton.setBackground(DefaultUI.GREEN_THEME);
        editButton.setForeground(DefaultUI.WHITE_COLOR);
        editButtonPanel.setBackground(DefaultUI.WHITE_COLOR);
        centerPanel.add(editButtonPanel);
    }

    private void subPanel(JPanel thePanel, String title, String iconPath, String text) {
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        subPanel.setBackground(DefaultUI.WHITE_COLOR);

        // 1. North title part
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(DefaultUI.WHITE_COLOR);

        // a. Add icon
        ImageIcon newIcon = new ImageIcon("./icons/itemIcons/" + iconPath +".png");
        Image img = newIcon.getImage();
        Image newImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        newIcon = new ImageIcon(newImg);
        JLabel newLabel = new JLabel(newIcon);
        newLabel.setBorder(new EmptyBorder(0, 10, 0 ,0));
        titlePanel.add(newLabel, BorderLayout.WEST);

        // b. Add Title
        JTextArea nameLabel = new JTextArea(title);
        nameLabel.setEditable(false);
        nameLabel.setFont(new Font(DefaultUI.TITLE_FONT, Font.BOLD, 13));
        nameLabel.setForeground(DefaultUI.GREEN_THEME);
        nameLabel.setBackground(DefaultUI.WHITE_COLOR);
        titlePanel.add(nameLabel, BorderLayout.CENTER);

        subPanel.add(titlePanel, BorderLayout.NORTH);

        // 2. Center Text Field Part
        if (!Objects.equals(text, "url")) {
            subPanel.add(addJPane(text), BorderLayout.CENTER);
        }
        // Hyperlink
        else {
            JPanel newPanel = new JPanel();
            newPanel.setEnabled(false);
            newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
            newPanel.setBackground(Color.WHITE);
            JTextPane wholefoodsLink = addLink(urlWholeFoods);
            wholefoodsLink.setEditable(false);
            JTextPane amazonLink = addLink(urlAmazonFresh);
            amazonLink.setEditable(false);
            newPanel.add(addJPane("1. Order on Whole Foods: "));
            newPanel.add(wholefoodsLink);
            newPanel.add(addJPane("2. Order on Amazon Fresh:"));
            newPanel.add(amazonLink);
            newPanel.add(addJPane("\n\n\n"));
            subPanel.add(newPanel, BorderLayout.CENTER);
        }

        thePanel.add(subPanel);
    }

    private JTextPane addJPane(String text) {
        JTextPane textPane = new JTextPane();
        textPane.setMargin(new Insets(10, 34, 0, 15));
        textPane.setEditable(false);
        StyledDocument textDoc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
        textDoc.setParagraphAttributes(0, textDoc.getLength(), center, false);
        try {
            textDoc.insertString(textDoc.getLength(), text, center);
        } catch (BadLocationException e) {
            System.out.println(e.getMessage());
        }
        return textPane;
    }

    private JTextPane addLink(String url) {
        JTextPane hyperLink = addJPane("    Click here");
        hyperLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hyperLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                hyperLink.setText("    Click here");
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                hyperLink.setText(url);
            }
        });
        return hyperLink;
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Edit")) {
            setVisible(false);
            EditWindow newWindow;
            try {
                newWindow = new EditWindow(theItem);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            newWindow.setVisible(true);
        }
        else {
            System.out.println("Unexpected error.");
        }
    }
}