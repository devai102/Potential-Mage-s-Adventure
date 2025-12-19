package core;

public class WinSession {
   private boolean active;
   private double timeSeconds;
   private StringBuilder nameBuffer = new StringBuilder();

   public WinSession() {
   }

   public void start(double var1) {
      this.active = true;
      this.timeSeconds = var1;
      this.nameBuffer.setLength(0);
   }

   public boolean isActive() {
      return this.active;
   }

   public double getTimeSeconds() {
      return this.timeSeconds;
   }

   public String getName() {
      return this.nameBuffer.toString();
   }

   public void appendChar(char var1) {
      if (this.active) {
         if (this.nameBuffer.length() < 16) {
            if (Character.isLetterOrDigit(var1) || Character.isSpaceChar(var1) || var1 == '-' || var1 == '_') {
               this.nameBuffer.append(var1);
            }
         }
      }
   }

   public void backspace() {
      if (this.active) {
         if (this.nameBuffer.length() > 0) {
            this.nameBuffer.deleteCharAt(this.nameBuffer.length() - 1);
         }

      }
   }

   public String finish() {
      this.active = false;
      return this.nameBuffer.toString().trim();
   }

   public void cancel() {
      this.active = false;
      this.nameBuffer.setLength(0);
   }
}
