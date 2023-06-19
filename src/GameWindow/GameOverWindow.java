package GameWindow;

import Utils.FontUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Utils.LeaderboardManager.getBestScore;

/** @brief The GameOverWindow class represents the game over window */
public class GameOverWindow extends JPanel implements ActionListener {
    /** @brief JButton taking the user back to the main menu */
    private final JButton backButton = new JButton("Back");

    /** @brief Number of points scored in a single game */
    private final long score;

    /** @brief Stylised custom font */
    private final Font pixelFont = FontUtils.readFonts("src/fonts/emulogic.ttf");

    /**
     * @brief Constructs a new GameOverWindow object
     * Initializes the layout and sets the background color
     * @param earnedPoints The number of points earned in the game
     */
    public GameOverWindow(long earnedPoints) {
        score = earnedPoints;
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        initialize();
    }
    /** @brief Initializes the components and adds them to the window */
    private void initialize() {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(20, 0, 20, 0);

        JLabel Title = new JLabel( "Game Over");
        Title.setFocusable(false);
        Title.setBackground(Color.BLACK);
        Title.setForeground(Color.YELLOW);
        Title.setFont(pixelFont.deriveFont(36f));

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        add(Title, constraints);

        JLabel newRecordText = new JLabel("! New Record !");
        newRecordText.setFocusable(false);
        newRecordText.setBackground(Color.BLACK);
        newRecordText.setForeground(Color.PINK);
        newRecordText.setFont(pixelFont.deriveFont(24f));
        newRecordText.setVisible(score >= getBestScore());

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        add(newRecordText, constraints);

        JLabel InfoText = new JLabel("- Your score -");
        InfoText.setFocusable(false);
        InfoText.setBackground(Color.BLACK);
        InfoText.setForeground(Color.YELLOW);
        InfoText.setFont(pixelFont.deriveFont(16f));

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        add(InfoText, constraints);

        JLabel Points = new JLabel(String.valueOf(score));
        Points.setFocusable(false);
        Points.setBackground(Color.BLACK);
        Points.setForeground(Color.YELLOW);
        Points.setFont(pixelFont.deriveFont(16f));

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        add(Points, constraints);

        backButton.setPreferredSize(new Dimension(250,60));
        backButton.setBackground(new Color(255, 255, 0));
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        backButton.setFont(pixelFont.deriveFont(16f));

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(backButton, constraints);
    }

    /**
     * @brief Handles the button click events
     * @param e The action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton){
            Pacman.openMainWindow();
        }
    }
}
