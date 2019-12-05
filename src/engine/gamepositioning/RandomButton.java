package engine.gamepositioning;

import model.global.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RandomButton {
    private BufferedImage random;

    public RandomButton(){
        try {
            random = ImageIO.read(getClass().getClassLoader().getResourceAsStream("pos.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        g.drawImage(random, (int) Constants.rect_random.x, (int)Constants.rect_random.y,
                (int) Constants.rect_random.width, (int)Constants.rect_random.height, null);

    }
}
