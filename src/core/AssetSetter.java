package core;

import object.Teddy;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new Teddy(gp);
        gp.obj[0].setLocation(23*gp.tileSize, 7*gp.tileSize);
    }

    public void setMonster(){
        
    }
}
