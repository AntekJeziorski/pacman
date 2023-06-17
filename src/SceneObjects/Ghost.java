package SceneObjects;

import javax.swing.*;
import java.awt.*;

public class Ghost extends SceneObject implements Runnable {

    protected int pacmanX;
    protected int pacmanY;
    protected int direction;

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

    public void getPacmanPos(PacmanObject pacman){}

    private void setRect() {
        rectangle = new Rectangle(x-1,y-1,width+2,height+2);
    }

}
