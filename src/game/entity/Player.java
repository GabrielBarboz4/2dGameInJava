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

    public Player(GamePanel gamePanel, KeyHandler keyH) {
        this.gamePanel = gamePanel;
        this.keyH = keyH;
        setDefaultValues();
        try {
            getPlayerImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        screenX = gamePanel.screenWidth/2 - (gamePanel.titleSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.titleSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);
    }

    public void setDefaultValues() {
        worldX = gamePanel.titleSize * 20;
        worldY = gamePanel.titleSize * 30;
        speed = 4;
        direction = "down";
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            }
            if (keyH.downPressed) {
                direction = "down";
            }
            if (keyH.leftPressed) {
                direction = "left";
            }
            if (keyH.rightPressed) {
                direction = "right";

            }

            //Check tile collision
            collisionOn = false;
            gamePanel.collisionManager.checkTile(this);

            if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum =2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void getPlayerImage() throws IOException {
        up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/sprite_up_1.png")));
        up2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/sprite_up_2.png")));
        down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/sprite_down_1.png")));
        down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/sprite_down_2.png"))) ;
        left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/sprite_left_1.png"))) ;
        left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/sprite_left_2.png"))) ;
        right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/sprite_right_1.png")));
        right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/sprite_right_2.png")));
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                } break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                } break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                } break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                } break;
        }
        g2.drawImage(image, screenX, screenY, gamePanel.titleSize, gamePanel.titleSize, null);
    }
}
