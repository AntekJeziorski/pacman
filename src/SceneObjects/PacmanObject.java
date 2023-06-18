package SceneObjects;

import Settings.Controls;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;

/**
 * @brief Represents pacman object
 */
public class PacmanObject extends SceneObject implements Runnable {
    /** @brief Handler for pacman keyboard input */
    private final Controls controls = new Controls();
    /** @brief Current pacman direction [0 - left, 1 - up, 2 - right, 3 - down] */
    private int currentDirection;
    /** @brief List of collisions by direction*/
    private boolean [] collision;
    /** @brief List of images for each direction */
    private final Image[] pacmanImages = new Image[4];

    /**
     * @brief Pacman object parametric constructor
     * @param new_x initial x coordinate of the created object
     * @param new_y initial y coordinate of the created object
     */
    public PacmanObject(int new_x, int new_y) {
        pacmanImages[0] = new ImageIcon("src/images/left.gif").getImage();
        pacmanImages[1] = new ImageIcon("src/images/up.gif").getImage();
        pacmanImages[2] = new ImageIcon("src/images/right.gif").getImage();
        pacmanImages[3] = new ImageIcon("src/images/down.gif").getImage();
        image = pacmanImages[0];
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = new_x * width;
        y = new_y * height;
        setRect();

    }

    /** @brief Overridden run() method from {@link Runnable} interface
     * <p>
     * Is responsible for pacman movement depending on direction on {@link Panels.MazePanel} form player input
     */
    @Override
    public void run() {
        int newDirection = controls.getDirection();


        if(newDirection != -1 && newDirection != currentDirection && !collision[newDirection]) {
            if(abs(newDirection - currentDirection)%2 == 0)
                currentDirection = newDirection;
            if(x%width == 0 && y%height == 0)
                currentDirection = newDirection;
        }

        switch (currentDirection) {
            case 0 -> {
                if (!collision[0])
                    x = x - 4;
                setRect();
            }
            case 1 -> {
                if (!collision[1])
                    y = y - 4;
                setRect();
            }
            case 2 -> {
                if (!collision[2])
                    x = x + 4;
                setRect();
            }
            case 3 -> {
                if (!collision[3])
                    y = y + 4;
                setRect();
            }
            default -> {
            }
        }
        switchImage();

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

    /**
     * @brief Select image based on current movement direction
     */
    private void switchImage() {
        image = pacmanImages[currentDirection];
    }

    /**
     * @brief Set rectangle for collision detection
     */
    private void setRect() {
        rectangle = new Rectangle(x-1,y-1,width+2,height+2);
    }

    /**
     * @brief Gets current pacman direction
     * @return current pacman direction
     */
    public int getCurrentDirection()
    {
        return currentDirection;
    }

    /**
     * Gets controls handler
     * @return control handler
     */
    public Controls getKeyAdapter(){return controls;}

    /**
     * @brief Sets collision directions
     * @param coll list of collision directions
     */
    public void setCollision(boolean[] coll) {collision = coll;}
}
