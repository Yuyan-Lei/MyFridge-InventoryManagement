import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;


public class AddWindow extends JFrame implements ActionListener {
    public static final Color GREEN_THEME = new Color(122, 156, 87);
    public static final Color WHITE_COLOR = new Color(255, 255, 255);
    public static final String TITLE_FONT = "San Francisco";
    public static final int WIDTH = 350;
    public static final int HEIGHT = 750;

    public static final int TOP_BAR_SIZE = 24;
    String types[] = { "VEGETABLE", "MEAT", "FRUIT", "DRINK", "OTHER"};
    String locations[] = {"FROZEN","REFRIGERATED" };
    private String theName;
    private int theQuantity;
    private Date theExpiration;
    private FoodItem.FoodType theType;
    private FoodItem.PlaceLocation theLocation;

    private JTextField name;
    private JTextField quantity;
    private JTextField expirationYear;
    private JTextField expirationMonth;
    private JTextField expirationDay;
    private JComboBox typeOption;
    private JComboBox locationOption;


    Boolean isValidate = true;
    AddWindow() throws ParseException {
//        super("My Fridge"); //title name
//
//        setSize(WIDTH, HEIGHT);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//        setVisible(true);
        DefaultUI ui = new DefaultUI("Add Items", this);
        setVisible(true);

        // Center add info
        JPanel addInfoPanel = new JPanel();
        addInfoPanel.setLayout(new BoxLayout(addInfoPanel, BoxLayout.PAGE_AXIS));

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Name:");
        name = new JTextField("Broccoli");
        name.setPreferredSize(new Dimension(120,30));
        namePanel.add(nameLabel);
        namePanel.add(name);
        addInfoPanel.add(namePanel);
//        theName = name.getText();
//        System.out.println(theName);

        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel quantityLabel = new JLabel("Quantity:");
        quantity = new JTextField("1");
        quantity.setPreferredSize(new Dimension(70,30));
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantity);
        addInfoPanel.add(quantityPanel);
//        theQuantity = Integer.parseInt(quantity.getText());
//        System.out.println(theQuantity);


        JPanel expirationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel expirationLabel = new JLabel("Expiration Date (yyyy-MM-dd):");
        JPanel expirationDate = new JPanel(new GridLayout(1,3));
        expirationYear = new JTextField("2022");
        expirationYear.setPreferredSize(new Dimension(70,30));
        expirationMonth = new JTextField("12");
        expirationMonth.setPreferredSize(new Dimension(20,30));
        expirationDay = new JTextField("31");
        expirationDay.setPreferredSize(new Dimension(20,30));

        expirationDate.add(expirationYear);
        expirationDate.add(expirationMonth);
        expirationDate.add(expirationDay);

        expirationPanel.add(expirationLabel);
        expirationPanel.add(expirationDate);
        addInfoPanel.add(expirationPanel);

//        theExpiration = new Date(Integer.parseInt(expirationYear.getText()),Integer.parseInt(expirationMonth.getText()),Integer.parseInt(expirationDay.getText()));
//        System.out.println(Integer.parseInt(expirationYear.getText()));
//        System.out.println(Integer.parseInt(expirationMonth.getText()));
//        System.out.println(Integer.parseInt(expirationDay.getText()));
//        System.out.println(theExpiration);


        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel typeLabel = new JLabel("Type:");
        typeOption = new JComboBox(types);
        typeOption.setSelectedIndex(0);
        typeOption.setPreferredSize(new Dimension(120,30));
        typePanel.add(typeLabel);
        typePanel.add(typeOption);
        addInfoPanel.add(typePanel);
        //theType = FoodItem.FoodType.valueOf(String.valueOf(typeOption.getSelectedItem()));
        //System.out.println(theType);

        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel locationLabel = new JLabel("Location:");
        locationOption = new JComboBox(locations);
        typeOption.setSelectedIndex(0);
        locationOption.setPreferredSize(new Dimension(120,30));
        locationPanel.add(locationLabel);
        locationPanel.add(locationOption);
        addInfoPanel.add(locationPanel);
        //theLocation = FoodItem.PlaceLocation.valueOf(String.valueOf(locationOption.getSelectedItem()));
        //System.out.println(theLocation);

        JPanel saveButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        saveButtonPanel.add(saveButton);
        addInfoPanel.add(saveButtonPanel);

        add(addInfoPanel,BorderLayout.CENTER);
    }

    public void getInput(){
    }

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
                WindowNotice aNewWindow = new WindowNotice();
                aNewWindow.setVisible(true);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (actionCommand.equals("recipe")) {
            setVisible(false);
            try {
                WindowRecipe aNewWindow = new WindowRecipe();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }

        else if (actionCommand.equals("Save")) {
            setVisible(false);
            if(isValidate){
                try {
                    Stock newItem = new Stock();
                    theName = name.getText();
                    theQuantity = Integer.parseInt(quantity.getText());
                    theExpiration = new Date((Integer.parseInt(expirationYear.getText())-1901),(Integer.parseInt(expirationMonth.getText())+11),Integer.parseInt(expirationDay.getText()));
                    theType = FoodItem.FoodType.valueOf(String.valueOf(typeOption.getSelectedItem()));
                    theLocation = FoodItem.PlaceLocation.valueOf(String.valueOf(locationOption.getSelectedItem()));
                    newItem.addItem(theName, theQuantity, theExpiration, theType, theLocation);
                    SaveWindow aNewWindow = new SaveWindow();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        else
            System.out.println("Unexpected error.");
    }

}
