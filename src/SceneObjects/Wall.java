package SceneObjects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Wall extends SceneObject {
    public Wall(int new_x, int new_y) {
        uploadImage("src/images/wall.png");
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = new_x*width;
        y = new_y*height;
        rectangle = new Rectangle(x,y,width,height);
    }
}
