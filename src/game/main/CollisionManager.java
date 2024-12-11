package game.main;

import game.entity.Entity;

public class CollisionManager {

    GamePanel gamePanel;
    public CollisionManager (GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gamePanel.titleSize;
        int entityRightCol = entityRightWorldX/gamePanel.titleSize;
        int entityTopRow = entityTopWorldY/gamePanel.titleSize;
        int entityBottomRow = entityBottomWorldY/gamePanel.titleSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {

            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gamePanel.titleSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];

                if (gamePanel.tileManager.tile[tileNum1].collision ||
                        gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                } break;

            case "down":
                entityBottomRow = (entityTopWorldY + entity.speed)/gamePanel.titleSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision ||
                        gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                } break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gamePanel.titleSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];

                if (gamePanel.tileManager.tile[tileNum1].collision ||
                        gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                } break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gamePanel.titleSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];

                if (gamePanel.tileManager.tile[tileNum1].collision ||
                        gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                } break;
        }
    }
}
