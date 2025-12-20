package object;

import core.GamePanel;
import entity.Entity;

public class Teddy extends Entity{
    public Teddy(GamePanel gp){
        super(gp);
        setDefaultValues();
        setImage();
    }

    @Override
    public void setDefaultValues(){
        name = "Teddy";
        type = 3;
        collision = true;
        solidArea = new java.awt.Rectangle(0,0,gp.tileSize,gp.tileSize);
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }

    @Override
    public void setImage(){
        image = setup("/res/image/object/Teddy", gp.tileSize, gp.tileSize);    
    }   
}
