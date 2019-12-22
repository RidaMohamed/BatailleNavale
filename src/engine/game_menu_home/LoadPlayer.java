package engine.game_menu_home;

import model.global.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoadPlayer {
    private BufferedImage img;

    public LoadPlayer(){
        try {
            img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("loadparty.png"));
            ImageIO.setUseCache(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        g.drawImage(img, (int) Constants.rect_load.x, (int)Constants.rect_load.y,
                (int) Constants.rect_load.width, (int)Constants.rect_load.height, null);

    }
}
