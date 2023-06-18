package GameWindow;

import CsvReader.Csvreader;
import Utils.FontUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LeaderBoardWindow extends JPanel implements ActionListener {
    private final JButton backButton = new JButton("Back");
    private final Font pixelFont = FontUtils.readFonts("src/fonts/emulogic.ttf");
    public LeaderBoardWindow() {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        initialize();
    }

    private void initialize() {
        Csvreader csv = new Csvreader();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 0, 40, 0);

        String aboutTitle = "Leader Board";
        JEditorPane Title = new JEditorPane();
        Title.setPreferredSize(new Dimension(450, 60));
        Title.setEditable(false);
        Title.setFocusable(false);
        Title.setBackground(Color.BLACK);
        Title.setForeground(Color.YELLOW);
        Title.setFont(pixelFont.deriveFont(24f));
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
            String concatenatedString = String.join(" -", leaderBoard.get(i));
            listModel.addElement(i+1 + "." + concatenatedString);
            listModel.addElement(" ");
        }
        JList<String> list = new JList<>(listModel);
        list.setCellRenderer(new CenteredCellRenderer());
        list.setPreferredSize(new Dimension(460, 200));
        list.setBackground(new Color(255, 255, 0));
        list.setFocusable(false);
        list.setFont(pixelFont.deriveFont(13f));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(0, 0, 10, 0);  // Optional: Add padding
        add(list, constraints);

        constraints.insets = new Insets(40, 0, 0, 0);
        backButton.setPreferredSize(new Dimension(250,60));
        backButton.setBackground(new Color(255, 255, 0));
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        backButton.setFont(pixelFont.deriveFont(16f));
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(backButton, constraints);

    }
    private void getLeaderBoard(){

    }

    private static class CenteredCellRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, false, cellHasFocus);
            setHorizontalAlignment(SwingConstants.CENTER);
            return this;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == backButton){ Pacman.openMainWindow(); }
    }
}
