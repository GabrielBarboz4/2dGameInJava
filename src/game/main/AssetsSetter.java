package game.main;

import game.object.ObjKey;

public class AssetsSetter {
    GamePanel gamePanel;

    public AssetsSetter (GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.obj[0] = new ObjKey();
        gamePanel.obj[0].worldX = 25 * gamePanel.titleSize;
        gamePanel.obj[0].worldY = 25 * gamePanel.titleSize;

        gamePanel.obj[1] = new ObjKey();
        gamePanel.obj[1].worldX = 21 * gamePanel.titleSize;
        gamePanel.obj[1].worldY = 18 * gamePanel.titleSize;
    }
}
