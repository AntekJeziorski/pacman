package SceneObjects;

import GameWindow.Pacman;
import Panels.MazePanel;
import Settings.Controls;

import java.awt.*;

public class PacmanObject extends SceneObject implements Runnable {
    private Controls direction = new Controls();
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
        int currentDirection = direction.getDirection();
        switch (currentDirection){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }
}
