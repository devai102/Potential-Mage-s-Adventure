package object;

import java.awt.Rectangle;

import javax.imageio.ImageIO;

public class Chest extends SuperObject{
    public Chest(){
        setImage();
        solidArea = new Rectangle(0,0,48,48);
    }
    
    void setImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/image/object/chest.png"));
        }catch(Exception e){
            e.getStackTrace();
        }
    }

    void setLoot(){
        // set loot inside chest at random

    }
}
