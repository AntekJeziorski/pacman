package Settings;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controls extends KeyAdapter {

    private int direction = -1;

    @Override
    public void keyPressed(KeyEvent event)
    {
//        System.out.println("Key Pressed");
        int key = event.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                direction = 0;
                break;
            case KeyEvent.VK_UP:
                direction = 1;
                break;
            case KeyEvent.VK_RIGHT:
                direction = 2;
                break;
            case KeyEvent.VK_DOWN:
                direction = 3;
                break;
            default:
                direction = -1;
                break;
        }
    }

    public int getDirection() {
        int newDirection = direction;
//        direction = -1;
        return newDirection;
    }
}