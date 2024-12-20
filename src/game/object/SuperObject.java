package game.object;

import game.main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gamePanel) {

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if (worldX + gamePanel.titleSize > gamePanel.player.worldX - gamePanel.player.screenX &&
            worldX - gamePanel.titleSize < gamePanel.player.worldX + gamePanel.player.screenX &&
            worldY + gamePanel.titleSize > gamePanel.player.worldY - gamePanel.player.screenY &&
            worldY - gamePanel.titleSize < gamePanel.player.worldY + gamePanel.player.screenY) {

            g2.drawImage(image, screenX, screenY, gamePanel.titleSize, gamePanel.titleSize, null);
        }
    }
}