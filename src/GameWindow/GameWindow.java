package GameWindow;

import Panels.MazePanel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class GameWindow extends JPanel {
    private final GridBagConstraints gbc;
    private MazePanel mazePanel;
    private String nickname;
    private long score = 0;
    private int lives = 3;
    private final JLabel ScoreValue = new JLabel("0");
    JLabel Lives = new JLabel(String.valueOf(lives));
    private ImageIcon pacManPicture;

    private Font ButtonFont;

    public GameWindow(String playerNickname) {
        mazePanel = new MazePanel();
        gbc = new GridBagConstraints();
        nickname = playerNickname;
        System.out.println(nickname);
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        initMazePanel();
    }

    private void readImage(){
        ImageIcon pacManIcon = new ImageIcon("src/images/pacman128.png");
        Image image = pacManIcon.getImage();
        pacManPicture = new ImageIcon(image.getScaledInstance(32, 32, Image.SCALE_SMOOTH));
    }
    private void readFonts() {
        try {
            Font buttonFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/emulogic.ttf"));
            ButtonFont = buttonFont.deriveFont(16f);
        } catch (FontFormatException | IOException e) {e.printStackTrace();}
    }

    private void initMazePanel() {
        readImage();
        readFonts();
        ScoreValue.setPreferredSize(new Dimension(448,60));
        ScoreValue.setFont(ButtonFont);
        ScoreValue.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.CENTER;
        add(ScoreValue, gbc);

        mazePanel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
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
                    if (  currentLives == 0) {
                        Pacman.openGameOverWindow(score);
                    }
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.CENTER;
        add(mazePanel, gbc);

        JPanel nestedPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcNested = new GridBagConstraints();


        nestedPanel.setBackground(Color.BLACK);

        JLabel pacManIcon = new JLabel(pacManPicture);
        pacManIcon.setPreferredSize(new Dimension(50,50));
        gbcNested.anchor = GridBagConstraints.WEST;
        gbcNested.fill = GridBagConstraints.WEST;
        gbcNested.gridx = 0;
        gbcNested.gridy = 0;
        gbcNested.insets = new Insets(10, 0, 0, 0);
        nestedPanel.add(pacManIcon, gbcNested);

        Lives.setFocusable(false);
        Lives.setBackground(Color.BLACK);
        Lives.setForeground(Color.YELLOW);
        Lives.setFont(ButtonFont.deriveFont(16f));

        gbcNested.insets = new Insets(10, 20, 0, 0);
        gbcNested.gridx = 1;
        gbcNested.gridy = 0;
        gbcNested.anchor = GridBagConstraints.CENTER;
        gbcNested.fill = GridBagConstraints.CENTER;
        nestedPanel.add(Lives, gbcNested);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.WEST;
        add(nestedPanel, gbc);

        mazePanel.initStartAdapter();
    }

    public void requestFocusInMazePanel()
    {
        mazePanel.requestFocusInWindow();
    }
}
