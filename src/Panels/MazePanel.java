package Panels;

import SceneObjects.*;
import Settings.Controls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MazePanel extends JPanel implements ActionListener {
    private final MazeGenerator mazeGenerator;
    private final PacmanObject pacman;

    private final Ghost ghost;
    private final Timer timer;
    private KeyAdapter pacmanKeyAdapter;

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private int points = 0;
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public MazePanel() {
        mazeGenerator = new MazeGenerator();
        pacman = new PacmanObject(14,23);
        timer = new Timer(50, this);
        ghost = new Blinky(10,23);
        pacmanKeyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
              int event = e.getKeyCode();
                if (event == KeyEvent.VK_UP || event == KeyEvent.VK_DOWN || event == KeyEvent.VK_LEFT || event == KeyEvent.VK_RIGHT)
                {
                    removeKeyListener(pacmanKeyAdapter);
                    addKeyListener(pacman.getKeyAdapter());
                    timer.start();
                }
            }
        };
        addKeyListener(pacman.getKeyAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(448, 576));
        setBackground(Color.black);
        setLayout(null);
    }
    private void drawObjects(Graphics graphics)
    {
        mazeGenerator.showMaze(graphics, this);
        pacman.show(graphics, this);
        ghost.show(graphics, this);
        Toolkit.getDefaultToolkit().sync();
    }
    public int getPoints(){
        return points;
    }

    public void initStartAdapter()
    {
        addKeyListener(pacmanKeyAdapter);
    }

    public void checkCollisions()
    {
        int width = 28;
        int pacmanPosX = pacman.getInfo().get("X")/pacman.getInfo().get("Width");
        int pacmanPosY = pacman.getInfo().get("Y")/pacman.getInfo().get("Height");
        int xOffset = 0;
        int yOffset = 0;
        SceneObject[] blocks = new SceneObject[4];
        boolean [] out = new boolean[4];

        blocks[0] = mazeGenerator.getWalls().get((pacmanPosY)*width+(pacmanPosX-1));
        blocks[1] = mazeGenerator.getWalls().get((pacmanPosY-1)*width+(pacmanPosX));
        blocks[2] = mazeGenerator.getWalls().get((pacmanPosY)*width+(pacmanPosX+1));
        blocks[3] = mazeGenerator.getWalls().get((pacmanPosY+1)*width+(pacmanPosX));

        for (int i = 0; i < blocks.length; i++) {
            if(blocks[i] instanceof Wall) {
                if (pacman.getRect().intersects(blocks[i].getRect())) {
                    out[i] = true;
                }
            }
            else
                out[i] = false;
        }
        pacman.setCollision(out);

    }

    public void eat()
    {
        int width = 28;
        int pacmanPosX = pacman.getInfo().get("X")/pacman.getInfo().get("Width");
        int pacmanPosY = pacman.getInfo().get("Y")/pacman.getInfo().get("Height");
        int pacmanDirection = pacman.getCurrentDirection();
        int xOffset = 0;
        int yOffset = 0;

        switch (pacmanDirection){
            case 0:
                xOffset = 1;
                yOffset = 0;
                break;
            case 1:
                xOffset = 0;
                yOffset = 1;
                break;
            case 2:
                xOffset = 0;
                yOffset = 0;
                break;
            case 3:
                xOffset = 0;
                yOffset = 0;
                break;
            default:
                break;
        }

        SceneObject block = mazeGenerator.getDots().get((pacmanPosY+yOffset)*width+(pacmanPosX+xOffset));

        if(block instanceof Dot) {
            if (pacman.getRect().intersects(block.getRect())) {
                int oldPoints = points;
                points++;
                pcs.firePropertyChange("Points", oldPoints * 10, points * 10);
                mazeGenerator.deleteDot((pacmanPosY+yOffset)*width+(pacmanPosX+xOffset), block.getInfo().get("X"), block.getInfo().get("Y"));
            }
        }
    }
    public void checkGhostCollision() {
        System.out.println(ghost.getRect());
        if (pacman.getRect().intersects(ghost.getRect())) {

            timer.stop();
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawObjects(graphics);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ghost.getPacmanPos(pacman);
        Thread thread = new Thread(pacman);
        Thread ghostThread = new Thread(ghost);
        thread.start();
        ghostThread.start();
        checkCollisions();
        checkGhostCollision();
        eat();
        repaint();
    }
}
