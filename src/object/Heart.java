package object;

import java.awt.image.BufferedImage;
import core.GamePanel;
import entity.Entity;

public class Heart extends Entity{
    GamePanel gp;
    BufferedImage[] images = new BufferedImage[3];

    public Heart(GamePanel gp){
        super(gp);
        setImage();
        setDefaultValues();
    }
    
    @Override
    public void setImage(){
        try{
            for(int i=0;i<3;i++){
                images[i] = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/image/object/heart_" + i + ".png"));
            }
        }catch(Exception e){
            e.getStackTrace();
        }
    }

    @Override
    public void setDefaultValues(){
        name = "Heart";
        type = 3;
        collisionOn = false;
    }
}
