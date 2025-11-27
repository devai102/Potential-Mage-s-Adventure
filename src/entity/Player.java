package src.entity;

import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.image.BufferedImage;
import java.util.logging.Handler;
import javax.imageio.ImageIO;

import src.core.GamePanel;
import src.core.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    BufferedImage right1,right2,right3,right4,right5;
    BufferedImage left1,left2,left3,left4,left5;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        setImage();
    }

    void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = gp.tileSize / 4;
    }

    void setImage(){
        try{
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/right4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/player/right5.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/left4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/player/left5.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if(keyH.leftPressed == true){
                direction = "left";
                worldX -= speed;
            }
            else if(keyH.rightPressed == true){
                direction = "right";
                worldX += speed;
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 3;
                }
                else if(spriteNum == 3){
                    spriteNum = 4;
                }
                else if(spriteNum == 4){
                    spriteNum = 5;
                }
                else if(spriteNum == 5){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics g2){
        switch (direction) {
            case "left":
                if(spriteNum == 1){
                    g2.drawImage(left1, worldX, worldY, gp.tileSize, gp.tileSize, null);
                }
                if(spriteNum == 2){
                    g2.drawImage(left2, worldX, worldY, gp.tileSize, gp.tileSize, null);
                }
                if(spriteNum == 3){
                    g2.drawImage(left3, worldX, worldY, gp.tileSize, gp.tileSize, null);
                }
                if(spriteNum == 4){
                    g2.drawImage(left4, worldX, worldY, gp.tileSize, gp.tileSize, null);
                }
                if(spriteNum == 5){
                    g2.drawImage(left5, worldX, worldY, gp.tileSize, gp.tileSize, null);
                }
                break;
            case "right":
                if(spriteNum == 1){
                    g2.drawImage(right1, worldX, worldY, gp.tileSize, gp.tileSize, null);
                }
                if(spriteNum == 2){
                    g2.drawImage(right2, worldX, worldY, gp.tileSize, gp.tileSize, null);
                }
                if(spriteNum == 3){
                    g2.drawImage(right3, worldX, worldY, gp.tileSize, gp.tileSize, null);
                }
                if(spriteNum == 4){
                    g2.drawImage(right4, worldX, worldY, gp.tileSize, gp.tileSize, null);
                }
                if(spriteNum == 5){
                    g2.drawImage(right5, worldX, worldY, gp.tileSize, gp.tileSize, null);
                }
                break;
        }
    }
}
