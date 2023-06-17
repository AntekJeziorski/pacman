package SceneObjects;

import Panels.MazePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class SceneObject extends Rectangle {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;
    protected Rectangle rectangle;

    public SceneObject() {}

    public Dictionary<String, Integer> getInfo() {
        Dictionary<String, Integer> position =  new Hashtable<>();
        position.put("X", x);
        position.put("Y", y);
        position.put("Width", width);
        position.put("Height", height);
        return position;
    }

    public Rectangle getRect() {
        return rectangle;
    }
    protected int randNumber(int min, int max)
    {
        return (min + (int) (Math.random() * ((max - min) + 1))) * 10;
    }

    protected void uploadImage(String imagePath) {
        ImageIcon tmp = new ImageIcon(imagePath);
        image =  tmp.getImage();
    }

    public void show(Graphics graph, MazePanel maze) {
        graph.drawImage(image, x, y, maze);
    }


}
