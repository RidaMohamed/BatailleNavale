package engine.game__splash_screen;

import model.BattleNavalePainter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SplashScreenPainter extends JPanel {

    private static final long serialVersionUID = 1L;
    private BattleNavalePainter battleNavalePainter;

    public SplashScreenPainter(BattleNavalePainter battleNavalePainter){
        super();
        this.setPreferredSize(new Dimension(battleNavalePainter.getScreenWidth(), battleNavalePainter.getScreenHeight()));
        this.battleNavalePainter = battleNavalePainter;
    }

    public void draw(BufferedImage im){
        Graphics g = im.getGraphics();
        try {
            BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream("/background.jpg"));
            g.drawImage(image ,0 ,0 , battleNavalePainter.getScreenWidth() , battleNavalePainter.getScreenHeight(), null);
            image = ImageIO.read(this.getClass().getResourceAsStream("/logo.png"));
            g.drawImage(image ,50 ,50 , 250 , 50, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void paint(Graphics g) {
        super.paint(g);
    }

}

