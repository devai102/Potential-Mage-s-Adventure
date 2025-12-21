package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import core.GamePanel;
import core.KeyHandler;

public class Player extends Entity{
    KeyHandler keyH;
    private int gravity = 2;
    private int jumpPower = 20;
    private int currentJumpSpeed = jumpPower;
    private int buffNumber = 0;
    private boolean Jumping = false;
    private boolean defenseOn = false;
    private boolean attackOn = false;

    public final int screenX , screenY;

    // image
    private BufferedImage[] attackRightImages = new BufferedImage[5];
    private BufferedImage[] attackLeftImages = new BufferedImage[5];
    private BufferedImage[] rightImages = new BufferedImage[5];
    private BufferedImage[] leftImages = new BufferedImage[5];
    private BufferedImage standingImages;
    private BufferedImage[] defenseLeftImages = new BufferedImage[5];
    private BufferedImage[] defenseRightImages = new BufferedImage[5];
    private BufferedImage[] jumpRightImages = new BufferedImage[5];
    private BufferedImage[] jumpLeftImages = new BufferedImage[5];
    private BufferedImage[] fallingLeftImages = new BufferedImage[5];
    private BufferedImage[] fallingRightImages = new BufferedImage[5];
    private BufferedImage[] jumpStraiImages = new BufferedImage[5];

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
        type = 0;
        worldX = gp.tileSize * 11;
        worldY = gp.tileSize * 50;
        speed = 4;
        directionX = "none";
        directionY = "none";
        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = this.solidArea.x;
        solidAreaDefaultY = this.solidArea.y;
        projectile = new object.FireBall(gp);
    }

    @Override
    public void setImage(){
        for(int i = 0; i < 5; i++) {
            rightImages[i] = setup("/res/image/player/right/" + (i+1), gp.tileSize, gp.tileSize);
            leftImages[i] = setup("/res/image/player/left/" + (i+1), gp.tileSize, gp.tileSize);
            attackRightImages[i] = setup("/res/image/player/attackRight/" + (i+1), gp.tileSize, gp.tileSize);
            attackLeftImages[i] = setup("/res/image/player/attackLeft/" + (i+1), gp.tileSize, gp.tileSize);
            jumpRightImages[i] = setup("/res/image/player/jumpRight/" + (i+1), gp.tileSize, gp.tileSize);
            jumpLeftImages[i] = setup("/res/image/player/jumpLeft/" + (i+1), gp.tileSize, gp.tileSize);
            fallingRightImages[i] = setup("/res/image/player/fallingRight/" + (i+1), gp.tileSize, gp.tileSize);
            fallingLeftImages[i] = setup("/res/image/player/fallingLeft/" + (i+1), gp.tileSize, gp.tileSize);
            jumpStraiImages[i] = setup("/res/image/player/jumpStraight/" + (i+1), gp.tileSize, gp.tileSize);
            defenseLeftImages[i] = setup("/res/image/player/defenseOn/left/" + (i+1), gp.tileSize, gp.tileSize);
            defenseRightImages[i] = setup("/res/image/player/defenseOn/right/" + (i+1), gp.tileSize, gp.tileSize);
        }
        standingImages = setup("/res/image/player/standing/1", gp.tileSize, gp.tileSize);
    }

    @Override
    public void update() {
        gp.cChecker.isFalling(this);

        if(!attackOn && !defenseOn){
            if(keyH.upPressed && !Jumping && !Falling) {
                Jumping = true;
                directionY = "up";
                currentJumpSpeed = jumpPower;
                spriteNum = 1;
            }
            if(keyH.downPressed) {
                defenseOn = true;
            }
            if(keyH.attackPressed) {
                attackOn = true;
                spriteCounter = 0;
                spriteNum = 1;
                keyH.attackPressed = false;
            }
        }

        if(!defenseOn && !attackOn){
            if(keyH.leftPressed) {
                directionX = "left";
            }
            if(keyH.rightPressed) {
                directionX = "right";
            }
            if(!keyH.leftPressed && !keyH.rightPressed) {
                directionX = "none";
            }
        }

        if (attackOn || defenseOn || Jumping || Falling) {
            setAction();
        }

        // Check tile collision
        collisionOn = gp.cChecker.checkTile(this);
        if(!collisionOn && !attackOn && !defenseOn) {
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

        // animation
        if(!attackOn && !defenseOn){
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
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        if(attackOn) {
            if(directionX.equals("right")){
                image = attackRightImages[spriteNum - 1];
            }else{
                image = attackLeftImages[spriteNum - 1];
            }
        }else if(defenseOn) {
            if(directionX.equals("right")){
                image = defenseRightImages[spriteNum - 1];
            }else {
                image = defenseLeftImages[spriteNum - 1];
            }
        } else if(!Falling && !Jumping) {
            if (directionX.equals("right")) {
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
            gp.playSE(5);
            gp.gameState = gp.gameOverState;
            gp.stopMusic();
        }
    }

    private void pickUpObject(int i) {
        if(i != 999) {
            switch (gp.obj[i].name) {
                case "Teddy":
                    gp.obj[i] = null;
                    gp.gameState = gp.winState;
                    gp.winSession.start(gp.playTime);
                    break;
                case "Chest":
                    gp.playSE(3);
                    buffNumber = ((object.Chest)gp.obj[i]).open();
                    switch(buffNumber){
                        case 1:
                            if(health < maxHealth)
                                health++;
                                gp.ui.showMessage("Healing!");
                            break;
                        case 2:
                            speed ++;
                            gp.ui.showMessage("Speed Up!");
                            break;
                        case 3:
                            attack ++;
                            gp.ui.showMessage("Attack Up!");
                            break;
                    }
                    gp.obj[i] = null;
                    break;
            }
        }
    }

    @Override
    public void setAction(){
        if(Jumping){
            worldY -= currentJumpSpeed;
            currentJumpSpeed -= gravity;
            if(currentJumpSpeed <= 0){ 
                Jumping = false;
                Falling = true;
                directionY = "down";
            }
        } else if(Falling){
            // Check ground collision
            collisionOn = false;
            directionY = "down";
            gp.cChecker.checkTile(this);
            
            if(!collisionOn){
                worldY += gravity;
            } else {
                Falling = false;
                directionY = "none";
            }
        } else if (attackOn){
            spriteCounter++;
            if(spriteCounter < 12) {
                spriteNum = 1;
            } else if(spriteCounter < 24) {
                spriteNum = 2;
            } else if(spriteCounter < 36) {
                spriteNum = 3;
                projectile.set(worldX, worldY, directionX, true, this);
                gp.projectileList.add(projectile);
            } else if(spriteCounter < 48) {
                spriteNum = 4;
            } else if(spriteCounter < 60) {
                spriteNum = 5;
            } else {
                spriteNum = 1;
                spriteCounter = 0;
                attackOn = false;
            }
        } else if (defenseOn){
            spriteCounter++;
            if(spriteCounter < 12) {
                spriteNum = 1;
            } else if(spriteCounter < 24) {
                spriteNum = 2;
            } else if(spriteCounter < 36) {
                spriteNum = 3;
            } else if(spriteCounter < 48) {
                spriteNum = 4;
            } else if(spriteCounter < 60) {
                spriteNum = 5;
            } else {
                spriteNum = 1;
                spriteCounter = 0;
                defenseOn = false;
            }
        } 
    }

    public void damageMonster(int i, int attack){
        if(i != 999){
            if(gp.monsters[i].invincible == false){
                gp.monsters[i].health -= attack;
                gp.monsters[i].invincible = true;
                gp.monsters[i].dying = true;
                if(gp.monsters[i].health <= 0){
                    gp.monsters[i].alive = false;
                }
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

    public int getAttack() {
        return attack;
    }
}