package SceneObjects;

import Panels.MazePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

public class SceneObject {

    protected int x;
    protected int y;
    protected int width = 16;
    protected int height = 16;
    protected Image image;

    public Dictionary<String, Integer> getPosition() {
        Dictionary<String, Integer> position =  new Hashtable<>();
        position.put("X", x);
        position.put("Y", y);
        return position;
    }

    protected void uploadImage(String imagePath) {
        image = new ImageIcon(imagePath).getImage();
    }

    public void show(Graphics graph, MazePanel maze) {
        graph.drawImage(image, x, y, maze);
    }


}
