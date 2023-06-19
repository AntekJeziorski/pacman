package SceneObjects;

import java.awt.*;

/** @brief Represents apple object */
public class Apple extends SceneObject{
    /**
     * @brief Apple object parametric constructor
     * @param new_x initial x coordinate of the created object
     * @param new_y initial y coordinate of the created object
     */
    public Apple(int new_x, int new_y) {
        uploadImage("src/images/apple.png");
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = new_x * width;
        y = new_y * height;
        rectangle = new Rectangle(x,y,width,height);
    }
}

