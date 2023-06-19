package GameWindow;

import Utils.FontUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.ActionListener;

/** @brief The MainWindow class represents the main menu window of the game */
public class MainWindow extends JPanel implements ActionListener {

    /** @brief JButton taking the user to the new game window */
    private final JButton newGameButton = new JButton("New Game");

    /** @brief JButton taking the user to the leaderboard window */
    private final JButton leaderButton = new JButton("Leader Board");

    /** @brief JButton taking the user to the about window */
    private final JButton aboutButton = new JButton("About");

    /** @brief JButton to close the programme */
    private final JButton exitButton = new JButton("Exit");

    /** @brief Stylised custom font for game title */
    private final Font titleFont = FontUtils.readFonts("src/fonts/PAC-FONT.TTF");

    /** @brief Stylised custom font */
    private final Font pixelFont = FontUtils.readFonts("src/fonts/emulogic.ttf");

    /**
     * @brief Constructs a new MainWindow object
     * Initializes the layout and sets the background color
     */
    public MainWindow() {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        initialize();
    }

    /** @brief Initializes the components and adds them to the window */
    private void initialize() {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 10, 0);

        JLabel title = new JLabel("PaCMaN");
        title.setBounds(0, 0, 100, 40);
        title.setFont(titleFont.deriveFont(48f));
        title.setForeground(Color.WHITE);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        add(title, constraints);

        ImageIcon pacManGif = new ImageIcon("src/images/pacman.gif");
        JLabel gifLabel = new JLabel(pacManGif);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(gifLabel, constraints);

        newGameButton.setPreferredSize(new Dimension(250,60));
        newGameButton.setBackground(new Color(255, 255, 0));
        newGameButton.setFocusable(false);
        newGameButton.addActionListener(this);
        newGameButton.setFont(pixelFont.deriveFont(16f));

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(newGameButton, constraints);

        leaderButton.setPreferredSize(new Dimension(250,60));
        leaderButton.setBackground(new Color(255, 255, 0));
        leaderButton.setFocusable(false);
        leaderButton.addActionListener(this);
        leaderButton.setFont(pixelFont.deriveFont(14f));

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(leaderButton, constraints);

        aboutButton.setPreferredSize(new Dimension(250,60));
        aboutButton.setBackground(new Color(255, 255, 0));
        aboutButton.setFocusable(false);
        aboutButton.addActionListener(this);
        aboutButton.setFont(pixelFont.deriveFont(16f));

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(aboutButton, constraints);

        exitButton.setPreferredSize(new Dimension(250,60));
        exitButton.setBackground(new Color(255, 255, 0));
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);
        exitButton.setFont(pixelFont.deriveFont(16f));

        constraints.gridx = 0;
        constraints.gridy = 5;
        add(exitButton, constraints);

        JLabel bottomLabel = new JLabel("Developed by: S.A.J - 2023");
        bottomLabel.setForeground(Color.GRAY);
        bottomLabel.setFont(pixelFont.deriveFont(8f));

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.insets = new Insets(20, 0, 10, 0);
        add(bottomLabel, constraints);
    }

    /**
     * @brief Handles the button click events
     * @param e The action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton) { Pacman.openNewPlayerWindow(); }
        if (e.getSource() == leaderButton) { Pacman.openLeaderBoardWindow(); }
        if (e.getSource() == aboutButton) { Pacman.openAboutWindow(); }
        if (e.getSource() == exitButton) { System.exit(0); }
    }

}
