import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWindow extends FoodItem implements ActionListener {
    JFrame frame = new JFrame();
    private JTextField name;
    private JTextField quantity;
    private JTextField expirationDate;

    private JComboBox typeOption, locationOption;

    String types[] = { "VEGETABLE", "MEAT", "FRUIT", "DRINK", "OTHER"};
    String locations[] = {"FROZEN","REFRIGERATED" };
    AddWindow(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,750);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        JButton backButton = new JButton("Back");
        frame.add(backButton, BorderLayout.NORTH);

        //add window layout
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new GridLayout(5,2));

        JLabel nameLabel = new JLabel("Name:");
        name = new JTextField();
        addPanel.add(nameLabel);
        addPanel.add(name);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantity = new JTextField();
        addPanel.add(quantityLabel);
        addPanel.add(quantity);

        JLabel expirationLabel = new JLabel("Expiration Date:");
        expirationDate = new JTextField();
        addPanel.add(expirationLabel);
        addPanel.add(expirationDate);

        JLabel typeLabel = new JLabel("Type:");
        typeOption = new JComboBox(types);
        addPanel.add(typeLabel);
        addPanel.add(typeOption);



        JLabel locationLabel = new JLabel("Location:");
        locationOption = new JComboBox(locations);
        addPanel.add(locationLabel);
        addPanel.add(locationOption);


        //buttons(back & save)
        frame.add(addPanel,BorderLayout.CENTER);
        JButton saveButton = new JButton("Save");
        frame.add(saveButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
