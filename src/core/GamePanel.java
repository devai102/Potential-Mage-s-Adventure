package core;

import javax.swing.*;
import java.awt.*;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    final int originalTile = 16;
    final int scale = 4;
    public final int tileSize = originalTile * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; 
    public final int screenHeight = tileSize * maxScreenRow; 

    public final int maxWorldCol = 74;
    public final int maxWorldRow = 50;

    int screenHeight2 = screenHeight;
    int screenWidth2 = screenWidth;

    private final int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);

    Sound musicSound = new Sound();
    Sound se = new Sound();

    UI ui = new UI(this);
    public LeaderboardStorage leaderboard = new LeaderboardManager();
    public WinSession winSession = new WinSession();
    public double playTime = 0;

    public int gameState;
    public final int playState = 0;
    public final int pauseState = 1;
    public final int winState = 2;
    public final int gameOverState = 3;
    public final int tileScreenState = 4;
    public final int leaderboardState = 5;
    public final int helpState = 6;

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    Thread gameThread;

    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[20];
    public Entity[] monsters = new Entity[20];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setMonster();
        gameState = 4;
        playMusic(1);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
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
            playTime += (double)1/60;
            player.update();
            checkWinCondition();
        }
        if(gameState == pauseState){
        }
        if(gameState == tileScreenState){
            ui.update();
        }
    }

    private void checkWinCondition(){
        int targetX = tileSize * 63;
        int targetY = tileSize * 8;
        java.awt.Rectangle targetTile = new java.awt.Rectangle(targetX, targetY, tileSize, tileSize);
        java.awt.Rectangle playerRect = new java.awt.Rectangle(
            player.worldX + player.solidArea.x,
            player.worldY + player.solidArea.y,
            player.solidArea.width,
            player.solidArea.height
        );
        if(playerRect.intersects(targetTile)){
            if(gameState != winState){
                gameState = winState;
                winSession.start(playTime);
            }
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

        tileM.draw(g2);

        player.draw(g2);

        ui.draw(g2);
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
