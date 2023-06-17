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
    private static void repaintNewWindow(JPanel Window){
        mainWindow.removeAll();
        mainWindow.add(Window);
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public static void openGameWindow() {
        GameWindow gameWindow = new GameWindow();
        repaintNewWindow(gameWindow);
        gameWindow.requestFocusInMazePanel();
    }

    public static void openMainWindow() {
        MainWindow newMainWindow = new MainWindow();
        repaintNewWindow(newMainWindow);
        newMainWindow.requestFocusInWindow();
    }

    public static void openAboutWindow() {
        AboutWindow aboutWindow = new AboutWindow();
        repaintNewWindow(aboutWindow);
        aboutWindow.requestFocusInWindow();
    }

    public static void openLeaderBoardWindow() {
        LeaderBoardWindow leaderBoardWindow = new LeaderBoardWindow();
        repaintNewWindow(leaderBoardWindow);
        leaderBoardWindow.requestFocusInWindow();
    }

    public static void openNewPlayerWindow() {
        NewPlayerWindow NewPlayerWindow = new NewPlayerWindow();
        repaintNewWindow(NewPlayerWindow);
        NewPlayerWindow.requestFocusInWindow();
    }

    public static void run() {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Pacman();
            ex.setVisible(true);
        });
    }
}
