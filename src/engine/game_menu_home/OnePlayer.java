package engine.game_menu_home;

import model.global.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OnePlayer {
    private BufferedImage img;

    public OnePlayer(){
        try {
            img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("oneplayer.png"));
            ImageIO.setUseCache(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        g.drawImage(img, (int) Constants.rect_oneplayer.x, (int)Constants.rect_oneplayer.y,
                (int) Constants.rect_oneplayer.width, (int)Constants.rect_oneplayer.height, null);

    }
}
