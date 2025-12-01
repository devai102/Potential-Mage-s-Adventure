package core;

import object.Chest;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new Chest();
        gp.obj[0].setLocation(5*gp.tileSize, 7*gp.tileSize);
    }
}
