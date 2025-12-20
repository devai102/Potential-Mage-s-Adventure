
package core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;

public class UI {
   GamePanel gp;
   boolean messageOn = false;
   String message = "";
   int delta = 20;
   int spriteCounter = 0;
   int spriteNum = 0;
   DecimalFormat dFormat = new DecimalFormat("#0.00");
   Graphics2D g2;
   BufferedImage[] playerImages = new BufferedImage[5];
   BufferedImage[] heartImages = new BufferedImage[3];
   BufferedImage[] buttonImages = new BufferedImage[4];
   public int commandNumber = 0;

   public UI(GamePanel g2) {
      this.gp = g2;
      this.setImages();
   }

   void setImages() {
      try {
         int g2;
         for(g2 = 0; g2 < 5; ++g2) {
            this.playerImages[g2] = ImageIO.read(this.getClass().getResourceAsStream("/res/image/player/left/" + (g2 + 1) + ".png"));
         }

         for(g2 = 0; g2 < 3; ++g2) {
            this.heartImages[g2] = ImageIO.read(this.getClass().getResourceAsStream("/res/image/object/heart/heart_" + (g2 + 1) + ".png"));
         }

         for(g2 = 0; g2 < 4; ++g2) {
            this.buttonImages[g2] = ImageIO.read(this.getClass().getResourceAsStream("/res/image/object/button/button" + (g2 + 1) + ".png"));
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public void showMessage(String g2) {
      this.message = g2;
      this.messageOn = true;
   }

   public void draw(Graphics2D g2){
      this.g2 = g2;
      g2.setFont(g2.getFont().deriveFont(0, 30.0F));
      int g20000 = this.gp.gameState;
      Objects.requireNonNull(this.gp);
      if (g20000 == 0) {
         this.drawPlayerLife();
         this.drawPlayScreen(g2);
      } else {
         g20000 = this.gp.gameState;
         Objects.requireNonNull(this.gp);
         if (g20000 == 1) {
            this.drawPlayerLife();
            this.drawPauseScreen(g2);
         } else {
            g20000 = this.gp.gameState;
            Objects.requireNonNull(this.gp);
            if (g20000 == 3) {
               this.drawGameOverScreen(g2);
            } else {
               g20000 = this.gp.gameState;
               Objects.requireNonNull(this.gp);
               if (g20000 == 2) {
                  this.drawWinScreen(g2);
               } else {
                  g20000 = this.gp.gameState;
                  Objects.requireNonNull(this.gp);
                  if (g20000 == 4) {
                     this.drawTitleScreen(g2);
                  } else {
                     g20000 = this.gp.gameState;
                     Objects.requireNonNull(this.gp);
                     if (g20000 == 5) {
                        this.drawLeaderboardScreen(g2);
                     } else {
                        g20000 = this.gp.gameState;
                        Objects.requireNonNull(this.gp);
                        if (g20000 == 6) {
                           this.drawHelpScreen(g2);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   void update() {
      ++this.spriteCounter;
      if (this.spriteCounter > 15) {
         switch (this.spriteNum) {
            case 0:
               this.spriteNum = 1;
               break;
            case 1:
               this.spriteNum = 2;
               break;
            case 2:
               this.spriteNum = 3;
               break;
            case 3:
               this.spriteNum = 4;
               break;
            case 4:
               this.spriteNum = 0;
         }
         this.spriteCounter = 0;
      }

   }

   private void drawPauseScreen(Graphics2D g2) {
      g2.setFont(g2.getFont().deriveFont(1, 75.0F));
      String text = "PAUSED";
      int x = this.getXForCenteredText(text);
      Objects.requireNonNull(this.gp);
      int y = gp.screenHeight / 2;

      g2.setColor(Color.white);
      g2.drawString(text, x, y);
      g2.setColor(Color.gray);
      g2.drawString(text, x + 5, y + 5);
   }

   void drawGameOverScreen(Graphics2D g2) {
      String var2 = "GAME OVER";
      int var3 = this.getXForCenteredText(var2);
      Objects.requireNonNull(this.gp);
      int var4 = 768 / 2;
      g2.setColor(new Color(0, 0, 0, 150));
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      g2.fillRect(0, 0, 1280, 768);
      g2.setColor(Color.white);
      g2.drawString(var2, var3, var4);
      this.gp.gameThread = null;
   }

   void drawWinScreen(Graphics2D g2) {
      g2.setColor(new Color(0, 0, 0, 200));
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      g2.fillRect(0, 0, 1280, 768);
      g2.setColor(Color.white);
      g2.setFont(g2.getFont().deriveFont(1, 60.0F));
      String var2 = "YOU WIN!";
      int var3 = this.getXForCenteredText(var2);
      Objects.requireNonNull(this.gp);
      int var4 = 64 * 4;
      g2.drawString(var2, var3, var4);
      g2.setFont(g2.getFont().deriveFont(0, 32.0F));
      String var5 = "Time: " + this.dFormat.format(this.gp.playTime) + "s";
      g2.drawString(var5, this.getXForCenteredText(var5), var4 + 50);
      String var6 = "Enter your name:";
      Objects.requireNonNull(this.gp);
      int g20002 = 64 * 3;
      Objects.requireNonNull(this.gp);
      g2.drawString(var6, g20002, 64 * 7);
      Objects.requireNonNull(this.gp);
      int var7 = 64 * 3;
      Objects.requireNonNull(this.gp);
      int var8 = 64 * 7 + 20;
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      int var9 = 1280 - 64 * 6;
      byte g20 = 50;
      g2.setColor(Color.white);
      g2.fillRoundRect(var7, var8, var9, g20, 10, 10);
      g2.setColor(Color.black);
      g2.drawRoundRect(var7, var8, var9, g20, 10, 10);
      g2.drawString(this.gp.winSession.getName(), var7 + 10, var8 + 35);
      g2.setFont(g2.getFont().deriveFont(0, 24.0F));
      String g21 = "Press Enter to save, ESC to cancel";
      g2.setColor(Color.white);
      g2.drawString(g21, this.getXForCenteredText(g21), var8 + 90);
   }

   void drawPlayScreen(Graphics2D g2) {
      g2.setColor(Color.white);
      String text = "Time: " + this.dFormat.format(this.gp.playTime);
      Objects.requireNonNull(this.gp);
      int x = 64 * 17 - 50;
      int y = 64;
      Objects.requireNonNull(this.gp);
      g2.drawString(text, x, y);
   }

   int getXForCenteredText(String g2) {
      int var2 = (int)this.g2.getFontMetrics().getStringBounds(g2, this.g2).getWidth();
      Objects.requireNonNull(this.gp);
      int var3 = 1280 / 2 - var2 / 2;
      return var3;
   }

   void drawTitleScreen(Graphics2D g2) {
      g2.setColor(Color.white);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      g2.fillRect(0, 0, 1280, 768);
      g2.setFont(g2.getFont().deriveFont(1, 75.0F));
      String var2 = "POTENTIAL MAGE'S ADVENTURE";
      int var3 = this.getXForCenteredText(var2);
      Objects.requireNonNull(this.gp);
      int var4 = 64 * 3;
      g2.setColor(Color.gray);
      g2.drawString(var2, var3 + 3, var4 + 3);
      g2.setColor(Color.black);
      g2.drawString(var2, var3, var4);
      Objects.requireNonNull(this.gp);
      int var5 = 64 * 3;
      Objects.requireNonNull(this.gp);
      int var6 = 64 * 5;
      Objects.requireNonNull(this.gp);
      int var7 = 64 * 2;
      if (this.commandNumber == 0) {
         var7 += this.delta;
      }

      g2.drawImage(this.buttonImages[0], var5, var6, var7, var7, (ImageObserver)null);
      Objects.requireNonNull(this.gp);
      int var8 = 64 * 15;
      Objects.requireNonNull(this.gp);
      int var9 = 64 * 5;
      Objects.requireNonNull(this.gp);
      int g20 = 64 * 2;
      if (this.commandNumber == 3) {
         g20 += this.delta;
      }

      g2.drawImage(this.buttonImages[1], var8, var9, g20, g20, (ImageObserver)null);
      Objects.requireNonNull(this.gp);
      int g21 = 64 * 7;
      Objects.requireNonNull(this.gp);
      int g22 = 64 * 5;
      Objects.requireNonNull(this.gp);
      int g23 = 64 * 2;
      if (this.commandNumber == 1) {
         g23 += this.delta;
      }

      g2.drawImage(this.buttonImages[2], g21, g22, g23, g23, (ImageObserver)null);
      Objects.requireNonNull(this.gp);
      int g24 = 64 * 11;
      Objects.requireNonNull(this.gp);
      int g25 = 64 * 5;
      Objects.requireNonNull(this.gp);
      int g26 = 64 * 2;
      if (this.commandNumber == 2) {
         g26 += this.delta;
      }

      g2.drawImage(this.buttonImages[3], g24, g25, g26, g26, (ImageObserver)null);
      BufferedImage g20001 = this.playerImages[this.spriteNum];
      Objects.requireNonNull(this.gp);
      int g20002 = 64 * 8;
      Objects.requireNonNull(this.gp);
      int g20003 = 64 * 7;
      Objects.requireNonNull(this.gp);
      int g20004 = 64 * 4;
      Objects.requireNonNull(this.gp);
      g2.drawImage(g20001, g20002, g20003, g20004, 64 * 4, (ImageObserver)null);
   }

   void drawLeaderboardScreen(Graphics2D g2) {
      g2.setColor(Color.black);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      g2.fillRect(0, 0, 1280, 768);
      g2.setColor(Color.white);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      int g20003 = 1280 - 64 * 2;
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      g2.fillRoundRect(64, 64, g20003, 768 - 64 * 2, 25, 25);
      g2.setColor(Color.black);
      Objects.requireNonNull(this.gp);
      int g20001 = 64 + 5;
      Objects.requireNonNull(this.gp);
      int g20002 = 64 + 5;
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      g20003 = 1280 - 64 * 2 - 10;
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      g2.fillRoundRect(g20001, g20002, g20003, 768 - 64 * 2 - 10, 25, 25);
      g2.setFont(g2.getFont().deriveFont(1, 50.0F));
      String var2 = "LEADERBOARD";
      int var3 = this.getXForCenteredText(var2);
      Objects.requireNonNull(this.gp);
      int var4 = 64 + 60;
      g2.setColor(Color.white);
      g2.drawString(var2, var3, var4);
      g2.setFont(g2.getFont().deriveFont(0, 28.0F));
      Objects.requireNonNull(this.gp);
      g20002 = 64 * 3;
      Objects.requireNonNull(this.gp);
      g2.drawString("Rank", g20002, 64 * 4);
      Objects.requireNonNull(this.gp);
      g20002 = 64 * 6;
      Objects.requireNonNull(this.gp);
      g2.drawString("Name", g20002, 64 * 4);
      Objects.requireNonNull(this.gp);
      g20002 = 64 * 12;
      Objects.requireNonNull(this.gp);
      g2.drawString("Time (s)", g20002, 64 * 4);
      List<LeaderboardEntry> var5 = this.gp.leaderboard.top(5);
      Objects.requireNonNull(this.gp);
      int var6 = 64 * 5;
      byte var7 = 36;

      for(int var8 = 0; var8 < var5.size(); ++var8) {
         LeaderboardEntry var9 = (LeaderboardEntry)var5.get(var8);
         int g20 = var6 + var8 * var7;
         String g21 = String.valueOf(var8 + 1);
         Objects.requireNonNull(this.gp);
         g2.drawString(g21, 64 * 3, g20);
         g21 = var9.getName();
         Objects.requireNonNull(this.gp);
         g2.drawString(g21, 64 * 6, g20);
         g21 = this.dFormat.format(var9.getTimeSeconds());
         Objects.requireNonNull(this.gp);
         g2.drawString(g21, 64 * 12, g20);
      }
   }

   void drawHelpScreen(Graphics2D g2) {
      g2.setColor(Color.black);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      g2.fillRect(0, 0, 1280, 768);
      g2.setColor(Color.white);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      int g20003 = 1280 - 64 * 2;
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      g2.fillRoundRect(64, 64, g20003, 768 - 64 * 2, 25, 25);
      g2.setColor(Color.black);
      Objects.requireNonNull(this.gp);
      int g20001 = 64 + 5;
      Objects.requireNonNull(this.gp);
      int g20002 = 64 + 5;
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      g20003 = 1280 - 64 * 2 - 10;
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      g2.fillRoundRect(g20001, g20002, g20003, 768 - 64 * 2 - 10, 25, 25);
      g2.setFont(g2.getFont().deriveFont(1, 50.0F));
      String var2 = "INSTRUCTIONS";
      int var3 = this.getXForCenteredText(var2);
      Objects.requireNonNull(this.gp);
      int var4 = 64 + 60;
      g2.setColor(Color.white);
      g2.drawString(var2, var3, var4);
      g2.setFont(g2.getFont().deriveFont(0, 30.0F));
      Objects.requireNonNull(this.gp);
      g20002 = 64 * 3;
      Objects.requireNonNull(this.gp);
      g2.drawString("Have a good moment!", g20002, 64 * 4);
   }

   void drawPlayerLife() {
      Objects.requireNonNull(this.gp);
      int g2 = 64 / 2;
      Objects.requireNonNull(this.gp);
      int var2 = 64 / 2;

      Graphics2D g20000;
      BufferedImage g20001;
      int var3;
      for(var3 = 0; var3 < this.gp.player.getMaxHp() / 2; g2 += 64) {
         g20000 = this.g2;
         g20001 = this.heartImages[0];
         Objects.requireNonNull(this.gp);
         Objects.requireNonNull(this.gp);
         g20000.drawImage(g20001, g2, var2, 64, 64, (ImageObserver)null);
         ++var3;
         Objects.requireNonNull(this.gp);
      }

      Objects.requireNonNull(this.gp);
      g2 = 64 / 2;
      Objects.requireNonNull(this.gp);
      var2 = 64 / 2;

      for(var3 = 0; var3 < this.gp.player.getHp(); g2 += 64) {
         g20000 = this.g2;
         g20001 = this.heartImages[1];
         Objects.requireNonNull(this.gp);
         Objects.requireNonNull(this.gp);
         g20000.drawImage(g20001, g2, var2, 64, 64, (ImageObserver)null);
         ++var3;
         if (var3 < this.gp.player.getHp()) {
            g20000 = this.g2;
            g20001 = this.heartImages[2];
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            g20000.drawImage(g20001, g2, var2, 64, 64, (ImageObserver)null);
         }

         ++var3;
         Objects.requireNonNull(this.gp);
      }
   }
}