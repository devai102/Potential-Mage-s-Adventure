package core;

import mob.*;
import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        // gp.obj[0] = new Teddy(gp);
        // gp.obj[0].setLocation(23*gp.tileSize, 7*gp.tileSize);

        gp.obj[0] = new Chest(gp);
        gp.obj[0].setLocation(23*gp.tileSize, 7*gp.tileSize);
    }

    public void setMonster(){
       gp.monsters[0] = new Golem(gp);
       gp.monsters[0].setLocation(23*gp.tileSize, 8*gp.tileSize);
    }
}
