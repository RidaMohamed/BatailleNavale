package engine.game_menu_home;

import engine.GameController;
import model.BattleNavalePainter;

import model.BattleNavalePainter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class InstructionsPainter extends JPanel {

    private static final long serialVersionUID = 1L;
    private BattleNavalePainter battleNavalePainter;
    private Retour retourbutton;


    public InstructionsPainter(BattleNavalePainter battleNavalePainter, GameController controller){
        super();
        this.setPreferredSize(new Dimension(battleNavalePainter.getScreenWidth(), battleNavalePainter.getScreenHeight()));
        this.battleNavalePainter = battleNavalePainter;
        this.retourbutton=new Retour();
        this.addMouseListener(controller);
    }

    public void draw(BufferedImage im){
        Graphics g = im.getGraphics();
        try {
            BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream("/background.jpg"));
            g.drawImage(image ,0 ,0 , battleNavalePainter.getScreenWidth() , battleNavalePainter.getScreenHeight(), null);
            image = ImageIO.read(this.getClass().getResourceAsStream("/inst1.png"));
            g.drawImage(image ,50 ,50 , 950 , 150, null);
            image = ImageIO.read(this.getClass().getResourceAsStream("/inst2.png"));
            g.drawImage(image ,50 ,200 , 950 , 75, null);
            image = ImageIO.read(this.getClass().getResourceAsStream("/inst3.png"));
            g.drawImage(image ,50 ,300 , 500 , 150, null);
            ImageIO.setUseCache(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        retourbutton.paint(im.getGraphics());

    }

    public void paint(Graphics g) {
        super.paint(g);
    }

}

