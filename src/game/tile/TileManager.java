package game.tile;

import game.main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager (GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap("maps/map_50x50_better.txt");
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image =  ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/tile_grass_2.png")));

            tile[1] = new Tile();
            tile[1].image =  ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/tile_wall_1.png")));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image =  ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/tile_water_1.png")));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image =  ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/tile_tree_1.png")));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image =  ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/tile_earth_1.png")));

            tile[5] = new Tile();
            tile[5].image =  ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/tile_sand_2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePatch) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePatch);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                String line = reader.readLine();

                while (col < gamePanel.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col] [row]  = num;
                    col++;
                }
                if (col == gamePanel.maxWorldCol) {
                    col = 0;
                    row ++;
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {

            int titleNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gamePanel.titleSize;
            int worldY = worldRow * gamePanel.titleSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if (worldX + gamePanel.titleSize > gamePanel.player.worldX - gamePanel.player.screenX && worldX - gamePanel.titleSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.titleSize > gamePanel.player.worldY - gamePanel.player.screenY && worldY - gamePanel.titleSize < gamePanel.player.worldY + gamePanel.player.screenY) {
                g2.drawImage(tile[titleNum].image, screenX, screenY, gamePanel.titleSize, gamePanel.titleSize, null);
            }
            worldCol++;

            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
