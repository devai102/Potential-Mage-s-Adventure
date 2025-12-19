package mob;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import core.GamePanel;
import entity.Entity;

public class Golem extends Entity{
    GamePanel gp;
    
    private int actionLockCounter;
    private BufferedImage[] leftImages = new BufferedImage[5];
    private BufferedImage[] rightImages = new BufferedImage[5];
    private BufferedImage[] skillImages = new BufferedImage[2];
    private BufferedImage[] dieImages = new BufferedImage[5];
    private boolean skillOn = false;
    private int skillDuration = 0;

    public Golem(GamePanel gp){
        this.gp = gp;
        setImage();
        setDefaultValues();
    }

    public void setDefaultValues(){
        this.health = 3;
        this.attack = 1;
        this.speed = gp.tileSize / 12;
        this.name = "Golem";
    }

    public void setImage(){
        try{
            for(int i = 0; i < 5; i++){
                leftImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/mob/golem/left/" + (i+1) + ".png"));
                rightImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/mob/golem/right/" + (i+1) + ".png"));
                dieImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/mob/golem/die/" + (i+1) + ".png"));
            }
            for(int i = 0 ; i < 2; i++){
                skillImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/mob/golem/skill/" + (i+1) + ".png"));
            }
        }catch(Exception e){
            e.getStackTrace();
        }
    }

    public void update(){
        setAction();

        if(!skillOn){
            gp.cChecker.checkTile(this);
            if(collisionOn == false){
                switch(directionX){
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
        }
    
        spriteCounter++;
        if(spriteCounter > 12 && skillOn == false){
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

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        if(skillOn){
            int rd = new Random().nextInt(2);
            image = skillImages[rd];
        }else{
            if(directionX == "left"){
                image = leftImages[spriteNum -1];
            }
            else if(directionX == "right"){
                image = rightImages[spriteNum -1];
            }
        }
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }

    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter >= 120 && skillOn == false){
            int rd = new Random().nextInt(100)+1;
            if (rd <=50) {
                directionX = "left";
            }
            else {
                directionX = "right";
            }

            int rd2 = new Random().nextInt(100)+1;
            if (rd2 > 90 && skillOn == false) {
                skillOn = true;
            }
            actionLockCounter = 0;
        }
        if(skillOn == true){
            skillDuration++;
            if (skillDuration > 90){
                skillOn = false;
                skillDuration = 0;
                spriteNum = 1;
            }
        }
    }
}
