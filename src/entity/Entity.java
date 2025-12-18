package entity;

import java.awt.Rectangle;

public class Entity {
    public int worldX;
    public int worldY;
    public int speed;

    public String directionX = "right";
    public String directionY = "none";
    protected boolean Falling = false;
    protected boolean Jumping = false;

    protected int spriteCounter = 0;
    protected int spriteNum = 1;

    public boolean collisionOn = false;
    public Rectangle solidArea;
    public int solidAreaDefaultX = solidArea.x;
    public int solidAreaDefaultY = solidArea.y;

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