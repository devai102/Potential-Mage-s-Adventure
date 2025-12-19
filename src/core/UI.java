// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
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

   public UI(GamePanel var1) {
      this.gp = var1;
      this.setImages();
   }

   void setImages() {
      try {
         int var1;
         for(var1 = 0; var1 < 5; ++var1) {
            this.playerImages[var1] = ImageIO.read(this.getClass().getResourceAsStream("/res/image/player/left/" + (var1 + 1) + ".png"));
         }

         for(var1 = 0; var1 < 3; ++var1) {
            this.heartImages[var1] = ImageIO.read(this.getClass().getResourceAsStream("/res/image/object/heart/heart_" + (var1 + 1) + ".png"));
         }

         for(var1 = 0; var1 < 4; ++var1) {
            this.buttonImages[var1] = ImageIO.read(this.getClass().getResourceAsStream("/res/image/object/button/button" + (var1 + 1) + ".png"));
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public void showMessage(String var1) {
      this.message = var1;
      this.messageOn = true;
   }

   public void draw(Graphics2D var1) {
      this.g2 = var1;
      var1.setFont(var1.getFont().deriveFont(0, 30.0F));
      int var10000 = this.gp.gameState;
      Objects.requireNonNull(this.gp);
      if (var10000 == 0) {
         this.drawPlayerLife();
         this.drawPlayScreen(var1);
      } else {
         var10000 = this.gp.gameState;
         Objects.requireNonNull(this.gp);
         if (var10000 == 1) {
            this.drawPlayerLife();
            this.drawPauseScreen(var1);
         } else {
            var10000 = this.gp.gameState;
            Objects.requireNonNull(this.gp);
            if (var10000 == 3) {
               this.drawGameOverScreen(var1);
            } else {
               var10000 = this.gp.gameState;
               Objects.requireNonNull(this.gp);
               if (var10000 == 2) {
                  this.drawWinScreen(var1);
               } else {
                  var10000 = this.gp.gameState;
                  Objects.requireNonNull(this.gp);
                  if (var10000 == 4) {
                     this.drawTitleScreen(var1);
                  } else {
                     var10000 = this.gp.gameState;
                     Objects.requireNonNull(this.gp);
                     if (var10000 == 5) {
                        this.drawLeaderboardScreen(var1);
                     } else {
                        var10000 = this.gp.gameState;
                        Objects.requireNonNull(this.gp);
                        if (var10000 == 6) {
                           this.drawHelpScreen(var1);
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

   void drawPauseScreen(Graphics2D var1) {
      var1.setFont(var1.getFont().deriveFont(1, 75.0F));
      String var2 = "PAUSED";
      int var3 = this.getXForCenteredText(var2);
      Objects.requireNonNull(this.gp);
      int var4 = 768 / 2;
      var1.setColor(Color.white);
      var1.drawString(var2, var3, var4);
      var1.setColor(Color.gray);
      var1.drawString(var2, var3 + 5, var4 + 5);
   }

   void drawGameOverScreen(Graphics2D var1) {
      String var2 = "GAME OVER";
      int var3 = this.getXForCenteredText(var2);
      Objects.requireNonNull(this.gp);
      int var4 = 768 / 2;
      var1.setColor(new Color(0, 0, 0, 150));
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      var1.fillRect(0, 0, 1280, 768);
      var1.setColor(Color.white);
      var1.drawString(var2, var3, var4);
      this.gp.gameThread = null;
   }

   void drawWinScreen(Graphics2D var1) {
      var1.setColor(new Color(0, 0, 0, 200));
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      var1.fillRect(0, 0, 1280, 768);
      var1.setColor(Color.white);
      var1.setFont(var1.getFont().deriveFont(1, 60.0F));
      String var2 = "YOU WIN!";
      int var3 = this.getXForCenteredText(var2);
      Objects.requireNonNull(this.gp);
      int var4 = 64 * 4;
      var1.drawString(var2, var3, var4);
      var1.setFont(var1.getFont().deriveFont(0, 32.0F));
      String var5 = "Time: " + this.dFormat.format(this.gp.playTime) + "s";
      var1.drawString(var5, this.getXForCenteredText(var5), var4 + 50);
      String var6 = "Enter your name:";
      Objects.requireNonNull(this.gp);
      int var10002 = 64 * 3;
      Objects.requireNonNull(this.gp);
      var1.drawString(var6, var10002, 64 * 7);
      Objects.requireNonNull(this.gp);
      int var7 = 64 * 3;
      Objects.requireNonNull(this.gp);
      int var8 = 64 * 7 + 20;
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      int var9 = 1280 - 64 * 6;
      byte var10 = 50;
      var1.setColor(Color.white);
      var1.fillRoundRect(var7, var8, var9, var10, 10, 10);
      var1.setColor(Color.black);
      var1.drawRoundRect(var7, var8, var9, var10, 10, 10);
      var1.drawString(this.gp.winSession.getName(), var7 + 10, var8 + 35);
      var1.setFont(var1.getFont().deriveFont(0, 24.0F));
      String var11 = "Press Enter to save, ESC to cancel";
      var1.setColor(Color.white);
      var1.drawString(var11, this.getXForCenteredText(var11), var8 + 90);
   }

   void drawPlayScreen(Graphics2D var1) {
      var1.setColor(Color.white);
      String var10001 = "Time: " + this.dFormat.format(this.gp.playTime);
      Objects.requireNonNull(this.gp);
      int var10002 = 64 * 17 - 50;
      Objects.requireNonNull(this.gp);
      var1.drawString(var10001, var10002, 64);
   }

   int getXForCenteredText(String var1) {
      int var2 = (int)this.g2.getFontMetrics().getStringBounds(var1, this.g2).getWidth();
      Objects.requireNonNull(this.gp);
      int var3 = 1280 / 2 - var2 / 2;
      return var3;
   }

   void drawTitleScreen(Graphics2D var1) {
      var1.setColor(Color.white);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      var1.fillRect(0, 0, 1280, 768);
      var1.setFont(var1.getFont().deriveFont(1, 75.0F));
      String var2 = "POTENTIAL MAGE'S ADVENTURE";
      int var3 = this.getXForCenteredText(var2);
      Objects.requireNonNull(this.gp);
      int var4 = 64 * 3;
      var1.setColor(Color.gray);
      var1.drawString(var2, var3 + 3, var4 + 3);
      var1.setColor(Color.black);
      var1.drawString(var2, var3, var4);
      Objects.requireNonNull(this.gp);
      int var5 = 64 * 3;
      Objects.requireNonNull(this.gp);
      int var6 = 64 * 5;
      Objects.requireNonNull(this.gp);
      int var7 = 64 * 2;
      if (this.commandNumber == 0) {
         var7 += this.delta;
      }

      var1.drawImage(this.buttonImages[0], var5, var6, var7, var7, (ImageObserver)null);
      Objects.requireNonNull(this.gp);
      int var8 = 64 * 15;
      Objects.requireNonNull(this.gp);
      int var9 = 64 * 5;
      Objects.requireNonNull(this.gp);
      int var10 = 64 * 2;
      if (this.commandNumber == 3) {
         var10 += this.delta;
      }

      var1.drawImage(this.buttonImages[1], var8, var9, var10, var10, (ImageObserver)null);
      Objects.requireNonNull(this.gp);
      int var11 = 64 * 7;
      Objects.requireNonNull(this.gp);
      int var12 = 64 * 5;
      Objects.requireNonNull(this.gp);
      int var13 = 64 * 2;
      if (this.commandNumber == 1) {
         var13 += this.delta;
      }

      var1.drawImage(this.buttonImages[2], var11, var12, var13, var13, (ImageObserver)null);
      Objects.requireNonNull(this.gp);
      int var14 = 64 * 11;
      Objects.requireNonNull(this.gp);
      int var15 = 64 * 5;
      Objects.requireNonNull(this.gp);
      int var16 = 64 * 2;
      if (this.commandNumber == 2) {
         var16 += this.delta;
      }

      var1.drawImage(this.buttonImages[3], var14, var15, var16, var16, (ImageObserver)null);
      BufferedImage var10001 = this.playerImages[this.spriteNum];
      Objects.requireNonNull(this.gp);
      int var10002 = 64 * 8;
      Objects.requireNonNull(this.gp);
      int var10003 = 64 * 7;
      Objects.requireNonNull(this.gp);
      int var10004 = 64 * 4;
      Objects.requireNonNull(this.gp);
      var1.drawImage(var10001, var10002, var10003, var10004, 64 * 4, (ImageObserver)null);
   }

   void drawLeaderboardScreen(Graphics2D var1) {
      var1.setColor(Color.black);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      var1.fillRect(0, 0, 1280, 768);
      var1.setColor(Color.white);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      int var10003 = 1280 - 64 * 2;
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      var1.fillRoundRect(64, 64, var10003, 768 - 64 * 2, 25, 25);
      var1.setColor(Color.black);
      Objects.requireNonNull(this.gp);
      int var10001 = 64 + 5;
      Objects.requireNonNull(this.gp);
      int var10002 = 64 + 5;
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      var10003 = 1280 - 64 * 2 - 10;
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      var1.fillRoundRect(var10001, var10002, var10003, 768 - 64 * 2 - 10, 25, 25);
      var1.setFont(var1.getFont().deriveFont(1, 50.0F));
      String var2 = "LEADERBOARD";
      int var3 = this.getXForCenteredText(var2);
      Objects.requireNonNull(this.gp);
      int var4 = 64 + 60;
      var1.setColor(Color.white);
      var1.drawString(var2, var3, var4);
      var1.setFont(var1.getFont().deriveFont(0, 28.0F));
      Objects.requireNonNull(this.gp);
      var10002 = 64 * 3;
      Objects.requireNonNull(this.gp);
      var1.drawString("Rank", var10002, 64 * 4);
      Objects.requireNonNull(this.gp);
      var10002 = 64 * 6;
      Objects.requireNonNull(this.gp);
      var1.drawString("Name", var10002, 64 * 4);
      Objects.requireNonNull(this.gp);
      var10002 = 64 * 12;
      Objects.requireNonNull(this.gp);
      var1.drawString("Time (s)", var10002, 64 * 4);
      List var5 = this.gp.leaderboard.top(5);
      Objects.requireNonNull(this.gp);
      int var6 = 64 * 5;
      byte var7 = 36;

      for(int var8 = 0; var8 < var5.size(); ++var8) {
         LeaderboardEntry var9 = (LeaderboardEntry)var5.get(var8);
         int var10 = var6 + var8 * var7;
         String var11 = String.valueOf(var8 + 1);
         Objects.requireNonNull(this.gp);
         var1.drawString(var11, 64 * 3, var10);
         var11 = var9.getName();
         Objects.requireNonNull(this.gp);
         var1.drawString(var11, 64 * 6, var10);
         var11 = this.dFormat.format(var9.getTimeSeconds());
         Objects.requireNonNull(this.gp);
         var1.drawString(var11, 64 * 12, var10);
      }

   }

   void drawHelpScreen(Graphics2D var1) {
      var1.setColor(Color.black);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      var1.fillRect(0, 0, 1280, 768);
      var1.setColor(Color.white);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      int var10003 = 1280 - 64 * 2;
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      var1.fillRoundRect(64, 64, var10003, 768 - 64 * 2, 25, 25);
      var1.setColor(Color.black);
      Objects.requireNonNull(this.gp);
      int var10001 = 64 + 5;
      Objects.requireNonNull(this.gp);
      int var10002 = 64 + 5;
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      var10003 = 1280 - 64 * 2 - 10;
      Objects.requireNonNull(this.gp);
      Objects.requireNonNull(this.gp);
      var1.fillRoundRect(var10001, var10002, var10003, 768 - 64 * 2 - 10, 25, 25);
      var1.setFont(var1.getFont().deriveFont(1, 50.0F));
      String var2 = "INSTRUCTIONS";
      int var3 = this.getXForCenteredText(var2);
      Objects.requireNonNull(this.gp);
      int var4 = 64 + 60;
      var1.setColor(Color.white);
      var1.drawString(var2, var3, var4);
      var1.setFont(var1.getFont().deriveFont(0, 30.0F));
      Objects.requireNonNull(this.gp);
      var10002 = 64 * 3;
      Objects.requireNonNull(this.gp);
      var1.drawString("Have a good moment!", var10002, 64 * 4);
   }

   void drawPlayerLife() {
      Objects.requireNonNull(this.gp);
      int var1 = 64 / 2;
      Objects.requireNonNull(this.gp);
      int var2 = 64 / 2;

      Graphics2D var10000;
      BufferedImage var10001;
      int var3;
      for(var3 = 0; var3 < this.gp.player.getMaxHp() / 2; var1 += 64) {
         var10000 = this.g2;
         var10001 = this.heartImages[0];
         Objects.requireNonNull(this.gp);
         Objects.requireNonNull(this.gp);
         var10000.drawImage(var10001, var1, var2, 64, 64, (ImageObserver)null);
         ++var3;
         Objects.requireNonNull(this.gp);
      }

      Objects.requireNonNull(this.gp);
      var1 = 64 / 2;
      Objects.requireNonNull(this.gp);
      var2 = 64 / 2;

      for(var3 = 0; var3 < this.gp.player.getHp(); var1 += 64) {
         var10000 = this.g2;
         var10001 = this.heartImages[1];
         Objects.requireNonNull(this.gp);
         Objects.requireNonNull(this.gp);
         var10000.drawImage(var10001, var1, var2, 64, 64, (ImageObserver)null);
         ++var3;
         if (var3 < this.gp.player.getHp()) {
            var10000 = this.g2;
            var10001 = this.heartImages[2];
            Objects.requireNonNull(this.gp);
            Objects.requireNonNull(this.gp);
            var10000.drawImage(var10001, var1, var2, 64, 64, (ImageObserver)null);
         }

         ++var3;
         Objects.requireNonNull(this.gp);
      }

   }
}
