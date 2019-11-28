package engine.gameParty;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 */

import engine.DrawingPanel;
import engine.GameController;
import engine.painter.Painter;
import model.global.Constant;
import model.global.Turn;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawingPanelParty extends DrawingPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private DrawingGride1 gride1;
	private  DrawingGride2 gride2;
	private  DrawingScore score;


	public DrawingPanelParty(Painter painter , GameController controller ) {
		super(painter, controller);
		this.setPreferredSize(new Dimension(this.width, this.height));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


		score = new DrawingScore(painter, controller);

        add(score);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		this.gride1=new DrawingGride1(painter);
		this.gride2= new DrawingGride2(painter ,controller);
		this.gride2.addMouseListener(controller);




		panel.add(this.gride2);
		panel.add(this.gride1 );
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
	public void drawGame() {
		try {
			BufferedImage image1 = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/back.jpg"));
			this.nextImage.getGraphics().drawImage(image1 , 0 , 0 , width , height , null);
		} catch (IOException e) {
			e.printStackTrace();
		}


		this.score.drawGame(this.nextImage);

		this.gride2.draw(this.nextImage);

		try {
			BufferedImage image2 = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/left_direction.png"));
			if (controller.getBattleNavaleGame().getTurn() == Turn.PlayerTurn)
				image2 = mirror(image2);
			this.nextImage.getGraphics().drawImage(image2 , 2* Constant.CASE_WIDTH + Constant.CASE_WIDTH * Constant.WIDTH, 7 * Constant.CASE_HEIGHT , Constant.CASE_WIDTH * 2 , Constant.CASE_HEIGHT * 2, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.gride1.draw(this.nextImage);


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
		//this.paintComponent(this.nextImage.getGraphics());
	}


	public BufferedImage mirror(BufferedImage image){
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		image = op.filter(image, null);
		return image;
	}



	@Override
	public void paint(Graphics g) {
		super.paint(g);


	}

}
