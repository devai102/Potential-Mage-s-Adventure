package entity;

import core.GamePanel;

public class ProjectileAtk extends Entity{
    Entity user;
    int limitRange;

    public ProjectileAtk(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String directionX, boolean alive, Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        this.directionX = directionX;
        this.alive = alive;
        this.user = user;
        this.health = this.maxHealth;
        this.limitRange = this.speed * 20;   // Increased multiplier for further flying distance
        this.solidArea = new java.awt.Rectangle(0, 0, gp.tileSize, gp.tileSize);
    }

    public void update(){
        if(user == gp.player){
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monsters);
            if(monsterIndex != 999){
                gp.player.damageMonster(monsterIndex, this.attack);
                alive = false;
                return;
            }
        }
        
        if(directionX.equals("left") || directionX.equals("none")){
            worldX -= speed;
        }else if(directionX.equals("right")){
            worldX += speed;
        }
        limitRange -= speed;

        if(limitRange <= 0){
            alive = false;
        }
    }
}
