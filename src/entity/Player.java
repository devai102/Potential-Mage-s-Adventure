package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import core.GamePanel;
import core.KeyHandler;

public class Player extends Entity{
    KeyHandler keyH;
    private boolean Falling = false;
    private int gravity = 2;
    private int jumpPower = 20;
    private boolean Jumping = false;
    private int currentJumpSpeed = jumpPower;

    private boolean defenseOn = false;

    public final int screenX , screenY;

    // image
    private BufferedImage[] rightImages = new BufferedImage[5];
    private BufferedImage[] leftImages = new BufferedImage[5];
    private BufferedImage standingImages;
    private BufferedImage defenseOnImage;
    private BufferedImage[] jumpRightImages = new BufferedImage[5];
    private BufferedImage[] jumpLeftImages = new BufferedImage[5];
    private BufferedImage[] fallingLeftImages = new BufferedImage[5];
    private BufferedImage[] fallingRightImages = new BufferedImage[5];

    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH = keyH;
        this.screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        this.screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        setDefaultValues();
        setImage();
    }

    @Override
    public void setDefaultValues(){
        maxHealth = 6;
        health = maxHealth;
        attack = 1;
        // can set up lai vi tri spawn
        worldX = gp.tileSize * 11;
        worldY = gp.tileSize * 8;
        speed = 4;
        directionX = "none";
        directionY = "none";
    }

    @Override
    public void setImage(){
        try {
            for(int i = 0; i < 5; i++) {
                rightImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/player/right/" + (i+1) + ".png"));
                leftImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/player/left/" + (i+1) + ".png"));
                jumpRightImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/player/jumpRight/" + (i+1) + ".png"));
                jumpLeftImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/player/jumpLeft/" + (i+1) + ".png"));
                fallingRightImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/player/fallingRight/" + (i+1) + ".png"));
                fallingLeftImages[i] = ImageIO.read(getClass().getResourceAsStream("/res/image/player/fallingLeft/" + (i+1) + ".png"));
            }
            defenseOnImage = ImageIO.read(getClass().getResourceAsStream("/res/image/player/defenseOn/defense.png"));

            standingImages = ImageIO.read(getClass().getResourceAsStream("/res/image/player/standing/1.png"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        // Check defense first
        if(keyH.downPressed && !Jumping && !Falling) {
            defenseOn = true;
            directionX = "none";
        } else {
            defenseOn = false;
        }

        if(!defenseOn){
            if(keyH.leftPressed) {
                directionX = "left";
            } else if(keyH.rightPressed) {
                directionX = "right";
            } else if(!keyH.leftPressed && !keyH.rightPressed) {
                directionX = "none";
            }
        }
        
        collisionOn = false;
        gp.cChecker.checkTile(this);
        if(!collisionOn) {
            if(directionX.equals("left")) worldX -= speed;
            if(directionX.equals("right")) worldX += speed;
        }

        // Check object collision
        int objectIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objectIndex);

        // Check monster collision
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monsters);
        contactMonster(monsterIndex);
        isAlive();
        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }

        // gravity and jumping mechanics
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
            worldY += gravity;
        }else{
            directionY = "none";
        }

        // animation
        if(directionX.equals("none")) {
            spriteNum = 1;
        }else {
            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                } else if(spriteNum == 2) {
                    spriteNum = 3;
                } else if(spriteNum == 3) {
                    spriteNum = 4;
                } else if(spriteNum == 4) {
                    spriteNum = 5;
                } else if(spriteNum == 5) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gp) {
        BufferedImage image = null;
        if(!Falling && !Jumping) {
            if (defenseOn) {
                image = defenseOnImage;
            } else if (directionX.equals("right")) {
                image = rightImages[spriteNum - 1];
            } else if (directionX.equals("left")) {
                image = leftImages[spriteNum - 1];
            } else if (directionX.equals("none")) {
                image = standingImages;
            } 
        }
        else if (Jumping) {
            if (directionX.equals("right")) {
                image = jumpRightImages[spriteNum - 1];
            } else if (directionX.equals("left")) {
                image = jumpLeftImages[spriteNum - 1];
            } else {
                image = standingImages;
            }
        } else if (Falling) {
            if (directionX.equals("right")) {
                image = fallingRightImages[spriteNum - 1];
            } else if (directionX.equals("left")) {
                image = fallingLeftImages[spriteNum - 1];
            } else {
                image = standingImages;
            }
        } 
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }


    private void contactMonster(int i) {
        if(i != 999) {
            if(defenseOn == false && invincible == false) {
                health -= gp.monsters[i].attack;
                if(health < 0) health = 0;
                invincible = true;
            }
        }
    }

    private void isAlive(){
        if(health <= 0){
            gp.playSE(3);
            gp.gameState = gp.gameOverState;
            gp.stopMusic();
        }
    }

    private void pickUpObject(int i) {
        if(i != 999) {
            switch (gp.obj[i].name) {
                case "Teddy":
                    gp.playSE(1);
                    gp.obj[i] = null;
                    gp.gameState = gp.winState;
                    gp.winSession.start(gp.playTime);
                    break;
                case "Chest":
                    gp.playSE(2);
                    gp.obj[i] = null;
                    break;
            }
        }
    }

    public int getHp() {
        return health;
    }

    public int getMaxHp() {
        return maxHealth;
    }

    public void setHp(int hp) {
        this.health = hp;
    }

    public boolean isDefending() {
        return defenseOn;
    }

    public void setFalling(boolean Falling) {
        this.Falling = Falling;
    }
}    