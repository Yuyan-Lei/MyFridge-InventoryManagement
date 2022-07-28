import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;


public class AddWindow extends JFrame implements ActionListener {
    JFrame frame = new JFrame();
    private JTextField name;
    private JTextField quantity;
    private JTextField expirationDate;

    private JComboBox typeOption, locationOption;
    public static final Color GREEN_THEME = new Color(122, 156, 87);
    public static final Color WHITE_COLOR = new Color(255, 255, 255);
    public static final String TITLE_FONT = "San Francisco";
    public static final int WIDTH = 350;
    public static final int HEIGHT = 750;
    public static final Color MENU_BACKGROUND = new Color(232, 240, 213);
    public static final int TOP_BAR_SIZE = 24;

    String types[] = { "VEGETABLE", "MEAT", "FRUIT", "DRINK", "OTHER"};
    String locations[] = {"FROZEN","REFRIGERATED" };
    AddWindow() throws ParseException {
        //super("My Fridge"); //title name
        DefaultUI ui = new DefaultUI("Add/Edit Items", this);
//        frame.setSize(WIDTH, HEIGHT);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());
        setVisible(true);

        // 1. Top bar
//        JPanel topPanel = new JPanel();
//        topPanel.setBackground(GREEN_THEME);
//        JLabel topText = new JLabel("Add/Edit Items");
//        topText.setFont(new Font(TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, TOP_BAR_SIZE));
//        topText.setForeground(WHITE_COLOR);
//        topPanel.add(topText);
//        frame.add(topPanel, BorderLayout.NORTH);

        // 2. Bottom Bar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,4));
        buttonPanel.setBackground(FridgeGUIwithAction.MENU_BACKGROUND);

        ImageIcon addIcon = new ImageIcon("./icons/add.png");
        Image img = addIcon.getImage();
        Image newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        addIcon = new ImageIcon(newImg);
        JButton addButton = new JButton(addIcon);
        addButton.setActionCommand("add");
        addButton.setBackground(FridgeGUIwithAction.MENU_BACKGROUND);
        addButton.setOpaque(true);
        addButton.setBorderPainted(false);
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        ImageIcon notificationIcon = new ImageIcon("./icons/expired.png");
        img = notificationIcon.getImage();
        newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        notificationIcon = new ImageIcon(newImg);
        JButton notificationButton = new JButton(notificationIcon);
        notificationButton.setActionCommand("notification");
        notificationButton.setBackground(FridgeGUIwithAction.MENU_BACKGROUND);
        notificationButton.setOpaque(true);
        notificationButton.setBorderPainted(false);
        notificationButton.addActionListener(this);
        buttonPanel.add(notificationButton);

        ImageIcon viewIcon = new ImageIcon("./icons/stock_g.png");
        img = viewIcon.getImage();
        newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        viewIcon = new ImageIcon(newImg);
        JButton viewButton = new JButton(viewIcon);
        viewButton.setActionCommand("stock");
        viewButton.setBackground(FridgeGUIwithAction.MENU_BACKGROUND);
        viewButton.setOpaque(true);
        viewButton.setBorderPainted(false);
        viewButton.addActionListener(this);
        buttonPanel.add(viewButton);

        ImageIcon recipeIcon = new ImageIcon("./icons/recipe.png");
        img = recipeIcon.getImage();
        newImg = img.getScaledInstance(FridgeGUIwithAction.ICON_SIZE, FridgeGUIwithAction.ICON_SIZE, Image.SCALE_SMOOTH);
        recipeIcon = new ImageIcon(newImg);
        JButton recipeButton = new JButton(recipeIcon);
        recipeButton.setActionCommand("recipe");
        recipeButton.setBackground(FridgeGUIwithAction.MENU_BACKGROUND);
        recipeButton.setOpaque(true);
        recipeButton.setBorderPainted(false);
        recipeButton.addActionListener(this);
        buttonPanel.add(recipeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // 3. center add info
        JPanel addInfoPanel = new JPanel();
        addInfoPanel.setLayout(new GridLayout(5,2));

        JLabel nameLabel = new JLabel("Name:");
        name = new JTextField();
        addInfoPanel.add(nameLabel);
        addInfoPanel.add(name);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantity = new JTextField();
        addInfoPanel.add(quantityLabel);
        addInfoPanel.add(quantity);

        JLabel expirationLabel = new JLabel("Expiration Date:");
        expirationDate = new JTextField();
        addInfoPanel.add(expirationLabel);
        addInfoPanel.add(expirationDate);

        JLabel typeLabel = new JLabel("Type:");
        typeOption = new JComboBox(types);
        addInfoPanel.add(typeLabel);
        addInfoPanel.add(typeOption);

        JLabel locationLabel = new JLabel("Location:");
        locationOption = new JComboBox(locations);
        addInfoPanel.add(locationLabel);
        addInfoPanel.add(locationOption);


        JPanel addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.PAGE_AXIS));
        addPanel.add(addInfoPanel);

        JButton saveButton = new JButton("Save");
        addPanel.add(saveButton);
        add(addPanel,BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
                FridgeGUIwithAction aNewWindow = new FridgeGUIwithAction();
                aNewWindow.setVisible(true);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (actionCommand.equals("recipe")) {
            setVisible(false);
            RecipeWindow aNewWindow = new RecipeWindow();
        }
        else
            System.out.println("Unexpected error.");
    }

}

