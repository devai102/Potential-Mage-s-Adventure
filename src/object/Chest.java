package object;

import java.awt.Rectangle;

import javax.imageio.ImageIO;

public class Chest extends SuperObject{
    public Chest(){
        setImage();
        solidArea = new Rectangle(8, 16, 32, 32);
    }
    
    void setImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/image/object/chest.png"));
        }catch(Exception e){
            e.getStackTrace();
        }
    }

    void setLoot(){

    }
}
