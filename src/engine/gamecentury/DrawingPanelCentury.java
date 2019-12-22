package engine.gamecentury;

import engine.DrawingPanel;
import engine.GameController;
import engine.game_menu_home.Retour;
import model.BattleNavalePainter;
import model.global.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawingPanelCentury extends DrawingPanel {
    private XXCenturyButton xxCenturyButton;
    private XVCenturyButton xvCenturyButton;
    private Retour retourbutton;
    /**
     * constructeur Il construit les images pour doublebuffering ainsi que le
     * Panel associe. Les images stockent le battleNavalePainter et on demande au panel la
     * mise a jour quand le battleNavalePainter est fini
     *
     * @param Painter
     * @param controller
     */
    public DrawingPanelCentury(BattleNavalePainter Painter, GameController controller) {
        super(Painter, controller);
        this.xvCenturyButton = new XVCenturyButton();
        this.xxCenturyButton = new XXCenturyButton();
        this.retourbutton = new Retour();
        this.addMouseListener(controller);
    }

    @Override
    protected void paintComponent(Graphics g){super.paintComponent(g);}

    @Override
    public void drawGame() {
        try {
            BufferedImage image1 = ImageIO.read(this.getClass().getResourceAsStream("/century.jpg"));
            this.nextImage.getGraphics().drawImage(image1 , 0 , 0 , width , height , null);

            BufferedImage image2 = ImageIO.read(this.getClass().getResourceAsStream("/century_selection.png"));
            this.nextImage.getGraphics().drawImage(image2 ,width/2 - 125 ,50 , 250 , 50, null);

            ImageIO.setUseCache(false);

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            BufferedImage xxcent = ImageIO.read(this.getClass().getResourceAsStream("/xvcenturybtn.png"));
            this.nextImage.getGraphics().drawImage(xxcent ,   (int) Constants.rect_xxcentury.x, (int) Constants.rect_xxcentury.y, (int)Constants.rect_xxcentury.width,(int) Constants.rect_xxcentury.height , null);
            ImageIO.setUseCache(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedImage xvcent = ImageIO.read(this.getClass().getResourceAsStream("/xxcenturybtn.png"));
            this.nextImage.getGraphics().drawImage(xvcent , (int) Constants.rect_xvcentury.x, (int) Constants.rect_xvcentury.y, (int)Constants.rect_xvcentury.width,(int) Constants.rect_xvcentury.height , null);
            ImageIO.setUseCache(false);
        } catch (IOException e) {
            e.printStackTrace();
        }try {
            BufferedImage xvcent = ImageIO.read(this.getClass().getResourceAsStream("/retour.png"));
            this.nextImage.getGraphics().drawImage(xvcent ,(int) Constants.rect_retour.x, (int) Constants.rect_retour.y, (int)Constants.rect_retour.width,(int) Constants.rect_retour.height , null);
            ImageIO.setUseCache(false);
        } catch (IOException e) {
            e.printStackTrace();
        }




        // inverses les images doublebuffereing
        BufferedImage temp = this.currentImage;
        // l'image a dessiner est celle qu'on a construite
        this.currentImage = this.nextImage;
        // l'ancienne image est videe
        this.nextImage = temp;
        this.nextImage.getGraphics().fillRect(0, 0, this.width, this.height);
        // met a jour l'image a afficher sur le panel
        this.repaint();
        //this.paintComponent(this.nextImage.getGraphics());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
