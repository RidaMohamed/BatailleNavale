package engine.game_party;

import model.BattleNavalePainter;
import model.global.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawingGride1 extends JPanel {

	private static final long serialVersionUID = 1L;

	private BattleNavalePainter battleNavalePainter;
	private int width, height;

	public DrawingGride1(BattleNavalePainter battleNavalePainter) {
		super();
		this.width = Constants.CASE_WIDTH * Constants.WIDTH;
		this.height = Constants.CASE_HEIGHT * Constants.HEIGHT;
		this.setPreferredSize(new Dimension(this.width, this.height));
		this.battleNavalePainter = battleNavalePainter;
	}

	public void draw(BufferedImage im) {
		try {
			BufferedImage youImage = ImageIO.read(this.getClass().getResourceAsStream("/you.png"));
			im.getGraphics().drawImage(youImage , 5* Constants.CASE_WIDTH + Constants.CASE_WIDTH * Constants.WIDTH,
					3 * Constants.CASE_HEIGHT + 10 + Constants.HEIGHT * Constants.CASE_HEIGHT  , 90 , 25 , null);

			BufferedImage image1 = ImageIO.read(this.getClass().getResourceAsStream("/sea.jpeg"));
			im.getGraphics().drawImage(image1 , 5* Constants.CASE_WIDTH + Constants.CASE_WIDTH * Constants.WIDTH,
					3 * Constants.CASE_HEIGHT , Constants.CASE_WIDTH * Constants.WIDTH ,
					Constants.CASE_HEIGHT * Constants.HEIGHT, null);
			ImageIO.setUseCache(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.battleNavalePainter.getBoard1Panter().draw(im);

		for ( int x = 1; x <= 10; x++ )
			for ( int y = 1; y <= 10; y ++ ){
				im.getGraphics().setColor(Color.decode("#3498db"));
				im.getGraphics().drawRect((x + 4 )* Constants.CASE_WIDTH + Constants.CASE_WIDTH * Constants.WIDTH,
						y* Constants.CASE_HEIGHT + 2* Constants.CASE_HEIGHT , Constants.CASE_WIDTH , Constants.CASE_HEIGHT);
			}
	}

	public void paint(Graphics g) {
		super.paint(g);
	}
}