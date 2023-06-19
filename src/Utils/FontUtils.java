package Utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/** @brief The FontUtils class provides utility methods for working with fonts  */
public class FontUtils {
    /**
     * @brief Reads a TrueType font file from the specified file path and creates a Font object.
     * @param filePath the file path of the TrueType font file
     * @return the Font object created from the font file
     */
    public static Font readFonts(String filePath) {
        Font readFont = null;
        try {
            readFont = Font.createFont(Font.TRUETYPE_FONT, new File(filePath));
        } catch (FontFormatException | IOException e) { e.printStackTrace(); }
        return readFont;
    }
}
