import javax.swing.*;

import core.GamePanel;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Potential Mage's Adventure");
        GamePanel gamePanel = new GamePanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize( gamePanel.screenWidth, gamePanel.screenHeight);
        frame.setVisible(true);
        
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);

        gamePanel.startGameThread();
    }
}