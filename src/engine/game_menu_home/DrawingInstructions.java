package engine.game_menu_home;

import engine.DrawingPanel;
import engine.GameController;
import model.BattleNavalePainter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingInstructions extends DrawingPanel {

    private static final long serialVersionUID = 1L;
    private InstructionsPainter intrsuctions;

    public DrawingInstructions(BattleNavalePainter battleNavalePainter, GameController controller) {
        super(battleNavalePainter, controller);
        this.setPreferredSize(new Dimension(battleNavalePainter.getScreenWidth(), battleNavalePainter.getScreenHeight()));

        this.intrsuctions = new InstructionsPainter(battleNavalePainter);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(this.intrsuctions );

        this.currentImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        this.nextImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void drawGame() {
        this.intrsuctions.draw(this.nextImage);
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
