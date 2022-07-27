import javax.swing.*;

public class RecipeWindow {
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Your recipes:");

    RecipeWindow(){
        label.setBounds(0,0,200,50);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,750);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
