package engine.game_menu_home;

import model.global.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Retour {
    private BufferedImage ok;

    public Retour(){
        try {
            ok = ImageIO.read(getClass().getClassLoader().getResourceAsStream("retour.png"));
            ImageIO.setUseCache(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        g.drawImage(ok, (int) Constants.rect_retour.x, (int)Constants.rect_retour.y,
                (int) Constants.rect_retour.width, (int)Constants.rect_retour.height, null);

    }
}
