import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;


public class AddWindow extends JFrame implements ActionListener {
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

    private String error = "";

    Boolean isValidate = true;
    AddWindow() throws ParseException {
        DefaultUI ui = new DefaultUI("Add Items", this);
        setVisible(true);

        // Center add info
        JPanel addInfoPanel = new JPanel();
        addInfoPanel.setLayout(new BoxLayout(addInfoPanel, BoxLayout.PAGE_AXIS));
        addInfoPanel.setBackground(DefaultUI.WHITE_COLOR);

        // Error Message
        JPanel errorMessagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel errorMessage = new JLabel(error);
        errorMessage.setForeground(Color.RED);
        errorMessagePanel.add(errorMessage);
        errorMessagePanel.setBackground(DefaultUI.WHITE_COLOR);
        addInfoPanel.add(errorMessagePanel);

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Name:");

        ImageIcon nameIcon = new ImageIcon("./itemIcons/url.png");
        Image nameImg = nameIcon.getImage();
        Image newImg0 = nameImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        nameIcon = new ImageIcon(newImg0);
        nameLabel.setIcon(nameIcon);
        name = new JTextField();
        name.setPreferredSize(new Dimension(120,30));
        namePanel.add(nameLabel);
        namePanel.add(name);
        name.setBackground(DefaultUI.WHITE_COLOR);
        namePanel.setBackground(DefaultUI.WHITE_COLOR);
        addInfoPanel.add(namePanel);

        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        ImageIcon quantityIcon = new ImageIcon("./itemIcons/quantity.png");
        Image quantityImg = quantityIcon.getImage();
        Image newImg1 = quantityImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        quantityIcon = new ImageIcon(newImg1);
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setIcon(quantityIcon);
        quantity = new JTextField();
        quantity.setPreferredSize(new Dimension(50,30));
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantity);
        quantity.setBackground(DefaultUI.WHITE_COLOR);
        quantityPanel.setBackground(DefaultUI.WHITE_COLOR);
        addInfoPanel.add(quantityPanel);


        JPanel expirationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ImageIcon expirationIcon = new ImageIcon("./itemIcons/expiration.png");
        Image expirationImg = expirationIcon.getImage();
        Image newImg2 = expirationImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        expirationIcon = new ImageIcon(newImg2);
        JLabel expirationLabel = new JLabel("Expiration Date (yyyy-MM-dd):");
        expirationLabel.setIcon(expirationIcon);
        //JLabel dash = new JLabel("-");
        JPanel expirationDate = new JPanel(new GridLayout(1,5));
        expirationYear = new JTextField("");
        expirationYear.setPreferredSize(new Dimension(50,30));
        expirationMonth = new JTextField("");
        expirationMonth.setPreferredSize(new Dimension(10,30));
        expirationDay = new JTextField("");
        expirationDay.setPreferredSize(new Dimension(10,30));

        expirationDate.add(expirationYear);
        expirationDate.add(expirationMonth);
        expirationDate.add(expirationDay);
        expirationPanel.add(expirationLabel);
        expirationPanel.add(expirationDate);
        expirationDay.setBackground(DefaultUI.WHITE_COLOR);
        expirationMonth.setBackground(DefaultUI.WHITE_COLOR);
        expirationYear.setBackground(DefaultUI.WHITE_COLOR);
        expirationPanel.setBackground(DefaultUI.WHITE_COLOR);
        addInfoPanel.add(expirationPanel);

        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ImageIcon typeIcon = new ImageIcon("./itemIcons/type.png");
        Image typeImg = typeIcon.getImage();
        Image newImg3 = typeImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        typeIcon = new ImageIcon(newImg3);
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setIcon(typeIcon);
        typeOption = new JComboBox(types);
        typeOption.setPreferredSize(new Dimension(120,30));
        typePanel.add(typeLabel);
        typePanel.add(typeOption);
        typePanel.setBackground(DefaultUI.WHITE_COLOR);
        addInfoPanel.add(typePanel);

        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel locationLabel = new JLabel("Location:");
        ImageIcon locationIcon = new ImageIcon("./itemIcons/location.png");
        Image locationImg = locationIcon.getImage();
        Image newImg4 = locationImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        locationIcon = new ImageIcon(newImg4);
        locationLabel.setIcon(locationIcon);

        locationOption = new JComboBox(locations);
        typeOption.setSelectedIndex(0);
        locationOption.setPreferredSize(new Dimension(120,30));
        locationPanel.add(locationLabel);
        locationPanel.add(locationOption);
        locationPanel.setBackground(DefaultUI.WHITE_COLOR);
        addInfoPanel.add(locationPanel);


        JPanel saveButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        saveButtonPanel.add(saveButton);
        saveButton.setBackground(DefaultUI.GREEN_THEME);
        saveButtonPanel.setBackground(DefaultUI.WHITE_COLOR);
        addInfoPanel.add(saveButtonPanel);

        add(addInfoPanel,BorderLayout.CENTER);

    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Save")) {
            // Check Error
            resetTextColor();
            checkError();
            // 1. No error situation
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
                } catch (ParseException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                setVisible(false);
            }
            // 2. Error situation
            else {
                setErrorColor();
            }
        }

        else
            System.out.println("Unexpected error.");
    }

    private void checkError() {
        // 1. Name should only contain letters
        if (!name.getText().chars().allMatch(Character::isLetter)) {
            error = "nameError";
        }
        // 2. Quantity should only contain digits and not be 0.
        else if (!quantity.getText().matches("[0-9]+")) {
            error = "qtyError";
        }
        else if (Objects.equals(quantity.getText(), "0")) {
            error = "qtyError";
        }
        // 3. Expirations should only contain digits
        else if (!expirationYear.getText().matches("[0-9]+")) {
            error = "yearError";
        }
        else if (!expirationMonth.getText().matches("[0-9]+")) {
            error = "monthError";
        }
        else if (!expirationDay.getText().matches("[0-9]+")) {
            error = "dayError";
        }
        // a. 2022 <= Expiration Year < 2032
        else if ((Integer.parseInt(expirationYear.getText())) < 2022 || (Integer.parseInt(expirationYear.getText())) > 2032) {
            error = "yearError";
        }
        // b. 0 < Expiration Month <= 12
        else if (Integer.parseInt(expirationMonth.getText()) <= 0 || Integer.parseInt(expirationMonth.getText()) > 12) {
            error = "monthError";
        }
        // b. 0 < Expiration Day <= 31
        else if  (Integer.parseInt(expirationDay.getText()) <= 0 || Integer.parseInt(expirationDay.getText()) > 31){
            error = "dayError";
        }

        if (!Objects.equals(error, "")) {
            isValidate = false;
        }
    }

    private void setErrorColor() {
        // set the specif text to red
        switch (error) {
            case "nameError" -> name.setForeground(Color.red);
            case "qtyError" -> quantity.setForeground(Color.red);
            case "yearError" -> expirationYear.setForeground(Color.red);
            case "monthError" -> expirationMonth.setForeground(Color.red);
            case "dayError" -> expirationDay.setForeground(Color.red);
        }
        // Reset error to null
        error = "";
        isValidate = true;
    }

    private void resetTextColor() {
        name.setForeground(Color.BLACK);
        quantity.setForeground(Color.BLACK);
        expirationYear.setForeground(Color.BLACK);
        expirationMonth.setForeground(Color.BLACK);
        expirationDay.setForeground(Color.BLACK);
    }
}
