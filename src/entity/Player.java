package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import core.GamePanel;
import core.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    private boolean shieldOn = false;

    private int speed;

    private int gravity = 3;
    private int jumpPower = 15;
    private boolean Jumping = false;

    public final int screenX, screenY; 

    private BufferedImage right1,right2,right3,right4,right5;
    private BufferedImage left1,left2,left3,left4,left5;
    private BufferedImage up1;
    private BufferedImage down1;

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
    }

    void setImage(){
        try{
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/image/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/image/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/image/right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/res/image/right4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/res/image/right5.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/image/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/image/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/image/left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/res/image/left4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/res/image/left5.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/image/up1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/image/down1.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void update(){
        collisionOn = false;

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            gp.cChecker.isFalling(this);
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
                else if(keyH.leftPressed == true){
                    directionX= "left";
                }
                else if(keyH.rightPressed == true){
                    directionX= "right";
                }
            }

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
        if(Falling == true){
            gp.cChecker.checkTile(this);
            gp.cChecker.isFalling(this);
            if(collisionOn == false){
                worldY += gravity;
            }else{
                Falling = false;
            }
        }
        
        
        

        //Sprite animation
        spriteCounter++;
        if(spriteCounter > 10){
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
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics g2){
        BufferedImage image = null;
        switch(directionX){
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                else if(spriteNum == 2){
                    image = right2;
                }
                else if(spriteNum == 3){
                    image = right3;
                }
                else if(spriteNum == 4){
                    image = right4;
                }
                else if(spriteNum == 5){
                    image = right5;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                else if(spriteNum == 2){
                    image = left2;
                }
                else if(spriteNum == 3){
                    image = left3;
                }
                else if(spriteNum == 4){
                    image = left4;
                }
                else if(spriteNum == 5){
                    image = left5;
                }
                break;
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}