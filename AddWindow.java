import javax.swing.*;

public class AddWindow {
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Please enter your food:");

    AddWindow(){
        label.setBounds(0,0,200,50);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,350);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
