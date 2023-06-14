package GameWindow;

import javax.swing.*;
import java.awt.*;
import javax.swing.JEditorPane;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutWindow extends JPanel implements ActionListener {
    private final ImageIcon BigPacMan = new ImageIcon("src/images/big-pacman.gif");
    private final JButton BackButton = new JButton("<html><p style='font-size:20px; color:black'>Back</p></html>");

    public AboutWindow() {
        setLayout(new GridBagLayout());
        setBackground(new Color(0, 0, 0));
        initialize();
    }
    private void initialize() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 0, 5, 0);

        JLabel gifLabel = new JLabel(BigPacMan);
        gifLabel.setBounds(100, 50, 100, 100);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(gifLabel, constraints);

        String css = "<style type='text/css'>" +
                "body { color: white; background-color: black; margin: 0; padding: 0; }" +
                "</style>";

        String htmlText = "<html><head>" + css + "</head><body>" +
                "<h1>Pac-Man</h1>" +
                "</body></html>";
        JEditorPane AboutText = new JEditorPane();
        AboutText.setContentType("text/html");
        AboutText.setEditable(false);
        AboutText.setText(htmlText);
        AboutText.setPreferredSize(new Dimension(400, 300));
        AboutText.setBorder(new LineBorder(Color.BLACK));

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.CENTER;
        add(AboutText, constraints);

        BackButton.setBounds(175, 100, 200, 40);
        BackButton.setBackground(new Color(255, 255, 0));
        BackButton.setFocusable(false);
        BackButton.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(BackButton, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == BackButton){
            Pacman.openMainWindow();
        }

    }
}
