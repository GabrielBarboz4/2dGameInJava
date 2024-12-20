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
        gamePanel.obj[0].worldX = 18 * gamePanel.titleSize;
        gamePanel.obj[0].worldY = 33 * gamePanel.titleSize;

        gamePanel.obj[1] = new Obj_Key();
        gamePanel.obj[1].worldX = 37 * gamePanel.titleSize;
        gamePanel.obj[1].worldY = 48 * gamePanel.titleSize;

        gamePanel.obj[2] = new Obj_Door();
        gamePanel.obj[2].worldX = 3 * gamePanel.titleSize;
        gamePanel.obj[2].worldY = 5 * gamePanel.titleSize;

        gamePanel.obj[3] = new Obj_Chest();
        gamePanel.obj[3].worldX = 3 * gamePanel.titleSize;
        gamePanel.obj[3].worldY = gamePanel.titleSize;

        gamePanel.obj[4] = new Obj_Boots();
        gamePanel.obj[4].worldX = 2 * gamePanel.titleSize;
        gamePanel.obj[4].worldY = 44 * gamePanel.titleSize;

        gamePanel.obj[5] = new Obj_Door();
        gamePanel.obj[5].worldX = 23 * gamePanel.titleSize;
        gamePanel.obj[5].worldY = 30 * gamePanel.titleSize;

        gamePanel.obj[6] = new Obj_Door();
        gamePanel.obj[6].worldX = 23 * gamePanel.titleSize;
        gamePanel.obj[6].worldY = 14 * gamePanel.titleSize;

        gamePanel.obj[7] = new Obj_Door();
        gamePanel.obj[7].worldX = 37 * gamePanel.titleSize;
        gamePanel.obj[7].worldY = 12 * gamePanel.titleSize;

        gamePanel.obj[8] = new Obj_Key();
        gamePanel.obj[8].worldX = 34 * gamePanel.titleSize;
        gamePanel.obj[8].worldY = 13 * gamePanel.titleSize;

        gamePanel.obj[9] = new Obj_Key();
        gamePanel.obj[9].worldX = 34 * gamePanel.titleSize;
        gamePanel.obj[9].worldY = 11 * gamePanel.titleSize;
    }
}
