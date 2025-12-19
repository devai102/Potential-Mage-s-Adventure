package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import core.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[15];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadTileImage();
        loadMap("/res/map/map01.txt");
    }

    void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            // Vòng lặp an toàn: Dừng lại nếu HẾT FILE hoặc ĐỦ ROW
            while (row < gp.maxWorldRow) {
                String line = br.readLine();

                // NẾU HẾT DÒNG ĐỂ ĐỌC -> THOÁT VÒNG LẶP NGAY
                if (line == null) {
                    break;
                }

                // Dùng \\s+ để xử lý nếu giữa các số có nhiều hơn 1 dấu cách
                String numbers[] = line.trim().split("\\s+");

                while (col < gp.maxWorldCol) {
                    // Đảm bảo không đọc quá số lượng phần tử thực tế của mảng numbers
                    if (col < numbers.length) {
                        int num = Integer.parseInt(numbers[col]);
                        mapTileNum[col][row] = num;
                    }
                    col++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void loadTileImage(){
        try{
            //NEN TRONG
            tile[0] = new Tile();
            tile[0].image = javax.imageio.ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/res/image/map/Sky/Sky1.png"))));
            tile[0].collision = false;

            tile[4] = new Tile();
            tile[4].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/map/Sky/Sky2.png")));
            tile[4].collision = false;

            tile[9] = new Tile();
            tile[9].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/map/Sky/Sky3.png")));
            tile[9].collision = false;

            tile[10] = new Tile();
            tile[10].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/map/Sky/Sky4.png")));
            tile[10].collision = true;

            tile[11] = new Tile();
            tile[11].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/map/Tree/SkyTree1.png")));
            tile[11].collision = false;

            tile[12] = new Tile();
            tile[12].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/map/Tree/SkyTree2.png")));
            tile[12].collision = false;

            tile[13] = new Tile();
            tile[13].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/map/Ground/GroundRia1.png")));
            tile[13].collision = true;

            tile[14] = new Tile();
            tile[14].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/map/Ground/GroundRia2.png")));
            tile[14].collision = true;

            tile[15] = new Tile();
            tile[15].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/map/Ground/UpGround3.png")));
            tile[15].collision = true;
            //DAT1
            tile[1] = new Tile();
            tile[1].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/map/Ground/Ground1.png")));
            tile[1].collision = true;
            //DAT2
            tile[2] = new Tile();
            tile[2].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/map/Ground/Ground2.png")));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/map/Ground/Ground3.png")));
            tile[3].collision = true;

            tile[5] = new Tile();
            tile[5].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/map/Ground/UpGround1.png")));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/map/Ground/UpGround2.png")));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/map/Ground/UnderGround1.png")));
            tile[7].collision = true;

            tile[8] = new Tile();
            tile[8].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/map/Ground/UnderGround2.png")));
            tile[8].collision = true;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
