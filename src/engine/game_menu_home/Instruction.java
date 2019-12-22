package engine.game_menu_home;

import model.global.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Instruction {
    private BufferedImage img;

    public Instruction(){
        try {
            img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("instructions.png"));
            ImageIO.setUseCache(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        g.drawImage(img, (int) Constants.rect_instructions.x, (int)Constants.rect_instructions.y,
                (int) Constants.rect_instructions.width, (int)Constants.rect_instructions.height, null);

    }
}
