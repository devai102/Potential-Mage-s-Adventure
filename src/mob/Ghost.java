package mob;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import core.GamePanel;

public class Ghost extends Mob{
    GamePanel gp;
    
    private int actionLockCounter;
    private BufferedImage skill;
    private boolean skillOn = false;
    private int counterSkill = 0;

    public Ghost(GamePanel gp){
        super(gp);
    }

    public void setDefaultValues(){
        this.health = 15;
        this.attack = 5;
        this.speed = gp.tileSize / 15;
        this.name = "Ghost";
    }

    public void setMobImage(){
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
        BufferedImage image2 = null;
        if(skillOn == true){
            image2 = skill;
        }else{
            switch(directionX){
                case "left":
                    if(spriteNum == 1){
                        image2 = left1;
                    }
                    if(spriteNum == 2){
                        image2 = left2;
                    }
                    break;
                case "right":
                    if(spriteNum == 1){
                        image2 = right1;
                    }
                    if(spriteNum == 2){
                        image2 = right2;
                    }
                    break;
            }
        }
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
