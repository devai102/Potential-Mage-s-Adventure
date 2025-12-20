package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;

public class UI {
    GamePanel gp;
    Font TNR_40 = new Font("Times New Roman", Font.PLAIN, 40);
    boolean messageOn = false;
    String message = "";
    int delta = 20; // Độ phóng to khi hover
    int spriteCounter = 0;
    int spriteNum = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    Graphics2D g2;

    final int charSize = 250;  // Kích thước nhân vật (250x250 pixels)
    final int arrowSize = 64;

    //HÌNH ẢNH
    BufferedImage[] playerImages = new BufferedImage[5];
    BufferedImage[] heartImages = new BufferedImage[3];
    BufferedImage bgImage, btnPlay, btnChar, btnLeader, btnAbout, btnSettings;

    public int commandNumber = 0;

    //MÀM HÌNH CHARACTER
    BufferedImage charBg, arrowLeft, arrowRight, btnBack;
    BufferedImage[] charPortraits = new BufferedImage[3]; // Giả sử có 3 nhân vật
    String[] charNames = {"Annie", "Vi", "Jinx"};
    int charIndex = 0;
    //VÙNG VA CHẠM (HITBOX) CHO CHUỘT
    Rectangle playRect, charRect, leaderRect, aboutRect, settingsRect;
    Rectangle leftArrowRect, rightArrowRect, backRect;
    public UI(GamePanel g2) {
        this.gp = g2;
        this.setImages();
    }

    void setImages() {
        try {
            int i;
            for (i = 0; i < 5; ++i) {
                this.playerImages[i] = ImageIO.read(this.getClass().getResourceAsStream("/res/image/player/left/" + (i + 1) + ".png"));
            }
            for (i = 0; i < 3; ++i) {
                this.heartImages[i] = ImageIO.read(this.getClass().getResourceAsStream("/res/image/object/heart/heart_" + (i + 1) + ".png"));
            }
        } catch (Exception e) {
            System.out.println("Lỗi load Player/Heart: " + e.getMessage());
        }
        try {
            bgImage = ImageIO.read(getClass().getResourceAsStream("/res/image/map/background/HomePage.jpg"));
            btnPlay = ImageIO.read(getClass().getResourceAsStream("/res/image/object/button/play.png"));
            btnChar = ImageIO.read(getClass().getResourceAsStream("/res/image/object/button/character.png"));
            btnLeader = ImageIO.read(getClass().getResourceAsStream("/res/image/object/button/leaderboard.png"));
            btnAbout = ImageIO.read(getClass().getResourceAsStream("/res/image/object/button/about.png"));
            btnSettings = ImageIO.read(getClass().getResourceAsStream("/res/image/object/button/settings.png"));
        } catch (Exception e) {
            System.out.println("Lỗi load ảnh Menu chính: " + e.getMessage());
        }
        // --- LOAD ẢNH CHARACTER SCREEN ---
        try {
            // Ảnh nền
            charBg = ImageIO.read(getClass().getResourceAsStream("/res/image/map/background/CharacterPage.png"));

            // Nút mũi tên và nút Back
            arrowLeft = ImageIO.read(getClass().getResourceAsStream("/res/image/object/button/arrow_left.png"));
            arrowRight = ImageIO.read(getClass().getResourceAsStream("/res/image/object/button/arrow_right.png"));
            btnBack = ImageIO.read(getClass().getResourceAsStream("/res/image/object/button/back.png"));

            // Ảnh chân dung nhân vật (Annie, Vi, Jinx)
            charPortraits[0] = ImageIO.read(getClass().getResourceAsStream("/res/image/player/forCharacterPage/Annie.png"));
            charPortraits[1] = ImageIO.read(getClass().getResourceAsStream("/res/image/player/forCharacterPage/Unknown1.png"));
            charPortraits[2] = ImageIO.read(getClass().getResourceAsStream("/res/image/player/forCharacterPage/Unknown2.png"));
        } catch (Exception e) {
            System.out.println("Lỗi load ảnh Menu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showMessage(String text) {
        this.message = text;
        this.messageOn = true;

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(g2.getFont().deriveFont(0, 30.0F));

        if (gp.gameState == 0) { // Play
            drawPlayerLife();
            drawPlayScreen(g2);
        } else if (gp.gameState == 1) { // Pause
            drawPlayerLife();
            drawPauseScreen(g2);
        } else if (gp.gameState == 2) { // Win
            drawWinScreen(g2);
        } else if (gp.gameState == 3) { // Game Over
            drawGameOverScreen(g2);
        } else if (gp.gameState == 4) { // Title (Menu Chính)
            drawTitleScreen(g2);
        } else if (gp.gameState == 5) { // Leaderboard
            drawLeaderboardScreen(g2);
        } else if (gp.gameState == 6) { // Help
            drawHelpScreen(g2);
        }
        else if (gp.gameState == 7) { // Character
            drawCharacterScreen(g2);
        }
    }

    void update() {
        ++this.spriteCounter;
        if (this.spriteCounter > 15) {
            this.spriteNum = (this.spriteNum + 1) % 5;
            this.spriteCounter = 0;
        }
    }

    void drawTitleScreen(Graphics2D g2) {
        // 1. Vẽ hình nền
        if(bgImage != null) {
            g2.drawImage(bgImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
        } else {
            g2.setColor(new Color(70, 120, 80)); // Màu nền tạm nếu thiếu ảnh
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        }

        // 2. Tính toán vị trí (Layout: 3 dưới, 2 góc trên)
        int mainBtnSize = 80;
        int cornerBtnSize = 80;
        int spacing = 30;
        int bottomY = gp.screenHeight - 150;

        int playX = (gp.screenWidth - mainBtnSize) / 2;
        int charX = playX - spacing - mainBtnSize;
        int leaderX = playX + mainBtnSize + spacing;

        int aboutX = 20;
        int cornerY = 20;
        int settingsX = gp.screenWidth - cornerBtnSize - 20;

        // 3. Khởi tạo vùng va chạm chuột (Hitbox)
        // Cập nhật liên tục để phòng trường hợp resize cửa sổ
        playRect = new Rectangle(playX, bottomY, mainBtnSize, mainBtnSize);
        charRect = new Rectangle(charX, bottomY, mainBtnSize, mainBtnSize);
        leaderRect = new Rectangle(leaderX, bottomY, mainBtnSize, mainBtnSize);
        aboutRect = new Rectangle(aboutX, cornerY, cornerBtnSize, cornerBtnSize);
        settingsRect = new Rectangle(settingsX, cornerY, cornerBtnSize, cornerBtnSize);

        // 4. Xử lý Logic Chuột (Hover & Click)
        // Giả sử cậu đã thêm MouseHandler vào GamePanel như Mimi dặn
        if(gp.mouseH != null) {
            checkMouseInput();
        }

        // 5. Vẽ tiêu đề Game
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String title = "POTENTIAL MAGE";
        int titleX = getXForCenteredText(title);
        int titleY = gp.screenHeight / 3;

        // Vẽ bóng chữ
        g2.setColor(Color.gray);
        g2.drawString(title, titleX + 5, titleY + 5);
        // Vẽ chữ chính
        g2.setColor(Color.white);
        g2.drawString(title, titleX, titleY);

        // 6. Vẽ nhân vật trang trí
        if(playerImages[spriteNum] != null) {
            g2.drawImage(playerImages[spriteNum], gp.screenWidth/2 - 60, titleY + 20, 120, 120, null);
        }

        // 7. Vẽ các Nút
        // Command Mapping: 0:Play, 1:Char, 2:Leader, 3:About, 4:Settings
        drawButtonHelper(g2, btnPlay, playX, bottomY, mainBtnSize, 0);       // Play
        drawButtonHelper(g2, btnChar, charX, bottomY, mainBtnSize, 1);       // Character
        drawButtonHelper(g2, btnLeader, leaderX, bottomY, mainBtnSize, 2);   // Leaderboard
        drawButtonHelper(g2, btnAbout, aboutX, cornerY, cornerBtnSize, 3);   // About/Help
        drawButtonHelper(g2, btnSettings, settingsX, cornerY, cornerBtnSize, 4); // Settings
    }

    void drawCharacterScreen(Graphics2D g2) {
        // 1. Vẽ nền
        if (charBg != null) {
            g2.drawImage(charBg, 0, 0, gp.screenWidth, gp.screenHeight, null);
        } else {
            g2.setColor(Color.DARK_GRAY);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        }

        // 2. Tính toán vị trí trung tâm
        int centerX = gp.screenWidth / 2;
        int centerY = gp.screenHeight / 2;
        int charSize = 200; // Kích thước ảnh nhân vật
        int arrowSize = 64; // Kích thước mũi tên

        // 3. Khởi tạo Hitbox (Cập nhật liên tục để đảm bảo đúng vị trí)
        int leftX = centerX - charSize/2 - 100;
        int rightX = centerX + charSize/2 + 50;
        int arrowY = centerY - arrowSize/2;
        int backX = gp.screenWidth - 80;
        int backY = 20;

        leftArrowRect = new Rectangle(leftX, arrowY, arrowSize, arrowSize);
        rightArrowRect = new Rectangle(rightX, arrowY, arrowSize, arrowSize);
        backRect = new Rectangle(backX, backY, 50, 50);

        // 4. Xử lý Chuột (Click)
        if (gp.mouseH != null) {
            checkCharacterInput();
        }

        // 5. Vẽ Nhân vật đang chọn
        if (charPortraits[charIndex] != null) {
            g2.drawImage(charPortraits[charIndex], centerX - charSize/2, centerY - charSize/2, charSize, charSize, null);
        } else {
            // Vẽ tạm ô vuông nếu thiếu ảnh
            g2.setColor(Color.gray);
            g2.fillRect(centerX - charSize/2, centerY - charSize/2, charSize, charSize);
        }

        // 6. Vẽ Tên nhân vật
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        g2.setColor(Color.ORANGE);
        String name = charNames[charIndex];
        int nameX = getXForCenteredText(name);
        g2.drawString(name, nameX, centerY + charSize/2 + 50);

        // 7. Vẽ Mũi tên (Có hiệu ứng hover đơn giản)
        g2.drawImage(arrowLeft, leftX, arrowY, arrowSize, arrowSize, null);
        g2.drawImage(arrowRight, rightX, arrowY, arrowSize, arrowSize, null);

        // 8. Vẽ nút Back/Exit
        if (btnBack != null) {
            g2.drawImage(btnBack, backX, backY, 50, 50, null);
        } else {
            g2.setColor(Color.red);
            g2.drawString("X", backX + 10, backY + 35);
        }
    }

    // Logic xử lý click trong màn hình Character
    private void checkCharacterInput() {
        int mx = gp.mouseH.x;
        int my = gp.mouseH.y;
        boolean clicked = gp.mouseH.mouseLeftPressed;

        // Click Mũi tên TRÁI
        if (leftArrowRect.contains(mx, my) && clicked) {
            charIndex--;
            if (charIndex < 0) charIndex = charNames.length - 1;
            gp.playSE(2);
            gp.mouseH.mouseLeftPressed = false; // Reset click
        }

        // Click Mũi tên PHẢI
        else if (rightArrowRect.contains(mx, my) && clicked) {
            charIndex++;
            if (charIndex >= charNames.length) charIndex = 0;
            gp.playSE(2);
            gp.mouseH.mouseLeftPressed = false;
        }

        // Click nút BACK (Về Menu)
        else if (backRect.contains(mx, my) && clicked) {
            gp.gameState = 4;
            gp.playSE(2);
            gp.mouseH.mouseLeftPressed = false;
        }
    }

    // Hàm phụ để vẽ nút với hiệu ứng phóng to
    private void drawButtonHelper(Graphics2D g2, BufferedImage img, int x, int y, int size, int btnID) {
        if(img == null) return; // Không vẽ nếu chưa load được ảnh

        if (commandNumber == btnID) {
            // Hiệu ứng HOVER: Vẽ ảnh to hơn (size + delta) và dịch sang trái/lên một chút
            g2.drawImage(img, x - delta/2, y - delta/2, size + delta, size + delta, null);

            // Vẽ thêm viền trắng mỏng để người dùng biết đang chọn
            g2.setColor(Color.white);
            g2.drawRoundRect(x - delta/2, y - delta/2, size + delta, size + delta, 10, 10);
        } else {
            // Vẽ bình thường
            g2.drawImage(img, x, y, size, size, null);
        }
    }

    // Hàm xử lý logic chuột trong Menu
    private void checkMouseInput() {
        int mx = gp.mouseH.x;
        int my = gp.mouseH.y;
        boolean clicked = gp.mouseH.mouseLeftPressed;

        if (playRect.contains(mx, my)) {
            commandNumber = 0;
            if (clicked) {
                gp.gameState = 0; // Vào game
                gp.stopMusic();
                gp.playMusic(0);

                gp.mouseH.mouseLeftPressed = false;
            }
        }
        else if (charRect.contains(mx, my)) {
            commandNumber = 1;
            if (clicked) {
                gp.gameState = 7;
                gp.mouseH.mouseLeftPressed = false;
            }
        }
        else if (leaderRect.contains(mx, my)) {
            commandNumber = 2;
            if (clicked) {
                gp.gameState = 5; // Leaderboard State cũ là 5
                gp.mouseH.mouseLeftPressed = false;
            }
        }
        else if (aboutRect.contains(mx, my)) {
            commandNumber = 3;
            if (clicked) {
                gp.gameState = 6; // Help State cũ là 6
                gp.mouseH.mouseLeftPressed = false;
            }
        }
        else if (settingsRect.contains(mx, my)) {
            commandNumber = 4;
            if (clicked) {
                System.out.println("Vào màn hình cài đặt!");
                gp.mouseH.mouseLeftPressed = false;
            }
        }
    }

    private void drawPauseScreen(Graphics2D g2) {
        g2.setFont(g2.getFont().deriveFont(1, 75.0F));
        String text = "PAUSED";
        int x = this.getXForCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }

    void drawGameOverScreen(Graphics2D g2) {
        String text = "GAME OVER";
        int x = this.getXForCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        this.gp.gameThread = null;
    }

    void drawWinScreen(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(1, 60.0F));
        String text = "YOU WIN!";
        int x = this.getXForCenteredText(text);
        int y = 64 * 4;
        g2.drawString(text, x, y);
        g2.setFont(g2.getFont().deriveFont(0, 32.0F));
        String timeText = "Time: " + this.dFormat.format(this.gp.playTime) + "s";
        g2.drawString(timeText, this.getXForCenteredText(timeText), y + 50);

        // Phần nhập tên (Giữ nguyên logic cũ)
        String namePrompt = "Enter your name:";
        g2.drawString(namePrompt, 64 * 3, 64 * 7);
        int rectX = 64 * 3;
        int rectY = 64 * 7 + 20;
        int rectW = gp.screenWidth - 64 * 6;
        g2.setColor(Color.white);
        g2.fillRoundRect(rectX, rectY, rectW, 50, 10, 10);
        g2.setColor(Color.black);
        g2.drawRoundRect(rectX, rectY, rectW, 50, 10, 10);
        if(gp.winSession != null) {
            g2.drawString(this.gp.winSession.getName(), rectX + 10, rectY + 35);
        }
        g2.setFont(g2.getFont().deriveFont(0, 24.0F));
        String guide = "Press Enter to save, ESC to cancel";
        g2.setColor(Color.white);
        g2.drawString(guide, this.getXForCenteredText(guide), rectY + 90);
    }

    void drawPlayScreen(Graphics2D g2) {
        g2.setColor(Color.white);
        // Hiển thị thời gian chơi
        String text = "Time: " + this.dFormat.format(this.gp.playTime);
        int x = gp.screenWidth - 250;
        int y = 50;
        g2.drawString(text, x, y);

        if(this.messageOn) {
            g2.setFont(g2.getFont().deriveFont(1, 20));
            g2.setColor(Color.black);
            g2.drawString(this.message, gp.player.screenX , gp.player.screenY - 10);
            this.spriteCounter++;
            if(this.spriteCounter > 120) {
                this.spriteCounter = 0;
                this.messageOn = false;
            }
        }
    }

    int getXForCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }

    void drawLeaderboardScreen(Graphics2D g2) {
        // Giữ nguyên logic vẽ bảng xếp hạng
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setColor(Color.white);
        g2.fillRoundRect(64, 64, gp.screenWidth - 128, gp.screenHeight - 128, 25, 25);
        g2.setColor(Color.black);
        g2.fillRoundRect(69, 69, gp.screenWidth - 138, gp.screenHeight - 138, 25, 25);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(1, 50.0F));
        String title = "LEADERBOARD";
        g2.drawString(title, getXForCenteredText(title), 124);

        g2.setFont(g2.getFont().deriveFont(0, 28.0F));
        g2.drawString("Rank", 192, 256);
        g2.drawString("Name", 384, 256);
        g2.drawString("Time (s)", 768, 256);

        if(gp.leaderboard != null) {
            List<LeaderboardEntry> list = this.gp.leaderboard.top(5);
            int startY = 320;
            for(int i = 0; i < list.size(); ++i) {
                LeaderboardEntry e = list.get(i);
                int y = startY + i * 36;
                g2.drawString(String.valueOf(i + 1), 192, y);
                g2.drawString(e.getName(), 384, y);
                g2.drawString(this.dFormat.format(e.getTimeSeconds()), 768, y);
            }
        }
    }

    void drawHelpScreen(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setColor(Color.white);
        g2.fillRoundRect(64, 64, gp.screenWidth - 128, gp.screenHeight - 128, 25, 25);
        g2.setColor(Color.black);
        g2.fillRoundRect(69, 69, gp.screenWidth - 138, gp.screenHeight - 138, 25, 25);

        g2.setFont(g2.getFont().deriveFont(1, 50.0F));
        String title = "INSTRUCTIONS";
        g2.setColor(Color.white);
        g2.drawString(title, getXForCenteredText(title), 124);

        g2.setFont(g2.getFont().deriveFont(0, 30.0F));
        g2.drawString("Have a good moment!", 192, 256);
        // Cậu có thể thêm hướng dẫn chi tiết ở đây
    }

    void drawPlayerLife() {
        int x = 64 / 2;
        int y = 64 / 2;
        int i = 0;
        // Vẽ tim trống (Max HP)
        while(i < gp.player.getMaxHp() / 2) {
            g2.drawImage(heartImages[0], x, y, 64, 64, null);
            i++;
            x += 64;
        }
        // Vẽ tim đỏ (Current HP)
        x = 64 / 2;
        i = 0;
        while(i < gp.player.getHp()) {
            g2.drawImage(heartImages[1], x, y, 64, 64, null);
            i++;
            if(i < gp.player.getHp()) {
                g2.drawImage(heartImages[2], x, y, 64, 64, null);
            }
            i++;
            x += 64;
        }
    }
}