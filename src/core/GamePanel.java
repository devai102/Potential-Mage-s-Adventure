package core;

import javax.swing.*;
import java.awt.*;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTile = 16;
    final int scale = 3;
    public final int tileSize = originalTile * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;  // 768px
    public final int screenHeight = tileSize * maxScreenRow; // 576px

    // WORLD SETTINGS (Nên tăng maxWorldRow để thế giới rộng hơn)
    public final int maxWorldCol = 64;
    public final int maxWorldRow = 50;

    // FPS
    final int FPS = 20;

    // SYSTEM
    public CollisionChecker cChecker = new CollisionChecker(this);
    public KeyHandler keyH = new KeyHandler(); // Đã chuyển sang public để Main hoặc lớp khác truy cập nếu cần
    public TileManager tileM = new TileManager(this);
    public AssetSetter aSetter = new AssetSetter(this); // Giả định bạn dùng class này để đặt object

    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[20]; // Tăng số lượng object lên nếu cần

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    // Hàm này nên được gọi trong Main trước khi khởi động Thread
    public void setupGame() {
        aSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // Sử dụng biến FPS đã khai báo thay vì số 10
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // --- LAYER 1: TILES (NỀN) ---
        tileM.draw(g2);

        // --- LAYER 2: OBJECTS (VẬT PHẨM) ---
        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // --- LAYER 3: ENTITIES (NGƯỜI CHƠI, QUÁI VẬT) ---
        player.draw(g2);

        g2.dispose();
    }
}
