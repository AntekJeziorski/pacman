package GameWindow;

import Utils.FontUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Utils.LeaderboardManager.getScoreRecords;

/** @brief The LeaderBoardWindow class represents the leaderboard window of the game. */
public class LeaderBoardWindow extends JPanel implements ActionListener {
    /** @brief JButton taking the user back to the main menu */
    private final JButton backButton = new JButton("Back");

    /** @brief Stylised custom font */
    private final Font pixelFont = FontUtils.readFonts("src/fonts/emulogic.ttf");

    /**
     * @brief Constructs a new LeaderBoardWindow object.
     * Initializes the layout and sets the background color.
     */
    public LeaderBoardWindow() {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        initialize();
    }

    /** @brief Initializes the components and adds them to the window */
    private void initialize() {
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
        Title.setFont(pixelFont.deriveFont(28f));
        Title.setText(aboutTitle);
        Title.setBorder(new LineBorder(Color.BLACK));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        add(Title, constraints);

        ArrayList<ArrayList<String>> leaderBoard = getScoreRecords();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        if (!leaderBoard.isEmpty()) {
            for (int i = 0; i < Math.min(5, leaderBoard.size()); i++) {
                ArrayList<String> record = leaderBoard.get(i);
                String index = String.valueOf(i + 1);
                String playerName = record.get(0);
                String score = record.get(1);
                listModel.addElement(index + ". " + playerName + " - " + score + " pts");
                listModel.addElement(" ");
            }
        }

        JList<String> list = new JList<>(listModel);
        list.setCellRenderer(new CenteredCellRenderer());
        list.setPreferredSize(new Dimension(450, 200));
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

    /** @brief The CenteredCellRenderer class is a custom list cell renderer that centers the text horizontally */
    private static class CenteredCellRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, false, cellHasFocus);
            setHorizontalAlignment(SwingConstants.LEFT);
            setHorizontalTextPosition(SwingConstants.LEFT);
            setPreferredSize(new Dimension(list.getWidth(), getPreferredSize().height));
            return this;
        }
    }

    /**
     * @brief Handles the button click events
     * @param e The action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton){ Pacman.openMainWindow(); }
    }
}
