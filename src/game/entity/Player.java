package game.entity;

import game.main.GamePanel;
import game.main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    private final GamePanel gamePanel;
    private final KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player( GamePanel gamePanel, KeyHandler keyH ) {

        super(gamePanel);

        this.gamePanel = gamePanel;
        this.keyH = keyH;
        setDefaultValues();

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
        getPlayerImage();
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

            // Check NPC or Monster Collision
            int npcIndex = gamePanel.collisionManager.checkEntity(this, gamePanel.npc);
            interactNPC ( npcIndex );

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
                    spriteNum = 2;
                } else if ( spriteNum == 2 ) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void getPlayerImage() {

        up1 = setup( "player/Walking sprites/boy_up_1" );
        up2 = setup( "player/Walking sprites/boy_up_2" );
        down1 = setup( "player/Walking sprites/boy_down_1" );
        down2 = setup( "player/Walking sprites/boy_down_2" );
        left1 = setup( "player/Walking sprites/boy_left_1" );
        left2 = setup( "player/Walking sprites/boy_left_2" );
        right1 = setup( "player/Walking sprites/boy_right_1" );
        right2 = setup( "player/Walking sprites/boy_right_2" );
    }

    private void interactNPC( int npcIndex ) {

        if ( npcIndex != 999 ) {
            System.out.println("Hit npc");
        }
    }

    public void pickUpObject ( int i ) {

        if ( i != 999 ) {

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

        g2.drawImage( image, screenX, screenY, null );
    }
}
