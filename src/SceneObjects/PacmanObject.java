package SceneObjects;

import Settings.Controls;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;

public class PacmanObject extends SceneObject implements Runnable {
    private final Controls controls = new Controls();
    private int currentDirection;
    private int lastDirection;
    private boolean [] collision;

    private Image[] pacmanImages = new Image[4];
    public PacmanObject(int new_x, int new_y) {
//        uploadImage("src/images/right3_16.png");
        pacmanImages[0] = new ImageIcon("src/images/left3_16.png").getImage();
        pacmanImages[1] = new ImageIcon("src/images/up3_16.png").getImage();
        pacmanImages[2] = new ImageIcon("src/images/right3_16.png").getImage();
        pacmanImages[3] = new ImageIcon("src/images/down3_16.png").getImage();
        image = pacmanImages[0];
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = new_x * width;
        y = new_y * height;
//        rectangle = new Rectangle(x-1,y-1,width+2,height+2);
        setRect();

    }

    @Override
    public void run() {
        int newDirection = controls.getDirection();

        if(newDirection == -1) {
            newDirection = lastDirection;
        }

        if(newDirection != -1 && newDirection != currentDirection && !collision[newDirection]) {
            if(abs(newDirection - currentDirection)%2 == 0)
                currentDirection = newDirection;
            if(x%width == 0 && y%height == 0)
                currentDirection = newDirection;
        }



        switch (currentDirection){
            case 0:
                if(!collision[0])
                    x = x-4;
                setRect();
                break;
            case 1:
                if(!collision[1])
                    y = y-4;
                setRect();
                break;
            case 2: ;
                if(!collision[2])
                    x = x+4;
                setRect();
                break;
            case 3:
                if(!collision[3])
                    y = y+4;
                setRect();
                break;
            default:
                break;
        }
        switchImage(currentDirection);
        if(newDirection != -1 && lastDirection != newDirection)
            lastDirection = newDirection;
        else
            lastDirection = currentDirection;
    }

    private void switchImage(int direction) {
        image = pacmanImages[direction];
    }
    private void setRect() {
        rectangle = new Rectangle(x-1,y-1,width+2,height+2);
    }

    public int getCurrentDirection()
    {
        return currentDirection;
    }

    public Controls getKeyAdapter(){return controls;}

    public void setCollision(boolean[] coll) {collision = coll;}
}
