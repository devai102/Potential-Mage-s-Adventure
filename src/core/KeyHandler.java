package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{    
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean attackPressed;
    private GamePanel gp;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if(gp.gameState == gp.winState && gp.winSession.isActive()){
            gp.winSession.appendChar(e.getKeyChar());
            gp.repaint();
        }
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
                    gp.resetGame();
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
                
            }
        } else if(gp.gameState == gp.winState || gp.gameState == gp.gameOverState){
            gp.setupGame();
        }
        if(code == KeyEvent.VK_J){
            attackPressed = true;
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
            }else if(gp.gameState == gp.leaderboardState || gp.gameState == gp.helpState){
                gp.gameState = gp.tileScreenState;
            }else if(gp.gameState == gp.winState){
                gp.winSession.cancel();
                gp.gameState = gp.tileScreenState;
            }
        }

        if(gp.gameState == gp.winState && gp.winSession.isActive()){
            if(code == KeyEvent.VK_BACK_SPACE){
                gp.winSession.backspace();
                gp.repaint();
            }
            if(code == KeyEvent.VK_ENTER){
                String name = gp.winSession.finish();
                if(name.isEmpty()){
                    gp.winSession.start(gp.playTime);
                    return;
                }
                gp.leaderboard.add(name, gp.winSession.getTimeSeconds());
                gp.playTime = 0;
                gp.gameState = gp.leaderboardState;
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
