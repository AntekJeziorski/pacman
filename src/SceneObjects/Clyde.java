package SceneObjects;

import javax.swing.*;

import static java.lang.Math.pow;

public class Clyde extends Ghost{
    public Clyde(int new_x, int new_y) {
        ghostImages[0] = new ImageIcon("src/images/Clyde_left.gif").getImage();
        ghostImages[1] = new ImageIcon("src/images/Clyde_up.gif").getImage();
        ghostImages[2] = new ImageIcon("src/images/Clyde_right.gif").getImage();
        ghostImages[3] = new ImageIcon("src/images/Clyde_down.gif").getImage();
        image = ghostImages[3];
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = new_x * width;
        y = new_y * height;
        setRect();
    }

    public void getPacmanPos(PacmanObject pacman) {
        int pacmanDirection = pacman.getCurrentDirection();
        int currentPacmanX = pacman.getInfo().get("X")/pacman.getInfo().get("Width");
        int currentPacmanY = pacman.getInfo().get("Y")/pacman.getInfo().get("Height");
        if(pow(currentPacmanX - x/16, 2) + pow(currentPacmanY - y/16, 2) > 64){
            switch (pacmanDirection){
                case 0:
                    pacmanX = currentPacmanX - 1;
                    pacmanY = currentPacmanY;
                    break;
                case 1:
                    pacmanX = currentPacmanX - 1;
                    pacmanY = currentPacmanY - 1;
                    break;
                case 2:
                    pacmanX = currentPacmanX + 1;
                    pacmanY = currentPacmanY;
                    break;
                case 3:
                    pacmanX = currentPacmanX;
                    pacmanY = currentPacmanY + 1;
                    break;
                default:
                    break;
            }
        }
        else {
            pacmanX = 0;
            pacmanY = 32;
        }


    }
}
