package GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JPanel implements ActionListener {
    private final ImageIcon PacManGif = new ImageIcon("src/images/pacman.gif");
    private final JButton NewGameButton = new JButton("New Game");
    private final JButton LeaderButton = new JButton("Leader Board");
    private final JButton AboutButton  = new JButton("About");
    private final JButton ExitButton = new JButton("Exit");
    private final JLabel BottomLabel = new JLabel("Developed by: S.A.J - 2023");
    private Font TitleFont;
    private Font ButtonFont;

    public MainWindow() {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        initialize();
    }

    private void readFonts(){
        try {
            Font titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/PAC-FONT.TTF"));
            TitleFont = titleFont.deriveFont(48f);
            Font buttonFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/emulogic.ttf"));
            ButtonFont = buttonFont.deriveFont(16f);
        } catch (FontFormatException | IOException e) {e.printStackTrace();}
    }
    private void initialize() {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 10, 0);

        readFonts();

        JLabel title = new JLabel("PaCMaN");
        title.setBounds(0, 0, 100, 40);
        title.setFont(TitleFont);
        title.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        add(title, constraints);

        JLabel gifLabel = new JLabel(PacManGif);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(gifLabel, constraints);

        NewGameButton.setPreferredSize(new Dimension(250,60));
        NewGameButton.setBackground(new Color(255, 255, 0));
        NewGameButton.setFocusable(false);
        NewGameButton.addActionListener(this);
        NewGameButton.setFont(ButtonFont.deriveFont(16f));

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(NewGameButton, constraints);

        LeaderButton.setPreferredSize(new Dimension(250,60));
        LeaderButton.setBackground(new Color(255, 255, 0));
        LeaderButton.setFocusable(false);
        LeaderButton.addActionListener(this);
        LeaderButton.setFont(ButtonFont.deriveFont(14f));

        constraints.gridx = 0;
        constraints.gridy = 3;
        add(LeaderButton, constraints);

        AboutButton.setPreferredSize(new Dimension(250,60));
        AboutButton.setBackground(new Color(255, 255, 0));
        AboutButton.setFocusable(false);
        AboutButton.addActionListener(this);
        AboutButton.setFont(ButtonFont.deriveFont(16f));

        constraints.gridx = 0;
        constraints.gridy = 4;
        add(AboutButton, constraints);

        ExitButton.setPreferredSize(new Dimension(250,60));
        ExitButton.setBackground(new Color(255, 255, 0));
        ExitButton.setFocusable(false);
        ExitButton.addActionListener(this);
        ExitButton.setFont(ButtonFont.deriveFont(16f));

        constraints.gridx = 0;
        constraints.gridy = 5;
        add(ExitButton, constraints);

        BottomLabel.setForeground(Color.GRAY);
        BottomLabel.setFont(ButtonFont.deriveFont(8f));

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.insets = new Insets(20, 0, 10, 0);

        add(BottomLabel, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == NewGameButton) { Pacman.openNewPlayerWindow(); }
        if (e.getSource() == LeaderButton) { Pacman.openLeaderBoardWindow(); }
        if (e.getSource() == AboutButton) { Pacman.openAboutWindow(); }
        if (e.getSource() == ExitButton) { System.exit(0); }
    }

}
