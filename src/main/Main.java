package main;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // let window properly close when user clicks the close(x) button
        window.setResizable(false); 
        window.setTitle("2D Adventure");

        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // causes this Window to be sized to fit the preferred size and layouts of its subcomponents (= GamePanel)

        window.setLocationRelativeTo(null); // not specify the location of the window = window will be displayed at the center of screen
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
