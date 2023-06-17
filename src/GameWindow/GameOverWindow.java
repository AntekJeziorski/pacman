package GameWindow;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameOverWindow extends JPanel implements ActionListener {
    private final JButton BackButton = new JButton("Back");
    private long points;
    private Font PixelFont;
    public GameOverWindow(long earnedPoints) {
        points = earnedPoints;
        setLayout(new GridBagLayout());
        setBackground(new Color(0, 0, 0));
        initialize();
    }

    private void readFonts(){
        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/emulogic.ttf"));
            PixelFont = pixelFont.deriveFont(16f);
        } catch (FontFormatException | IOException e) {e.printStackTrace();}
    }
    private void initialize() {
        readFonts();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(20, 0, 20, 0);


        JLabel Title = new JLabel( "Game Over");

        Title.setFocusable(false);
        Title.setBackground(Color.BLACK);
        Title.setForeground(Color.YELLOW);
        Title.setFont(PixelFont.deriveFont(36f));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        add(Title, constraints);

        JLabel InfoText = new JLabel("Your score:");
        InfoText.setFocusable(false);
        InfoText.setBackground(Color.BLACK);
        InfoText.setForeground(Color.YELLOW);
        InfoText.setFont(PixelFont.deriveFont(16f));

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        add(InfoText, constraints);

        JLabel Points = new JLabel(String.valueOf(points));
        Points.setFocusable(false);
        Points.setBackground(Color.BLACK);
        Points.setForeground(Color.YELLOW);
        Points.setFont(PixelFont.deriveFont(16f));

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        add(Points, constraints);

        BackButton.setPreferredSize(new Dimension(250,60));
        BackButton.setBackground(new Color(255, 255, 0));
        BackButton.setFocusable(false);
        BackButton.addActionListener(this);
        BackButton.setFont(PixelFont);
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(BackButton, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == BackButton){ Pacman.openMainWindow(); }
    }
}
