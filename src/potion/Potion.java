package potion;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.GamePanel;
import entity.Entity;

public class Potion extends Entity {   
    BufferedImage image;
    int duration;

    public Potion(GamePanel gp) {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}
