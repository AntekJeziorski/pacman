package Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class NickNameField extends JTextField implements FocusListener {
    private final String placeholder;
    private boolean showPlaceholder;

    public NickNameField(String placeholder) {
        this.placeholder = placeholder;
        this.showPlaceholder = true;
        addFocusListener(this);
    }

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

    @Override
    public void focusGained(FocusEvent e) {
        showPlaceholder = false;
        repaint();
    }

    @Override
    public void focusLost(FocusEvent e) {
        showPlaceholder = true;
        repaint();
    }
}
