package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import core.GamePanel;

public abstract class Entity {
    protected GamePanel gp;

    public int worldX;
    public int worldY;

    //status
    protected int maxHealth;
    protected int health;
    protected int attack;
    protected int speed;
    protected String name;

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
    public boolean collision = false;

    public Entity(GamePanel gp){
        this.gp = gp;
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

    public void draw(Graphics2D g2){
    }

    public void update(){
    }

    public void setAction(){
    }

    public void setDefaultValues(){
    }

    public void setImage(){
    }
    
    public void setLocation(int worldX, int worldY){
        this.worldX = worldX;
        this.worldY = worldY;
    }
}