package Panels;

import SceneObjects.MazeGenerator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class MazePanel extends JPanel {
    private final MazeGenerator mazeGenerator;


    public MazePanel() {
        mazeGenerator = new MazeGenerator();
        setFocusable(true);
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.black);
        setLayout(null);
    }
    private void drawObjects(Graphics graphics)
    {
        mazeGenerator.showMaze(graphics, this);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        drawObjects(graphics);
    }

}
