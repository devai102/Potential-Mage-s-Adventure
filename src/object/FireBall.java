package object;

import java.awt.image.BufferedImage;

import core.GamePanel;
import entity.ProjectileAtk;

public class FireBall extends ProjectileAtk{
        @Override
        public void draw(java.awt.Graphics2D g2) {
            java.awt.image.BufferedImage image = null;
            if (directionX.equals("left") || directionX.equals("none")) {
                image = leftImage;
            } else if (directionX.equals("right")) {
                image = rightImage;
            }
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        }
    BufferedImage leftImage, rightImage;
    public FireBall(GamePanel gp) {
        super(gp);
        setDefaultValues();
        getImage();
    }

    public void setDefaultValues(){
        name = "Fire Ball";
        speed = 6;
        maxHealth = 8;
        health = maxHealth;
        alive = false;
        type = 2;
    }

    public void getImage(){
        leftImage = setup("/res/image/object/projectile/fireBallLeft", gp.tileSize, gp.tileSize);
        rightImage = setup("/res/image/object/projectile/fireBallRight", gp.tileSize, gp.tileSize);
    }
    
}
