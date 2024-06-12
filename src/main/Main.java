package main;

import javax.swing.*;
public class Main {
    public static JFrame window;
    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // let window properly close when user clicks the close(x) button
        window.setResizable(false); 
        window.setTitle("2D Adventure");
        window.setUndecorated(true);    //remove top bar
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        gamePanel.config.loadConfig();

        window.pack(); // causes this Window to be sized to fit the preferred size and layouts of its subcomponents (= GamePanel)

        window.setLocationRelativeTo(null); // not specify the location of the window = window will be displayed at the center of screen
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}