package window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.text.ParseException;
import model.RecipeItem;

public class RecipeDetailsWindow extends JFrame{
    RecipeDetailsWindow(RecipeItem recipeToOpen) throws ParseException {
        new DefaultUI("Recipes", this);
        setVisible(true);

        // Center Items
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(DefaultUI.WHITE_COLOR);

        // a. Recipe name - top
        JTextPane nameLabel = new JTextPane();
        nameLabel.setEditable(false);
        nameLabel.setMargin(new Insets(25, 15, 25, 15));
        StyledDocument nameDoc = nameLabel.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        nameDoc.setParagraphAttributes(0, nameDoc.getLength(), center, false);
        try {
            nameDoc.insertString(nameDoc.getLength(), recipeToOpen.getName(), center);
        } catch (BadLocationException e) {
            System.out.println(e.getMessage());
        }
        nameLabel.setFont(new Font(DefaultUI.TITLE_FONT, Font.BOLD, 15));
        nameLabel.setForeground(DefaultUI.GREEN_THEME);
        nameLabel.setBackground(DefaultUI.WHITE_COLOR);

        // b. Other information - Center
        JPanel servePanel = subPanel(" Servings", "serving", recipeToOpen.getServing());
        JPanel timePanel = subPanel(" Cook Time", "cookTime", recipeToOpen.getCookTime());
        JPanel ingredientPanel = subPanel(" Ingredients", "ingredients", recipeToOpen.getDetailsOfIngredient());
        JPanel methodPanel = subPanel(" Instructions", "methods", recipeToOpen.getMethod().replaceAll(": ", ": \n"));

        centerPanel.add(nameLabel);
        centerPanel.add(servePanel);
        centerPanel.add(timePanel);
        centerPanel.add(ingredientPanel);
        centerPanel.add(methodPanel);

        add(centerPanel);
    }

    private JPanel subPanel(String title, String iconPath, String text) {
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        subPanel.setBackground(DefaultUI.WHITE_COLOR);

        // 1. North title part
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(DefaultUI.WHITE_COLOR);

        // a. Add icon
        ImageIcon newIcon = new ImageIcon("./icons/recipeIcons/" + iconPath +".png");
        Image img = newIcon.getImage();
        Image newImg = img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        newIcon = new ImageIcon(newImg);
        JLabel newLabel = new JLabel(newIcon);
        newLabel.setBorder(new EmptyBorder(0, 10, 0 ,0));
        titlePanel.add(newLabel, BorderLayout.WEST);

        // b. Add Title
        JTextArea nameLabel = new JTextArea(title);
        nameLabel.setFont(new Font(DefaultUI.TITLE_FONT, Font.BOLD, 13));
        nameLabel.setForeground(DefaultUI.GREEN_THEME);
        nameLabel.setBackground(DefaultUI.WHITE_COLOR);
        titlePanel.add(nameLabel, BorderLayout.CENTER);

        subPanel.add(titlePanel, BorderLayout.NORTH);

        // 2. Center Text Field Part
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setMargin(new Insets(10, 28, 25, 15));
        StyledDocument textDoc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
        textDoc.setParagraphAttributes(0, textDoc.getLength(), center, false);
        try {
            textDoc.insertString(textDoc.getLength(), text, center);
        } catch (BadLocationException e) {
            System.out.println(e.getMessage());
        }

        if (title.equals(" Instructions")) {
            textPane.setCaretPosition(0);
            JScrollPane scrollPane = new JScrollPane(textPane);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            subPanel.add(scrollPane, BorderLayout.CENTER);
        } else {
            subPanel.add(textPane);
        }
        return subPanel;
    }
}
