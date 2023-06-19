package GameWindow;

import Utils.FontUtils;
import Utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** @brief The AboutWindow class represents the about window that contains information about the game */
public class AboutWindow extends JPanel implements ActionListener {
    /** @brief JButton taking the user back to the main menu */
    private final JButton backButton = new JButton("Back");

    /** @brief Stylised custom font */
    private final Font pixelFont = FontUtils.readFonts("src/fonts/emulogic.ttf");

    /**
     * @brief Constructs a new AboutWindow object
     * Initializes the layout and sets the background color
     */
    public AboutWindow() {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        initialize();
    }

    /** @brief Initializes the components and adds them to the window */
    private void initialize() {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 10, 0);

        String aboutTitle = "About";
        JEditorPane Title = new JEditorPane();
        Title.setPreferredSize(new Dimension(400, 80));
        Title.setEditable(false);
        Title.setFocusable(false);
        Title.setBackground(Color.BLACK);
        Title.setForeground(Color.YELLOW);
        Title.setFont(pixelFont.deriveFont(36f));
        Title.setText(aboutTitle);
        Title.setBorder(new LineBorder(Color.BLACK));

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        add(Title, constraints);

        String aboutText = """
                Pac-Man, the iconic arcade game, has captivated players for decades with its simpleyet addictive
                gameplay. Released in 1980, Pac-Man introduced the world to a hungry, yellow character navigating
                through a maze, devouring dots and evading ghosts. This application was developed as a project for .Net Java platform course.
                
                Developed by:
                Antoni Jeziorski,
                    259254@student.pwr.edu.pl
                Szymon Sobczak,
                    259275@student.pwr.edu.pl
                Jedrzej Szymczyk,
                    254898@student.pwr.edu.pl""";
        JEditorPane AboutText = new JEditorPane();
        AboutText.setPreferredSize(new Dimension(400,400));
        AboutText.setEditable(false);
        AboutText.setFocusable(false);
        AboutText.setBackground(Color.BLACK);
        AboutText.setForeground(Color.YELLOW);
        AboutText.setFont(pixelFont.deriveFont(12f));
        AboutText.setText(aboutText);
        AboutText.setPreferredSize(new Dimension(400, 300));
        AboutText.setBorder(new LineBorder(Color.BLACK));

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        add(AboutText, constraints);

        backButton.setPreferredSize(new Dimension(250,60));
        backButton.setBackground(new Color(255, 255, 0));
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        backButton.setFont(pixelFont.deriveFont(16f));
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(backButton, constraints);

        Image bigPacMan = ImageUtils.createImage("src/images/pacman128.png");
        ImageIcon bigPacManScaled = new ImageIcon(bigPacMan.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel gifLabel = new JLabel(bigPacManScaled);
        gifLabel.setPreferredSize(new Dimension(50,50));
        constraints.anchor = GridBagConstraints.BASELINE;
        constraints.fill = GridBagConstraints.BASELINE;
        constraints.gridy = 3;
        add(gifLabel, constraints);
    }

    /**
     * @brief Handles the button click events
     * @param e The action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) { Pacman.openMainWindow(); }
    }
}
