package Panels;

import SceneObjects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MazePanel extends JPanel implements ActionListener {
    private MazeGenerator mazeGenerator;
    private PacmanObject pacman;
    private Ghost ghost;
    private Timer timer;
    private KeyAdapter pacmanKeyAdapter;
    private int lives = 3;

    private int eatenDots = 0;
    private long score = 0;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public MazePanel() {
        loadMaze();
        setPreferredSize(new Dimension(448, 496));
        setBackground(Color.black);
        setLayout(null);
    }


    private int calculateElementIndex(){
        int width = 28;
        int pacmanPosX = pacman.getInfo().get("X") / pacman.getInfo().get("Width");
        int pacmanPosY = pacman.getInfo().get("Y") / pacman.getInfo().get("Height");
        int pacmanDirection = pacman.getCurrentDirection();
        int xOffset = 0;
        int yOffset = 0;

        switch (pacmanDirection) {
            case 0 -> xOffset = 1;
            case 1 -> yOffset = 1;
            default -> {
            }
        }

        return (pacmanPosY+yOffset)*width+(pacmanPosX+xOffset);
    }
    public void checkCollisions() {
        int width = 28;
        int pacmanPosX = pacman.getInfo().get("X") / pacman.getInfo().get("Width");
        int pacmanPosY = pacman.getInfo().get("Y") / pacman.getInfo().get("Height");
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

    private void checkGhostCollision() {
        if (pacman.getRect().intersects(ghost.getRect())) {
            timer.stop();
            int oldLivesNumber = lives;
            lives--;
            pcs.firePropertyChange("Lives", oldLivesNumber, lives);
            loadMaze();
        }
    }
    private void drawObjects(Graphics graphics) {
        mazeGenerator.showMaze(graphics, this);
        pacman.show(graphics, this);
        ghost.show(graphics, this);
        Toolkit.getDefaultToolkit().sync();
    }
    private void loadMaze(){
        mazeGenerator = new MazeGenerator();
        pacman = new PacmanObject(14,23);
        timer = new Timer(50, this);
        ghost = new Blinky(10,26);
        pacmanKeyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int event = e.getKeyCode();
                if (event == KeyEvent.VK_UP || event == KeyEvent.VK_DOWN || event == KeyEvent.VK_LEFT || event == KeyEvent.VK_RIGHT) {
                    removeKeyListener(pacmanKeyAdapter);
                    addKeyListener(pacman.getKeyAdapter());
                    timer.start();
                }
            }
        };
        addKeyListener(pacman.getKeyAdapter());
        initStartAdapter();
        setFocusable(true);
    }
    private void collectDots() {
        int elementIndex = calculateElementIndex();
        SceneObject block = mazeGenerator.getDots().get(elementIndex);

        if(block instanceof Apple) {
            if (pacman.getRect().intersects(block.getRect())) {
                long oldPoints = score;
                score += 50;
                pcs.firePropertyChange("Points", oldPoints,  score);
                mazeGenerator.deleteDot(elementIndex, block.getInfo().get("X"), block.getInfo().get("Y"));
            }
        }

        if(block instanceof Dot) {
            if (pacman.getRect().intersects(block.getRect())) {
                long oldPoints = score;
                eatenDots++;
                score += 10;
                pcs.firePropertyChange("Points", oldPoints, score);
                mazeGenerator.deleteDot(elementIndex, block.getInfo().get("X"), block.getInfo().get("Y"));
            }
        }

        if (eatenDots == 240){
            eatenDots = 0;
            timer.stop();
            loadMaze();
        }
    }

    public void initStartAdapter() {
        addKeyListener(pacmanKeyAdapter);
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
        collectDots();
        repaint();
    }
}
