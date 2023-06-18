package SceneObjects;

import javax.swing.*;

public class Pinky extends Ghost{
    public Pinky(int new_x, int new_y) {
        ghostImages[0] = new ImageIcon("src/images/Pinky_left.gif").getImage();
        ghostImages[1] = new ImageIcon("src/images/Pinky_up.gif").getImage();
        ghostImages[2] = new ImageIcon("src/images/Pinky_right.gif").getImage();
        ghostImages[3] = new ImageIcon("src/images/Pinky_down.gif").getImage();
        image = ghostImages[2];
        width = image.getWidth(null);
        height = image.getHeight(null);
        direction = 2;
        x = new_x * width;
        y = new_y * height;
        setRect();
    }

    public void getPacmanPos(PacmanObject pacman){
        int pacmanDirection = pacman.getCurrentDirection();
        int currentPacmanX = pacman.getInfo().get("X")/pacman.getInfo().get("Width");
        int currentPacmanY = pacman.getInfo().get("Y")/pacman.getInfo().get("Height");
        switch (pacmanDirection){
            case 0:
                pacmanX = currentPacmanX - 2;
                pacmanY = currentPacmanY;
                break;
            case 1:
                pacmanX = currentPacmanX - 2;
                pacmanY = currentPacmanY - 2;
                break;
            case 2:
                pacmanX = currentPacmanX + 2;
                pacmanY = currentPacmanY;
                break;
            case 3:
                pacmanX = currentPacmanX;
                pacmanY = currentPacmanY + 2;
                break;
        }
    }
}
