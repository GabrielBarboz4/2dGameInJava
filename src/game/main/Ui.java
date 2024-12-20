package game.main;

import game.object.Obj_Key;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class Ui {

    GamePanel gamePanel;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    int messageCounter = 0;
    double playeTime;
    DecimalFormat dFormat = new DecimalFormat("#0");

    public boolean messageOn = false;
    public String message = "";
    public boolean gameFinished = false;

    public Ui ( GamePanel gamePanel) {

        this.gamePanel = gamePanel;
        arial_40 = new Font ("Arial", Font.PLAIN, 40);
        arial_80B = new Font ("Arial", Font.BOLD, 80);
        Obj_Key key = new Obj_Key();
        keyImage = key.image;
    }

    public void showMessagem ( String text ) {

        message = text;
        messageOn = true;
    }
    public void draw ( Graphics2D g2 ) {

        if ( !gameFinished ) {
            g2.setFont( arial_40 );
            g2.setColor( Color.white );
            g2.drawImage( keyImage, gamePanel.titleSize / 2, gamePanel.titleSize / 2, gamePanel.titleSize, gamePanel.titleSize, null );
            g2.drawString("x " + gamePanel.player.hasKey, 74, 60 );

            // Time

            playeTime += (double) 1/60;
            g2.drawString("Time: " + dFormat.format(playeTime), gamePanel.titleSize/2, 560);

            // Pop up message on the screen
            if ( messageOn ) {

                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString( message, gamePanel.titleSize / 2, gamePanel.titleSize * 5);

                messageCounter ++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        } else {
            g2.setFont( arial_40 );
            g2.setColor( Color.white );

            String text = "You found the treasure!";
            int textLength = (int) g2.getFontMetrics().getStringBounds( text, g2 ).getWidth();

            int x = gamePanel.screenWidth/2 - textLength / 2;
            int y = gamePanel.screenHeight/2 - ( gamePanel.titleSize * 3 );
            g2.drawString( text, x, y );

            text = "Your Time is: " + dFormat.format(playeTime) + " seconds";
            textLength = (int) g2.getFontMetrics().getStringBounds( text, g2 ).getWidth();
            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 + ( gamePanel.titleSize * 5 );
            g2.drawString( text, x, y );

            g2.setFont( arial_80B);
            g2.setColor( Color.white );
            text = "Congratulations!!!";
            textLength = (int) g2.getFontMetrics().getStringBounds( text, g2 ).getWidth();

            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 + ( gamePanel.titleSize * 3 );
            g2.drawString( text, x, y );
            gamePanel.gameThread = null;
        }
    }
}

