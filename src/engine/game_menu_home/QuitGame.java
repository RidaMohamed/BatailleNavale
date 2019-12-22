package engine.game_menu_home;

import model.global.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class QuitGame {
    private BufferedImage img;

    public QuitGame(){
        try {
            img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("quit.png"));
            ImageIO.setUseCache(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        g.drawImage(img, (int) Constants.rect_quit.x, (int)Constants.rect_quit.y,
                (int) Constants.rect_quit.width, (int)Constants.rect_quit.height, null);

    }
}
