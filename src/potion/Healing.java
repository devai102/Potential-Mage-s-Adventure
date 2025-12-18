package potion;

import java.awt.Rectangle;
import javax.imageio.ImageIO;

import core.GamePanel;

public class Healing extends SuperPotion{
    public Healing(GamePanel gp){
        super(gp);
        setDefaultValues();
        setImage();
    }

    void setDefaultValues(){
        name = "Healing Potion";
        solidArea = new Rectangle(0,0,gp.tileSize,gp.tileSize);
    }

    void setImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream(""));
        }catch(Exception e){
            e.getStackTrace();
        }
    }
}