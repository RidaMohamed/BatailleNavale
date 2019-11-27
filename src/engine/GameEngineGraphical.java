package engine;

import engine.painter.Painter;
import model.BattleNavaleGame;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * moteur de game generique.
 * On lui passe un game et un afficheur et il permet d'executer un game.
 */
public class GameEngineGraphical {

	/**
	 * le game a executer
	 */
	private BattleNavaleGame game;

	/**
	 * l'afficheur a utiliser pour le rendu
	 */
	private Painter gamePainter;

	/**
	 * le controlleur a utiliser pour recuperer les commandes
	 */
	private GameController gameController;

	/**
	 * l'interface graphique
	 */
	private GraphicalInterface gui;

	/**
	 * construit un moteur
	 * 
	 * @param game
	 *            game a lancer
	 * @param gamePainter
	 *            afficheur a utiliser
	 * @param gameController
	 *            controlleur a utiliser
	 *            
	 */
	public GameEngineGraphical(BattleNavaleGame game, Painter gamePainter, GameController gameController) {
		// creation du game
		this.game = game;
		this.gamePainter = gamePainter;
		this.gameController = gameController;
		this.gui = new GraphicalInterface((Painter) this.gamePainter,this.gameController);

	}



	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException {

		game.setIsFinished(-3);


		if (game.isFinished() == -3) {
			System.out.println("splash");
			this.gui.paintSplash();
			Thread.sleep(4000);
			game.setIsFinished(1);
		}


		// creation de l'interface graphique

		// boucle de game
		while (this.game.isFinished() >=0 ) {
			// demande controle utilisateur
			//Clicks c = this.gameController.getClicks();
			// fait evoluer le game
			//this.game.evolve(c);
			// affiche le game
			if (this.game.isFinished() == 1)
			this.gui.paintParty(false , "");
			// met en attente
			Thread.sleep(100);
		}
	}

}
