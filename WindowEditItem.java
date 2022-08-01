import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class WindowEditItem extends JFrame implements ActionListener {
    public WindowEditItem(FoodItem itemToEdit) throws ParseException {
        DefaultUI ui = new DefaultUI("Edit Item", this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("save")) {
            setVisible(false);
            // needs to complete
        }
        else {
            System.out.println("Unexpected error.");
        }
    }
}
