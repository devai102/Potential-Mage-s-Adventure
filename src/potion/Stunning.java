package potion;

import core.GamePanel;

public class Stunning extends SuperPotion{
    private int duration; // duration of paralysis effect in seconds
    
    public Stunning(GamePanel gp) {
        super(gp);
        setImage();
        setDefaultValues();
    }
    
    void setDefaultValues(){
        name = "Paralysis Potion";
        solidArea = new java.awt.Rectangle(0,0,gp.tileSize,gp.tileSize);
    }

    void setImage(){
        try{
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream(""));
        }catch(Exception e){
            e.getStackTrace();
        }
    }
}
