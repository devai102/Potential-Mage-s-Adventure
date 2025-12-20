package object;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import core.GamePanel;
import entity.Entity;

public class Chest extends Entity{
    BufferedImage image;

    public Chest(GamePanel gp){
        super(gp);
        setDefaultValues();
        setImage();
    }

    @Override
    public void setDefaultValues(){
        name = "Chest";
        type = 3;
        collisionOn = true;
    }

    @Override
    public void setImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/image/object/chest.png"));
        }catch(Exception e){
            e.getStackTrace();
        }
    }

    @Override
    public void draw(java.awt.Graphics2D g2){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
    void loot(){

    }
}
