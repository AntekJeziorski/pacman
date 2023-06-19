package SceneObjects;

import javax.swing.*;

/** @brief Represents blue ghost */
public class Inky extends Ghost{

    /** @brief Blinky x coordinate */
    private int blinkyX;

    /** @brief Blinky y coordinate */
    private int blinkyY;

    /**
     * @brief Inky object parametric constructor
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
     * @brief Sets private fields for blinky coordinates
     * @param blinky blinky object from which coordinates are fetched
     */
    public void getBlinkyPos(Blinky blinky) {
        blinkyX = blinky.getInfo().get("X")/blinky.getInfo().get("Width");
        blinkyY = blinky.getInfo().get("Y")/blinky.getInfo().get("Height");
    }

    /**
     * @brief Calculates destination coordinates for Inky
     * @param pacman pacman object for which coordinates are calculated
     */
    public void getPacmanPos(PacmanObject pacman){
        int currentPacmanX = pacman.getInfo().get("X")/pacman.getInfo().get("Width");
        int currentPacmanY = pacman.getInfo().get("Y")/pacman.getInfo().get("Height");
        pacmanX = 2 * currentPacmanX - blinkyX;
        pacmanY = 2 * currentPacmanY - blinkyY;
    }
}
