package SceneObjects;

import Panels.MazePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MazeGenerator {
    private List<SceneObject> walls;

    public MazeGenerator() {
        walls = new ArrayList<>();
        walls.add(new Wall(0, 0));
        walls.add(new Wall(0, 16));
        walls.add(new Wall(0, 32));
        walls.add(new Wall(16, 0));
        walls.add(new Wall(48-16, 48-16));
        walls.add(new Wall(200-8, 200-8));
        walls.add(new Wall(200-8, 0));
        walls.add(new Wall(400-16, 0));
//        for(int i = 0; i < 10; i++) {
//            for(int j = 0; j < 10; j++) {
//                walls.add(new Wall(400, 400));
//            }
//        }
    }

    public void showMaze(Graphics graph, MazePanel maze) {
        for(SceneObject wall: walls) {
            wall.show(graph, maze);
        }
    }

    public List<SceneObject> getWalls() {
        return walls;
    }
}
