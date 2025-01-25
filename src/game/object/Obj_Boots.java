package game.object;

import game.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Obj_Boots extends SuperObject {

    GamePanel gamePanel;

    public  Obj_Boots ( GamePanel gamePanel ) {

        this.gamePanel = gamePanel;

        name = "Boots";
        try {
            image = ImageIO.read( ( Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/objects_boots_1.png" ))));
            uTool.scaleImage( image, gamePanel.titleSize, gamePanel.titleSize );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
