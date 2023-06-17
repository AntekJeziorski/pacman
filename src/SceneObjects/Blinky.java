package SceneObjects;

public class Blinky extends Ghost{
    public Blinky(int new_x, int new_y) {
        super(new_x, new_y);
    }

    public void getPacmanPos(PacmanObject pacman) {
        int pacmanDirection = pacman.getCurrentDirection();
        int currentPacmanX = pacman.getInfo().get("X")/pacman.getInfo().get("Width");
        int currentPacmanY = pacman.getInfo().get("Y")/pacman.getInfo().get("Height");
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
        }
    }
}
