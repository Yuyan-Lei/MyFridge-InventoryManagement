package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class SaveWindow extends JFrame implements ActionListener {

    SaveWindow() throws ParseException {
        DefaultUI ui = new DefaultUI("Item Saved", this);
        setVisible(true);

        //Central body - saveIcon
        JPanel savePanel = new JPanel(new GridBagLayout());
        savePanel.setBackground(DefaultUI.WHITE_COLOR);
        JLabel message = new JLabel();
        savePanel.add(message);
        add(savePanel,BorderLayout.CENTER);
        ImageIcon saveIcon = new ImageIcon("./icons/saved.png");
        Image saveImg = saveIcon.getImage();
        saveImg = saveImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        saveIcon = new ImageIcon(saveImg);
        message.setIcon(saveIcon);

//        JLabel savedLabel = new JLabel("Saved!");
//        savedLabel.setBackground(window.DefaultUI.WHITE_COLOR);
//        savedLabel.setFont(new Font(24, window.DefaultUI.Font.PLAIN, ));
    }

    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("add")) {
            setVisible(false); //can keep the new window opened only (looks like close the previous window)
            try {
                AddWindow aNewWindow = new AddWindow();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (actionCommand.equals("notification")) {
            setVisible(false);
            try {
                NoticeWindow aNewWindow = new NoticeWindow();
                aNewWindow.setVisible(true);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (actionCommand.equals("stock")) {
            setVisible(false);
            try {
                StockWindow aNewWindow = new StockWindow();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        else
            System.out.println("Unexpected error.");
    }
}