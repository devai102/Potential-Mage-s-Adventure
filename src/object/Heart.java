package object;

import java.awt.image.BufferedImage;
import core.GamePanel;

public class Heart extends SuperObject{
    GamePanel gp;
    BufferedImage[] images = new BufferedImage[3];

    public Heart(GamePanel gp){
        this.gp = gp;
        name = "Heart";
        setImage();
        collision = false;
    }
    
    void setImage(){
        try{
            for(int i=0;i<3;i++){
                images[i] = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/image/object/heart_"+i+".png"));
                uTool.scaleImage(images[i], gp.tileSize, gp.tileSize);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
    }
    
}
