package entity;

import java.awt.Rectangle;

import core.GamePanel;

public class Entity {
    protected GamePanel gp;

    public int worldX;
    public int worldY;

    //status
    protected String name;
    protected int maxHealth;
    protected int health;
    protected int attack;
    protected int speed;
    protected String directionX = "right";
    protected String directionY = "none";
    protected int spriteCounter = 0;
    protected int spriteNum = 1;
    public boolean collisionOn = false;
    protected boolean invincible = false;
    protected int invincibleCounter = 0;
    public Rectangle solidArea = new Rectangle(8,16,32,32);
    public int solidAreaDefaultX = solidArea.x;
    public int solidAreaDefaultY = solidArea.y;
    protected int type; // 1 = player, 2 = mob, 3 = object

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction(){}
    
    public String getDirectionX(){
        return this.directionX;
    }

    public String getDirectionY(){
        return this.directionY;
    }

    public int getSpeed(){
        return this.speed;
    }
}