package potion;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.imageio.ImageIO;

import core.GamePanel;

public class speedPotion extends SuperPotion{
    private int duration = 300;
    private int buffSpeed = 5;
    public speedPotion(GamePanel gp) {
        super(gp);
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

    public int getDuration(){
        return duration;
    }
    
    // public void effect(){
    //     gp.player.speed += buffSpeed;
    // }
}  
