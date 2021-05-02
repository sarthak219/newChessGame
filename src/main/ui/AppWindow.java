package main.ui;

import javax.swing.*;
import java.awt.*;

/**
 * represents the main window of the application
 */
public class AppWindow extends JFrame {
    public static final int WIDTH = 680;
    public static final int HEIGHT = 800;

    public AppWindow() {
        initialiseGraphics();
        add(new GameScreen(WIDTH, HEIGHT));
        setVisible(true);
    }

    // EFFECTS: initializes the window with WIDTH and HEIGHT and a bg colour
    public void initialiseGraphics() {
        setSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(19, 19, 19));
        setLocationRelativeTo(null);
        setLayout(null);
    }
}
