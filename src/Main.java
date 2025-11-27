package src;

import javax.swing.*;

import src.core.GamePanel;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Potential Mage's Adventure");
        GamePanel gamePanel = new GamePanel();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.add(gamePanel);
        frame.pack();

        gamePanel.startGameThread();
    }
}