package SceneObjects;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Ghost extends SceneObject implements Runnable {

    protected int pacmanX;
    protected int pacmanY;
    protected int direction = 0;
    protected boolean [] collision;

    protected Image[] ghostImages = new Image[4];

    public Ghost() {
    }

    @Override
    public void run() {
        int path = 1000000000;
        int minPath = 1000000000;
        int nextDirection = direction;
        System.out.println(pacmanX);
        System.out.println(pacmanY);
        for(int i = 0; i < 4; i++) {
            if(!(abs(i - direction)%2 == 0 && i != direction) && collision[i] == false && x % 16 == 0 && y % 16 == 0) {
                switch (i){
                    case 0:
                        path = (int) (pow(pacmanX - (x/width - 1), 2) + pow(pacmanY - y/height, 2));
                        break;
                    case 1:
                        path = (int) (pow(pacmanX - x/width, 2) + pow(pacmanY - (y/height - 1), 2));
                        break;
                    case 2:
                        path = (int) (pow(pacmanX - (x/width + 1), 2) + pow(pacmanY - y/height, 2));
                        break;
                    case 3:
                        path = (int) (pow(pacmanX - x/width, 2) + pow(pacmanY - (y/height + 1), 2));
                        break;
                    default:
                        break;
                }

                if(minPath > path) {
                    minPath = path;
//                    if(!((y/16 == 23 || y/16 == 11) && (x/16 == 12 || x/16 == 15) && i == 1))
                        nextDirection = i;
                }
            }
        }
        System.out.println(minPath);
        System.out.println();
        direction = nextDirection;
        switch (direction){
            case 0:
                x = x-4;
                setRect();
                break;
            case 1:
                y = y-4;
                setRect();
                break;
            case 2:
                x = x+4;
                setRect();
                break;
            case 3:
                y = y+4;
                setRect();
                break;
            default:
                break;
        }
        switchImage(direction);
        if(x/width == -1 && y/height == 14)
        {
            x = 27*width;
            setRect();
        }
        if(x/width == 28 && y/height == 14)
        {
            x = 0;
            setRect();
        }
    }

    public void getPacmanPos(PacmanObject pacman){}
    protected void setRect() {
        rectangle = new Rectangle(x-1,y-1,width+2,height+2);
    }
    public void setCollision(boolean[] coll) {collision = coll;}
    private void switchImage(int direction) {
        image = ghostImages[direction];
    }
}
