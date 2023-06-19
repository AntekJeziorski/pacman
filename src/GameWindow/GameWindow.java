package GameWindow;

import Panels.MazePanel;
import Utils.FontUtils;
import Utils.ImageUtils;

import javax.swing.*;
import java.awt.*;

import static Utils.LeaderboardManager.getBestScore;
import static Utils.LeaderboardManager.saveScoreRecord;

public class GameWindow extends JPanel {
    private String nickname;
    private long score = 0;
    private int lives = 3;
    private final MazePanel mazePanel;
    private final JLabel ScoreValue = new JLabel("0");
    private final JLabel topScoreValue = new JLabel(String.valueOf(getBestScore()));
    private final JLabel Lives = new JLabel(String.valueOf(lives));
    private final Image pacManPicture = ImageUtils.createImage("src/images/pacman128.png");
    private final Font pixelFont = FontUtils.readFonts("src/fonts/emulogic.ttf");

    public GameWindow(String playerNickname) {
        mazePanel = new MazePanel();
        nickname = playerNickname;

        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        initialize();
    }

    private void initialize() {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;

        ScoreValue.setPreferredSize(new Dimension(448,60));
        ScoreValue.setFont(pixelFont.deriveFont(16f));
        ScoreValue.setForeground(Color.WHITE);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(ScoreValue, constraints);

        mazePanel.addPropertyChangeListener(evt -> {
            String propertyName = evt.getPropertyName();
            if (propertyName.equals("Points")) {
                score = (long) evt.getNewValue();
                String newValue = String.valueOf(score);
                ScoreValue.setText(newValue);
            }

            if (propertyName.equals("Lives")){
                int currentLives = (int) evt.getNewValue();
                lives = currentLives;
                Lives.setText(String.valueOf(lives));
                if (currentLives == 0) {
                    saveScoreRecord(nickname, score);
                    Pacman.openGameOverWindow(score);
                }
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.CENTER;
        add(mazePanel, constraints);

        JPanel nestedPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraintsNested = new GridBagConstraints();

        nestedPanel.setBackground(Color.BLACK);

        ImageIcon bigPacManScaled = new ImageIcon(pacManPicture.getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        JLabel pacManIcon = new JLabel(bigPacManScaled);
        pacManIcon.setPreferredSize(new Dimension(50,50));
        constraintsNested.anchor = GridBagConstraints.WEST;
        constraintsNested.fill = GridBagConstraints.WEST;
        constraintsNested.gridx = 0;
        constraintsNested.gridy = 0;
        constraintsNested.insets = new Insets(10, 0, 0, 0);
        nestedPanel.add(pacManIcon, constraintsNested);

        Lives.setFocusable(false);
        Lives.setBackground(Color.BLACK);
        Lives.setForeground(Color.YELLOW);
        Lives.setFont(pixelFont.deriveFont(16f));

        constraintsNested.insets = new Insets(10, 20, 0, 20);
        constraintsNested.gridx = 1;
        constraintsNested.gridy = 0;
        constraintsNested.anchor = GridBagConstraints.CENTER;
        constraintsNested.fill = GridBagConstraints.CENTER;
        nestedPanel.add(Lives, constraintsNested);

        JLabel bestScoreText = new JLabel("Top Score");
        bestScoreText.setFont(pixelFont.deriveFont(12f));
        bestScoreText.setForeground(Color.WHITE);
        constraintsNested.insets = new Insets(10, 20, 0, 0);
        constraintsNested.gridx = 2;
        constraintsNested.gridy = 0;
        constraintsNested.anchor = GridBagConstraints.CENTER;
        constraintsNested.fill = GridBagConstraints.CENTER;
        nestedPanel.add(bestScoreText, constraintsNested);

        topScoreValue.setFont(pixelFont.deriveFont(12f));
        topScoreValue.setForeground(Color.WHITE);
        constraintsNested.insets = new Insets(10, 20, 0, 0);
        constraintsNested.gridx = 3;
        constraintsNested.gridy = 0;
        constraintsNested.anchor = GridBagConstraints.CENTER;
        constraintsNested.fill = GridBagConstraints.CENTER;
        nestedPanel.add(topScoreValue, constraintsNested);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.WEST;
        add(nestedPanel, constraints);

        mazePanel.initStartAdapter();
    }

    public void requestFocusInMazePanel()
    {
        mazePanel.requestFocusInWindow();
    }
}
