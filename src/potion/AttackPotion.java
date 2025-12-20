package potion;

import javax.imageio.ImageIO;

import core.GamePanel;

public class AttackPotion extends Potion{
    public AttackPotion(GamePanel gp){
        super(gp);
        setDefaultValues();
        setImage();
    }

    public void setDefaultValues(){
        name = "Attack Potion";
        duration = 120;
    }

    public void setImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/image/potion/attackPotion.png"));
        }catch(Exception e){
            e.getStackTrace();
        }
    }
}
