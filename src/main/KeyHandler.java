package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// KeyListener: the listener interface for receiving keyboard events (keystrokes)
public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, spacePressed, fPressed;
    public boolean AnnouceCompleteAnimation;
    // DEBUG
    boolean checkDrawTime = false;
    GamePanel gp;
    public boolean isMove = false;
    int temp_map;
    double temp_woldX, temp_woldY;
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        isMove = true;
        int key = e.getKeyCode();

        //TITTLE STATE

        if (gp.gameState == gp.tittleState) {
            tittleState(key);
        }

        if (gp.gameState == gp.selectPlayerState) {
            selectPlayerState(key);
        }
        if (gp.currentMap == 0) {
            //PLAY STATE
            if (gp.gameState == gp.playState || gp.gameState == gp.autoDisplayState) {
                gamePlayerState(key);
            }

            else if (gp.gameState == gp.collectionState) {
                collectionState(key);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        isMove = false;
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (key == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (key == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (key == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (key == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if (key == KeyEvent.VK_F) {
            fPressed = false;
        }
    }

    public void tittleState(int key) {
        if (key == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            gp.playSoundEffect("select_sound", 6);
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
        }
        if (key == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            gp.playSoundEffect("select_sound", 6);
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
        }
        if (key == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.playSoundEffect("click_sound", 7);
                gp.gameState = gp.selectPlayerState;
            }
            if (gp.ui.commandNum == 1) {
                // add later
            }
            if (gp.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }

    public void selectPlayerState(int key) {
        if (key == KeyEvent.VK_A) {
            gp.ui.commandNum--;
            gp.playSoundEffect("select_sound", 6);
            if (gp.ui.commandNum < 1) {
                gp.ui.commandNum = 2;
            }
        }
        if (key == KeyEvent.VK_D) {
            gp.ui.commandNum++;
            gp.playSoundEffect("select_sound", 6);
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 1;
            }
        }
        if (key == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 1) {
                gp.playSoundEffect("click_sound", 7);
                gp.player.setPlayerImage("Human");
                gp.gameState = gp.playState;
            } else if (gp.ui.commandNum == 2) {
                gp.playSoundEffect("click_sound", 7);
                gp.player.setPlayerImage("Dino");
                gp.gameState = gp.playState;
            }
            gp.ui.commandNum = 0;
        }
    }

    public void gamePlayerState(int key) {
        if (key == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (key == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (key == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (key == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if (key == KeyEvent.VK_F) {
            fPressed = true;
        }
        if (key == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionState;
            gp.music.stop("Bird");
            gp.music.stop("Background");
        }

//        if (key == KeyEvent.VK_H) {
//            gp.player.physical = gp.player.maxPhysical;
//        }
        if (key == KeyEvent.VK_C) {
            gp.gameState = gp.collectionState;
        }
//        if (key == KeyEvent.VK_B) {
//            gp.gameState = gp.inventoryState;
//        }
        if (key == KeyEvent.VK_L) {
            gp.gameState = gp.transitionState;
            temp_map = 1;
            temp_woldX = 0;
            temp_woldY = 0;
//
//            gp.player.temp_worldX = gp.player.worldX;
//            gp.player.temp_worldY = gp.player.worldY;
        }

        //DEBUG
        if (key == KeyEvent.VK_T) {
            checkDrawTime = (checkDrawTime == true) ? false : true;
        }
    }

    public void collectionState(int key) {
         if (key == KeyEvent.VK_C || key == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
    }

}    
