package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class DefaultUI implements ActionListener {
    public static final int WIDTH = 350;
    public static final int HEIGHT = 750;

    // Colors
    public static final Color GREEN_THEME = new Color(122, 156, 87);
    //  public static final Color MAIN_BACKGROUND = new Color(242, 245, 237);
    public static final Color MAIN_BACKGROUND = new Color(255, 255, 255);
    public static final Color MENU_BACKGROUND = new Color(232, 240, 213);
    public static final Color WHITE_COLOR = new Color(255, 255, 255);
    public static final Color TITLE_COLOR = new Color(122, 156, 87);
    public static final Color RED_COLOR = new Color(226, 136, 129);

    // Fonts
    public static final String TITLE_FONT = "San Francisco";
    public static final String TEXT_FONT = "Arial";

    // Sizes
    public static final int TOP_BAR_SIZE = 24;
    public static final int TITLE_SIZE = 18;
    public static final int TEXT_SIZE = 14;
    public static final int ICON_SIZE = 44;

    private JFrame frame;
    private String panelName;
    public DefaultUI(String title, JFrame frame) throws ParseException {
        this.frame = frame;
        frame.setTitle("My Fridge");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        setTopPanel(title, frame);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,4));
        buttonPanel.setBackground(DefaultUI.MENU_BACKGROUND);

        buildBottomIcon(buttonPanel, "add", "add");
        buildBottomIcon(buttonPanel, "expired", "notice");
        buildBottomIcon(buttonPanel, "stock", "stock");
        buildBottomIcon(buttonPanel, "recipe", "recipe");

        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setTopPanel(String title, JFrame frame){
        this.panelName = title;
        JPanel topPanel = new JPanel();
        topPanel.setBackground(GREEN_THEME);
        JLabel topText = new JLabel(title);
        topText.setFont(new Font(TITLE_FONT, Font.PLAIN, TOP_BAR_SIZE));
        topText.setForeground(WHITE_COLOR);
        topPanel.add(topText);
        frame.add(topPanel, BorderLayout.NORTH);
    }

    public String getPanelName() {
        return panelName;
    }

    public void buildBottomIcon(JPanel buttonPanel, String iconPath, String command) {
        if(getPanelName().equals("Notice") && iconPath.equals("expired")){
            iconPath = "expired_green";
        }
        else if(getPanelName().equals("Stock") && iconPath.equals("stock")){
            iconPath = "stock_green";
        }
        else if(getPanelName().equals("Add Items") && iconPath.equals("add")){
            iconPath = "add_green";
        }
        else if(getPanelName().equals("Recipes") && iconPath.equals("recipe")){
            iconPath = "recipe_green";
        }
        ImageIcon addIcon = new ImageIcon("./icons/basicIcons/" + iconPath + ".png");
        Image img = addIcon.getImage();
        Image newImg = img.getScaledInstance(DefaultUI.ICON_SIZE, DefaultUI.ICON_SIZE, Image.SCALE_SMOOTH);
        addIcon = new ImageIcon(newImg);
        JButton addButton = new JButton(addIcon);
        addButton.setActionCommand(command);
        addButton.setBackground(DefaultUI.MENU_BACKGROUND);
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        addButton.addActionListener(this);
        addButton.setFocusPainted(false);
        buttonPanel.add(addButton);
    }

    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "add" -> {
                frame.setVisible(false);
                try {
                    AddWindow aNewWindow = new AddWindow();
                    aNewWindow.setVisible(true);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case "notice" -> {
                frame.setVisible(false);
                try {
                    NoticeWindow aNewWindow = new NoticeWindow();
                    aNewWindow.setVisible(true);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case "recipe" -> {
                frame.setVisible(false);
                try {
                    RecipeWindow aNewWindow = new RecipeWindow();
                    aNewWindow.setVisible(true);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case "stock" -> {
                frame.setVisible(false);
                try {
                    StockWindow aNewWindow = new StockWindow();
                    aNewWindow.setVisible(true);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
            default -> System.out.println("Unexpected error.");
        }
    }

}
