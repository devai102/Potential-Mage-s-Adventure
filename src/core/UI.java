package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    boolean messageOn = false;
    String message = "";
    int delta = 20;

    int spriteCounter = 0;
    int spriteNum = 0;
    
    double playTime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    Graphics2D g2;

    BufferedImage[] playerImages = new BufferedImage[5];
    BufferedImage[] heartImages = new BufferedImage[3];
    BufferedImage[] buttonImages = new BufferedImage[4];

    public int commandNumber = 0;

    public UI(GamePanel gp){
        this.gp = gp;
        setImages();
    }

    void setImages(){
        // Load images here
        try{
            for(int i = 0; i < 5; i++) {
                playerImages[i] = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/image/player/left/" + (i+1) + ".png"));
            }
            for(int i = 0; i < 3; i++) {
                heartImages[i] = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/image/object/heart/heart_" + (i+1) + ".png"));
            }
            for(int i = 0; i < 4; i++) {
                buttonImages[i] = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/image/object/button/button" + (i+1) + ".png"));
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
        if(gp.gameState == gp.playState){
            drawPlayScreen(g2);
        }
        else if(gp.gameState == gp.pauseState){
            drawPauseScreen(g2);
        }
        else if(gp.gameState == gp.gameOverState){
            drawGameOverScreen(g2);
        }
        else if(gp.gameState == gp.winState){
            drawWinScreen(g2);
        }
        else if(gp.gameState == gp.tileScreenState){
            drawTitleScreen(g2);
        }
        else if(gp.gameState == gp.leaderboardState){
            drawLeaderboardScreen(g2);
        }
        else if(gp.gameState == gp.helpState){
            drawHelpScreen(g2);
        }
    }
    
    void update(){
        spriteCounter++;
        if(spriteCounter > 15){
            switch(spriteNum){
                case 0: spriteNum = 1; break;
                case 1: spriteNum = 2; break;
                case 2: spriteNum = 3; break;
                case 3: spriteNum = 4; break;
                case 4: spriteNum = 0; break;
            }
            spriteCounter = 0;
        }
    }

    void drawPauseScreen(Graphics2D g2){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 75F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

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

        g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 17 - 50, gp.tileSize);
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
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 75F));   
        String text1 = "POTENTIAL MAGE'S ADVENTURE";
        int x1 = getXForCenteredText(text1);
        int y1 = gp.tileSize * 3;
        g2.setColor(Color.gray);
        g2.drawString(text1, x1 + 3, y1 + 3);
        g2.setColor(Color.black);
        g2.drawString(text1, x1, y1);

        // Start game button  
        int x2 = gp.tileSize * 3;
        int y2 = gp.tileSize * 5;
        int size2 = gp.tileSize * 2;
        if(commandNumber == 0){
            size2 += delta;
        }
        g2.drawImage(buttonImages[0], x2, y2, size2 , size2 , null);
        
        //Quit button
        int x5 = gp.tileSize * 15;
        int y5 = gp.tileSize * 5;
        int size5 = gp.tileSize * 2;
        if(commandNumber == 3){
            size5 += delta;
        }
        g2.drawImage(buttonImages[1], x5, y5, size5 , size5 , null);

        // leaderboard
        int x3 = gp.tileSize * 7;
        int y3 = gp.tileSize * 5;
        int size3 = gp.tileSize * 2;
        if(commandNumber == 1){
            size3 += delta;
        }
        g2.drawImage(buttonImages[2], x3, y3, size3 , size3, null);

        //Note
        int x4 = gp.tileSize * 11;
        int y4 = gp.tileSize * 5;
        int size4 = gp.tileSize * 2;
        if(commandNumber == 2){
            size4 += delta;
        }
        g2.drawImage(buttonImages[3], x4, y4, size4, size4, null);

        // Player image
        g2.drawImage(playerImages[spriteNum], gp.tileSize * 8, gp.tileSize * 7, gp.tileSize * 4, gp.tileSize * 4, null);
    }

    void drawLeaderboardScreen(Graphics2D g2){
        // Background
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setColor(Color.white);
        g2.fillRoundRect(gp.tileSize, gp.tileSize, gp.screenWidth - gp.tileSize*2, gp.screenHeight - gp.tileSize * 2, 25, 25);

        g2.setColor(Color.black);
        g2.fillRoundRect(gp.tileSize + 5, gp.tileSize + 5, gp.screenWidth - gp.tileSize * 2 - 10, gp.screenHeight - gp.tileSize * 2 - 10, 25, 25);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
        String text = "LEADERBOARD";
        int x = getXForCenteredText(text);
        int y = gp.tileSize + 60;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.drawString("Rank                  Name                  Time", gp.tileSize * 3, gp.tileSize * 4);

    }

    void drawHelpScreen(Graphics2D g2){
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        g2.setColor(Color.white);
        g2.fillRoundRect(gp.tileSize, gp.tileSize, gp.screenWidth - gp.tileSize*2, gp.screenHeight - gp.tileSize * 2, 25, 25);

        g2.setColor(Color.black);
        g2.fillRoundRect(gp.tileSize + 5, gp.tileSize + 5, gp.screenWidth - gp.tileSize * 2 - 10, gp.screenHeight - gp.tileSize * 2 - 10, 25, 25);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
        String text = "INSTRUCTIONS";
        int x = getXForCenteredText(text);
        int y = gp.tileSize + 60;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        g2.drawString("Have a good moment!", gp.tileSize * 3, gp.tileSize * 4);
    }
}