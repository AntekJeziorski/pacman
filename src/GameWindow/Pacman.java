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
        mainWindow.add(new MainWindow());
        add(mainWindow);
        setTitle("Pacman");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(550, 700));
        pack();

        setLocationRelativeTo(null);



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

    public static void openMainWindow() {
        MainWindow newMainWindow = new MainWindow();
        mainWindow.removeAll();
        mainWindow.add(newMainWindow);
        mainWindow.revalidate();
        mainWindow.repaint();
        newMainWindow.requestFocusInWindow();
    }

    public static void openAboutWindow() {
        AboutWindow aboutWindow = new AboutWindow();
        mainWindow.removeAll();
        mainWindow.add(aboutWindow);
        mainWindow.revalidate();
        mainWindow.repaint();
        aboutWindow.requestFocusInWindow();
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
