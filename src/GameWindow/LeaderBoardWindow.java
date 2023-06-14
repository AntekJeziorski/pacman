package GameWindow;

import CsvReader.Csvreader;

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
import java.util.ArrayList;

public class LeaderBoardWindow extends JPanel implements ActionListener {
    private final JButton BackButton = new JButton("Back");
    private Font PixelFont;
    public LeaderBoardWindow() {
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
        Csvreader csv = new Csvreader();

        readFonts();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 10, 0);

        String aboutTitle = "Leader Board";
        JEditorPane Title = new JEditorPane();
        Title.setPreferredSize(new Dimension(450, 80));
        Title.setEditable(false);
        Title.setBackground(Color.BLACK);
        Title.setForeground(Color.YELLOW);
        Title.setFont(PixelFont.deriveFont(24f));
        Title.setText(aboutTitle);
        Title.setBorder(new LineBorder(Color.BLACK));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        add(Title, constraints);

        ArrayList<ArrayList<String>> leaderBoard = csv.getParsedFile();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        int size = listModel.getSize();
        for (int i = 0; i < leaderBoard.size(); i++) {
            String concatenatedString = String.join(" - ", leaderBoard.get(i));
            listModel.addElement(i+1 + ". " + concatenatedString);
            listModel.addElement(" ");
        }
        JList<String> list = new JList<>(listModel);

        list.setPreferredSize(new Dimension(450, 250));
        list.setBackground(new Color(255, 255, 0));
        list.setFocusable(false);
        list.setFont(PixelFont);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(list, constraints);

        BackButton.setPreferredSize(new Dimension(250,60));
        BackButton.setBackground(new Color(255, 255, 0));
        BackButton.setFocusable(false);
        BackButton.addActionListener(this);
        BackButton.setFont(PixelFont);
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(BackButton, constraints);
    }
    private void getLeaderBoard(){

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == BackButton){
            Pacman.openMainWindow();
        }

    }
}
