package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{    
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    
    private GamePanel gp;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }


    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(gp.gameState == gp.tileScreenState){
            if(code == KeyEvent.VK_S){
                gp.ui.commandNumber--;
                if(gp.ui.commandNumber < 0){
                    gp.ui.commandNumber = 3;
                }
            }
            if(code == KeyEvent.VK_W){
                gp.ui.commandNumber++;
                if(gp.ui.commandNumber > 3){
                    gp.ui.commandNumber = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNumber == 0){
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNumber == 1){
                    gp.gameState = gp.leaderboardState;
                }
                if(gp.ui.commandNumber == 2){
                    gp.gameState = gp.helpState;
                }
                if(gp.ui.commandNumber == 3){
                    System.exit(0);
                }
                if(gp.gameState == gp.winState || gp.gameState == gp.gameOverState){
                    gp.setupGame();
                }
            }
        }

        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
