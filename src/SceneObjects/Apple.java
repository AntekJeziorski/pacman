package SceneObjects;

import java.awt.*;

public class Apple extends SceneObject{
    public Apple(int new_x, int new_y) {
        uploadImage("src/images/apple.png");
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = new_x * width;
        y = new_y * height;
        rectangle = new Rectangle(x,y,width,height);
    }
}

