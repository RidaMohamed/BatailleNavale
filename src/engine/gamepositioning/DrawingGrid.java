package engine.gamepositioning;

import model.BattleNavalePainter;
import model.global.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawingGrid extends JPanel {
    private static final long serialVersionUID = 1L;

    private BattleNavalePainter painter;
    private int width, height;

    public DrawingGrid(BattleNavalePainter painter) {
        super();
        this.width = Constants.CASE_WIDTH * Constants.WIDTH;
        this.height = Constants.CASE_HEIGHT * Constants.HEIGHT;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.painter=painter;
    }

    public void draw(BufferedImage im){
        try {
            BufferedImage image1 = ImageIO.read(this.getClass().getResourceAsStream("/sea.jpeg"));
            im.getGraphics().drawImage(image1 ,  325,
                    3 * Constants.CASE_HEIGHT , Constants.CASE_WIDTH * Constants.WIDTH ,
                    Constants.CASE_HEIGHT * Constants.HEIGHT, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.painter.getBoardPositioningPainter().draw(im);
        for ( int x = 1; x <= 10; x++ )
            for ( int y = 1; y <= 10; y ++ ){
                im.getGraphics().setColor(Color.decode("#3498db"));
                im.getGraphics().drawRect((x * 40 )+ 285,
                        y* Constants.CASE_HEIGHT + 2* Constants.CASE_HEIGHT , Constants.CASE_WIDTH , Constants.CASE_HEIGHT);
            }


    }

    public void paint(Graphics g) {
        super.paint(g);
    }
}
