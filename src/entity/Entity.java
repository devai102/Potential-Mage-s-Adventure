package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.GamePanel;

public class Entity {
    protected GamePanel gp;

    //position
    public int worldX;
    public int worldY;
    protected String directionX = "right";
    protected String directionY = "none";

    //status
    protected int maxHealth;
    protected int health;
    protected int attack;
    protected int speed;
    protected String name;

    //counters
    protected int spriteCounter = 0;
    protected int spriteNum = 1;

    //collision
    public boolean collisionOn = false;
    protected boolean invincible = false;
    protected int invincibleCounter = 0;
    public Rectangle solidArea = new Rectangle(8,16,32,32);
    public int solidAreaDefaultX = solidArea.x;
    public int solidAreaDefaultY = solidArea.y;
    protected int type; // 1 = player, 2 = mob, 3 = object
    public boolean collision = false;
    protected boolean Falling = false;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void draw(Graphics2D g2){}
    public void update(){}
    public void setAction(){}
    public void setDefaultValues(){}
    public void setImage(){}
    
    public void setLocation(int worldX, int worldY){
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public BufferedImage setup(String imagePath, int width, int height){
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = scaledImage.createGraphics();
            g2.drawImage(image, 0, 0, width, height, null);
            g2.dispose();
            return scaledImage;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public String getDirectionX(){
        return this.directionX;
    }

    public String getDirectionY(){
        return this.directionY;
    }

    public int getSpeed(){
        return this.speed;
    }

    public void setFalling(boolean Falling) {
        this.Falling = Falling;
    }
}