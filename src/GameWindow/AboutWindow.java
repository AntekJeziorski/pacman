package GameWindow;

import javax.swing.*;
import java.awt.*;
import javax.swing.JEditorPane;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;

public class AboutWindow extends JPanel implements ActionListener {
    private ImageIcon BigPacMan;
    private final JButton BackButton = new JButton("Back");

    private Font PixelFont;
    public AboutWindow() {
        setLayout(new GridBagLayout());
        setBackground(new Color(0, 0, 0));
        initialize();
    }

    private void readImage(){
        ImageIcon pacManIcon = new ImageIcon("src/images/big-pacman.png");
        Image image = pacManIcon.getImage();
        BigPacMan = new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
    }

    private void readFonts(){
        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/emulogic.ttf"));
            PixelFont = pixelFont.deriveFont(16f);
        } catch (FontFormatException | IOException e) {e.printStackTrace();}
    }
    private void initialize() {
        readImage();
        readFonts();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 10, 0);

        String aboutTitle = "About";
        JEditorPane Title = new JEditorPane();
        Title.setPreferredSize(new Dimension(400, 80));
        Title.setEditable(false);
        Title.setBackground(Color.BLACK);
        Title.setForeground(Color.YELLOW);
        Title.setFont(PixelFont.deriveFont(36f));
        Title.setText(aboutTitle);
        Title.setBorder(new LineBorder(Color.BLACK));

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        add(Title, constraints);

        String aboutText = "Pac-Man, the iconic arcade game, has captivated players for decades with its simple" +
                "yet addictive gameplay. Released in 1980, Pac-Manintroduced the world to a hungry, yellow " +
                "character navigating through a maze, devouring dots and evading colorful ghosts. " +
                "This application was developed as a project for .Net Java platform course.\n" +
                "\nDeveloped by:\n"+
                "Antoni Jeziorski,\n    259254@student.pwr.edu.pl\n" +
                "Szymon Sobczak,\n    259275@student.pwr.edu.pl\n" +
                "Jedrzej Szymczyk,\n    254898@student.pwr.edu.pl";
        JEditorPane AboutText = new JEditorPane();
        AboutText.setPreferredSize(new Dimension(400,400));
        AboutText.setEditable(false);
        AboutText.setBackground(Color.BLACK);
        AboutText.setForeground(Color.YELLOW);
        AboutText.setFont(PixelFont.deriveFont(12f));
        AboutText.setText(aboutText);
        AboutText.setPreferredSize(new Dimension(400, 300));
        AboutText.setBorder(new LineBorder(Color.BLACK));

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        add(AboutText, constraints);

        BackButton.setPreferredSize(new Dimension(250,60));
        BackButton.setBackground(new Color(255, 255, 0));
        BackButton.setFocusable(false);
        BackButton.addActionListener(this);
        BackButton.setFont(PixelFont);
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(BackButton, constraints);

        JLabel gifLabel = new JLabel(BigPacMan);
        gifLabel.setPreferredSize(new Dimension(50,50));
        constraints.anchor = GridBagConstraints.BASELINE;
        constraints.fill = GridBagConstraints.BASELINE;
        constraints.gridy = 3;
        add(gifLabel, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == BackButton){ Pacman.openMainWindow(); }
    }
}
