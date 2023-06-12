package GameWindow;

import Panels.MazePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameWindow extends JPanel {
    private final GridBagConstraints gbc;
    private MazePanel mazePanel;
    JButton button;
    public GameWindow()
    {
        mazePanel = new MazePanel();
        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        initMazePanel();
    }

    private void initMazePanel()
    {
        button = new JButton("Button 1");
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(button, gbc);

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
