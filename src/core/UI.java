package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
    GamePanel gp;
    Font TNR_40;
    boolean messageOn = false;
    String message = "";
    public UI(GamePanel gp){
        this.gp = gp;

        TNR_40 = new Font("Times New Roman", Font.PLAIN, 40);
    }

    public void draw(Graphics2D g2){

        g2.setFont(TNR_40);
        g2.setColor(Color.WHITE);
        g2.drawString("UI Test", 50, 50);
    }

    public void drawGameOver(Graphics2D g2){
        g2.setFont(TNR_40);
        g2.setColor(Color.RED);
        g2.drawString("GAME OVER", gp.screenWidth/2 - 100, gp.screenHeight/2);
    }

    public void drawVictory(Graphics2D g2){
        g2.setFont(TNR_40);
        g2.setColor(Color.YELLOW);
        g2.drawString("VICTORY!", gp.screenWidth/2 - 80, gp.screenHeight/2);
    }

    public void drawPause(Graphics2D g2){
        g2.setFont(TNR_40);
        g2.setColor(Color.WHITE);
        g2.drawString("PAUSED", gp.screenWidth/2 - 70, gp.screenHeight/2);
    }

    public void showMessage(String text){
        
        message = text;
        messageOn = true;
    }

}
