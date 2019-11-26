package engine.gameParty;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 */

import engine.painter.Painter;
import global.Constant;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawingGride1 extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Painter painter;
	private int width, height;

	public DrawingGride1(Painter painter) {
		super();
		this.width = 500;
		this.height = 500;
		this.setPreferredSize(new Dimension(this.width, this.height));
		this.painter=painter;
	}

	public void draw(BufferedImage im) {

		try {

			BufferedImage youImage = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/you.png"));
			im.getGraphics().drawImage(youImage , 5* Constant.CASE_WIDTH + Constant.CASE_WIDTH * Constant.WIDTH, 3 * Constant.CASE_HEIGHT + 10 + Constant.HEIGHT * Constant.CASE_HEIGHT  , 90 , 25 , null);


			BufferedImage image1 = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/sea.jpeg"));
			im.getGraphics().drawImage(image1 , 5* Constant.CASE_WIDTH + Constant.CASE_WIDTH * Constant.WIDTH, 3 * Constant.CASE_HEIGHT , Constant.CASE_WIDTH * Constant.WIDTH , Constant.CASE_HEIGHT * Constant.HEIGHT, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.painter.getBoard1Panter().draw(im);


		for ( int x = 1; x <= 10; x++ )
			for ( int y = 1; y <= 10; y ++ ){
				im.getGraphics().setColor(Color.decode("#3498db"));
				im.getGraphics().drawRect((x + 4 )* Constant.CASE_WIDTH + Constant.CASE_WIDTH * Constant.WIDTH, y* Constant.CASE_HEIGHT + 2*Constant.CASE_HEIGHT , Constant.CASE_WIDTH , Constant.CASE_HEIGHT);

			}

		/*addMouseMotionListener(
				new MouseMotionAdapter() {

					public void mouseDragged(MouseEvent e) {

						if (e.getX()>=440 ||e.getX()<40 || e.getY()<40 || e.getY() >=440)
							return;
					}

				});
*/

	}

	public void paint(Graphics g) {
		super.paint(g);
	}

}
