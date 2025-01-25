package game.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gamePanel;

    public KeyHandler ( GamePanel gamePanel ) {
        this.gamePanel = gamePanel;
    }

    // For moving the player
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    // For Debug mode
    public boolean checkDrawTime = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if ( code == KeyEvent.VK_T) {
            checkDrawTime = !checkDrawTime;
        }
        if ( code == KeyEvent.VK_P ) {
            if ( gamePanel.gameState == gamePanel.playState ) {
                gamePanel.gameState = gamePanel.pauseState;
            } else if ( gamePanel.gameState == gamePanel.pauseState ) {
                gamePanel.gameState = gamePanel.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
