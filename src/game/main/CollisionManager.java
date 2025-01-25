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

        int entityLeftCol = entityLeftWorldX / gamePanel.titleSize;
        int entityRightCol = entityRightWorldX / gamePanel.titleSize;
        int entityTopRow = entityTopWorldY / gamePanel.titleSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.titleSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {

            case "up":
                entityTopRow = ( entityTopWorldY - entity.speed ) / gamePanel.titleSize;
                tileNum1 = gamePanel.tileManager.mapTileNum [entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum [entityRightCol][entityTopRow];

                if ( gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision ) {
                    entity.collisionOn = true;
                } break;

            case "down":
                entityBottomRow = ( entityBottomWorldY + entity.speed ) / gamePanel.titleSize;
                tileNum1 = gamePanel.tileManager.mapTileNum [ entityLeftCol ][ entityBottomRow ];
                tileNum2 = gamePanel.tileManager.mapTileNum [ entityRightCol ][ entityBottomRow ];
                if ( gamePanel.tileManager.tile[ tileNum1 ].collision || gamePanel.tileManager.tile[ tileNum2 ].collision ) {
                    entity.collisionOn = true;
                } break;

            case "left":
                entityLeftCol = ( entityLeftWorldX - entity.speed ) / gamePanel.titleSize;
                tileNum1 = gamePanel.tileManager.mapTileNum [ entityLeftCol ][ entityTopRow ];
                tileNum2 = gamePanel.tileManager.mapTileNum [ entityLeftCol ][ entityBottomRow ];

                if ( gamePanel.tileManager.tile[ tileNum1 ].collision || gamePanel.tileManager.tile[ tileNum2 ].collision ) {
                    entity.collisionOn = true;
                } break;

            case "right":
                entityRightCol = ( entityRightWorldX + entity.speed ) / gamePanel.titleSize;
                tileNum1 = gamePanel.tileManager.mapTileNum [ entityRightCol ][ entityTopRow ];
                tileNum2 = gamePanel.tileManager.mapTileNum [ entityRightCol ][ entityBottomRow ];

                if ( gamePanel.tileManager.tile[ tileNum1 ].collision || gamePanel.tileManager.tile[ tileNum2 ].collision ) {
                    entity.collisionOn = true;
                } break;
        }
    }

    public int checkObject ( Entity entity, boolean player ) {
        int index = 999;

        for ( int i = 0; i < gamePanel.obj.length; i++ ) {
            if ( gamePanel.obj[i] != null ) {

                // Get Entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get the Object's solid area position
                gamePanel.obj[i].solidArea.x = gamePanel.obj[i].worldX + gamePanel.obj[i].solidArea.x;
                gamePanel.obj[i].solidArea.y = gamePanel.obj[i].worldY + gamePanel.obj[i].solidArea.y;

                switch ( entity.direction ) {

                    case "up":
                        entity.solidArea.y -= entity.speed;

                        if ( entity.solidArea.intersects(gamePanel.obj[i].solidArea )) {
                            if ( gamePanel.obj[i].collision ) {
                                entity.collisionOn = true;
                            }

                            if ( player ) {
                                index = i;
                            }}

                        break;

                    case "down":
                        entity.solidArea.y += entity.speed;
                        if ( entity.solidArea.intersects(gamePanel.obj[i].solidArea )) {
                            if ( gamePanel.obj[i].collision) {
                                entity.collisionOn = true;
                            }

                            if ( player ) {
                                index = i;
                            }
                        }
                        break;

                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if ( entity.solidArea.intersects(gamePanel.obj[i].solidArea )) {
                            if ( gamePanel.obj[i].collision ) {
                                entity.collisionOn = true;
                            }

                            if ( player ) {
                                index = i;
                            }
                        }
                        break;

                    case "right": entity.solidArea.x += entity.speed;

                        if ( entity.solidArea.intersects(gamePanel.obj[i].solidArea )) {
                            if ( gamePanel.obj[i].collision ) {
                                entity.collisionOn = true;
                            }

                            if ( player ) {
                                index = i;
                            }
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

                gamePanel.obj[i].solidArea.x = gamePanel.obj[i].solidAreaDefaultX;
                gamePanel.obj[i].solidArea.y = gamePanel.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }

    // NPC OR MONSTER COLLISION
    public int checkEntity ( Entity entity, Entity[] target ) {

        int index = 999;

        for ( int i = 0; i < target.length; i++ ) {

            if ( target[i] != null ) {

                // Get Entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get the Object's solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch ( entity.direction ) {

                    case "up":
                        entity.solidArea.y -= entity.speed;

                        if ( entity.solidArea.intersects( target[i].solidArea )) {

                            entity.collisionOn = true;
                            index = i;
                        }
                        break;

                    case "down":
                        entity.solidArea.y += entity.speed;

                        if ( entity.solidArea.intersects( target[i].solidArea )) {

                            entity.collisionOn = true;
                            index = i;
                            }
                        break;

                    case "left":
                        entity.solidArea.x -= entity.speed;

                        if ( entity.solidArea.intersects( target[i].solidArea )) {

                            entity.collisionOn = true;
                            index = i;
                        }
                        break;

                    case "right": entity.solidArea.x += entity.speed;

                        if ( entity.solidArea.intersects( target[i].solidArea )) {

                                entity.collisionOn = true;
                                index = i;
                            }
                        break;
                        }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;

            if (target[i] != null) {
                target[i].solidArea.x = target[i].solidAreaDefaultX;
            }

            if (target[i] != null) {
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }

        return index;
    }

    public void checkPlayer ( Entity entity ) {
        // Get Entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        // Get the Object's solid area position
        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

        switch ( entity.direction ) {

            case "up":
                entity.solidArea.y -= entity.speed;

                if ( entity.solidArea.intersects( gamePanel.player.solidArea )) {

                    entity.collisionOn = true;
                }
                break;

            case "down":
                entity.solidArea.y += entity.speed;

                if ( entity.solidArea.intersects( gamePanel.player.solidArea )) {

                    entity.collisionOn = true;
                }
                break;

            case "left":
                entity.solidArea.x -= entity.speed;

                if ( entity.solidArea.intersects( gamePanel.player.solidArea )) {

                    entity.collisionOn = true;
                }
                break;

            case "right": entity.solidArea.x += entity.speed;

                if ( entity.solidArea.intersects( gamePanel.player.solidArea )) {

                    entity.collisionOn = true;
                }
                break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;

        if (gamePanel.player != null) {
            gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        }

        if (gamePanel.player != null) {
            gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        }
    }
}
