package game.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Obj_Chest extends SuperObject {

    public  Obj_Chest () {
        name = "Chest";
        collision = true;
        try {
            image = ImageIO.read((Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/objects_chest_1.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
