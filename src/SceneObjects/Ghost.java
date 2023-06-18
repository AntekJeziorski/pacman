package SceneObjects;

import java.awt.*;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

/**
 * @brief Represents ghost object
 */
public class Ghost extends SceneObject implements Runnable {

    /** @brief Pacman x coordinate */
    protected int pacmanX;

    /** @brief Pacman y coordinate */
    protected int pacmanY;

    /** @brief Ghost direction [0 - left, 1 - up, 2 - right, 3 - down] */
    protected int direction = 0;

    /** @brief List of collisions by direction*/
    protected boolean [] collision;

    /** @brief List of images for each direction */
    protected Image[] ghostImages = new Image[4];

    /** @brief Non-parametric ghost object constructor */
    public Ghost() { }

    /** @brief Overridden run() method from {@link Runnable} interface
     * <p>
     * Is responsible for ghost movement depending on destination coordinates on {@link Panels.MazePanel}
     */
    @Override
    public void run() {
        int path = 1000000000;
        int minPath = 1000000000;
        int nextDirection = direction;
        for(int i = 0; i < 4; i++) {
            if(!(abs(i - direction)%2 == 0 && i != direction) && !collision[i] && x % 16 == 0 && y % 16 == 0) {
                switch (i) {
                    case 0 -> //noinspection IntegerDivisionInFloatingPointContext
                            path = (int) (pow(pacmanX - (x / width - 1), 2) + pow(pacmanY - y / height, 2));
                    case 1 -> //noinspection IntegerDivisionInFloatingPointContext
                            path = (int) (pow(pacmanX - x / width, 2) + pow(pacmanY - (y / height - 1), 2));
                    case 2 -> //noinspection IntegerDivisionInFloatingPointContext
                            path = (int) (pow(pacmanX - (x / width + 1), 2) + pow(pacmanY - y / height, 2));
                    case 3 -> //noinspection IntegerDivisionInFloatingPointContext
                            path = (int) (pow(pacmanX - x / width, 2) + pow(pacmanY - (y / height + 1), 2));
                    default -> {
                    }
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
            case 0 -> {
                x -= 2;
                setRect();
            }
            case 1 -> {
                y -= 2;
                setRect();
            }
            case 2 -> {
                x += 2;
                setRect();
            }
            case 3 -> {
                y += 2;
                setRect();
            }
            default -> {
            }
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
     * @brief "Abstract" method for calculating ghost destination based on pacman coordinates
     * @param pacman pacman object for which coordinates are calculated
     */
    public void getPacmanPos(PacmanObject pacman){}

    /**
     * @brief Set rectangle for collision detection
     */
    protected void setRect() {
        rectangle = new Rectangle(x-1,y-1,width+2,height+2);
    }

    /**
     * @brief Set local collision list based on collisions checked in {@link Panels.MazePanel}
     * @param coll list of collision directions
     */
    public void setCollision(boolean[] coll) {collision = coll;}

    /**
     * @brief Select image based on current movement direction
     */
    private void switchImage() {
        image = ghostImages[direction];
    }
}
