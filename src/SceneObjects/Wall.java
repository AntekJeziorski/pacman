package SceneObjects;

public class Wall extends SceneObject {

    public Wall(int new_x, int new_y) {
        uploadImage("src/images/wall.png");
        x = new_x;
        y = new_y;
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
}
