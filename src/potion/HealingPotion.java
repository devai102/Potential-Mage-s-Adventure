package potion;

import javax.imageio.ImageIO;

import core.GamePanel;

public class HealingPotion extends Potion{
    public HealingPotion(GamePanel gp){
        super(gp);
        setDefaultValues();
        setImage();
    }

    public void setDefaultValues(){
        name = "Healing Potion";
        duration = 0;
    }

    public void setImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/image/potion/healingPotion.png"));
        }catch(Exception e){
            e.getStackTrace();
        }
    }
}