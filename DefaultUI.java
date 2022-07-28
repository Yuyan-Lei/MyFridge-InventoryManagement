import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class DefaultUI  {
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

    public DefaultUI(String title, JFrame frame) throws ParseException {
        frame.setTitle("My Fridge");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        setTopPanel(title, frame);
    }

    public void setTopPanel(String title, JFrame frame){
        JPanel topPanel = new JPanel();
        topPanel.setBackground(GREEN_THEME);
        JLabel topText = new JLabel(title);
        topText.setFont(new Font(TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, TOP_BAR_SIZE));
        topText.setForeground(WHITE_COLOR);
        topPanel.add(topText);
        frame.add(topPanel, BorderLayout.NORTH);
    }

    public void setMenuButtonPanel(String page, JFrame frame){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,4));
        buttonPanel.setBackground(MENU_BACKGROUND);

        ImageIcon addIcon = new ImageIcon("./icons/add.png");
        Image img = addIcon.getImage();
        Image newImg = img.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        addIcon = new ImageIcon(newImg);
        JButton addButton = new JButton(addIcon);
        addButton.setActionCommand("add");
        addButton.setBackground(MENU_BACKGROUND);
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
//        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        ImageIcon notificationIcon = new ImageIcon("./icons/expired_g.png");
        img = notificationIcon.getImage();
        newImg = img.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        notificationIcon = new ImageIcon(newImg);
        JButton notificationButton = new JButton(notificationIcon);
        notificationButton.setActionCommand("notification");
        notificationButton.setBackground(MENU_BACKGROUND);
        notificationButton.setOpaque(true);
        notificationButton.setBorderPainted(false);
//        notificationButton.addActionListener(this);
        buttonPanel.add(notificationButton);

        ImageIcon viewIcon = new ImageIcon("./icons/stock.png");
        img = viewIcon.getImage();
        newImg = img.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        viewIcon = new ImageIcon(newImg);
        JButton viewButton = new JButton(viewIcon);
        viewButton.setActionCommand("stock");
        viewButton.setBackground(MENU_BACKGROUND);
        viewButton.setOpaque(true);
        viewButton.setBorderPainted(false);
//        viewButton.addActionListener(this);
        buttonPanel.add(viewButton);

        ImageIcon recipeIcon = new ImageIcon("./icons/recipe.png");
        img = recipeIcon.getImage();
        newImg = img.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
        recipeIcon = new ImageIcon(newImg);
        JButton recipeButton = new JButton(recipeIcon);
        recipeButton.setActionCommand("recipe");
        recipeButton.setBackground(MENU_BACKGROUND);
        recipeButton.setOpaque(true);
        recipeButton.setBorderPainted(false);
//        recipeButton.addActionListener(this);
        buttonPanel.add(recipeButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

    }

//    public void actionPerformed(ActionEvent e){
//        String actionCommand = e.getActionCommand();
//        if (actionCommand.equals("add")) {
//            setVisible(false); //can keep the new window opened only (looks like close the previous window)
//            AddWindow aNewWindow = new AddWindow();
//        }
//        else if (actionCommand.equals("stock")) {
//            setVisible(false);
//            try {
//                StockWindow aNewWindow = new StockWindow();
//            } catch (ParseException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//        else if (actionCommand.equals("recipe")) {
//            setVisible(false);
//            RecipeWindow aNewWindow = new RecipeWindow();
//        }
//        else
//            System.out.println("Unexpected error.");
//    }

    public void setLabel(){

    }
}
