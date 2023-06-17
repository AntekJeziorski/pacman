package GameWindow;

import Panels.MazePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class GameWindow extends JPanel {
    private final GridBagConstraints gbc;
    private MazePanel mazePanel;
    private int Points;
    private final JLabel ScoreValue = new JLabel("0");

    private Font ButtonFont;
    JButton button;
    public GameWindow()
    {
        mazePanel = new MazePanel();
        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        initMazePanel();
    }

    private void readFonts(){
        try {
            Font buttonFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/emulogic.ttf"));
            ButtonFont = buttonFont.deriveFont(16f);
        } catch (FontFormatException | IOException e) {e.printStackTrace();}
    }

    private void initMazePanel() {
        readFonts();
        ScoreValue.setPreferredSize(new Dimension(448,60));
        ScoreValue.setFont(ButtonFont);
        ScoreValue.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.CENTER;
        add(ScoreValue, gbc);

        mazePanel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if (propertyName.equals("Points")) {
                    String newValue = evt.getNewValue().toString();
                    ScoreValue.setText(newValue);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.CENTER;
        add(mazePanel, gbc);

        mazePanel.initStartAdapter();
    }

    public void requestFocusInMazePanel()
    {
        mazePanel.requestFocusInWindow();
    }
}
