package Settings;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @brief Represents controls object
 */
public class Controls extends KeyAdapter {

    /** @brief Currently set direction */
    private int direction = -1;

    /**
     * @brief Overrides keyPressed method from {@link KeyAdapter} interface
     * @param event the event to be processed
     */
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

    /**
     * Gets current direction
     * @return returns direction
     */
    public int getDirection() {
        return direction;
    }
}