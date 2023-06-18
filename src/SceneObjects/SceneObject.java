package SceneObjects;

import Panels.MazePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @brief Represents scene object
 */
public class SceneObject {
    /** @brief Object x coordinates */
    protected int x;
    /** @brief Object y coordinates */
    protected int y;
    /** @brief Object width */
    protected int width;
    /** @brief Object height  */
    protected int height;
    /** @brief Object's image */
    protected Image image;
    /** @brief Object's rectangle representation in coordinate system */
    protected Rectangle rectangle;

    /** @brief Non-parametric scene object constructor */
    public SceneObject() {}

    /**
     * @brief Returns basic information about scene object
     * @return dictionary of object's coordinates and dimensions
     */
    public Dictionary<String, Integer> getInfo() {
        Dictionary<String, Integer> position =  new Hashtable<>();
        position.put("X", x);
        position.put("Y", y);
        position.put("Width", width);
        position.put("Height", height);
        return position;
    }

    /**
     * @brief Gets scene object's rectangle object
     * @return scene object's rectangle object
     */
    public Rectangle getRect() {
        return rectangle;
    }

    /**
     * @brief Uploads image file to object
     * @param imagePath image file path
     */
    protected void uploadImage(String imagePath) {
        ImageIcon tmp = new ImageIcon(imagePath);
        image =  tmp.getImage();
    }

    /**
     * @brief Shows object on {@link MazePanel}
     * @param graph graphics handler
     * @param maze destination panel
     */
    public void show(Graphics graph, MazePanel maze) {
        graph.drawImage(image, x, y, maze);
    }
}
