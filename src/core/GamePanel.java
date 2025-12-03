package core;

import javax.swing.*;
import java.awt.*;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    
    final int originalTile = 16;
    final int scale = 3;
    public final int tileSize = originalTile * scale; 
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //World settings
    public final int maxWorldCol = 64;
    public final int maxWorldRow = 12;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    final int FPS = 60;

    public CollisionChecker cChecker = new CollisionChecker(this);
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        double drawInterval = 1000000000 / 10;   
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        } 
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        //draw objects
        // for(int i = 0; i < obj.length; i++){
        //     if(obj[i] != null){
        //         obj[i].draw(g2, this);
        //     }
        // }

        // draw tile
        tileM.draw(g2);
        
        // draw player
        player.draw(g2);

        g2.dispose();
    }
}
