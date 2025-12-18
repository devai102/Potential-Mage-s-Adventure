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

    //Systems
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);

    Sound musicSound = new Sound();
    Sound se = new Sound();

    UI ui = new UI(this);

    //Game state
    public int gameState;
    public final int playState = 0;
    public final int pauseState = 1;
    public final int winState = 2;
    public final int gameOverState = 3;
    public final int tileScreenState = 4;

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

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

    public void setupGame(){
        aSetter.setObject();
        gameState = 4;
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

    public void update(){
        if(gameState == playState){
            player.update();
        }
        if(gameState == pauseState){

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // draw objects
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        if(gameState == tileScreenState){
            
        }else{
            // draw tile
            tileM.draw(g2);

            // draw player
            player.draw(g2);

            //draw UI
            ui.draw(g2);
        }
        g2.dispose();
    }

    public void playMusic(int i){
        musicSound.setFile(i);
        musicSound.play();
        musicSound.loop();
    }

    public void stopMusic(){
        musicSound.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}
