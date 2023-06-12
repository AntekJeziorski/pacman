package Panels;

import SceneObjects.MazeGenerator;
import SceneObjects.PacmanObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class MazePanel extends JPanel implements ActionListener {
    private final MazeGenerator mazeGenerator;
    private final PacmanObject pacman;
    private final Timer timer;


    public MazePanel() {
        mazeGenerator = new MazeGenerator();
        pacman = new PacmanObject(14,26);
        timer = new Timer(10, this);
        setFocusable(true);
        setPreferredSize(new Dimension(448, 576));
        setBackground(Color.black);
        setLayout(null);
    }
    private void drawObjects(Graphics graphics)
    {
        mazeGenerator.showMaze(graphics, this);
        pacman.show(graphics, this);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        drawObjects(graphics);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
