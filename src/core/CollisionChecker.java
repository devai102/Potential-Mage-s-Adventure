package core;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        entity.collisionOn = false;
        
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.getDirectionX()){
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gp.tileSize;
                if(entityLeftCol >= 0 && entityLeftCol < gp.maxWorldCol && 
                   entityTopRow >= 0 && entityTopRow < gp.maxWorldRow &&
                   entityBottomRow >= 0 && entityBottomRow < gp.maxWorldRow){
                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                    if((tileNum1 >= 0 && tileNum1 < gp.tileM.tile.length && gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum1].collision) ||
                       (tileNum2 >= 0 && tileNum2 < gp.tileM.tile.length && gp.tileM.tile[tileNum2] != null && gp.tileM.tile[tileNum2].collision)){
                        entity.collisionOn = true;
                    }
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gp.tileSize;
                if(entityRightCol >= 0 && entityRightCol < gp.maxWorldCol && 
                   entityTopRow >= 0 && entityTopRow < gp.maxWorldRow &&
                   entityBottomRow >= 0 && entityBottomRow < gp.maxWorldRow){
                    tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                    if((tileNum1 >= 0 && tileNum1 < gp.tileM.tile.length && gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum1].collision) ||
                       (tileNum2 >= 0 && tileNum2 < gp.tileM.tile.length && gp.tileM.tile[tileNum2] != null && gp.tileM.tile[tileNum2].collision)){
                        entity.collisionOn = true;
                    }
                }
                break;
        }

        switch(entity.getDirectionY()){
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gp.tileSize;
                if(entityLeftCol >= 0 && entityLeftCol < gp.maxWorldCol &&
                   entityRightCol >= 0 && entityRightCol < gp.maxWorldCol &&
                   entityTopRow >= 0 && entityTopRow < gp.maxWorldRow){
                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                    if((tileNum1 >= 0 && tileNum1 < gp.tileM.tile.length && gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum1].collision) ||
                       (tileNum2 >= 0 && tileNum2 < gp.tileM.tile.length && gp.tileM.tile[tileNum2] != null && gp.tileM.tile[tileNum2].collision)){
                        entity.collisionOn = true;
                    }
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gp.tileSize;
                if(entityLeftCol >= 0 && entityLeftCol < gp.maxWorldCol &&
                   entityRightCol >= 0 && entityRightCol < gp.maxWorldCol &&
                   entityBottomRow >= 0 && entityBottomRow < gp.maxWorldRow){
                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                    if((tileNum1 >= 0 && tileNum1 < gp.tileM.tile.length && gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum1].collision) ||
                       (tileNum2 >= 0 && tileNum2 < gp.tileM.tile.length && gp.tileM.tile[tileNum2] != null && gp.tileM.tile[tileNum2].collision)){
                        entity.collisionOn = true;
                    }
                }
                break;
            case "none":
                break;
        }
    }

    public void isFalling(Entity entity){
        int entityLeftWorldX = gp.player.worldX + gp.player.solidArea.x;
        int entityRightWorldX = gp.player.worldX + gp.player.solidArea.x + gp.player.solidArea.width;
        int entityBottomWorldY = gp.player.worldY + gp.player.solidArea.y + gp.player.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityBottomRow = (entityBottomWorldY + 4) / gp.tileSize;

        if(entityLeftCol >= 0 && entityLeftCol < gp.maxWorldCol &&
           entityRightCol >= 0 && entityRightCol < gp.maxWorldCol &&
           entityBottomRow >= 0 && entityBottomRow < gp.maxWorldRow){
            
            int tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            int tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

            boolean collision1 = (tileNum1 >= 0 && tileNum1 < gp.tileM.tile.length && 
                                 gp.tileM.tile[tileNum1] != null && gp.tileM.tile[tileNum1].collision);
            boolean collision2 = (tileNum2 >= 0 && tileNum2 < gp.tileM.tile.length && 
                                 gp.tileM.tile[tileNum2] != null && gp.tileM.tile[tileNum2].collision);

            if(collision1 || collision2){
                gp.player.setFalling(false);
            } else {
                gp.player.setFalling(true);
                gp.player.collisionOn = false;
            }
        } else {
            gp.player.setFalling(true);
            gp.player.collisionOn = false;
        }
    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){
                int entitySolidAreaX = entity.solidArea.x;
                int entitySolidAreaY = entity.solidArea.y;
                int objSolidAreaX = gp.obj[i].solidArea.x;
                int objSolidAreaY = gp.obj[i].solidArea.y;

                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch(entity.getDirectionX()){
                    case "left":
                        entity.solidArea.x -= entity.getSpeed();;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.getSpeed();;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                }

                switch(entity.getDirectionY()){
                    case "up":
                        entity.solidArea.y -= entity.getSpeed();;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.getSpeed();;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                }

                entity.solidArea.x = entitySolidAreaX;
                entity.solidArea.y = entitySolidAreaY;
                gp.obj[i].solidArea.x = objSolidAreaX;
                gp.obj[i].solidArea.y = objSolidAreaY;
            }
        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[] target){
        int index = 999;
        for(int i = 0; i < target.length; i++){
            if(target[i] != null){
                int entitySolidAreaX = entity.solidArea.x;
                int entitySolidAreaY = entity.solidArea.y;
                int targetSolidAreaX = target[i].solidArea.x;
                int targetSolidAreaY = target[i].solidArea.y;

                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch(entity.getDirectionX()){
                    case "left":
                        entity.solidArea.x -= entity.getSpeed();;
                        break;
                    case "right":
                        entity.solidArea.x += entity.getSpeed();;
                        break;
                }

                switch(entity.getDirectionY()){
                    case "up":
                        entity.solidArea.y -= entity.getSpeed();;
                        break;
                    case "down":
                        entity.solidArea.y += entity.getSpeed();;
                        break;
                }

                if(entity.solidArea.intersects(target[i].solidArea)){
                    entity.collisionOn = true;
                    index = i;
                }

                entity.solidArea.x = entitySolidAreaX;
                entity.solidArea.y = entitySolidAreaY;
                target[i].solidArea.x = targetSolidAreaX;
                target[i].solidArea.y = targetSolidAreaY;
            }
        }
        return index;
    }

    public void checkPlayer(Entity entity){
        int entitySolidAreaX = entity.solidArea.x;
        int entitySolidAreaY = entity.solidArea.y;
        int playerSolidAreaX = gp.player.solidArea.x;
        int playerSolidAreaY = gp.player.solidArea.y;

        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch(entity.getDirectionX()){
            case "left":
                entity.solidArea.x -= entity.getSpeed();;
                break;
            case "right":
                entity.solidArea.x += entity.getSpeed();;
                break;
        }

        switch(entity.getDirectionY()){
            case "up":
                entity.solidArea.y -= entity.getSpeed();;
                break;
            case "down":
                entity.solidArea.y += entity.getSpeed();;
                break;
        }

        if(entity.solidArea.intersects(gp.player.solidArea)){
            entity.collisionOn = true;
        }

        entity.solidArea.x = entitySolidAreaX;
        entity.solidArea.y = entitySolidAreaY;
        gp.player.solidArea.x = playerSolidAreaX;
        gp.player.solidArea.y = playerSolidAreaY;
    }
}
