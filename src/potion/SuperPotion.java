package potion;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import core.GamePanel;

public class SuperPotion {
    GamePanel   gp;
    
    protected int x, y;
    
    protected String name;
    protected BufferedImage image;
    protected Rectangle solidArea;

    public SuperPotion(GamePanel gp){
        this.gp = gp;    
    }
}
