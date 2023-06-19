package GameWindow;

import Utils.ImageUtils;

import javax.swing.*;
import java.awt.*;

/** @brief Represents the main entry point for the Pacman game application */
public class Pacman extends JFrame {
    /**
     * @brief Main programme window
     */
    private static JPanel mainWindow;

    /**
     *  @brief Constructs a new Pacman object
     */
    Pacman() {
        mainWindow = new JPanel(new CardLayout());
        mainWindow.add(new MainWindow());
        add(mainWindow);

        ImageIcon pacManIcon = ImageUtils.createImageIcon("src/images/pacman128.png");
        setIconImage(pacManIcon.getImage());

        setTitle("Pacman");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(550, 700));
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * @brief Opens the specified window in the main window panel
     * @param window The JPanel window to be opened
     */
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

    /**
     * @brief Opens the game window with the specified player nickname
     * @param playerNickname The nickname of the player
     */
    public static void openGameWindow(String playerNickname) {
        GameWindow gameWindow = new GameWindow(playerNickname);
        openWindow(gameWindow);
        gameWindow.requestFocusInMazePanel();
    }

    /**
     * @brief Opens the main window
     */
    public static void openMainWindow() {
        MainWindow newMainWindow = new MainWindow();
        openWindow(newMainWindow);
    }

    /**
     * @brief Opens the about window
     */
    public static void openAboutWindow() {
        AboutWindow aboutWindow = new AboutWindow();
        openWindow(aboutWindow);
    }

    /**
     * @brief Opens the leaderboard window
     */
    public static void openLeaderBoardWindow() {
        LeaderBoardWindow leaderBoardWindow = new LeaderBoardWindow();
        openWindow(leaderBoardWindow);
    }

    /**
     * @brief Opens the new player window
     */
    public static void openNewPlayerWindow() {
        NewPlayerWindow NewPlayerWindow = new NewPlayerWindow();
        openWindow(NewPlayerWindow);
    }

    /**
     * @brief Opens the game over window with the earned points
     * @param earnedPoints The points earned in the game
     */
    public static void openGameOverWindow(long earnedPoints) {
        GameOverWindow NewGameOverWindow = new GameOverWindow(earnedPoints);
        openWindow(NewGameOverWindow);
    }

    /**
     * @brief Runs the Pacman game application
     */
    public static void run() {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Pacman();
            ex.setVisible(true);
        });
    }
}
