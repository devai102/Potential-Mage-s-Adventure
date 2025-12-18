package object;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Chest extends SuperObject{
    BufferedImage image;

    public Chest(){
        name = "Chest";
        setImage();
        collision = true;
    }
    
    void setImage(){
        try{
            image1 = ImageIO.read(getClass().getResourceAsStream("/res/image/object/chest.png"));
        }catch(Exception e){
            e.getStackTrace();
        }
    }

    void setLoot(){
        

    }
}
