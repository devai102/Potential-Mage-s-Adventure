package core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    final int originalTile = 16;
    final int scale = 4;
    public final int tileSize = originalTile * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; 
    public final int screenHeight = tileSize * maxScreenRow; 

    public final int maxWorldCol = 82;
    public final int maxWorldRow = 45;

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
    public Entity[] obj = new Entity[20];
    public Entity[] monsters = new Entity[20];
    ArrayList<Entity> entityList= new ArrayList<>();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        gameState = tileScreenState;
        playMusic(1);
        aSetter.setObject();
        aSetter.setMonster();
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
            
            for(int i = 0; i < monsters.length; i++) {
                if(monsters[i] != null) {
                    monsters[i].update();
                }
            }

        }
        if(gameState == pauseState){
        }
        if(gameState == tileScreenState){
            ui.update();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // draw objects
        if (gameState == tileScreenState) {
            ui.draw(g2);
        }
        else {
            tileM.draw(g2);
            entityList.add(player);

            for(int i = 0; i < obj.length; i++) {
                if(obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }

            for(int i = 0; i < monsters.length; i++) {
                if(monsters[i] != null) {
                    entityList.add(monsters[i]);
                }
            }

            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    return Integer.compare(e1.worldY, e2.worldY);
                }
            });

            for(int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }   

            for(int i = 0; i < entityList.size(); i++) {
                entityList.remove(i);
            }

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
