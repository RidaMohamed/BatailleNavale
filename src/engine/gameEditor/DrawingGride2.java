package engine.gameEditor;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 */

import engine.GameController;
import engine.painter.Painter;
import global.Constant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawingGride2 extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Painter painter;
	private int width, height;

	public DrawingGride2(Painter painter , GameController controller) {
		super();
		this.width = 500;
		this.height = 500;
		this.setPreferredSize(new Dimension(this.width, this.height));
		this.painter=painter;
		this.addMouseListener(controller);
	}

	public void draw(BufferedImage im) {


		try {
			BufferedImage advarsayimage = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/advarsary.png"));
			im.getGraphics().drawImage(advarsayimage , Constant.CASE_WIDTH , 3 * Constant.CASE_HEIGHT + 10 + Constant.HEIGHT * Constant.CASE_HEIGHT  , 140 , 30, null);


			BufferedImage image1 = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/sea.jpeg"));
			im.getGraphics().drawImage(image1 , Constant.CASE_WIDTH , 3 * Constant.CASE_HEIGHT  , Constant.CASE_WIDTH * Constant.WIDTH , Constant.CASE_HEIGHT * Constant.HEIGHT, null);
		} catch (IOException e) {
			e.printStackTrace();
		}


		this.painter.getBoard2Painter().draw(im);
		Graphics2D g1 = (Graphics2D)im.getGraphics();

		for ( int x = 1; x <= 10; x++ )
			for ( int y = 1; y <= 10; y ++ ){
				Rectangle2D rect = new Rectangle2D.Double(x* Constant.CASE_WIDTH, y* Constant.CASE_HEIGHT + 2*Constant.CASE_HEIGHT , Constant.CASE_WIDTH , Constant.CASE_HEIGHT);
				g1.draw(rect);
				g1.setColor(Color.decode("#3498db"));
				//g1.fill(rect);

			}

	}

	public void paint(Graphics g) {
		super.paint(g);
	}



}
