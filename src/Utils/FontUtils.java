package Utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontUtils {
    public static Font readFonts(String filePath) {
        Font readFont = null;
        try {
            readFont = Font.createFont(Font.TRUETYPE_FONT, new File(filePath));
        } catch (FontFormatException | IOException e) { e.printStackTrace(); }
        return readFont;
    }
}
