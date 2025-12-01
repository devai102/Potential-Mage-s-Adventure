package mob;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import core.GamePanel;

public class Nor extends Mob{
    GamePanel gp;
    
    private int actionLockCounter;

    public Nor(GamePanel gp){
        super(gp);
    }

    public void setDefaultValues(){
        this.health = 20;
        this.attack = 7;
        this.speed = gp.tileSize / 12;
        this.name = "Nor";
    }

    // public void setMobImage(){
    //     try{
    //         left1 = ImageIO.read(getClass().getResourceAsStream(""));
    //         left2 = ImageIO.read(getClass().getResourceAsStream(""));
    //         right1 = ImageIO.read(getClass().getResourceAsStream(""));
    //         right2 = ImageIO.read(getClass().getResourceAsStream(""));
    //     }catch(Exception e){
    //         e.getStackTrace();
    //     }
    // }

    public void update(){
        setAction();
        // move mob
        switch(directionX){
            case "left":
                worldX -= speed;
                break;
            case "right":
                worldX += speed;
                break;
        }

        spriteCounter++;
        if(spriteCounter > 15){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else{
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch(directionX){
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }

    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter >= 120){
            int rd = new java.util.Random().nextInt(100)+1;
            if (rd <=50) {
                directionX = "left";
            }
            else {
                directionX = "right";
            }
            actionLockCounter = 0;
        }
    }
}
