package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import core.GamePanel;
import core.KeyHandler;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;

    private boolean defenseOn = false;

    private int speed;

    
    private int gravity = 3;
    private int jumpPower = 15;
    private boolean Jumping = false;

    public final int screenX, screenY; 

    private BufferedImage[] rightImages = new BufferedImage[7];
    private BufferedImage[] leftImages = new BufferedImage[7];
    private BufferedImage standingImage;
    private BufferedImage defenseOnImage;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        this.screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        this.screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        setDefaultValues();
        setImage();
    }

    void setDefaultValues(){
        solidArea = new Rectangle(8, 12, 32, 32);
        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 10;
        speed = gp.tileSize / 4;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    void setImage(){
        try{
            for(int i = 0; i < 7; i++){
                rightImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/right/" + (i+1) + ".png"));
            }

            for(int i = 0; i < 7; i++){
                leftImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/left/" + (i+1) + ".png"));
            }

            defenseOnImage = ImageIO.read(getClass().getResourceAsStream("/res/image/defenseOn/defenseOn.png"));

            standingImage = ImageIO.read(getClass().getResourceAsStream("/res/image/standing.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            gp.cChecker.checkTile(this);
            if(Jumping == false){
                if(keyH.leftPressed == true && keyH.upPressed == true && Falling == false){
                    directionX= "left";
                    directionY= "up";
                    Jumping = true;
                }
                else if(keyH.rightPressed == true && keyH.upPressed == true && Falling == false){
                    directionX= "right";
                    directionY= "up";
                    Jumping = true;
                }
                else if(keyH.upPressed == true && Falling == false){
                    directionY= "up";
                    directionX= "none";
                }
                else if(keyH.downPressed == true && Falling == false){
                    directionY= "down";
                    directionX= "none";
                    defenseOn = true;
                }
                else if(keyH.downPressed == true && Falling == true){
                    directionY= "down";
                    directionX= "none";
                }
                else if(keyH.leftPressed == true){
                    directionX= "left";
                }
                else if(keyH.rightPressed == true){
                    directionX= "right";
                }
            }
            
            // move along X axis
            switch(directionX){
                case "left":
                    gp.cChecker.checkTile(this);
                    if(collisionOn == false){
                        worldX -= speed;
                    }
                    break;
                case "right":
                    gp.cChecker.checkTile(this);
                    if(collisionOn == false){
                        worldX += speed;
                    }
                    break;
            }
        }
        else if(keyH.downPressed == false && keyH.upPressed == false && keyH.leftPressed == false && keyH.rightPressed == false){
            directionX = "none";
            directionY = "none";
        }

        //Jumping
        if(Jumping == true){
            gp.cChecker.checkTile(this);
            if(collisionOn == false){
                worldY -= jumpPower;
            }else{
                jumpPower = 0;
                Jumping = false;
                Falling = true;
            }
            jumpPower -= gravity;
            if(jumpPower <= 0){
                jumpPower = 15;
                Jumping = false;
                Falling = true;
            }
        }

        //Falling
        gp.cChecker.isFalling(this);
        if(Falling == true){
            if(collisionOn == false){
                this.worldY += gravity;
                gp.cChecker.isFalling(this);
            }else{
                Falling = false;
            }
        }
        

        //Sprite running animation
        spriteCounter++;
        if(spriteCounter > 4){
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
                spriteNum = 6;
            }
            else if(spriteNum == 6){
                spriteNum = 7;
            }
            else if(spriteNum == 7){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics g2){
        BufferedImage image = null;
        if(directionY.equals("none")){
            if(directionX.equals("right")){
                switch(spriteNum){
                    case 1: image = rightImages[0]; break;
                    case 2: image = rightImages[1]; break;
                    case 3: image = rightImages[2]; break;
                    case 4: image = rightImages[3]; break;
                    case 5: image = rightImages[4]; break;
                    case 6: image = rightImages[5]; break;
                    case 7: image = rightImages[6]; break;
                }
            }
            else if(directionX.equals("left")){
                switch(spriteNum){
                    case 1: image = leftImages[0]; break;
                    case 2: image = leftImages[1]; break;
                    case 3: image = leftImages[2]; break;
                    case 4: image = leftImages[3]; break;
                    case 5: image = leftImages[4]; break;
                    case 6: image = leftImages[5]; break;
                    case 7: image = leftImages[6]; break;
                }
            }
            else if(directionX.equals("none")){
                image = standingImage;
            }
        }
        // else if(directionY.equals("up")){
            
        // }
        // else if(directionY.equals("down")){
        //     if(defenseOn == true){
        //         image = defenseOnImage;
        //     }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}