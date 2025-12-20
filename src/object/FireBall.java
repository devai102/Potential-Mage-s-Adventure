package object;

import core.GamePanel;
import entity.ProjectileAtk;

public class FireBall extends ProjectileAtk{
    
    public FireBall(GamePanel gp) {
        super(gp);
        setDefaultValues();
        getImage();
    }

    public void setDefaultValues(){
        name = "Fire Ball";
        speed = 10;
        maxHealth = 80;
        health = maxHealth;
        attack = gp.player.getAttack();
    }
    public void getImage(){
    }
    
}
