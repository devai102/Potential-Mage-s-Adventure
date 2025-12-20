package object;

import core.GamePanel;
import entity.Entity;

public class Chest extends Entity{
    public Chest(GamePanel gp){
        super(gp);
        setDefaultValues();
        setImage();
    }

    @Override
    public void setDefaultValues(){
        name = "Chest";
        type = 3;
        collision = true;
        solidArea = new java.awt.Rectangle(0,0,gp.tileSize,gp.tileSize);
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }
    
    @Override
    public void setImage(){
        image = setup("/res/image/object/chest", gp.tileSize, gp.tileSize);
    }

    public int open(){
        int rd = new java.util.Random().nextInt(3) + 1;
        return rd;
    }
}
