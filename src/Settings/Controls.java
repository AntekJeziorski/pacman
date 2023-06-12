package Settings;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controls extends KeyAdapter {

    private int direction = 0;

    @Override
    public void keyPressed(KeyEvent event)
    {

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
        }
    }

    public int getDirection() {return direction;}
}