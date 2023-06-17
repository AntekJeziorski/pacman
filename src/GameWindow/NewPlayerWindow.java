package GameWindow;

import Utils.NickNameField;

import javax.swing.*;
import java.awt.*;
import javax.swing.JEditorPane;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Image;
import java.io.IOException;

public class NewPlayerWindow extends JPanel implements ActionListener {
    private final JButton BackButton = new JButton("Back");
    private final JButton PlayButton = new JButton("Play");
    private final JTextField NickName = new NickNameField("Enter nickname");
    private final JLabel ErrorLabel = new JLabel();
    private Font PixelFont;
    public NewPlayerWindow() {
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
        constraints.insets = new Insets(10, 0, 10, 0);

        String aboutTitle = "New Game";
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

        NickName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { validateText(); }
            @Override
            public void removeUpdate(DocumentEvent e) { validateText(); }
            @Override
            public void changedUpdate(DocumentEvent e) { validateText();}
            private void validateText() {
                SwingUtilities.invokeLater(() ->{
                if (NickName.getText().length() > 8) {
                    ErrorLabel.setVisible(true);
                    NickName.setText("");
                    ErrorLabel.setText("Nickname is too long!");
                }
            });
            }
        });

        NickName.setPreferredSize(new Dimension(250,60));
        NickName.setBackground(new Color(255, 255, 0));
        NickName.addActionListener(this);
        NickName.setFont(PixelFont);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(NickName, constraints);

        ErrorLabel.setBackground(new Color(255, 255, 0));
        ErrorLabel.setForeground(new Color(255, 255, 255));
        ErrorLabel.setVisible(false);
        ErrorLabel.setFont(PixelFont.deriveFont(10f));
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(ErrorLabel, constraints);

        PlayButton.setPreferredSize(new Dimension(250,60));
        PlayButton.setBackground(new Color(255, 255, 0));
        PlayButton.setFocusable(false);
        PlayButton.addActionListener(this);
        PlayButton.setFont(PixelFont);
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(PlayButton, constraints);

        BackButton.setPreferredSize(new Dimension(250,60));
        BackButton.setBackground(new Color(255, 255, 0));
        BackButton.setFocusable(false);
        BackButton.addActionListener(this);
        BackButton.setFont(PixelFont);
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(BackButton, constraints);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == BackButton){ Pacman.openMainWindow(); }
        if (e.getSource() == PlayButton){
            if (NickName.getText().length() == 0){
                ErrorLabel.setVisible(true);
                ErrorLabel.setText("Nickname is too short!");
            }
            else{
                Pacman.openGameWindow();
            }
        }
    }
}
