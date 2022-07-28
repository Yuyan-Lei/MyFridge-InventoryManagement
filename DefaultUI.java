import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;

public class DefaultUI extends JFrame implements GUI{
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


    public DefaultUI() throws ParseException {
        super();
        setTitle("MY Fridge");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(GREEN_THEME);
        JLabel topText = new JLabel();
        topText.setFont(new Font(TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, TOP_BAR_SIZE));
        topText.setForeground(WHITE_COLOR);
        topPanel.add(topText);
        add(topPanel, BorderLayout.NORTH);
    }

    public DefaultUI(String title) throws ParseException {
        super();
        setTitle("MY Fridge");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(GREEN_THEME);
        JLabel topText = new JLabel(title);
        topText.setFont(new Font(TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, TOP_BAR_SIZE));
        topText.setForeground(WHITE_COLOR);
        topPanel.add(topText);
        add(topPanel, BorderLayout.NORTH);

//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new GridLayout(1,4));
//        buttonPanel.setBackground(MENU_BACKGROUND);
//
//        ImageIcon addIcon = new ImageIcon("./icons/add.png");
//        Image img = addIcon.getImage();
//        Image newImg = img.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
//        addIcon = new ImageIcon(newImg);
//        JButton addButton = new JButton(addIcon);
//        addButton.setActionCommand("add");
//        addButton.setBackground(MENU_BACKGROUND);
//        addButton.setOpaque(true);
//        addButton.setBorderPainted(false);
////        addButton.addActionListener(this);
//        buttonPanel.add(addButton);
//
//        ImageIcon notificationIcon = new ImageIcon("./icons/expired_g.png");
//        img = notificationIcon.getImage();
//        newImg = img.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
//        notificationIcon = new ImageIcon(newImg);
//        JButton notificationButton = new JButton(notificationIcon);
//        notificationButton.setActionCommand("notification");
//        notificationButton.setBackground(MENU_BACKGROUND);
//        notificationButton.setOpaque(true);
//        notificationButton.setBorderPainted(false);
////        notificationButton.addActionListener(this);
//        buttonPanel.add(notificationButton);
//
//        ImageIcon viewIcon = new ImageIcon("./icons/stock.png");
//        img = viewIcon.getImage();
//        newImg = img.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
//        viewIcon = new ImageIcon(newImg);
//        JButton viewButton = new JButton(viewIcon);
//        viewButton.setActionCommand("stock");
//        viewButton.setBackground(MENU_BACKGROUND);
//        viewButton.setOpaque(true);
//        viewButton.setBorderPainted(false);
////        viewButton.addActionListener(this);
//        buttonPanel.add(viewButton);
//        add(buttonPanel);
    }

//    public DefaultUI(JFrame frame) throws ParseException {
//        frame = new JFrame();
//        frame.setSize(WIDTH, HEIGHT);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());
//    }

    public void setTopPanel(String title){
//        JPanel topPanel = new JPanel();
//        topPanel.setBackground(GREEN_THEME);
//        JLabel topText = new JLabel(title);
//        topText.setFont(new Font(TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, TOP_BAR_SIZE));
//        topText.setForeground(WHITE_COLOR);
//        topPanel.add(topText);
//        frame.add(topPanel, BorderLayout.NORTH);
    }

    public void setButtonPanel(){

    }

    public void setLabel(){

    }
}
