package mob;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import core.GamePanel;

public class Golem extends Mob{
    GamePanel gp;
    
    private int actionLockCounter;
    private BufferedImage[] leftImages = new BufferedImage[5];
    private BufferedImage[] rightImages = new BufferedImage[5];
    
    public Golem(GamePanel gp){
        super(gp);
        setImage();
        setDefaultValues();
    }

    public void setDefaultValues(){
        this.health = 20;
        this.attack = 7;
        this.speed = gp.tileSize / 12;
        this.name = "Nor";
    }

    public void setImage(){
        try{
            for(int i = 0; i < 5; i++){
                leftImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/mob/golem/left/" + (i+1) + ".png"));
            }
            for(int i = 0; i < 5; i++){
                rightImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/mob/golem/right/" + (i+1) + ".png"));
            }
        }catch(Exception e){
            e.getStackTrace();
        }
    }

    public void update(){
        setAction();
        // move mob
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

        spriteCounter++;
        if(spriteCounter > 15){
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
        if(directionX == "left"){
            image = leftImages[spriteNum -1];
        }
        else if(directionX == "right"){
            image = rightImages[spriteNum -1];
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
