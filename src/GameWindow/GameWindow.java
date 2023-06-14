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
        setBackground(Color.BLACK);
        initMazePanel();
    }

    private void initMazePanel()
    {
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
