package SceneObjects;

import java.awt.*;

/**
 * @brief Represents dot object
 */
public class Dot extends SceneObject{
    /**
     * @brief Dot object parametric constructor
     * @param new_x initial x coordinate of the created object
     * @param new_y initial y coordinate of the created object
     */
    public Dot(int new_x, int new_y) {
        uploadImage("src/images/dot.png");
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = new_x * width;
        y = new_y * height;
        rectangle = new Rectangle(x+4,y+4,8,8);
    }
}
