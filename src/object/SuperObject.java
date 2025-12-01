package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import core.GamePanel;

public class SuperObject {
    protected int worldX, worldY;
    protected Rectangle solidArea;
    protected BufferedImage image;
    protected boolean collision = false;

    public void setLocation(int worldX, int worldY){
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public void draw(Graphics2D g2,GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
