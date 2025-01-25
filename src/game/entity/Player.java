package game.entity;

import game.main.GamePanel;
import game.main.KeyHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    private final GamePanel gamePanel;
    private final KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player( GamePanel gamePanel, KeyHandler keyH ) {

        this.gamePanel = gamePanel;
        this.keyH = keyH;
        setDefaultValues();

        try { getPlayerImage();}

        catch ( IOException e ) {
            throw new RuntimeException(e);
        }

        screenX = gamePanel.screenWidth / 2 - ( gamePanel.titleSize / 2 );
        screenY = gamePanel.screenHeight / 2 - ( gamePanel.titleSize / 2 );

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void setDefaultValues() {

        worldX = gamePanel.titleSize * 20;
        worldY = gamePanel.titleSize * 30;
        speed = 3;
        direction = "down";
    }

    public void update() {

        if ( keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed ) {
            if ( keyH.upPressed ) direction = "up";
            if ( keyH.downPressed ) direction = "down";
            if ( keyH.leftPressed ) direction = "left";
            if ( keyH.rightPressed ) direction = "right";

            // Check tile collision
            collisionOn = false;
            gamePanel.collisionManager.checkTile( this) ;

            // Check object collision
            int objIndex = gamePanel.collisionManager.checkObject( this, true );
            pickUpObject( objIndex );

            // Check if collision is false, if its false: Player can move
            if ( !collisionOn ) {
                switch ( direction ) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            spriteCounter++;
            if ( spriteCounter > 12 ) {
                if ( spriteNum == 1 ) {
                    spriteNum =2;
                } else if ( spriteNum == 2 ) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void getPlayerImage() throws IOException {

        up1 = ImageIO.read( Objects.requireNonNull ( getClass().getClassLoader().getResourceAsStream ("player/sprite_up_1.png" )));
        up2 = ImageIO.read( Objects.requireNonNull (getClass().getClassLoader().getResourceAsStream ("player/sprite_up_2.png" )));
        down1 = ImageIO.read( Objects.requireNonNull (getClass().getClassLoader().getResourceAsStream ("player/sprite_down_1.png" )));
        down2 = ImageIO.read( Objects.requireNonNull (getClass().getClassLoader().getResourceAsStream ("player/sprite_down_2.png" ))) ;
        left1 = ImageIO.read( Objects.requireNonNull (getClass().getClassLoader().getResourceAsStream ("player/sprite_left_1.png" ))) ;
        left2 = ImageIO.read( Objects.requireNonNull (getClass().getClassLoader().getResourceAsStream ("player/sprite_left_2.png" ))) ;
        right1 = ImageIO.read( Objects.requireNonNull (getClass().getClassLoader().getResourceAsStream ("player/sprite_right_1.png" )));
        right2 = ImageIO.read( Objects.requireNonNull (getClass().getClassLoader().getResourceAsStream ("player/sprite_right_2.png" )));
    }

    public void pickUpObject ( int i ) {

        if ( i != 999 ) {

            String objectName = gamePanel.obj[i].name;

            switch ( objectName ) {
                case "Key":
                    gamePanel.playSoundEffect(1);
                    hasKey++;
                    gamePanel.obj[i] = null;
                    gamePanel.ui.showMessagem("You found a Key!");

                    break;
                case "Door":

                    if (hasKey > 0) {
                        gamePanel.playSoundEffect(4);
                        gamePanel.obj[i] = null;
                        hasKey --;
                        gamePanel.ui.showMessagem("You opened the door!");
                    } else {
                        gamePanel.ui.showMessagem("You need to find a key");
                    }
                    break;

                case "Boots":
                    gamePanel.playSoundEffect(3);
                    gamePanel.obj[i] = null;
                    gamePanel.ui.showMessagem("You found a boots, now you move 25 % faster");
                    speed ++;
                    break;

                case "Chest":
                    gamePanel.ui.gameFinished = true;
                    gamePanel.stopMusic();
                    gamePanel.playSoundEffect( 2 );
                    break;
            }
        }
    }

    public void draw ( Graphics2D g2 ) {

        BufferedImage image = null;

        switch ( direction ) {

            case "up":
                if ( spriteNum == 1 ) image = up1;
                if ( spriteNum == 2 ) image = up2;
                break;

            case "down":
                if ( spriteNum == 1 ) image = down1;
                if ( spriteNum == 2 ) image = down2;
                break;

            case "left":
                if ( spriteNum == 1 ) image = left1;
                if ( spriteNum == 2 ) image = left2;
                break;

            case "right":
                if ( spriteNum == 1 ) image = right1;
                if ( spriteNum == 2 ) image = right2;
                break;
        }

        g2.drawImage( image, screenX, screenY, gamePanel.titleSize, gamePanel.titleSize, null );
    }
}
