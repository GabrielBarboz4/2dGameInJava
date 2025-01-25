package game.object;

import game.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Obj_Door extends SuperObject{

    GamePanel gamePanel;

    public Obj_Door( GamePanel gamePanel ) {

        this.gamePanel = gamePanel;

        name = "Door";
        try {
            image = ImageIO.read ((Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/objects_door_1.png"))));
            uTool.scaleImage(image, gamePanel.titleSize, gamePanel.titleSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
