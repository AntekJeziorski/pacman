package Utils;

import javax.swing.*;
import java.awt.*;

public class ImageUtils {
    public static Image createImage(String filePath) {
        ImageIcon imageIcon = createImageIcon(filePath);
        return imageIcon.getImage();
    }

    public static ImageIcon createImageIcon(String filePath) {
        return new ImageIcon(filePath);
    }
}
