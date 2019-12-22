package engine.result_party;

import engine.DrawingPanel;
import engine.GameController;
import model.BattleNavalePainter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawingPanelResult extends DrawingPanel {

    private static final long serialVersionUID = 1L;
    private  BufferedImage im;
    private BattleNavalePainter painter;

    public DrawingPanelResult(BattleNavalePainter battleNavalePainter, GameController controller) {
        super(battleNavalePainter, controller);
        this.setPreferredSize(new Dimension(battleNavalePainter.getScreenWidth(), battleNavalePainter.getScreenHeight()));
        this.painter = battleNavalePainter;
        this.currentImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        this.nextImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
    }

     @Override
    public void drawGame() {
         try {
             if (this.controller.getBattleNavaleGame().getHumanPlayer().getPv() == 0)
                im = ImageIO.read(this.getClass().getResourceAsStream("/over.jpg"));
             else
                 im = ImageIO.read(this.getClass().getResourceAsStream("/win.jpg"));
             this.nextImage.getGraphics().drawImage(im ,0 ,0 , this.painter.getScreenWidth() , this.painter.getScreenHeight(), null);

         } catch (IOException e) {
             e.printStackTrace();
         }

         // inverses les images doublebuffereing
         BufferedImage temp = this.currentImage;
         // l'image a dessiner est celle qu'on a construite
         this.currentImage = this.nextImage;
         // l'ancienne image est videe
         this.nextImage = temp;
         this.nextImage.getGraphics()
                 .fillRect(0, 0, this.width, this.height);
         // met a jour l'image a afficher sur le panel
         this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
