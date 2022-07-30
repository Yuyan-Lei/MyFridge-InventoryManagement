import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;


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


    Boolean isValidate = true;
    AddWindow() throws ParseException {
        DefaultUI ui = new DefaultUI("Add Items", this);
        setVisible(true);

        // Center add info
        JPanel addInfoPanel = new JPanel();
        addInfoPanel.setLayout(new BoxLayout(addInfoPanel, BoxLayout.PAGE_AXIS));

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
        //expirationDate.add(dash);
        expirationDate.add(expirationMonth);
        //expirationDate.add(dash);
        expirationDate.add(expirationDay);
        expirationPanel.add(expirationLabel);
        expirationPanel.add(expirationDate);
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
        addInfoPanel.add(locationPanel);


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
