package GameWindow;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Pacman extends JFrame {
    static JPanel mainWindow;

    Pacman() {
        initializeWindow();
    }

    public void initializeWindow() {
        mainWindow = new JPanel(new CardLayout());
        mainWindow.add(new GameWindow());
        add(mainWindow);
        setResizable(false);
        setPreferredSize(new Dimension(550, 700));
        pack();
        setTitle("Pacman");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        File iconFile = new File("src/images/right3.png");
        if (iconFile.exists()) {
            ImageIcon icon = new ImageIcon(iconFile.getAbsolutePath());
            setIconImage(icon.getImage());
        }

    }

    public static void openGameWindow() {
        GameWindow gameWindow = new GameWindow();
        mainWindow.removeAll();
        mainWindow.add(gameWindow);
        mainWindow.revalidate();
        mainWindow.repaint();
        gameWindow.requestFocusInMazePanel();

    }
    public static void run()
    {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Pacman();
            ex.setVisible(true);
        });
    }
//    public static void openGameOverWindow() {
//        GameOverWindow gameOverWindow = new GameOverWindow();
//        mainWindow.removeAll();
//        mainWindow.add(gameOverWindow);
//        mainWindow.revalidate();
//        mainWindow.repaint();
//        gameOverWindow.requestFocusInWindow();
//    }
}
