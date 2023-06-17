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

public class MazePanel extends JPanel implements ActionListener {
    private final MazeGenerator mazeGenerator;
    private final PacmanObject pacman;

    private final Ghost ghost;
    private final Timer timer;
    private KeyAdapter pacmanKeyAdapter;

    private int points = 0;


    public MazePanel() {
        mazeGenerator = new MazeGenerator();
        pacman = new PacmanObject(14,26);
        timer = new Timer(50, this);
        ghost = new Blinky(10,26);
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
        ghost.show(graphics, this);
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
//        int pacmanDirection = pacman.getCurrentDirection();
        int xOffset = 0;
        int yOffset = 0;
        SceneObject[] blocks = new SceneObject[4];
        boolean [] out = new boolean[4];

//        switch (pacmanDirection){
//            case 0:
//                xOffset = -1;
//                yOffset = 0;
//                break;
//            case 1:
//                xOffset = 0;
//                yOffset = -1;
//                break;
//            case 2:
//                xOffset = 1;
//                yOffset = 0;
//                break;
//            case 3:
//                xOffset = 0;
//                yOffset = 1;
//                break;
//            default:
//                break;
//        }

        blocks[0] = mazeGenerator.getWalls().get((pacmanPosY-3)*width+(pacmanPosX-1));
        blocks[1] = mazeGenerator.getWalls().get((pacmanPosY-4)*width+(pacmanPosX));
        blocks[2] = mazeGenerator.getWalls().get((pacmanPosY-3)*width+(pacmanPosX+1));
        blocks[3] = mazeGenerator.getWalls().get((pacmanPosY-2)*width+(pacmanPosX));
//        System.out.print("X:");
//        System.out.print(pacmanPosX+xOffset);
//        System.out.print("Y:");
//        System.out.println(pacmanPosY+yOffset);
        for (int i = 0; i < blocks.length; i++) {
            if(blocks[i] instanceof Wall) {
//                System.out.println("Wall!!!");
                if (pacman.getRect().intersects(blocks[i].getRect())) {
//                System.out.println("Intersects");
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

        SceneObject block = mazeGenerator.getDots().get((pacmanPosY+yOffset-3)*width+(pacmanPosX+xOffset));

        if(block instanceof Dot) {
            if (pacman.getRect().intersects(block.getRect())) {
                points++;
//                System.out.println(points);
                mazeGenerator.deleteDot((pacmanPosY+yOffset-3)*width+(pacmanPosX+xOffset), block.getInfo().get("X"), block.getInfo().get("Y"));
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
    public void paintComponent(Graphics graphics)
    {
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
