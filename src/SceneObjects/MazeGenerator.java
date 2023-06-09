package SceneObjects;

import Panels.MazePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MazeGenerator {
    private List<Wall> walls;

    public MazeGenerator() {
        walls = new ArrayList<Wall>();
        for(int i = 0; i < 400; i++) {
            for(int j = 0; j < 400; j++) {
                walls.add(new Wall(i,j));
            }
        }
    }

    public void showMaze(Graphics graph, MazePanel maze) {
        for(SceneObject wall: walls) {
            wall.show(graph, maze);
        }
    }

    public List<Wall> getWalls() {
        return walls;
    }
}
