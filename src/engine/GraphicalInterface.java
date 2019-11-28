package engine;

import engine.gameMenu.DrawingPanelSplashScreen;
import engine.gameParty.DrawingPanelParty;
import engine.painter.Painter;

import javax.swing.*;
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
	private DrawingPanelSplashScreen splashScreen;
	private JPanel panel;
    private JFrame f;
    private Menu menu;
	/**
	 * la construction de l'interface graphique: JFrame avec panel pour le game
	 * 
	 *
	 */
	public GraphicalInterface(Painter painter, GameController controller){
		f=new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		// attacher le panel contenant l'afficheur du game
		this.party=new DrawingPanelParty(painter , controller);
		this.splashScreen = new DrawingPanelSplashScreen(painter, controller);


        //Affichage plein ecran

		f.setPreferredSize(new Dimension(painter.getScreenWidth() , painter.getScreenHeight()));

     	//f.setUndecorated(true);

		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		//panel.add(this.scorePanel );
		this.panel.add(this.splashScreen);
		f.setContentPane(panel);
		f.setResizable(false);

        menu = new Menu();
        f.setJMenuBar(menu);

		f.pack();
		f.setVisible(true);
		f.getContentPane().setFocusable(true);
		f.getContentPane().requestFocus();
	}
	



	public void paintParty(boolean over,String s) {
		menu.setVisible(true);
		this.panel.removeAll();
		this.panel.repaint();
		this.panel.add(this.party);
		this.party.drawGame();
		this.panel.updateUI();


	}

	public void paintSplash() {
		menu.setVisible(false);
		this.panel.removeAll();
		this.panel.repaint();
		this.panel.add(this.splashScreen);
		this.splashScreen.drawGame();
		this.panel.updateUI();

	}




}
