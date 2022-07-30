import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

public class WindowRecipe extends JFrame implements ActionListener {
    private ArrayList<RecipeItem> recipeList;

    WindowRecipe() throws ParseException {
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
        Stock stock = new Stock();
        ArrayList<String> ingredients = stock.getIngredientList();
        RecipeDatabase recipeDatabase = new RecipeDatabase();
        recipeList = recipeDatabase.getSpecificRecipeList(ingredients, 10);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(10, 1));

        for (int i = 0; i < 10; i++) {
            JPanel thePanel = new JPanel(new BorderLayout());
            thePanel.setBackground(DefaultUI.WHITE_COLOR);

            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new GridLayout(2, 1));
            itemPanel.setBackground(DefaultUI.WHITE_COLOR);

            // Recipe Title
            JLabel nameLabel = new JLabel(capitalizeString(recipeList.get(i).getName()));
            nameLabel.setFont(new Font(WindowNotice.TITLE_FONT, Font.BOLD, 13));
            nameLabel.setForeground(DefaultUI.GREEN_THEME);
            nameLabel.setBackground(WindowNotice.WHITE_COLOR);
            itemPanel.add(nameLabel);
            itemPanel.setBackground(DefaultUI.WHITE_COLOR);

            // Recipe Ingredients
            JLabel ingredientsLabel = new JLabel();
            StringBuilder ingredientsText = new StringBuilder();
            for (String text : recipeList.get(i).getIngredient()) {
                ingredientsText.append(text);
                ingredientsText.append(", ");
            }
            ingredientsText.setLength(ingredientsText.length() - 2);
            ingredientsLabel.setText(capitalizeString(ingredientsText.toString()));
            ingredientsLabel.setFont(new Font(WindowNotice.TITLE_FONT, Font.PLAIN, 11));
            ingredientsLabel.setBackground(DefaultUI.WHITE_COLOR);
            itemPanel.add(ingredientsLabel);

            // Open Button
            // Build an Open Button
            ImageIcon openIcon = new ImageIcon("./stockIcons/open.png");
            Image img = openIcon.getImage();
            Image newImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            openIcon = new ImageIcon(newImg);

            JButton openButton = new JButton(openIcon);
            openButton.setActionCommand("Recipe" + i);
            openButton.setOpaque(true);
            openButton.setBorderPainted(false);
            openButton.addActionListener(this);
            openButton.setBackground(WindowNotice.WHITE_COLOR);

            // Recipe Icon
            ImageIcon leftIcon = new ImageIcon("icons/recipe_list.png");
            img = leftIcon.getImage();
            newImg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            leftIcon = new ImageIcon(newImg);
            JLabel leftLable = new JLabel(leftIcon);
            leftLable.setBorder(new EmptyBorder(0, 10, 0 ,10));

            thePanel.setBorder(BorderFactory.createLineBorder(DefaultUI.MENU_BACKGROUND));
            thePanel.add(leftLable, BorderLayout.WEST);
            thePanel.add(itemPanel, BorderLayout.CENTER);
            thePanel.add(openButton, BorderLayout.EAST);
            centerPanel.add(thePanel);
        }

        centerPanel.setBackground(WindowNotice.MAIN_BACKGROUND);
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
                aNewWindow.setVisible(true);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (actionCommand.startsWith("Recipe")) {
            int recipeNum = Integer.parseInt(actionCommand.replaceAll("[\\D]", ""));
            RecipeItem recipeToOpen = recipeList.get(recipeNum);
            WindowRecipeDetails newWindow = null;
            try {
                newWindow = new WindowRecipeDetails(recipeToOpen);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            this.setVisible(false);
            newWindow.setVisible(true);
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

    private String capitalizeString(String s) {
        String lower = s.toLowerCase();
        return lower.substring(0, 1).toUpperCase() + lower.substring(1);
    }
}
