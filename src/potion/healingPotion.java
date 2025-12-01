package potion;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.imageio.ImageIO;

import core.GamePanel;

public class healingPotion extends SuperPotion{
    public healingPotion(GamePanel gp){
        super(gp);
        setDefaultValues();
        setImage();
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setDefaultValues(){
        name = "Healing Potion";
        solidArea = new Rectangle(8, 16, 32, 32);
    }

    public void setImage(){
        try{
            image = ImageIO.read(getClass().getResourceAsStream(""));
        }catch(Exception e){
            e.getStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
