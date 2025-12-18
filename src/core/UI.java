package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font TNR_40;
    boolean messageOn = false;
    String message = "";

    double playTime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    BufferedImage heart_full, heart_half, heart_blank;
    int gameState; 

    public UI(GamePanel gp){
        this.gp = gp;

        TNR_40 = new Font("Times New Roman", Font.PLAIN, 40);
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        g2.setFont(TNR_40);
        switch(gameState){
            case 0:
                drawDialogueScreen(g2);
                break;
            case 1:
                drawWinScreen(g2);
                break;
            case 2:
                drawGameOverScreen(g2);
                break;
            case 3:
                drawPauseScreen(g2);
                break;
        }
    }
    
    void drawPauseScreen(Graphics2D g2){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = gp.screenWidth / 2 - g2.getFontMetrics().stringWidth(text) / 2;
        int y = gp.screenHeight / 2;

        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }

    void drawGameOverScreen(Graphics2D g2){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "GAME OVER";
        int x = gp.screenWidth / 2 - g2.getFontMetrics().stringWidth(text) / 2;
        int y = gp.screenHeight / 2;

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        gp.gameThread = null;
    }

    void drawWinScreen(Graphics2D g2){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "YOU WIN!";
        int x = gp.screenWidth / 2 - g2.getFontMetrics().stringWidth(text) / 2;
        int y = gp.screenHeight / 2;

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        gp.gameThread = null;
    }

    void drawDialogueScreen(Graphics2D g2){
        //Timing
        playTime += (double)1/60;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        g2.setColor(Color.white);

        g2.drawString("Time: " + dFormat.format(playTime), 30, 50);
    }
}
