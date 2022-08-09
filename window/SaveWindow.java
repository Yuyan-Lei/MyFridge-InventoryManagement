package window;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;

public class SaveWindow extends JFrame {

    SaveWindow() throws ParseException {
        new DefaultUI("Item Saved", this);
        setVisible(true);

        // Central body - save icon
        JPanel savePanel = new JPanel(new GridBagLayout());
        savePanel.setBackground(window.DefaultUI.WHITE_COLOR);
        JPanel saveIconPanel = new JPanel(new GridBagLayout());
        saveIconPanel.setBackground(DefaultUI.WHITE_COLOR);
        JLabel message = new JLabel();
        saveIconPanel.add(message);
        add(saveIconPanel,BorderLayout.CENTER);
        ImageIcon saveIcon = new ImageIcon("icons/basicIcons/saved.png");
        Image saveImg = saveIcon.getImage();
        saveImg = saveImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        saveIcon = new ImageIcon(saveImg);
        message.setIcon(saveIcon);

        // Central body - save label
        JPanel saveLabelPanel = new JPanel(new GridBagLayout());
        saveLabelPanel.setBackground(window.DefaultUI.WHITE_COLOR);
        JLabel savedLabel = new JLabel("Saved!");
        savedLabel.setForeground(DefaultUI.GREEN_THEME);
        savedLabel.setFont(new Font("Saved!", Font.PLAIN, 21));
        saveLabelPanel.add(savedLabel);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        savePanel.add(saveIconPanel,c);
        c.gridx = 0;
        c.gridy = 1;
        savePanel.add(saveLabelPanel,c);
        add(savePanel);
    }
    
}