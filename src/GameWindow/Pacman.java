package GameWindow;

import Utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Pacman extends JFrame {
    private static JPanel mainWindow;

    Pacman() {
        mainWindow = new JPanel(new CardLayout());
        mainWindow.add(new MainWindow());
        add(mainWindow);

        setTitle("Pacman");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(550, 700));
        pack();
        setLocationRelativeTo(null);

        ImageIcon programIcon = ImageUtils.createImageIcon("src/images/pacman128.png");
        setIconImage(programIcon.getImage());
    }

    private static void openWindow(JPanel window) {
        try {
            mainWindow.removeAll();
            mainWindow.add(window);
            mainWindow.revalidate();
            mainWindow.repaint();

            window.requestFocusInWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void openGameWindow(String playerNickname) {
        GameWindow gameWindow = new GameWindow(playerNickname);
        openWindow(gameWindow);
        gameWindow.requestFocusInMazePanel();
    }

    public static void openMainWindow() {
        MainWindow newMainWindow = new MainWindow();
        openWindow(newMainWindow);
    }

    public static void openAboutWindow() {
        AboutWindow aboutWindow = new AboutWindow();
        openWindow(aboutWindow);
    }

    public static void openLeaderBoardWindow() {
        LeaderBoardWindow leaderBoardWindow = new LeaderBoardWindow();
        openWindow(leaderBoardWindow);
    }

    public static void openNewPlayerWindow() {
        NewPlayerWindow NewPlayerWindow = new NewPlayerWindow();
        openWindow(NewPlayerWindow);
    }

    public static void openGameOverWindow(long earnedPoints) {
        GameOverWindow NewGameOverWindow = new GameOverWindow(earnedPoints);
        openWindow(NewGameOverWindow);
    }


    public static void run() {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Pacman();
            ex.setVisible(true);
        });
    }
}
