package potion;

import java.awt.Rectangle;
import javax.imageio.ImageIO;

import core.GamePanel;

public class Speed extends SuperPotion{
    private int duration = 300;
    private int buffSpeed = 5;
    public Speed(GamePanel gp) {
        super(gp);
        setImage();
        setDefaultValues();
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

    public int getDuration(){
        return duration;
    }
    
}  
