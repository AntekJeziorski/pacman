package SceneObjects;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Ghost extends SceneObject implements Runnable {

    /** Pacman x coordinate */
    protected int pacmanX;
    /** Pacman y coordinate */
    protected int pacmanY;
    /** Ghost direction [0 - left, 1 - up, 2 - right, 3 - down] */
    protected int direction = 0;
    /** List of collisions by direction*/
    protected boolean [] collision;
    /** List of images for each direction */
    protected Image[] ghostImages = new Image[4];

    /** Non-parametric ghost class constructor */
    public Ghost() { }

    /** @brief Overriden run() method from {@link Runnable} interface
     *
     * Is responsible for ghost movement depending on destination coordinates on {@link Panels.MazePanel}
     */
    @Override
    public void run() {
        int path = 1000000000;
        int minPath = 1000000000;
        int nextDirection = direction;
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
        direction = nextDirection;
        switch (direction) {
            case 0:
                x -= 2;
                setRect();
                break;
            case 1:
                y -= 2;
                setRect();
                break;
            case 2:
                x += 2;
                setRect();
                break;
            case 3:
                y += 2;
                setRect();
                break;
            default:
                break;
        }

        switchImage();

        if(x/width == -1 && y/height == 14) {
            x = 27*width;
            setRect();
        }

        if(x/width == 28 && y/height == 14) {
            x = 0;
            setRect();
        }
    }

    /**
     * "Abstract" method for calculating ghost destination based on pacman coordinates
     * @param pacman pacman object for which coordinates are calculated
     */
    public void getPacmanPos(PacmanObject pacman){}

    /**
     * Set rectangle for collision detection
     */
    protected void setRect() {
        rectangle = new Rectangle(x-1,y-1,width+2,height+2);
    }

    /**
     * Set local collision list based on collisions checked in {@link Panels.MazePanel}
     * @param coll list of collision directions
     */
    public void setCollision(boolean[] coll) {collision = coll;}

    /**
     * Select image based on current movement direction
     */
    private void switchImage() {
        image = ghostImages[direction];
    }
}
