package game.tile;

import game.main.GamePanel;
import game.main.UtilityTool;

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

    public TileManager ( GamePanel gamePanel ) {
        this.gamePanel = gamePanel;
        tile = new Tile[50];
        mapTileNum = new int[ gamePanel.maxWorldCol ][ gamePanel.maxWorldRow ];
        getTileImage();
        loadMap( "maps/map_50x50_better.txt" );
    }

    public void getTileImage(){

        setup(2, "grass00", false);
        setup(3, "grass01", false);
        setup(20, "wall00", true);
        setup(21, "water00", true);
        setup(19, "tree00", true);
        setup(0, "earth00", false);
        setup(5, "road00", false);
        setup(1, "floor01", false);
        setup(22, "water01", true);
        setup(23, "water02", true);
        setup(24, "water03", true);
        setup(25, "water04", true);
        setup(26, "water05", true);
        setup(27, "water06", true);
        setup(28, "water07", true);
        setup(29, "water08", true);
        setup(30, "water09", true);
        setup(31, "water10", true);
        setup(32, "water11", true);
        setup(33, "water12", true);
        setup(34, "water13", true);
        setup(6, "road01", false);
        setup(7, "road02", false);
        setup(8, "road03", false);
        setup(9, "road04", false);
        setup(10, "road05", false);
        setup(11, "road06", false);
        setup(12, "road07", false);
        setup(13, "road08", false);
        setup(14, "road09", false);
        setup(15, "road10", false);
        setup(16, "road11", false);
        setup(17, "road12", false);
        setup(4, "hut", true);
        setup(18, "table01", true);

    }

    public void setup ( int index, String imagePath, boolean collision ) {

        UtilityTool uTool = new UtilityTool();

        try {

            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream( "tiles/" + imagePath + ".png" )));
            tile[index].image = uTool.scaleImage( tile[index].image, gamePanel.titleSize, gamePanel.titleSize );
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap( String filePatch ) {

        try {

            InputStream is = getClass().getClassLoader().getResourceAsStream(filePatch);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
            int col = 0;
            int row = 0;

            while ( col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow ) {
                String line = reader.readLine();

                while ( col < gamePanel.maxWorldCol ) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[ col ]);

                    mapTileNum[ col ][ row ]  = num;
                    col++;
                }
                if ( col == gamePanel.maxWorldCol ) {
                    col = 0;
                    row ++;
                }
            }
            reader.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void draw( Graphics2D g2 ) {

        int worldCol = 0;
        int worldRow = 0;

        while ( worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow ) {

            int titleNum = mapTileNum[ worldCol ][ worldRow ];
            int worldX = worldCol * gamePanel.titleSize;
            int worldY = worldRow * gamePanel.titleSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if ( worldX + gamePanel.titleSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                 worldX - gamePanel.titleSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                 worldY + gamePanel.titleSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                 worldY - gamePanel.titleSize < gamePanel.player.worldY + gamePanel.player.screenY ) {

                g2.drawImage( tile[titleNum].image, screenX, screenY, null) ;
            }

            worldCol++;

            if ( worldCol == gamePanel.maxWorldCol ) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
