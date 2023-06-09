package Panels;

import SceneObjects.MazeGenerator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class MazePanel extends JPanel {
    private MazeGenerator mazeGenerator;


    public MazePanel() {
        mazeGenerator = new MazeGenerator();


        setFocusable(true);
        setBackground(Color.black);
        setLayout(null);
    }



}
