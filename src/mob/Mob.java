package mob;

import java.awt.image.BufferedImage;

import core.GamePanel;
import entity.Entity;

public class Mob extends Entity{
    GamePanel gp;
    protected int health;
    protected int attack;
    protected int speed;
    protected String name;

    public Mob(GamePanel gp){
        this.gp = gp;
    }

    BufferedImage left1, left2, right1, right2;
}
