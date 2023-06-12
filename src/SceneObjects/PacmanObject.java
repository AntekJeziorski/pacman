package SceneObjects;

import Settings.Controls;

import java.awt.*;

public class PacmanObject extends SceneObject implements Runnable {
    private final Controls controls = new Controls();
    public PacmanObject(int new_x, int new_y) {
        uploadImage("src/images/right3_16.png");
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = new_x * width;
        y = new_y * height;
        rectangle = new Rectangle(x-1,y-1,width+2,height+2);
    }

    @Override
    public void run() {
        int currentDirection = controls.getDirection();
        switch (currentDirection){
            case 0:
                x = x-1;
                System.out.println("Left");
                break;
            case 1:
                System.out.println("Up");
                y = y-1;
                break;
            case 2:
                System.out.println("Right");
                x = x+1;
                break;
            case 3:
                System.out.println("Down");
                y = y+1;
                break;
            default:
                break;
        }
    }

    public Controls getKeyAdapter(){return controls;}
}
