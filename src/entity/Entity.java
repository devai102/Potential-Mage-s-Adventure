package entity;

import java.awt.Rectangle;

public class Entity {
    public int worldX;
    public int worldY;
    public int speed;

    public String directionX = "right";
    public String directionY = "none";
    protected boolean Falling = false;

    protected int spriteCounter = 0;
    protected int spriteNum = 1;

    public boolean collisionOn = false;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public Rectangle solidArea;

    public void isFalling(boolean Falling){
        this.Falling = Falling;
    }

    public String getDirectionX(){
        return this.directionX;
    }

    public String getDirectionY(){
        return this.directionY;
    }
}