package potion;

import core.GamePanel;

public class paralysisPotion extends SuperPotion{
    private int duration; // duration of paralysis effect in seconds
    
    public paralysisPotion(GamePanel gp) {
        super(gp);
        setDefaultValues();
    }

    public void setDefaultValues(){
        name = "Paralysis Potion";
        solidArea = new java.awt.Rectangle(8, 16, 32, 32);
    }

    // public void setImage(){
    //     try{
    //         image = javax.imageio.ImageIO.read(getClass().getResourceAsStream(""));
    //     }catch(Exception e){
    //         e.getStackTrace();
    //     }
    // }

    public void draw(java.awt.Graphics2D g2){
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    public int getDuration(){
        return duration;
    }
}
