package GameWindow;

import Utils.FontUtils;
import Utils.NickNameField;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class NewPlayerWindow extends JPanel implements ActionListener {
    private final JButton backButton = new JButton("Back");
    private final JButton playButton = new JButton("Play");
    private final JTextField nickName = new NickNameField("Enter nickname");
    private final JLabel errorLabel = new JLabel();
    private final Font pixelFont = FontUtils.readFonts("src/fonts/emulogic.ttf");
    public NewPlayerWindow() {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        initialize();
    }
    private void initialize() {
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 10, 0);

        String aboutTitle = "New Game";
        JEditorPane Title = new JEditorPane();
        Title.setPreferredSize(new Dimension(400, 80));
        Title.setEditable(false);
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

        nickName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { validateText(); }
            @Override
            public void removeUpdate(DocumentEvent e) { validateText(); }
            @Override
            public void changedUpdate(DocumentEvent e) { validateText();}
            private void validateText() {
                SwingUtilities.invokeLater(() ->{
                if (nickName.getText().length() > 8) {
                    errorLabel.setVisible(true);
                    nickName.setText("");
                    errorLabel.setText("Nickname is too long!");
                }
            });
            }
        });

        nickName.setPreferredSize(new Dimension(250,60));
        nickName.setBackground(new Color(255, 255, 0));
        nickName.addActionListener(this);
        nickName.setFont(pixelFont.deriveFont(16f));
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(nickName, constraints);

        errorLabel.setBackground(new Color(255, 255, 0));
        errorLabel.setForeground(new Color(255, 255, 255));
        errorLabel.setVisible(false);
        errorLabel.setFont(pixelFont.deriveFont(10f));
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(errorLabel, constraints);

        playButton.setPreferredSize(new Dimension(250,60));
        playButton.setBackground(new Color(255, 255, 0));
        playButton.setFocusable(false);
        playButton.addActionListener(this);
        playButton.setFont(pixelFont.deriveFont(16f));
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(playButton, constraints);

        backButton.setPreferredSize(new Dimension(250,60));
        backButton.setBackground(new Color(255, 255, 0));
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        backButton.setFont(pixelFont.deriveFont(16f));
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(backButton, constraints);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == backButton){ Pacman.openMainWindow(); }
        if (e.getSource() == playButton){
            if (nickName.getText().length() == 0){
                errorLabel.setVisible(true);
                errorLabel.setText("Nickname is too short!");
            }
            else {
                Pacman.openGameWindow(nickName.getText());
            }
        }
    }
}
