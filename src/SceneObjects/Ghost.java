package SceneObjects;

import javax.swing.*;
import java.awt.*;

public class Ghost extends SceneObject implements Runnable {

    protected int pacmanX;
    protected int pacmanY;
    protected int direction;

    protected boolean [] collision;

    protected Image[] ghostImages = new Image[4];

    public Ghost() {
    }


    @Override
    public void run() {
        x = x+4;
        setRect();
    }

    public void getPacmanPos(PacmanObject pacman){}

    protected void setRect() {
        rectangle = new Rectangle(x-1,y-1,width+2,height+2);
    }

}
