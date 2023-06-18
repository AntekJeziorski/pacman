package Settings;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controls extends KeyAdapter {

    private int direction = -1;

    @Override
    public void keyPressed(KeyEvent event)
    {
        int key = event.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT -> direction = 0;
            case KeyEvent.VK_UP -> direction = 1;
            case KeyEvent.VK_RIGHT -> direction = 2;
            case KeyEvent.VK_DOWN -> direction = 3;
            default -> direction = -1;
        }
    }

    public int getDirection() {
        return direction;
    }
}