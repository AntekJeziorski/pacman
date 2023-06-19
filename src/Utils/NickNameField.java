package Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * @brief The NickNameField class extends JTextField and provides a text field with a placeholder feature
 * When the field does not have focus and no text is entered, it displays a placeholder text
 */
public class NickNameField extends JTextField implements FocusListener {

    /** @brief Placeholder text to be displayed when the field is empty and does not have focus */
    private final String placeholder;

    /** @brief Boolean value indicating whether to display the placeholder*/
    private boolean showPlaceholder;

    /**
     * @brief Constructs a new NickNameField object with the specified placeholder text
     * @param placeholder the placeholder text to be displayed when the field is empty and does not have focus
     */
    public NickNameField(String placeholder) {
        this.placeholder = placeholder;
        this.showPlaceholder = true;
        addFocusListener(this);
    }

    /**
     * @brief Overrides the paintComponent method to display the placeholder text if the field is empty and the placeholder is set to be shown.
     * @param g the Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty() && showPlaceholder) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.GRAY);
            FontMetrics fontMetrics = g2d.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(placeholder);
            int textHeight = fontMetrics.getHeight();
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() - textHeight) / 2 + fontMetrics.getAscent();

            g2d.drawString(placeholder, x, y);
            g2d.dispose();
        }
    }

    /**
     * @brief Overrides the focusGained method to hide the placeholder text when the field gains focus
     * @param e the FocusEvent representing the focus gained event
     */
    @Override
    public void focusGained(FocusEvent e) {
        showPlaceholder = false;
        repaint();
    }

    /**
     * @brief Overrides the focusLost method to show the placeholder text when the field loses focus and no text is entered.
     * @param e the FocusEvent representing the focus lost event
     */
    @Override
    public void focusLost(FocusEvent e) {
        showPlaceholder = true;
        repaint();
    }
}
