package engine.gameMenu;

import engine.DrawingPanel;
import engine.GameController;
import engine.painter.Painter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingPanelSplashScreen extends DrawingPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private SplashScreenPainter grideMenu;

    public DrawingPanelSplashScreen(Painter painter , GameController controller) {
        super(painter, controller);
        this.setPreferredSize(new Dimension(painter.getScreenWidth(), painter.getScreenHeight()));

        this.grideMenu = new SplashScreenPainter(painter);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));


        add(this.grideMenu );

        this.currentImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        this.nextImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);


    }

     @Override
    public void drawGame() {
         this.grideMenu.draw(this.nextImage);

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
