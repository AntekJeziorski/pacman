package SceneObjects;

import java.awt.*;

public class Dot extends SceneObject{
    public Dot(int new_x, int new_y) {
        uploadImage("src/images/dot.png");
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = new_x * width;
        y = new_y * height;
        rectangle = new Rectangle(x+4,y+4,8,8);
    }
}
