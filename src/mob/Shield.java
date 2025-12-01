package mob;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;

import core.GamePanel;

public class Shield extends Mob{
    GamePanel gp;
    
    private BufferedImage skill;
    private int counterSkill = 0;
    private boolean skillOn = false;
    private int actionLockCounter;

    public Shield(GamePanel gp, int x, int y){
        super(gp);
        setImage();
        setDefaultValues();
    }

    public void setDefaultValues(){
        this.health = 10;
        this.attack = 5;
        this.speed = gp.tileSize / 15;
        this.name = "Shield";
    }

    public void setImage(){
        try{
            left1 = ImageIO.read(getClass().getResourceAsStream(""));
            left2 = ImageIO.read(getClass().getResourceAsStream(""));
            right1 = ImageIO.read(getClass().getResourceAsStream(""));
            right2 = ImageIO.read(getClass().getResourceAsStream(""));
            skill = ImageIO.read(getClass().getResourceAsStream(""));
        }catch(Exception e){
            e.getStackTrace();
        }
    }

    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter >= 120){
            int rd = new Random().nextInt(100)+1;
            if (rd <=50) {
                directionX = "left";
            }
            else {
                directionX = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void update(){
        setAction();
        
        // check collision random move
        collisionOn = false;
        if(skillOn == false){
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

        //set image 
        if(skillOn = false){
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else{
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        
        // set skill time 
        counterSkill++;
        if(skillOn == false){
            if(counterSkill == 300){
                skillOn = true;
                counterSkill = 0;
            }
        }else{
            if(counterSkill == 120){
                skillOn = false;
                counterSkill = 0;
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        if (skillOn == false) {
            image = skill;
        }
        else{
            if (directionX == "left") {
                if(spriteNum == 1){
                    image = left1;
                }else{
                    image = left2;
                }
            }
            if  (directionX == "right"){
                if(spriteNum == 1){
                    image = right1;
                }else{
                    image = right2;
                }
            }
        }
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }

    public void SkillEffect(){
        
    }
}
