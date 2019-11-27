package engine.gameMenu;

import engine.painter.Painter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SplashScreenPainter extends JPanel {

    private static final long serialVersionUID = 1L;

    private Painter painter;

    public SplashScreenPainter(Painter painter){
        super();
        this.setPreferredSize(new Dimension(painter.getScreenWidth(), painter.getScreenHeight()));
        this.painter=painter;


    }


    public void draw(BufferedImage im){
        Graphics g = im.getGraphics();
        try {
            BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/background.jpg"));
            g.drawImage(image ,0 ,0 , painter.getScreenWidth() , painter.getScreenHeight(), null);
            image = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/logo.png"));
            g.drawImage(image ,50 ,50 , 400 , 100, null);
            image = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/loading.png"));
            g.drawImage(image ,painter.getScreenWidth()-220 ,painter.getScreenHeight()-100 , 140 , 50, null);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void paint(Graphics g) {
        super.paint(g);
    }

}

