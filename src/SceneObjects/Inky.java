package SceneObjects;

import javax.swing.*;

/**
 * Represents blue ghost
 *
 */
public class Inky extends Ghost{

    /** Blinky x coordinate */
    private int blinkyX;
    /** Blinky y coordinate */
    private int blinkyY;

    /**
     * Inky object parametric constructor
     * @param new_x initial x coordinate of the created object
     * @param new_y initial y coordinate of the created object
     */
    public Inky(int new_x, int new_y) {
        ghostImages[0] = new ImageIcon("src/images/Inky_left.gif").getImage();
        ghostImages[1] = new ImageIcon("src/images/Inky_up.gif").getImage();
        ghostImages[2] = new ImageIcon("src/images/Inky_right.gif").getImage();
        ghostImages[3] = new ImageIcon("src/images/Inky_down.gif").getImage();
        image = ghostImages[3];
        width = image.getWidth(null);
        height = image.getHeight(null);
        direction = 2;
        x = new_x * width;
        y = new_y * height;
        setRect();
    }

    /**
     * Sets private fields for blinky coordinates
     * @param blinky blinky object from which coordinates are fetched
     */
    public void getBlinkyPos(Blinky blinky) {
        blinkyX = blinky.getInfo().get("X")/blinky.getInfo().get("Width");
        blinkyY = blinky.getInfo().get("Y")/blinky.getInfo().get("Height");
    }

    /**
     * Calculates destination coordinates for Inky
     * @param pacman pacman object for which coordinates are calculated
     */
    public void getPacmanPos(PacmanObject pacman){
        int pacmanDirection = pacman.getCurrentDirection();
        int currentPacmanX = pacman.getInfo().get("X")/pacman.getInfo().get("Width");
        int currentPacmanY = pacman.getInfo().get("Y")/pacman.getInfo().get("Height");
        pacmanX = 2 * currentPacmanX - blinkyX;
        pacmanY = 2 * currentPacmanY - blinkyY;
    }
}
