package SceneObjects;

import java.awt.*;

/**
 * @brief Represents wall object
 */
public class Wall extends SceneObject {
    /**
     * @brief Wall object parametric constructor
     * @param new_x initial x coordinate of the created object
     * @param new_y initial y coordinate of the created object
     */
    public Wall(int new_x, int new_y) {
        uploadImage("src/images/wall.png");
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = new_x*width;
        y = new_y*height;
        rectangle = new Rectangle(x,y,width,height);
    }
}
