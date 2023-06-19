package SceneObjects;

import java.awt.*;

/** @brief Represents air object */
public class Air extends SceneObject{
    /**
     * @brief Air object parametric constructor
     * @param new_x initial x coordinate of the created object
     * @param new_y initial y coordinate of the created object
     */
    public Air(int new_x, int new_y) {
        width = 16;
        height = 16;
        x = new_x * width;
        y = new_y * height;
        rectangle = new Rectangle(x,y,width,height);
    }
}
