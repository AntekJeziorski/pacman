package Panels;

import SceneObjects.MazeGenerator;
import SceneObjects.PacmanObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;

public class MazePanel extends JPanel implements ActionListener {
    private final MazeGenerator mazeGenerator;
    private final PacmanObject pacman;
    private final Timer timer;
    private KeyAdapter pacmanKeyAdapter;


    public MazePanel() {
        mazeGenerator = new MazeGenerator();
        pacman = new PacmanObject(14,26);
        timer = new Timer(1000, this);
        pacmanKeyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
              int event = e.getKeyCode();
                System.out.println(event);
                if (event == KeyEvent.VK_UP || event == KeyEvent.VK_DOWN || event == KeyEvent.VK_LEFT || event == KeyEvent.VK_RIGHT)
                {
                    removeKeyListener(pacmanKeyAdapter);
                    addKeyListener(pacman.getKeyAdapter());
                    timer.start();
                }
            }
        };
//        removeKeyListener(pacmanKeyAdapter);
        addKeyListener(pacman.getKeyAdapter());
//        timer.start();
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

    public void initStartAdapter()
    {
        addKeyListener(pacmanKeyAdapter);
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        drawObjects(graphics);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Gówno");
        Thread thread = new Thread(pacman);
        thread.start();
        repaint();
    }
}
