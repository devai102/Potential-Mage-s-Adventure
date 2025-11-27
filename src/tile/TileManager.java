package src.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import src.core.GamePanel;

public class TileManager extends Tile {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        loadTileImage();
        loadMap("/rsc/map/map01.txt");
    }

    void loadMap(String filePath){
        InputStream is = getClass().getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try{
            int col = 0;
            int row = 0;
            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();
                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    void loadTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/rsc/image/grass.png"));

            tile[1] = new Tile();
            tile[1].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/rsc/image/rock.png"));
        }catch(Exception e){
            e.printStackTrace();
        }    
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;
        int x = 0;
        int y = 0;
        while(worldCol < gp.maxScreenCol && worldRow < gp.maxScreenRow){
            int tileNum = mapTileNum[worldCol][worldRow];
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            worldCol++;
            x += gp.tileSize;
            if(worldCol == gp.maxScreenCol){
                worldCol = 0;
                x = 0;
                worldRow++;
                y += gp.tileSize;
            }
        }
    }
}
