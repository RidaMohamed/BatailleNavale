package engine.game_menu_home;

import model.global.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MultiPlayer {
    private BufferedImage img;

    public MultiPlayer(){
        try {
            img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("multiplayer.png"));
            ImageIO.setUseCache(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        g.drawImage(img, (int) Constants.rect_multiplayer.x, (int)Constants.rect_multiplayer.y,
                (int) Constants.rect_multiplayer.width, (int)Constants.rect_multiplayer.height, null);

    }
}
