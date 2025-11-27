package src.entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import src.core.GamePanel;
import src.core.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX, screenY; 

    BufferedImage right1,right2,right3,right4,right5;
    BufferedImage left1,left2,left3,left4,left5;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        this.screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        this.screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        setDefaultValues();
        setImage();
    }

    void setDefaultValues(){
        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 10;
        speed = gp.tileSize / 4;
    }

    void setImage(){
        try{
            right1 = ImageIO.read(getClass().getResourceAsStream("/rsc/image/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/rsc/image/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/rsc/image/right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/rsc/image/right4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/rsc/image/right5.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/rsc/image/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/rsc/image/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/rsc/image/left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/rsc/image/left4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/rsc/image/left5.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if(keyH.leftPressed == true){
                direction = "left";
                worldX -= speed;
            }
            else if(keyH.rightPressed == true){
                direction = "right";
                worldX += speed;
            }
        }

        //sprite animation
        spriteCounter++;
        if(spriteCounter > 12){
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

    public void draw(Graphics g2){
        BufferedImage image = null;
        switch(direction){
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
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}