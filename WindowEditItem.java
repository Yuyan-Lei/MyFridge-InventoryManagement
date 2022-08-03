import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.*;
import java.util.Objects;

public class WindowEditItem extends JFrame implements ActionListener {
    String types[] = {"VEGETABLE", "MEAT", "FRUIT", "DRINK", "OTHER"};
    String locations[] = {"FROZEN", "REFRIGERATED"};
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
    private JLabel errorMessage;
    private FoodItem theItem;
    Boolean isValidate = true;

    public WindowEditItem(FoodItem itemToEdit) throws ParseException {
        DefaultUI ui = new DefaultUI("Edit Item", this);
        setVisible(true);

        //get the item details
        theItem = itemToEdit;
        theName = theItem.getName();
        theQuantity = theItem.getQuantity();
        theExpiration =  theItem.getExpiration();
        theType = theItem.getType();
        theLocation = theItem.getLocation();

        // Center add
        JPanel editInfoPanel = new JPanel();
        editInfoPanel.setLayout(new BoxLayout(editInfoPanel, BoxLayout.PAGE_AXIS));
        editInfoPanel.setBackground(DefaultUI.WHITE_COLOR);

        // Error Message
        JPanel errorMessagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        errorMessage = new JLabel(error);
        errorMessage.setForeground(Color.RED);
        errorMessagePanel.add(errorMessage);
        errorMessagePanel.setBackground(DefaultUI.WHITE_COLOR);
        editInfoPanel.add(errorMessagePanel);

        //Center add info - name
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Name:");
        ImageIcon nameIcon = new ImageIcon("./itemIcons/url.png");
        Image nameImg = nameIcon.getImage();
        Image newImg0 = nameImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        nameIcon = new ImageIcon(newImg0);
        nameLabel.setIcon(nameIcon);
        name = new JTextField(theName);
        name.setPreferredSize(new Dimension(120, 30));
        namePanel.add(nameLabel);
        namePanel.add(name);
        name.setBackground(DefaultUI.WHITE_COLOR);
        namePanel.setBackground(DefaultUI.WHITE_COLOR);
        editInfoPanel.add(namePanel);

        //Center add info - quantity
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ImageIcon quantityIcon = new ImageIcon("./itemIcons/quantity.png");
        Image quantityImg = quantityIcon.getImage();
        Image newImg1 = quantityImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        quantityIcon = new ImageIcon(newImg1);
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setIcon(quantityIcon);

        JButton addQuantityButton = new JButton();
        ImageIcon addQuantityIcon = new ImageIcon("./itemIcons/add_zenjia.png");
        Image addQuantityImg = addQuantityIcon.getImage();
        Image addImg = addQuantityImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        addQuantityIcon = new ImageIcon(addImg);
        addQuantityButton.setIcon(addQuantityIcon);
        addQuantityButton.setBorderPainted(false);
        addQuantityButton.setActionCommand("addOne");
        addQuantityButton.addActionListener(this);

        JButton minusQuantityButton = new JButton();
        ImageIcon minusQuantityIcon = new ImageIcon("./itemIcons/add_jianshao.png");
        Image minusQuantityImg = minusQuantityIcon.getImage();
        Image minusImg = minusQuantityImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        minusQuantityIcon = new ImageIcon(minusImg);
        minusQuantityButton.setIcon(minusQuantityIcon);
        minusQuantityButton.setBorderPainted(false);
        minusQuantityButton.setActionCommand("minusOne");
        minusQuantityButton.addActionListener(this);

        quantity = new JTextField(String.valueOf(theQuantity));
        quantity.setPreferredSize(new Dimension(30, 30));
        quantityPanel.add(quantityLabel);
        quantityPanel.add(minusQuantityButton);
        quantityPanel.add(quantity);
        quantityPanel.add(addQuantityButton);

        quantity.setBackground(DefaultUI.WHITE_COLOR);
        quantityPanel.setBackground(DefaultUI.WHITE_COLOR);
        editInfoPanel.add(quantityPanel);

        //Center add info - expiration
        JPanel expirationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ImageIcon expirationIcon = new ImageIcon("./itemIcons/expiration.png");
        Image expirationImg = expirationIcon.getImage();
        Image newImg2 = expirationImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        expirationIcon = new ImageIcon(newImg2);
        JLabel expirationLabel = new JLabel("Expiration (yyyy-MM-dd):");
        expirationLabel.setIcon(expirationIcon);
        JLabel dash1 = new JLabel("-");
        JLabel dash2 = new JLabel("-");
        String theExpirationDate = theItem.getExpirationToString();
        String [] theExpiration = theExpirationDate.split("-");
        JPanel expirationDate = new JPanel(new FlowLayout(FlowLayout.LEFT));
        expirationYear = new JTextField(String.valueOf(theExpiration[0]));
        expirationYear.setPreferredSize(new Dimension(43, 30));
        expirationMonth = new JTextField(String.valueOf(theExpiration[1]));
        expirationMonth.setPreferredSize(new Dimension(27, 30));
        expirationDay = new JTextField(theExpiration[2]);
        expirationDay.setPreferredSize(new Dimension(27, 30));

        expirationDate.add(expirationYear);
        expirationDate.add(dash1);
        expirationDate.add(expirationMonth);
        expirationDate.add(dash2);
        expirationDate.add(expirationDay);
        expirationPanel.add(expirationLabel);
        expirationPanel.add(expirationDate);
        expirationDate.setBackground(DefaultUI.WHITE_COLOR);
        expirationPanel.setBackground(DefaultUI.WHITE_COLOR);
        editInfoPanel.add(expirationPanel);

        //Type
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ImageIcon typeIcon = new ImageIcon("./itemIcons/type.png");
        Image typeImg = typeIcon.getImage();
        Image newImg3 = typeImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        typeIcon = new ImageIcon(newImg3);
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setIcon(typeIcon);
        typeOption = new JComboBox(types);
        typeOption.setPreferredSize(new Dimension(120, 30));
        typeOption.setSelectedItem(String.valueOf(theType));
        typePanel.add(typeLabel);
        typePanel.add(typeOption);
        typePanel.setBackground(DefaultUI.WHITE_COLOR);
        editInfoPanel.add(typePanel);

        //Location
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel locationLabel = new JLabel("Location:");
        ImageIcon locationIcon = new ImageIcon("./itemIcons/location.png");
        Image locationImg = locationIcon.getImage();
        Image newImg4 = locationImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        locationIcon = new ImageIcon(newImg4);
        locationLabel.setIcon(locationIcon);
        locationOption = new JComboBox(locations);
        locationOption.setSelectedItem(String.valueOf(theLocation));
        locationOption.setPreferredSize(new Dimension(120, 30));
        locationPanel.add(locationLabel);
        locationPanel.add(locationOption);
        locationPanel.setBackground(DefaultUI.WHITE_COLOR);
        editInfoPanel.add(locationPanel);


        JPanel saveButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Update");
        saveButton.addActionListener(this);
        saveButtonPanel.add(saveButton);
        saveButton.setBorderPainted(false);
        saveButton.setOpaque(true);
        saveButton.setBackground(DefaultUI.GREEN_THEME);
        saveButton.setForeground(DefaultUI.WHITE_COLOR);
        saveButtonPanel.setBackground(DefaultUI.WHITE_COLOR);
        editInfoPanel.add(saveButtonPanel);

        add(editInfoPanel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        boolean actionSuccess = false;
        if (actionCommand.equals("addOne")){
            int number = Integer.parseInt(quantity.getText())+1;
            quantity.setText(String.valueOf(number));
            actionSuccess = true;
        }

        if (actionCommand.equals("minusOne")){
            int number = Integer.parseInt(quantity.getText());
            if (number >1){
                number = Integer.parseInt(quantity.getText())-1;
                quantity.setText(String.valueOf(number));
            }
            actionSuccess = true;
        }

        if (actionCommand.equals("Update")) {
            // Check Error
            resetTextColor();
            checkError();
            // No error situation
            if(isValidate){
                try {
                    Stock newItem = new Stock();
                    theName = String.valueOf(name.getText());
                    theQuantity = Integer.parseInt(quantity.getText());
                    theExpiration = new Date((Integer.parseInt(expirationYear.getText())-1901),(Integer.parseInt(expirationMonth.getText())+11),Integer.parseInt(expirationDay.getText()));
                    theType = FoodItem.FoodType.valueOf(String.valueOf(typeOption.getSelectedItem()));
                    theLocation = FoodItem.PlaceLocation.valueOf(String.valueOf(locationOption.getSelectedItem()));
                    newItem.updateItem(theItem, theName, theQuantity, theExpiration, theType, theLocation);
                    SaveWindow aNewWindow = new SaveWindow();
                } catch (ParseException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                setVisible(false);
            }
            else {
                // Reset error to null
                error = "";
                isValidate = true;
            }
            actionSuccess = true;
        }

        if (!actionSuccess) {
            System.out.println("Unexpected error.");
        }
    }

    private void setErrorEffect() {
        // set the specif text to red
        switch (error) {
            case "nameError" -> {
                name.setForeground(Color.red);
                errorMessage.setText(errorMessage.getText() + "\nError: Name should not be empty");
                break;
            }
            case "qtyError" -> {
                quantity.setForeground(Color.red);
                errorMessage.setText(errorMessage.getText() + "\nError: Quantity should be a positive integer");
                break;
            }
            case "yearError" -> {
                expirationYear.setForeground(Color.red);
                errorMessage.setText(errorMessage.getText() + "\nError: Expiration year is limited to 2022 - 2032");
                break;
            }
            case "monthError" -> {
                expirationMonth.setForeground(Color.red);
                errorMessage.setText(errorMessage.getText() + "\nError: Expiration month is limited to 1 - 12");
                break;
            }
            case "dayError" -> {
                expirationDay.setForeground(Color.red);
                errorMessage.setText(errorMessage.getText() + "\nError: Expiration day is limited to 1 - 31");
                break;
            }
        }
    }

    private void checkError() {
        // 1. Name should not be empty
        if (Objects.equals(name.getText(), "")) {
            error = "nameError";
            setErrorEffect();
        }
        // 2. Quantity should only contain digits and not be 0.
        if (!quantity.getText().matches("[0-9]+")) {
            error = "qtyError";
            setErrorEffect();
        }
        if (Objects.equals(quantity.getText(), "0")) {
            error = "qtyError";
            setErrorEffect();
        }
        // 3. Expirations should only contain digits
        if (!expirationYear.getText().matches("[0-9]+")) {
            error = "yearError";
            setErrorEffect();
        }
        else {
            // a. 2022 <= Expiration Year < 2032
            if ((Integer.parseInt(expirationYear.getText())) < 2022 || (Integer.parseInt(expirationYear.getText())) > 2032) {
                error = "yearError";
                setErrorEffect();
            }
        }
        if (!expirationMonth.getText().matches("[0-9]+")) {
            error = "monthError";
            setErrorEffect();
        }
        else {
            // b. 0 < Expiration Month <= 12
            if (Integer.parseInt(expirationMonth.getText()) <= 0 || Integer.parseInt(expirationMonth.getText()) > 12) {
                error = "monthError";
                setErrorEffect();
            }
        }
        if (!expirationDay.getText().matches("[0-9]+")) {
            error = "dayError";
            setErrorEffect();
        }
        else {
            // c. 0 < Expiration Day <= 31
            if  (Integer.parseInt(expirationDay.getText()) <= 0 || Integer.parseInt(expirationDay.getText()) > 31){
                error = "dayError";
                setErrorEffect();
            }
        }

        if (!Objects.equals(error, "")) {
            isValidate = false;
        }
    }

    private void resetTextColor() {
        name.setForeground(Color.BLACK);
        quantity.setForeground(Color.BLACK);
        expirationYear.setForeground(Color.BLACK);
        expirationMonth.setForeground(Color.BLACK);
        expirationDay.setForeground(Color.BLACK);
        errorMessage.setText("");
    }
}
