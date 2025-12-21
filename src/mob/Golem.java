package mob;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import core.GamePanel;
import entity.Entity;

public class Golem extends Entity{ 
    private int actionLockCounter;
    private BufferedImage[] leftImages = new BufferedImage[5];
    private BufferedImage[] rightImages = new BufferedImage[5];
    private BufferedImage[] skillImages = new BufferedImage[2];
 
    private int skillDuration = 0;
    private int rdImageNum = 0;

    public Golem(GamePanel gp){
        super(gp);
        setImage();
        setDefaultValues();
    }

    public void setDefaultValues(){
        name = "Golem";
        health = 3;
        attack = 1;
        speed = 2;
        type = 2;
        directionX = "left";
        solidArea = new java.awt.Rectangle(8,16,32,32);
        solidAreaDefaultX = this.solidArea.x;
        solidAreaDefaultY = this.solidArea.y;

    }

    public void setImage(){
        for(int i = 0; i < 5; i++){
            leftImages[i] = setup("/res/image/mob/golem/left/" + (i+1), gp.tileSize, gp.tileSize);
            rightImages[i] = setup("/res/image/mob/golem/right/" + (i+1), gp.tileSize, gp.tileSize);
            // dieImages[i] = setup("/res/image/mob/golem/die/" + (i+1));
        }
        for(int i = 0 ; i < 2; i++){
            skillImages[i] = setup("/res/image/mob/golem/skill/" + (i+1), gp.tileSize, gp.tileSize);
        }
    }

    @Override
    public void update(){
        setAction();

        if(!skillOn){
            collisionOn = gp.cChecker.checkTile(this);
            if(collisionOn == false){
                switch(directionX){
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            } else{
                directionX = (directionX == "left") ? "right" : "left";
                spriteNum = 1;
            }
        }

        gp.cChecker.checkPlayer(this);
        gp.cChecker.checkObject(this, false);

        spriteCounter++;
        if(spriteCounter > 15 && skillOn == false){
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

    @Override
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        if(skillOn == true){
            image = skillImages[rdImageNum];
        }else{
            if(directionX == "left"){
                image = leftImages[spriteNum -1];
            }
            else if(directionX == "right"){
                image = rightImages[spriteNum -1];
            }
        }
        
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
           worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    @Override
    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter >= 120 && skillOn == false){
            int rd = new Random().nextInt(100)+1;
            if (rd <=50) {
                directionX = "left";
                spriteNum = 1;
            }
            else {
                directionX = "right";
                spriteNum = 1;
            }

            int rd2 = new Random().nextInt(100)+1;
            if (rd2 > 90 && skillOn == false) {
                skillOn = true;
                spriteNum = 1;
                rdImageNum = new Random().nextInt(2);
            }
            actionLockCounter = 0;
        }

        if(skillOn == true){
            skillDuration++;
            if (skillDuration > 120){
                skillOn = false;
                skillDuration = 0;
                spriteNum = 1;
            }
        }
    }
}