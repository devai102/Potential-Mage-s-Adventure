package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import core.GamePanel;
import core.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public int hp;

    private int gravity = 3;
    private int jumpPower = 15;
    private boolean Jumping = false;

    public final int screenX, screenY; 

    private BufferedImage[] rightImages = new BufferedImage[5];
    private BufferedImage[] leftImages = new BufferedImage[5];
    private BufferedImage standingImage;
    private BufferedImage[] defenseOnImage = new BufferedImage[5];

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        // Luôn giữ nhân vật ở giữa màn hình (Camera focus)
        this.screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        this.screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultValues();
        getPlayerImage();
    }

    void setDefaultValues(){
        hp = 6;
        solidArea = new Rectangle(8, 12, 32, 32);
        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 10;
        speed = gp.tileSize / 4;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 10;
        speed = 4;
        directionX = "none";
        directionY = "none";
    }

    void getPlayerImage(){
        try {
            for(int i = 0; i < 7; i++) {
                rightImages[i] = ImageIO.read(getClass().getResourceAsStream("/image/right/" + (i+1) + ".png"));
                leftImages[i] = ImageIO.read(getClass().getResourceAsStream("/image/left/" + (i+1) + ".png"));
            }
//            defenseOnImage = ImageIO.read(getClass().getResourceAsStream("/image/defenseOn.png"));
            standingImage = ImageIO.read(getClass().getResourceAsStream("/image/standing.png"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        // 1. XỬ LÝ DI CHUYỂN NGANG (X-AXIS)
        if(keyH.leftPressed) {
            directionX = "left";
        } else if(keyH.rightPressed) {
            directionX = "right";
        } else {
            directionX = "none";
        }

        // Kiểm tra va chạm trục X
        collisionOn = false;
        gp.cChecker.checkTile(this);
        if(!collisionOn) {
            if(directionX.equals("left")) worldX -= speed;
            if(directionX.equals("right")) worldX += speed;
        }

        // 2. XỬ LÝ NHẢY & TRỌNG LỰC (Y-AXIS)
        if(keyH.upPressed && !Jumping && !Falling) {
            Jumping = true;
            currentJumpSpeed = jumpPower;
        }

        if(Jumping) {
            worldY -= currentJumpSpeed;
            currentJumpSpeed -= gravity;
            if(currentJumpSpeed <= 0) {
                Jumping = false;
                Falling = true;
            }
        }

        // 3. XỬ LÝ RƠI (FALLING)
        gp.cChecker.isFalling(this); // Hàm này nên set Falling = true nếu ko có gạch dưới chân
        if(Falling && !Jumping) {
            worldY += gravity * 4; // Tốc độ rơi
        }

        // 4. ANIMATION LOGIC
        if(directionX.equals("none")) {
            spriteNum = 1;
        } else {
            spriteCounter++;
            if(spriteCounter > 5) {
                spriteNum = (spriteNum % 7) + 1; // Tự động xoay vòng từ 1-7
                spriteCounter = 0;
            }
        }
    }

public void draw(Graphics g2) {
    BufferedImage image = null;

    // Ưu tiên hiển thị ảnh di chuyển
    if (directionX.equals("right")) {
        image = rightImages[spriteNum - 1];
    } else if (directionX.equals("left")) {
        image = leftImages[spriteNum - 1];
    }
    // Nếu không di chuyển ngang, kiểm tra đứng yên hoặc thủ
    else {
        if (defenseOn) {
            image = defenseOnImage;
        } else {
            image = standingImage;
        }
    }
}