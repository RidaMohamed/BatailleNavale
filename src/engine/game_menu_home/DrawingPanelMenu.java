package engine.game_menu_home;

import engine.DrawingPanel;
import engine.GameController;
import model.BattleNavalePainter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawingPanelMenu extends DrawingPanel {
    private static final long serialVersionUID = 1L;
    private DrawingMenu drawingMenu;
    private BufferedImage logo = null;

    public DrawingPanelMenu(BattleNavalePainter painter, GameController controller) {
        super(painter, controller);
        this.setPreferredSize(new Dimension(this.width, this.height));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));

        this.drawingMenu = new DrawingMenu(painter);
        this.drawingMenu.addMouseListener(controller);
        this.drawingMenu.addMouseMotionListener(controller);

        panel.add(this.drawingMenu);
        add(panel);

        this.currentImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        this.nextImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void drawGame(){
        try {
            BufferedImage image1 = ImageIO.read(this.getClass().getResourceAsStream("/background_menu.jpg"));
            logo = ImageIO.read(this.getClass().getResourceAsStream("/logo.png"));
            this.nextImage.getGraphics().drawImage(image1 , 0 , 0 , width - 15 , height - 40 , null);
            this.nextImage.getGraphics().drawImage(logo , 650 , 80 , 250 , 120 , null);
            ImageIO.setUseCache(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.drawingMenu.draw(nextImage);
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
