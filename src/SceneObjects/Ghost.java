package SceneObjects;

import javax.swing.*;
import java.awt.*;

public class Ghost extends SceneObject implements Runnable {

    private int pacmanX;
    private int pacmanY;
    private int direction;

    public Ghost(int new_x, int new_y) {
        image = new ImageIcon("src/images/ghost_test.png").getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = new_x * width;
        y = new_y * height;
        setRect();
    }


    @Override
    public void run() {
        x = x+4;
        setRect();
    }

    public void getPacmanPos(PacmanObject pacman) {
        int pacmanDirection = pacman.getCurrentDirection();
        int currentPacmanX = pacman.getInfo().get("X")/pacman.getInfo().get("Width");
        int currentPacmanY = pacman.getInfo().get("Y")/pacman.getInfo().get("Height");
        switch (pacmanDirection){
            case 0:
                pacmanX = currentPacmanX - 1;
                pacmanY = currentPacmanY;
                break;
            case 1:
                pacmanX = currentPacmanX - 1;
                pacmanY = currentPacmanY - 1;
                break;
            case 2:
                pacmanX = currentPacmanX + 1;
                pacmanY = currentPacmanY;
                break;
            case 3:
                pacmanX = currentPacmanX;
                pacmanY = currentPacmanY + 1;
                break;
        }
    }

    private void setRect() {
        rectangle = new Rectangle(x-1,y-1,width+2,height+2);
    }

}
