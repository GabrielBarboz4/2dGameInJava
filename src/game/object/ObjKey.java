package game.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjKey extends SuperObject{
    public  ObjKey () {
        name = "key";
        try {
            image = ImageIO.read((getClass().getClassLoader().getResourceAsStream("objects/objects_key_1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
