package game.object;

import game.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Obj_Chest extends SuperObject {

    GamePanel gamePanel;

    public  Obj_Chest ( GamePanel gamePanel ) {

        this.gamePanel = gamePanel;

        name = "Chest";
        collision = true;
        try {
            image = ImageIO.read( ( Objects.requireNonNull (getClass().getClassLoader().getResourceAsStream ("objects/objects_chest_1.png" ))));
            uTool.scaleImage( image, gamePanel.titleSize, gamePanel.titleSize );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
