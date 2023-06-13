package SceneObjects;

import Settings.Controls;

import javax.swing.*;
import java.awt.*;

public class PacmanObject extends SceneObject implements Runnable {
    private final Controls controls = new Controls();
    private int lastDirection;

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
        rectangle = new Rectangle(x-1,y-1,width+2,height+2);

    }

    @Override
    public void run() {
        int currentDirection = controls.getDirection();
        if(currentDirection != lastDirection)
            lastDirection = currentDirection;
            switchImage(currentDirection);
        switch (currentDirection){
            case 0:
                x = x-4;
//                System.out.println("Left");
                break;
            case 1:
//                System.out.println("Up");
                y = y-4;
                break;
            case 2:
//                System.out.println("Right");
                x = x+4;
                break;
            case 3:
//                System.out.println("Down");
                y = y+4;
                break;
            default:
                break;
        }
    }

    private void switchImage(int direction) {
        image = pacmanImages[direction];
    }


    public Controls getKeyAdapter(){return controls;}
}
