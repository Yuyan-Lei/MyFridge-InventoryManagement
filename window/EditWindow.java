package window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.awt.*;
import java.util.Objects;
import model.FoodItem;
import model.Stock;

public class EditWindow extends JFrame implements ActionListener {
    private String theName;
    private int theQuantity;
    private Date theExpiration;
    private FoodItem.FoodType theType;
    private FoodItem.PlaceLocation theLocation;

    private final JTextField name;
    private final JTextField quantity;
    private final JTextField expirationYear;
    private final JTextField expirationMonth;
    private final JTextField expirationDay;
    private final JComboBox<String> typeOption;
    private final JComboBox<String> locationOption;
    private String error = "";
    private JLabel errorMessage;
    private final FoodItem theItem;
    private Boolean isValidate = true;

    public EditWindow(FoodItem itemToEdit) throws ParseException {
        new DefaultUI("Edit Item", this);
        setVisible(true);

        // Get the item details
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

        // Center add info - name
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Name:");
        ImageIcon nameIcon = new ImageIcon("./icons/itemIcons/url.png");
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

        // Center add info - quantity
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ImageIcon quantityIcon = new ImageIcon("./icons/itemIcons/quantity.png");
        Image quantityImg = quantityIcon.getImage();
        Image newImg1 = quantityImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        quantityIcon = new ImageIcon(newImg1);
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setIcon(quantityIcon);
        JButton addQuantityButton = new JButton();
        ImageIcon addQuantityIcon = new ImageIcon("icons/itemIcons/add.png");
        Image addQuantityImg = addQuantityIcon.getImage();
        Image addImg = addQuantityImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        addQuantityIcon = new ImageIcon(addImg);
        addQuantityButton.setIcon(addQuantityIcon);
        addQuantityButton.setBorderPainted(false);
        addQuantityButton.setActionCommand("addOne");
        addQuantityButton.addActionListener(this);
        JButton minusQuantityButton = new JButton();
        ImageIcon minusQuantityIcon = new ImageIcon("icons/itemIcons/minus.png");
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

        // Center add info - expiration
        JPanel expirationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ImageIcon expirationIcon = new ImageIcon("./icons/itemIcons/expiration.png");
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

        // Center add info - Type
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ImageIcon typeIcon = new ImageIcon("./icons/itemIcons/type.png");
        Image typeImg = typeIcon.getImage();
        Image newImg3 = typeImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        typeIcon = new ImageIcon(newImg3);
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setIcon(typeIcon);
        typeOption = new JComboBox<>(new String[]{"VEGETABLE", "MEAT", "FRUIT", "DRINK", "OTHER"});
        typeOption.setPreferredSize(new Dimension(120, 30));
        typeOption.setSelectedItem(String.valueOf(theType));
        typePanel.add(typeLabel);
        typePanel.add(typeOption);
        typePanel.setBackground(DefaultUI.WHITE_COLOR);
        editInfoPanel.add(typePanel);

        // Center add info - Location
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel locationLabel = new JLabel("Location:");
        ImageIcon locationIcon = new ImageIcon("./icons/itemIcons/location.png");
        Image locationImg = locationIcon.getImage();
        Image newImg4 = locationImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        locationIcon = new ImageIcon(newImg4);
        locationLabel.setIcon(locationIcon);
        locationOption = new JComboBox<>(new String[]{"FROZEN", "REFRIGERATED"});
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
        switch (actionCommand) {
            case "addOne" -> {
                int number = Integer.parseInt(quantity.getText()) + 1;
                quantity.setText(String.valueOf(number));
                actionSuccess = true;
            }
            case "minusOne" -> {
                int number = Integer.parseInt(quantity.getText());
                if (number > 1) {
                    number = Integer.parseInt(quantity.getText()) - 1;
                    quantity.setText(String.valueOf(number));
                }
                actionSuccess = true;
            }
            case "Update" -> {
                // Check Error
                resetTextColor();
                checkError();
                // No error situation
                if (isValidate) {
                    try {
                        Stock newItem = new Stock();
                        theName = name.getText();
                        theQuantity = Integer.parseInt(quantity.getText());
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.YEAR,Integer.parseInt(expirationYear.getText()));
                        cal.set(Calendar.MONTH,(Integer.parseInt(expirationMonth.getText()) - 1));
                        cal.set(Calendar.DATE, (Integer.parseInt(expirationDay.getText())));
                        theExpiration = cal.getTime();
                        theType = FoodItem.FoodType.valueOf(String.valueOf(typeOption.getSelectedItem()));
                        theLocation = FoodItem.PlaceLocation.valueOf(String.valueOf(locationOption.getSelectedItem()));
                        newItem.updateItem(theItem, theName, theQuantity, theExpiration, theType, theLocation);
                        SaveWindow aNewWindow = new SaveWindow();
                        aNewWindow.setVisible(true);
                    } catch (ParseException | IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    setVisible(false);
                } else {
                    // Reset error to null
                    error = "";
                    isValidate = true;
                }
                actionSuccess = true;
            }
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
                errorMessage.setText(errorMessage.getText() + "<html>Error: Name should not be empty, or contain '///' or ','<br>");
            }
            case "qtyError" -> {
                quantity.setForeground(Color.red);
                errorMessage.setText(errorMessage.getText() + "<html>Error: Quantity should be a positive integer<br>");
            }
            case "yearError" -> {
                expirationYear.setForeground(Color.red);
                errorMessage.setText(errorMessage.getText() + "<html>Error: Expiration year is limited to 2022 - 2032<br>");
            }
            case "monthError" -> {
                expirationMonth.setForeground(Color.red);
                errorMessage.setText(errorMessage.getText() + "<html>Error: Expiration month is limited to 1 - 12<br>");
            }
            case "dayError" -> {
                expirationDay.setForeground(Color.red);
                errorMessage.setText(errorMessage.getText() + "<html>Error: Expiration day is limited to 1 - 31");
            }
        }
    }

    private void checkError() {
        // 1. Name should not be empty, or contain "///" or ","
        if (Objects.equals(name.getText(), "")) {
            error = "nameError";
            setErrorEffect();
        }
        else if (name.getText().matches(".*///.*")) {
            error = "nameError";
            setErrorEffect();
        }
        else if (name.getText().matches(".*,.*")) {
            error = "nameError";
            setErrorEffect();
        }
        // 2. Quantity should only contain digits and not be 0.
        if (!quantity.getText().matches("\\d+")) {
            error = "qtyError";
            setErrorEffect();
        }
        else if (Objects.equals(quantity.getText(), "0")) {
            error = "qtyError";
            setErrorEffect();
        }
        // 3. Expirations should only contain digits
        if (!expirationYear.getText().matches("\\d+")) {
            error = "yearError";
            setErrorEffect();
        }
        // a. 2022 <= Expiration Year < 2032
        else if ((Integer.parseInt(expirationYear.getText())) < 2022 || (Integer.parseInt(expirationYear.getText())) > 2032) {
            error = "yearError";
            setErrorEffect();
        }
        if (!expirationMonth.getText().matches("\\d+")) {
            error = "monthError";
            setErrorEffect();
        }
        // b. 0 < Expiration Month <= 12
        else if (Integer.parseInt(expirationMonth.getText()) <= 0 || Integer.parseInt(expirationMonth.getText()) > 12) {
            error = "monthError";
            setErrorEffect();
        }
        if (!expirationDay.getText().matches("\\d+")) {
            error = "dayError";
            setErrorEffect();
        }
        // c. 0 < Expiration Day <= 31
        else if  (Integer.parseInt(expirationDay.getText()) <= 0 || Integer.parseInt(expirationDay.getText()) > 31){
            error = "dayError";
            setErrorEffect();
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
