package object;

import javax.imageio.ImageIO;

public class Chest extends SuperObject{
    public Chest(){
        name = "Chest";
        setImage();
        collision = true;
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
