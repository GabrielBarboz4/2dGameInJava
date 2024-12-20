package game.main;

import game.object.Obj_Boots;
import game.object.Obj_Chest;
import game.object.Obj_Door;
import game.object.Obj_Key;

public class AssetsSetter {
    GamePanel gamePanel;

    public AssetsSetter (GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.obj[0] = new Obj_Key();
        gamePanel.obj[0].worldX = 25 * gamePanel.titleSize;
        gamePanel.obj[0].worldY = 25 * gamePanel.titleSize;

        gamePanel.obj[1] = new Obj_Key();
        gamePanel.obj[1].worldX = 21 * gamePanel.titleSize;
        gamePanel.obj[1].worldY = 18 * gamePanel.titleSize;

        gamePanel.obj[2] = new Obj_Door();
        gamePanel.obj[2].worldX = 4 * gamePanel.titleSize;
        gamePanel.obj[2].worldY = 5 * gamePanel.titleSize;

        gamePanel.obj[3] = new Obj_Chest();
        gamePanel.obj[3].worldX = gamePanel.titleSize;
        gamePanel.obj[3].worldY = gamePanel.titleSize;

        gamePanel.obj[4] = new Obj_Boots();
        gamePanel.obj[4].worldX = 3 * gamePanel.titleSize;
        gamePanel.obj[4].worldY = 2 * gamePanel.titleSize;

    }
}
