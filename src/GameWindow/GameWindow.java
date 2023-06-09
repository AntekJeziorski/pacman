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

    public GameWindow()
    {
        mazePanel = new MazePanel();
        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        initMazePanel();
    }

    private void initMazePanel()
    {
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(mazePanel, gbc);
    }

    public void requestFocusInMazePanel()
    {
        mazePanel.requestFocusInWindow();
    }
}
