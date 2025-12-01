import java.awt.Image;
import java.awt.Rectangle;

import core.GamePanel;

public class SuperObject {
    GamePanel   gp;

    protected int worldX, worldY;
    protected Rectangle solidArea;
    protected Image image;
    
    public SuperObject(GamePanel gp){
        this.gp = gp;    
    }
}
