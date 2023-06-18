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

/**
 * @brief Represents maze panel object
 */
public class MazePanel extends JPanel implements ActionListener {

    /** @brief MazeGenerator object */
    private MazeGenerator mazeGenerator;
    /** @brief PacmanObject class object */
    private PacmanObject pacman;
    /** @brief Game timer */
    private final Timer timer;
    /** @brief KeyAdapter for pacman player control */
    private KeyAdapter pacmanKeyAdapter;
    /** @brief Current number of lives */
    private int lives = 3;
    /** @brief Current number of eaten dots */
    private int eatenDots = 0;
    /** @brief Current score */
    private long score = 0;


    /** @brief Red ghost object */
    private Blinky blinky;
    /** @brief Pink ghost object */
    private Pinky pinky;
    /** @brief Blue ghost object */
    private Inky inky;
    /** @brief Orange ghost object */
    private Clyde clyde;
    /** @brief PropertyChangeSupport handler */
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * @brief Adds listener to pcs
     * @param listener  the PropertyChangeListener to be added
     *
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * @brief Non-parametric MazePanel constructor
     *
     * Initializes game timer and all drawable objects
     */
    public MazePanel() {
        timer = new Timer(40, this);
        loadMaze();
        setPreferredSize(new Dimension(448, 496));
        setBackground(Color.black);
        setLayout(null);
    }

    /**
     * @brief Initializes pacman key listener
     */
    public void initStartAdapter() {
        addKeyListener(pacmanKeyAdapter);
    }

    /**
     * @brief Calculates index of a block in front of pacman
     * @return index of block in front of pacman
     */
    private int calculateElementIndex() {
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

        return (pacmanPosY + yOffset) * width + (pacmanPosX + xOffset);
    }

    /**
     * @brief Checks pacman collisions with walls
     */
    public void checkCollisions() {
        int width = 28;
        int pacmanPosX = pacman.getInfo().get("X") / pacman.getInfo().get("Width");
        int pacmanPosY = pacman.getInfo().get("Y") / pacman.getInfo().get("Height");
        SceneObject[] blocks = new SceneObject[4];
        boolean[] out = new boolean[4];

        blocks[0] = mazeGenerator.getWalls().get((pacmanPosY) * width + (pacmanPosX - 1));
        blocks[1] = mazeGenerator.getWalls().get((pacmanPosY - 1) * width + (pacmanPosX));
        blocks[2] = mazeGenerator.getWalls().get((pacmanPosY) * width + (pacmanPosX + 1));
        blocks[3] = mazeGenerator.getWalls().get((pacmanPosY + 1) * width + (pacmanPosX));

        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] instanceof Wall) {
                if (pacman.getRect().intersects(blocks[i].getRect())) {
                    out[i] = true;
                }
            } else
                out[i] = false;
        }
        pacman.setCollision(out);

    }

    /**
     * @brief Checks ghost's collision with walls
     * @param ghost ghost object for collision detection
     */
    public void checkGhostCollisions(Ghost ghost) {
        int width = 28;
        int ghostPosX = ghost.getInfo().get("X") / ghost.getInfo().get("Width");
        int ghostPosY = ghost.getInfo().get("Y") / ghost.getInfo().get("Height");
        SceneObject[] blocks = new SceneObject[4];
        boolean[] out = new boolean[4];

        blocks[0] = mazeGenerator.getWalls().get((ghostPosY) * width + (ghostPosX - 1));
        blocks[1] = mazeGenerator.getWalls().get((ghostPosY - 1) * width + (ghostPosX));
        blocks[2] = mazeGenerator.getWalls().get((ghostPosY) * width + (ghostPosX + 1));
        blocks[3] = mazeGenerator.getWalls().get((ghostPosY + 1) * width + (ghostPosX));

        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] instanceof Wall) {
                if (ghost.getRect().intersects(blocks[i].getRect())) {
                    out[i] = true;
                }
            } else
                out[i] = false;
        }
        ghost.setCollision(out);
    }

    /**
     * @brief Draws all objects on panel
     * @param graphics graphics handler
     */
    private void drawObjects(Graphics graphics) {
        mazeGenerator.showMaze(graphics, this);
        pacman.show(graphics, this);
        blinky.show(graphics, this);
        pinky.show(graphics, this);
        inky.show(graphics, this);
        clyde.show(graphics, this);
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * @brief Initializes all interactive objects on maze panel
     */
    private void loadMaze() {
        mazeGenerator = new MazeGenerator();
        pacman = new PacmanObject(14, 23);

        blinky = new Blinky(12, 11);
        pinky = new Pinky(14, 11);
        inky = new Inky(15, 11);
        clyde = new Clyde(13, 11);
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

    /**
     * @brief Checks collisions with dots and apples, and calculates score
     */
    private void collectDots() {
        int elementIndex = calculateElementIndex();
        SceneObject block = mazeGenerator.getDots().get(elementIndex);

        if (block instanceof Apple) {
            if (pacman.getRect().intersects(block.getRect())) {
                long oldPoints = score;
                score += 50;
                pcs.firePropertyChange("Points", oldPoints, score);
                mazeGenerator.deleteDot(elementIndex, block.getInfo().get("X"), block.getInfo().get("Y"));
            }
        }

        if (block instanceof Dot) {
            if (pacman.getRect().intersects(block.getRect())) {
                long oldPoints = score;
                eatenDots++;
                score += 10;
                pcs.firePropertyChange("Points", oldPoints, score);
                mazeGenerator.deleteDot(elementIndex, block.getInfo().get("X"), block.getInfo().get("Y"));
            }
        }

//        System.out.println(eatenDots);
        if (eatenDots == 240) {
            timer.stop();
            eatenDots = 0;
            loadMaze();
        }
    }

    /**
     * @brief Checks ghost's collision with pacman
     * @param ghost ghost object for collision detection
     */
    public void checkPacmanCollisionWithGhost (Ghost ghost){
        if (pacman.getRect().intersects(ghost.getRect())) {
            timer.stop();
            eatenDots = 0;
            int oldLivesNumber = lives;
            lives--;
            pcs.firePropertyChange("Lives", oldLivesNumber, lives);
            loadMaze();
        }
    }

    /**
     * @brief Overrides {@link JComponent's} method paintComponent
     * @param graphics the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent (Graphics graphics){
        super.paintComponent(graphics);
        drawObjects(graphics);
    }

    /**
     * @brief Overrides actionPerformed method, acts as game loop
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed (ActionEvent e){
        blinky.getPacmanPos(pacman);
        pinky.getPacmanPos(pacman);
        inky.getPacmanPos(pacman);
        clyde.getPacmanPos(pacman);

        inky.getBlinkyPos(blinky);

        Thread thread = new Thread(pacman);
        Thread blinkyThread = new Thread(blinky);
        Thread pinkyThread = new Thread(pinky);
        Thread inkyThread = new Thread(inky);
        Thread clydeThread = new Thread(clyde);

        checkCollisions();

        checkPacmanCollisionWithGhost(blinky);
        checkPacmanCollisionWithGhost(pinky);
        checkPacmanCollisionWithGhost(inky);
        checkPacmanCollisionWithGhost(clyde);

        checkGhostCollisions(blinky);
        checkGhostCollisions(pinky);
        checkGhostCollisions(inky);
        checkGhostCollisions(clyde);

        thread.start();
        blinkyThread.start();
        pinkyThread.start();
        inkyThread.start();
        clydeThread.start();

        try {
            clydeThread.join();
            inkyThread.join();
            pinkyThread.join();
            blinkyThread.join();
            thread.join();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }


        collectDots();

        repaint();
    }

}
