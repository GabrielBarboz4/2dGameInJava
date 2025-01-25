package game.entity;

import game.main.GamePanel;
import game.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {

    GamePanel gamePanel;
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteNum = 1;
    public int spriteCounter = 0;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;

    public Entity ( GamePanel gamePanel ) {
        this.gamePanel = gamePanel;
    }

    public void setAction () {

    }

    public void update ( ) {

        setAction();
        collisionOn = false;
        gamePanel.collisionManager.checkTile(this);
        gamePanel.collisionManager.checkObject(this, false);
        gamePanel.collisionManager.checkPlayer(this);

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

    public void draw ( Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if (worldX + gamePanel.titleSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.titleSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.titleSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.titleSize < gamePanel.player.worldY + gamePanel.player.screenY) {

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

            g2.drawImage(image, screenX, screenY, gamePanel.titleSize, gamePanel.titleSize, null);
        }
    }

    public BufferedImage setup ( String imagePath ) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {

            image = ImageIO.read( (Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream( imagePath + ".png"))));
            image = uTool.scaleImage(image, gamePanel.titleSize, gamePanel.titleSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
