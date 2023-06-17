package SceneObjects;

import javax.swing.*;

public class Inky extends Ghost{

    private int blinkyX;
    private int blinkyY;

    public Inky(int new_x, int new_y) {
        ghostImages[0] = new ImageIcon("src/images/ghost_test.png").getImage();
        ghostImages[1] = new ImageIcon("src/images/ghost_test.png").getImage();
        ghostImages[2] = new ImageIcon("src/images/ghost_test.png").getImage();
        ghostImages[3] = new ImageIcon("src/images/ghost_test.png").getImage();
        image = new ImageIcon("src/images/ghost_test.png").getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = new_x * width;
        y = new_y * height;
        setRect();
    }

    public void getBlinkyPos(Blinky blinky) {
        blinkyX = blinky.getInfo().get("X")/blinky.getInfo().get("Width");
        blinkyY = blinky.getInfo().get("Y")/blinky.getInfo().get("Height");
    }

    public void getPacmanPos(PacmanObject pacman){
        int pacmanDirection = pacman.getCurrentDirection();
        int currentPacmanX = pacman.getInfo().get("X")/pacman.getInfo().get("Width");
        int currentPacmanY = pacman.getInfo().get("Y")/pacman.getInfo().get("Height");
        pacmanX = 2 * currentPacmanX - blinkyX;
        pacmanY = 2 * currentPacmanY - blinkyY;
    }
}
