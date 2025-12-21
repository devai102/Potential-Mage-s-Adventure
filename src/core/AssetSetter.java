package core;

import mob.*;
import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        for (int i = 0; i < gp.obj.length; i++) {
            gp.obj[i] = null;
        }
        gp.obj[0] = new Teddy(gp);
        gp.obj[0].setLocation(15*gp.tileSize, 49*gp.tileSize);

        gp.obj[1] = new Chest(gp);
        gp.obj[1].setLocation(40*gp.tileSize, 7*gp.tileSize);

        gp.obj[2] = new Chest(gp);
        gp.obj[2].setLocation(10*gp.tileSize, 49*gp.tileSize);

        gp.obj[3] = new Chest(gp);
        gp.obj[3].setLocation(10*gp.tileSize, 49*gp.tileSize);

        gp.obj[4] = new Chest(gp);
        gp.obj[4].setLocation(10*gp.tileSize, 49*gp.tileSize);
    }

    public void setMonster(){
       for (int i = 0; i < gp.monsters.length; i++) {
           gp.monsters[i] = null;
       }
       gp.monsters[0] = new Golem(gp);
       gp.monsters[0].setLocation(23*gp.tileSize, 8*gp.tileSize);

       gp.monsters[1] = new Golem(gp);
       gp.monsters[1].setLocation(46*gp.tileSize, 7*gp.tileSize);

       gp.monsters[2] = new Golem(gp);
       gp.monsters[2].setLocation(23*gp.tileSize, 8*gp.tileSize);

       gp.monsters[3] = new Golem(gp);
       gp.monsters[3].setLocation(23*gp.tileSize, 8*gp.tileSize);

       gp.monsters[4] = new Golem(gp);
       gp.monsters[4].setLocation(23*gp.tileSize, 8*gp.tileSize);
    }
}
