package window;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;

public class SaveWindow extends JFrame {

    SaveWindow() throws ParseException {
        new DefaultUI("Item Saved", this);
        setVisible(true);

        //Central body - saveIcon
        JPanel savePanel = new JPanel(new GridBagLayout());
        savePanel.setBackground(DefaultUI.WHITE_COLOR);
        JLabel message = new JLabel();
        savePanel.add(message);
        add(savePanel,BorderLayout.CENTER);
        ImageIcon saveIcon = new ImageIcon("icons/basicIcons/saved.png");
        Image saveImg = saveIcon.getImage();
        saveImg = saveImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        saveIcon = new ImageIcon(saveImg);
        message.setIcon(saveIcon);

        JLabel savedLabel = new JLabel("Saved!");
        savedLabel.setBackground(window.DefaultUI.WHITE_COLOR);
        savedLabel.setFont(new Font("Saved!", Font.PLAIN, 14));
        add(savePanel);
    }
}