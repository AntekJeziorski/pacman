package GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JPanel implements ActionListener {
    private Image image;
    private JTextField nickname;

    public MainWindow() {
        setLayout(null);
        addElements();
        setBackground(new Color(52, 203, 52));
    }

    private void addElements()
    {
        JButton start_button = new JButton("<html><p style='font-size:20px; color:white'>Start game</p></html>");
        start_button.setBounds(240, 610, 320, 100);
        start_button.setBackground(new Color(97, 73, 204));
        start_button.setFocusable(false);
        start_button.addActionListener(this);
        add(start_button);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Pacman.openGameWindow();
    }

}
