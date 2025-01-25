package game.main;

import java.awt.*;

public class Ui {

    GamePanel gamePanel;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";

    public Ui ( GamePanel gamePanel) {

        this.gamePanel = gamePanel;
        arial_40 = new Font ("Arial", Font.PLAIN, 40);
        arial_80B = new Font ("Arial", Font.BOLD, 80);
    }

    public void showMessage(String text ) {

        message = text;
        messageOn = true;
    }

    public void draw ( Graphics2D g2 ) {

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        // PLAY STATE
        if ( gamePanel.gameState == gamePanel.playState) {

        }

        // PAUSE STATE
        if ( gamePanel.gameState == gamePanel.pauseState ) {
            drawPauseScreen();
        }

        // DIALOGUE STATE

    }

    public void drawPauseScreen ( ) {

        g2.setFont( g2.getFont().deriveFont( Font.PLAIN, 80F ));
        String text = "PAUSED";
        int x = getXForCenteredText( text );
        int y = gamePanel.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public int getXForCenteredText(String text ) {

        int length = ( int ) g2.getFontMetrics().getStringBounds( text, g2 ).getWidth();

        return gamePanel.screenWidth / 2 - length / 2;
    }
}

