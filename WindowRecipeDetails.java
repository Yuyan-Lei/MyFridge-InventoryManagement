import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import static java.awt.BorderLayout.NORTH;


public class WindowRecipeDetails extends JFrame implements ActionListener {

    WindowRecipeDetails(RecipeItem recipeToOpen) throws ParseException {
        DefaultUI ui = new DefaultUI("Recipes", this);
        setVisible(true);

        // 2. Bottom Bar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,4));
        buttonPanel.setBackground(WindowNotice.MENU_BACKGROUND);
        buildBottomIcon(buttonPanel, "add", "add");
        buildBottomIcon(buttonPanel, "expired", "notification");
        buildBottomIcon(buttonPanel, "stock", "stock");
        buildBottomIcon(buttonPanel, "recipe_g", "recipe");
        add(buttonPanel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // 3. Center Items
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(DefaultUI.WHITE_COLOR);

        // a. Recipe name - top
        JLabel nameLabel = new JLabel(recipeToOpen.getName());
        nameLabel.setFont(new Font(WindowNotice.TITLE_FONT, Font.BOLD, 15));
        nameLabel.setForeground(DefaultUI.GREEN_THEME);
        nameLabel.setBackground(WindowNotice.WHITE_COLOR);

        // b. Serving - middle
        JLabel servingLabel = new JLabel(recipeToOpen.getServing());
        // c. Cook Time - middle
        JLabel cookTime = new JLabel(recipeToOpen.getCookTime());
        // d. Ingredient details - middle
        JLabel ingredientLabel = new JLabel(recipeToOpen.getDetailsOfIngredient());
        // e. Methods - bottom
        JLabel methodLabel = new JLabel(recipeToOpen.getMethod());

        centerPanel.add(nameLabel);
        centerPanel.add(servingLabel);
        centerPanel.add(cookTime);
        centerPanel.add(ingredientLabel);
        centerPanel.add(methodLabel);

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
}
