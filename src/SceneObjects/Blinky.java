package SceneObjects;

import javax.swing.*;

/**
 * @brief Represents red ghost
 *
 */
public class Blinky extends Ghost{
    /**
     * @brief Blinky object parametric constructor
     * @param new_x initial x coordinate of the created object
     * @param new_y initial y coordinate of the created object
     */
    public Blinky(int new_x, int new_y) {
        ghostImages[0] = new ImageIcon("src/images/Blinky_left.gif").getImage();
        ghostImages[1] = new ImageIcon("src/images/Blinky_up.gif").getImage();
        ghostImages[2] = new ImageIcon("src/images/Blinky_right.gif").getImage();
        ghostImages[3] = new ImageIcon("src/images/Blinky_down.gif").getImage();
        image = ghostImages[3];
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = new_x * width;
        y = new_y * height;
        setRect();
    }

    /**
     * @brief Calculates destination coordinates for Blinky
     * @param pacman pacman object for which coordinates are calculated
     */
    public void getPacmanPos(PacmanObject pacman) {
        int pacmanDirection = pacman.getCurrentDirection();
        int currentPacmanX = pacman.getInfo().get("X")/pacman.getInfo().get("Width");
        int currentPacmanY = pacman.getInfo().get("Y")/pacman.getInfo().get("Height");
        switch (pacmanDirection) {
            case 0 -> {
                pacmanX = currentPacmanX - 1;
                pacmanY = currentPacmanY;
            }
            case 1 -> {
                pacmanX = currentPacmanX - 1;
                pacmanY = currentPacmanY - 1;
            }
            case 2 -> {
                pacmanX = currentPacmanX + 1;
                pacmanY = currentPacmanY;
            }
            case 3 -> {
                pacmanX = currentPacmanX;
                pacmanY = currentPacmanY + 1;
            }
            default -> {
            }
        }
    }
}
