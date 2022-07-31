import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class DefaultUI implements ActionListener {
    public static final int WIDTH = 350;
    public static final int HEIGHT = 750;
    private ActionEvent e;

    // Colors
    public static final Color GREEN_THEME = new Color(122, 156, 87);
    //  public static final Color MAIN_BACKGROUND = new Color(242, 245, 237);
    public static final Color MAIN_BACKGROUND = new Color(255, 255, 255);
    public static final Color MENU_BACKGROUND = new Color(232, 240, 213);
    public static final Color WHITE_COLOR = new Color(255, 255, 255);
    public static final Color TITLE_COLOR = new Color(122, 156, 87);
    public static final Color RED_COLOR = new Color(223, 185, 182);

    // Fonts
    public static final String TITLE_FONT = "San Francisco";
    public static final String TEXT_FONT = "Arial";

    // Sizes
    public static final int TOP_BAR_SIZE = 24;
    public static final int TITLE_SIZE = 18;
    public static final int TEXT_SIZE = 14;
    public static final int MENU_SIZE = 12;
    public static final Font MENU_FORMAT = new Font(TITLE_FONT, Font.PLAIN, MENU_SIZE);
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
        topText.setFont(new Font(TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, TOP_BAR_SIZE));
        topText.setForeground(WHITE_COLOR);
        topPanel.add(topText);
        frame.add(topPanel, BorderLayout.NORTH);
    }

    public String getPanelName() {
        return panelName;
    }

    public void buildBottomIcon(JPanel buttonPanel, String iconPath, String command) {
        if(getPanelName().equals("Notice") && iconPath.equals("expired")){
            iconPath = "expired_g";
        }
        else if(getPanelName().equals("Stock") && iconPath.equals("stock")){
            iconPath = "stock_g";
        }
        else if(getPanelName().equals("Add Items") && iconPath.equals("add")){
            iconPath = "add_g";
        }
        else if(getPanelName().equals("Recipes") && iconPath.equals("recipe")){
            iconPath = "recipe_g";
        }
        ImageIcon addIcon = new ImageIcon("./icons/" + iconPath + ".png");
        Image img = addIcon.getImage();
        Image newImg = img.getScaledInstance(DefaultUI.ICON_SIZE, DefaultUI.ICON_SIZE, Image.SCALE_SMOOTH);
        addIcon = new ImageIcon(newImg);
        JButton addButton = new JButton(addIcon);
        addButton.setActionCommand(command);
        addButton.setBackground(DefaultUI.MENU_BACKGROUND);
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        addButton.addActionListener(this);
        buttonPanel.add(addButton);
    }

    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("add")) {
            frame.setVisible(false);
            try {
                AddWindow aNewWindow = new AddWindow();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (actionCommand.equals("notice")) {
            frame.setVisible(false);
            try {
                WindowNotice aNewWindow = new WindowNotice();
                aNewWindow.setVisible(true);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (actionCommand.equals("recipe")) {
            frame.setVisible(false);
            try {
                WindowRecipe aNewWindow = new WindowRecipe();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (actionCommand.equals("stock")) {
            frame.setVisible(false);
            try {
                StockWindow aNewWindow = new StockWindow();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else
            System.out.println("Unexpected error.");
    }

}
