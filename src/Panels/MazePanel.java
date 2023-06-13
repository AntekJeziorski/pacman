package Panels;

import SceneObjects.MazeGenerator;
import SceneObjects.PacmanObject;
import SceneObjects.SceneObject;
import SceneObjects.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MazePanel extends JPanel implements ActionListener {
    private final MazeGenerator mazeGenerator;
    private final PacmanObject pacman;
    private final Timer timer;
    private KeyAdapter pacmanKeyAdapter;


    public MazePanel() {
        mazeGenerator = new MazeGenerator();
        pacman = new PacmanObject(14,26);
        timer = new Timer(100, this);
        pacmanKeyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
              int event = e.getKeyCode();
//                System.out.println(event);
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

    public void checkCollisions()
    {
        int width = 28;
        int pacmanPosX = pacman.getInfo().get("X")/pacman.getInfo().get("Width");
        int pacmanPosY = pacman.getInfo().get("Y")/pacman.getInfo().get("Height");
        int pacmanDirection = pacman.getCurrentDirection();
        int xOffset = 0;
        int yOffset = 0;
        SceneObject block;

        switch (pacmanDirection){
            case 0:
                xOffset = -1;
                yOffset = 0;
                break;
            case 1:
                xOffset = 0;
                yOffset = -1;
                break;
            case 2:
                xOffset = 1;
                yOffset = 0;
                break;
            case 3:
                xOffset = 0;
                yOffset = 1;
                break;
            default:
                break;
        }

        block = mazeGenerator.getWalls().get((pacmanPosY+yOffset-3)*width+(pacmanPosX+xOffset));
//        System.out.print("X:");
//        System.out.print(pacmanPosX+xOffset);
//        System.out.print("Y:");
//        System.out.println(pacmanPosY+yOffset);
        if(block instanceof Wall) {
            System.out.println("Wall!!!");
            if (pacman.getRect().intersects(block.getRect())) {
//                System.out.println("Intersects");
                pacman.setCollision(true);
            }
        }
        else
            pacman.setCollision(false);
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        drawObjects(graphics);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Thread thread = new Thread(pacman);
        thread.start();
        checkCollisions();
        repaint();
    }
}
