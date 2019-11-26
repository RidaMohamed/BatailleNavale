package engine.gameParty;

import engine.painter.Painter;
import global.Constant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawingScore extends JPanel {

    private Painter painter;
    private int width, height;

    public DrawingScore(Painter painter) {
        super();
        this.width = painter.getScreenWidth();
        this.height = 100;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.painter=painter;
    }

    public void drawGame(BufferedImage im) {

        try {
            BufferedImage image1 = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/header.png"));
            im.getGraphics().drawImage(image1 , 0 , 0 , this.width , this.height, null);

            BufferedImage logo = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/logo.png"));
            im.getGraphics().drawImage(logo , 9 , 10 , 200 , 75, null);

            BufferedImage water = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/water-hit.png"));
            im.getGraphics().drawImage(water , 400 , 30 , 50 , 50, null);
            im.getGraphics().drawString("17" , 500 , 58);
            im.getGraphics().setColor(Color.WHITE);
            im.getGraphics().drawRect(390 , 20 , 150 , 70);

            BufferedImage hit = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/hit.png"));
            im.getGraphics().drawImage(hit , 600 , 25 , 50 , 50, null);
            im.getGraphics().drawString("17" , 700 , 58);
            im.getGraphics().setColor(Color.WHITE);
            im.getGraphics().drawRect(590 , 20 , 150 , 70);

            BufferedImage sniper = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/sniper.png"));
            im.getGraphics().drawImage(sniper , 800 , 30 , 50 , 50, null);
            im.getGraphics().drawString("17" , 900 , 58);
            im.getGraphics().setColor(Color.WHITE);
            im.getGraphics().drawRect(790 , 20 , 150 , 70);




        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void paint(Graphics g) {
        super.paint(g);
    }

}
