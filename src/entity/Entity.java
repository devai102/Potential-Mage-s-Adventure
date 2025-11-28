package src.entity;

import java.awt.Rectangle;

public class Entity {
    public int worldX;
    public int worldY;
    public int speed;

    public String direction;

    int spriteCounter = 0;
    int spriteNum = 1;

    public boolean collisionOn = false;
    public Rectangle solidArea;

    public boolean onGround = true;
}