import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;


public class AddWindow extends JFrame implements ActionListener {
    //JFrame frame = new JFrame();
    public static final Color GREEN_THEME = new Color(122, 156, 87);
    public static final Color WHITE_COLOR = new Color(255, 255, 255);
    public static final String TITLE_FONT = "San Francisco";
    public static final int WIDTH = 350;
    public static final int HEIGHT = 750;

    public static final int TOP_BAR_SIZE = 24;

    String types[] = { "VEGETABLE", "MEAT", "FRUIT", "DRINK", "OTHER"};
    String locations[] = {"FROZEN","REFRIGERATED" };
    AddWindow() throws ParseException {
//        super("My Fridge"); //title name
//
//        setSize(WIDTH, HEIGHT);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//        setVisible(true);
        DefaultUI ui = new DefaultUI("Add/Edit Items", this);
        setVisible(true);

        // 1. Top bar
//        JPanel topPanel = new JPanel();
//        topPanel.setBackground(GREEN_THEME);
//        JLabel topText = new JLabel("Add/Edit Items");
//        topText.setFont(new Font(TITLE_FONT, Font.LAYOUT_LEFT_TO_RIGHT, TOP_BAR_SIZE));
//        topText.setForeground(WHITE_COLOR);
//        topPanel.add(topText);
//        add(topPanel, BorderLayout.NORTH);

        // 2. Bottom Bar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,4));
        buttonPanel.setBackground(FridgeGUIwithAction.MENU_BACKGROUND);

        ImageIcon addIcon = new ImageIcon("./icons/add_g.png");
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

        ImageIcon viewIcon = new ImageIcon("./icons/stock.png");
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
        addInfoPanel.setLayout(new BoxLayout(addInfoPanel, BoxLayout.PAGE_AXIS));

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Name:");
        JTextField name = new JTextField();
        name.setPreferredSize(new Dimension(120,30));
        namePanel.add(nameLabel);
        namePanel.add(name);
        addInfoPanel.add(namePanel);

        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantity = new JTextField();
        quantity.setPreferredSize(new Dimension(70,30));
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantity);
        addInfoPanel.add(quantityPanel);


        JPanel expirationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel expirationLabel = new JLabel("Expiration Date (yyyy-MM-dd):");
        JPanel expirationDate = new JPanel(new GridLayout(1,3));
        JTextField expirationYear = new JTextField();
        expirationYear.setPreferredSize(new Dimension(70,30));
        JTextField expirationMonth = new JTextField();
        expirationMonth.setPreferredSize(new Dimension(20,30));
        JTextField expirationDay = new JTextField();
        expirationDay.setPreferredSize(new Dimension(20,30));

        expirationDate.add(expirationYear);
        expirationDate.add(expirationMonth);
        expirationDate.add(expirationDay);

        expirationPanel.add(expirationLabel);
        expirationPanel.add(expirationDate);
        addInfoPanel.add(expirationPanel);

        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel typeLabel = new JLabel("Type:");
        JComboBox typeOption = new JComboBox(types);
        typeOption.setPreferredSize(new Dimension(120,30));
        typePanel.add(typeLabel);
        typePanel.add(typeOption);
        addInfoPanel.add(typePanel);

        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel locationLabel = new JLabel("Location:");
        JComboBox locationOption = new JComboBox(locations);
        locationOption.setPreferredSize(new Dimension(120,30));
        locationPanel.add(locationLabel);
        locationPanel.add(locationOption);
        addInfoPanel.add(locationPanel);

        JPanel saveButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Save");
        saveButtonPanel.add(saveButton);
        addInfoPanel.add(saveButtonPanel);

        add(addInfoPanel,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("stock")) {
            setVisible(false); //can keep the new window opened only (looks like close the previous window)
            try {
                StockWindow aNewWindow = new StockWindow();
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
            try {
                RecipeWindow aNewWindow = new RecipeWindow();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else
            System.out.println("Unexpected error.");
    }

}