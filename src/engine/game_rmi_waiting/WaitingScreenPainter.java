package engine.game_rmi_waiting;

import model.BattleNavalePainter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class WaitingScreenPainter extends JPanel {

    private static final long serialVersionUID = 1L;
    private BattleNavalePainter battleNavalePainter;

    public WaitingScreenPainter(BattleNavalePainter battleNavalePainter){
        super();
        this.setPreferredSize(new Dimension(battleNavalePainter.getScreenWidth(), battleNavalePainter.getScreenHeight()));
        this.battleNavalePainter = battleNavalePainter;
    }

    public void drawWaiting(BufferedImage im){
        Graphics g = im.getGraphics();
        try {
            BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream("/back.jpg"));
            g.drawImage(image ,0 ,0 , battleNavalePainter.getScreenWidth() , battleNavalePainter.getScreenHeight(), null);
            image = ImageIO.read(this.getClass().getResourceAsStream("/searching_adversary.png"));
            g.drawImage(image ,getWidth()/2 - 125 , getHeight()/2 - 25 , 250 , 50, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void drawReady(BufferedImage im){
        Graphics g = im.getGraphics();
        try {
            BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream("/back.jpg"));
            g.drawImage(image ,0 ,0 , battleNavalePainter.getScreenWidth() , battleNavalePainter.getScreenHeight(), null);
            image = ImageIO.read(this.getClass().getResourceAsStream("/waiting_adversary.png"));
            g.drawImage(image ,getWidth()/2 - 125 , getHeight()/2 - 25 , 250 , 50, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void paint(Graphics g) {
        super.paint(g);
    }

}

