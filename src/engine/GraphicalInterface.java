package engine;

import engine.gameEditor.DrawingPanelEditor;
import engine.gameParty.DrawingPanelParty;
import engine.painter.Painter;

import javax.swing.JFrame;
import java.awt.*;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * interface graphique avec son controller et son afficheur
 *
 */
public class GraphicalInterface  {

	/**
	 * le Panel pour l'afficheur
	 */
	private DrawingPanelParty party;
	private DrawingPanelEditor editor;

	/**
	 * la construction de l'interface graphique: JFrame avec panel pour le game
	 * 
	 * @param gamePainter l'afficheur a utiliser dans le moteur
	 * @param gameController l'afficheur a utiliser dans le moteur
	 * 
	 */
	public GraphicalInterface(Painter painter, GameController controller){
		JFrame f=new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		painter.setGetScreenHeight((int) dim.getHeight());
		painter.setScreenWidth((int) dim.getWidth());


		// attacher le panel contenant l'afficheur du game
		this.party=new DrawingPanelParty(painter , controller);
		this.editor = new DrawingPanelEditor(painter , controller);
		f.setContentPane(this.editor);

		// attacher controller au panel du game
		this.party.addKeyListener(controller);
		
        //Affichage plein ecran

		GraphicsEnvironment env =
				GraphicsEnvironment.getLocalGraphicsEnvironment();
		f.setExtendedState(f.getExtendedState() | f.MAXIMIZED_BOTH);

     	f.setUndecorated(true);


		f.pack();
		f.setVisible(true);
		f.getContentPane().setFocusable(true);
		f.getContentPane().requestFocus();
	}
	
	/**
	 * mise a jour du dessin
	 */
	public void paint() {
		this.editor.drawGame();
	}
	
}
