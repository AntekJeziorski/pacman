package Utils;

import javax.swing.*;
import java.awt.*;

/** @brief The ImageUtils class provides utility methods for working with images */
public class ImageUtils {
    /**
     * @brief Creates an Image object from the specified image file path.
     * @param filePath the file path of the image file
     * @return the Image object created from the image file
     */
    public static Image createImage(String filePath) {
        ImageIcon imageIcon = createImageIcon(filePath);
        return imageIcon.getImage();
    }

    /**
     * @brief Creates an ImageIcon object from the specified image file path
     * @param filePath the file path of the image file
     * @return the ImageIcon object created from the image file
     */
    public static ImageIcon createImageIcon(String filePath) {
        return new ImageIcon(filePath);
    }
}
