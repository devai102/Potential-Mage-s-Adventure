package potion;

import javax.imageio.ImageIO;
import core.GamePanel;

public class SpeedPotion extends Potion{
    public SpeedPotion(GamePanel gp){
        super(gp);
        setImage();
        setDefaultValues();
    }

    public void setDefaultValues(){
        name = "Speed Potion";
        duration = 120;
    }

    public void setImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/image/potion/speedPotion.png"));
        }catch(Exception e){
            e.getStackTrace();
        }
    }
}  
