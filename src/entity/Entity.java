package entity;

import java.awt.Rectangle;

import core.GamePanel;

public class Entity {
    protected GamePanel gp;

    public int worldX;
    public int worldY;

    //status
    public String name;
    public int maxHealth;
    public int health;
    public int attack;
    public int speed;

    public String directionX = "right";
    public String directionY = "none";
    protected boolean Falling = false;
    protected boolean Jumping = false;

    protected int spriteCounter = 0;
    protected int spriteNum = 1;

    public boolean collisionOn = false;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public Rectangle solidArea = new Rectangle(8,16,32,32);
    public int solidAreaDefaultX = solidArea.x;
    public int solidAreaDefaultY = solidArea.y;
    public void setAction(){}

    public Entity(GamePanel gp){
        this.gp = gp;
    }
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