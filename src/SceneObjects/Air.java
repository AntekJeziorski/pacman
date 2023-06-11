package SceneObjects;

import java.awt.*;

public class Air extends SceneObject{
    public Air(int new_x, int new_y) {
        width = 16;
        height = 16;
        x = new_x * width;
        y = new_y * height;
        rectangle = new Rectangle(x,y,width,height);
    }
}
