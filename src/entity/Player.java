package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import core.GamePanel;
import core.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public int hp;

    private int gravity = 3;
    private int jumpPower = 15;
    private boolean Jumping = false;
    private int currentJumpSpeed = jumpPower;

    private boolean defenseOn = false;

    public final int screenX, screenY; 

    private BufferedImage[] rightImages = new BufferedImage[5];
    private BufferedImage[] leftImages = new BufferedImage[5];
    private BufferedImage[] standingImages = new BufferedImage[2];
    private BufferedImage[] defenseOnImage = new BufferedImage[5];
    private BufferedImage[] jumpRightImages = new BufferedImage[5];
    private BufferedImage[] jumpLeftImages = new BufferedImage[5];

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        this.screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        this.screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultValues();
        setPlayerImage();
    }

    void setDefaultValues(){
        hp = 6;
        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 10;
        speed = gp.tileSize / 4;

        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 10;
        speed = 4;
        directionX = "none";
        directionY = "none";
    }

    void setPlayerImage(){
        try {
            for(int i = 0; i < 5; i++) {
                rightImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/player/right/" + (i+1) + ".png"));
                leftImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/player/left/" + (i+1) + ".png"));
                defenseOnImage[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/player/defenseOn/" + (i+1) + ".png"));
                jumpRightImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/player/jumpRight/" + (i+1) + ".png"));
                jumpLeftImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/player/jumpLeft/" + (i+1) + ".png"));
            }
            for(int i = 0; i < 2; i++) {
                standingImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/player/standing/" + (i+1) + ".png"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        // 1. XỬ LÝ DI CHUYỂN (X-AXIS)
        if(keyH.leftPressed) {
            directionX = "left";
        } else if(keyH.rightPressed) {
            directionX = "right";
        } else if(!keyH.leftPressed && !keyH.rightPressed) {
            directionX = "none";
        }

        // Kiểm tra va chạm trục X
        collisionOn = false;
        gp.cChecker.checkTile(this);
        if(!collisionOn) {
            if(directionX.equals("left")) worldX -= speed;
            if(directionX.equals("right")) worldX += speed;
        }

        // 2. XỬ LÝ NHẢY & TRỌNG LỰC (Y-AXIS)
        if(keyH.upPressed && !Jumping && !Falling) {
            Jumping = true;
            directionY = "up";
            currentJumpSpeed = jumpPower;
        }

        if(Jumping){
            worldY -= currentJumpSpeed;
            currentJumpSpeed -= gravity;
            if(currentJumpSpeed <= 0) {
                Jumping = false;
                Falling = true;
                directionY = "down";
            }
        }

        gp.cChecker.isFalling(this);
        if(Falling && !Jumping) {
            worldY += gravity * 4;
        }else{
            directionY = "none";
        }

        // 4. ANIMATION LOGIC
        if(directionX.equals("none")) {
            spriteNum = 1;
        } else {
            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 3;
                }
                else if(spriteNum == 3 && defenseOn == false){
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

    public void draw(Graphics g2) {
        BufferedImage image = null;
        if(!Falling && !Jumping) {
            if (directionX.equals("right")) {
                image = rightImages[spriteNum - 1];
            } else if (directionX.equals("left")) {
                image = leftImages[spriteNum - 1];
            } else if (directionX.equals("none")) {
                image = standingImages[0];
            } else if (defenseOn) {
                image = defenseOnImage[spriteNum - 1];
            }
        else if (Jumping) {
            if (directionX.equals("right")) {
                image = rightImages[2];
            } else if (directionX.equals("left")) {
                image = leftImages[2];
            } else {
                image = standingImages[1];
            }
        } else if (Falling) {
            if (directionX.equals("right")) {
                image = rightImages[3];
            } else if (directionX.equals("left")) {
                image = leftImages[3];
            } else {
                image = standingImages[1];
            }
        }
            
        
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}    