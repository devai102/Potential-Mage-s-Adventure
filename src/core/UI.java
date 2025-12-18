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

    Graphics2D g2;

    BufferedImage[] playerImages = new BufferedImage[2];
    BufferedImage heart_full, heart_half, heart_blank;

    public UI(GamePanel gp){
        this.gp = gp;

        TNR_40 = new Font("Times New Roman", Font.PLAIN, 40);
    }

    void setImages(){
        // Load images here
        try{
            for(int i = 0; i < 2; i++) {
                playerImages[i] = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/image/player/standing/" + (i+1) + ".png"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        switch(gp.gameState){
            case 0:
                drawPlayScreen(g2);
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
            case 4:
                drawTitleScreen(g2);
                break;
        }
    }
    
    void drawPauseScreen(Graphics2D g2){
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);
    }

    void drawGameOverScreen(Graphics2D g2){
        String text = "GAME OVER";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        gp.gameThread = null;
    }

    void drawWinScreen(Graphics2D g2){
        String text = "YOU WIN!";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        gp.gameThread = null;
    }

    void drawPlayScreen(Graphics2D g2){
        //Timing
        playTime += (double)1/60;
        g2.setColor(Color.white);

        g2.drawString("Time: " + dFormat.format(playTime), 30, 50);
    }

    int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    void drawTitleScreen(Graphics2D g2){
        // Background
        g2.setColor(Color.white);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));   
        String text1 = "POTENTIAL MAGE'S ADVENTURE";
        int x1 = getXForCenteredText(text1);
        int y1 = gp.tileSize * 3;

        g2.setColor(Color.gray);
        g2.drawString(text1, x1 + 3, y1 + 3);

        g2.setColor(Color.black);
        g2.drawString(text1, x1, y1);

        // Start game button
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        
        String text2 = "Start Game";
        int x2 = getXForCenteredText(text2);
        int y2 = gp.tileSize * 6;
        g2.setColor(Color.gray);
        g2.drawString(text2, x2 + 2, y2 + 2);
        g2.setColor(Color.black);
        g2.drawString(text2, x2, y2);

        // Quit game button
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        String text3 = "Quit Game";
        int x3 = getXForCenteredText(text3);
        int y3 = gp.tileSize * 7 + 40;
        g2.setColor(Color.gray);
        g2.drawString(text3, x3 + 2, y3 + 2);
        g2.setColor(Color.black);
        g2.drawString(text3, x3, y3);

        // Player image
        g2.drawImage(playerImages[0], gp.screenWidth/2 - gp.tileSize, gp.tileSize *4, gp.tileSize, gp.tileSize, null);
    }
}