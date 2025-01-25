package game.main;

import game.entity.Npc_Oldman;


public class AssetsSetter {

    GamePanel gamePanel;

    public AssetsSetter ( GamePanel gamePanel ) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {

    }

    public void setNpc() {

        gamePanel.npc[0] = new Npc_Oldman( gamePanel );
        gamePanel.npc[0].worldX = gamePanel.titleSize * 22;
        gamePanel.npc[0].worldY = gamePanel.titleSize * 28;
    }
}