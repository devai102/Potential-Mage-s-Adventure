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
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadTileImage();
        loadMap("/res/map/map01.txt");
    }

//    void loadMap(String filePath){
//        InputStream is = getClass().getResourceAsStream(filePath);
//        BufferedReader br = new BufferedReader(new InputStreamReader(is));
//        try{
//            int col = 0;
//            int row = 0;
//            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
//                String line = br.readLine();
//                while(col < gp.maxWorldCol){
//                    String[] numbers = line.split(" ");
//                    int num = Integer.parseInt(numbers[col]);
//                    mapTileNum[col][row] = num;
//                    col++;
//                }
//                if(col == gp.maxWorldCol){
//                    col = 0;
//                    row++;
//                }
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
public void loadMap(String filePath) {
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
        System.out.println("Lỗi tại dòng: ");
        e.printStackTrace();
    }
}

    void loadTileImage(){
        try{
            //NEN TRONG
            tile[0] = new Tile();
            tile[0].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/Nen3.jpg")));
            tile[0].collision = false;
            //DAT1
            tile[1] = new Tile();
            tile[1].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/Dat1.jpg")));
            tile[1].collision = true;
            //DAT2
            tile[2] = new Tile();
            tile[2].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/Tuong1.png")));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = javax.imageio.ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/image/Tuong2.png")));
            tile[3].collision = true;
//
//            tile[1] = new Tile();
//            tile[1].image = javax.imageio.ImageIO.read(getClass().getResourceAsStream("/res/image/Dat1.jpg"));
//            tile[1].collision = true;
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
