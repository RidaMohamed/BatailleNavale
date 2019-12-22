package engine.game_party;


import engine.GameController;
import model.BattleNavalePainter;
import model.global.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawingGride2 extends JPanel {

	private static final long serialVersionUID = 1L;

	private BattleNavalePainter battleNavalePainter;
	private int width, height;

	public DrawingGride2(BattleNavalePainter battleNavalePainter, GameController controller) {
		super();
		this.width = 500;
		this.height = 500;
		this.setPreferredSize(new Dimension(this.width, this.height));
		this.battleNavalePainter = battleNavalePainter;
	}

	public void draw(BufferedImage im) {
		try {
			BufferedImage advarsayimage = ImageIO.read(this.getClass().getResourceAsStream("/advarsary.png"));
			im.getGraphics().drawImage(advarsayimage , Constants.CASE_WIDTH , 3 * Constants.CASE_HEIGHT + 10 +
					Constants.HEIGHT * Constants.CASE_HEIGHT  , 140 , 30, null);

			BufferedImage image1 = ImageIO.read(this.getClass().getResourceAsStream("/sea.jpeg"));
			im.getGraphics().drawImage(image1 , Constants.CASE_WIDTH , 3 * Constants.CASE_HEIGHT  ,
					Constants.CASE_WIDTH * Constants.WIDTH , Constants.CASE_HEIGHT * Constants.HEIGHT, null);
			ImageIO.setUseCache(false);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.battleNavalePainter.getBoard2Painter().draw(im);
		Graphics2D g1 = (Graphics2D)im.getGraphics();

		for ( int x = 1; x <= 10; x++ )
			for ( int y = 1; y <= 10; y ++ ){
				Rectangle2D rect = new Rectangle2D.Double(x* Constants.CASE_WIDTH, y* Constants.CASE_HEIGHT + 2* Constants.CASE_HEIGHT , Constants.CASE_WIDTH , Constants.CASE_HEIGHT);
				g1.draw(rect);
				g1.setColor(Color.decode("#3498db"));
			}
	}
	public void paint(Graphics g) {
		super.paint(g);
	}
}