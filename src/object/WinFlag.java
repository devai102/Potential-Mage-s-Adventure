package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class WinFlag extends SuperObject {

    public WinFlag() {
        name = "Flag";

        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("res/image/object/Win Flag/teddy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true; // cần va chạm
    }
}

