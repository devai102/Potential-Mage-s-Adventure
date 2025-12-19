package object;

import java.awt.image.BufferedImage;
import core.GamePanel;
import entity.Entity;

public class Teddy extends Entity{
    BufferedImage image;
    public Teddy(GamePanel gp){
        super(gp);
        setDefaultValues();
        setImage();
    }

    @Override
    public void setDefaultValues(){
        name = "Teddy";
        type = 3;
        collisionOn = true;
    }

    @Override
    public void setImage(){
        try{
            image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/image/object/teddy.png"));
        }catch(Exception e){
            e.getStackTrace();
        }
    }
}
