package engine;

import engine.game_menu_home.DrawingInstructions;
import engine.game_menu_home.DrawingPanelMenu;
import engine.game_menu_home.DrawingInstructions;
import engine.game_party.DrawingPanelParty;
import engine.game_rmi_waiting.DrawingPanelWaitingScreen;
import engine.game_splash_screen.DrawingPanelSplashScreen;
import engine.gamecentury.DrawingPanelCentury;
import engine.menu_bar.Menu;
import model.BattleNavaleController;
import model.BattleNavalePainter;
import engine.gamepositioning.DrawingPanelPositioning;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class GraphicalInterface  {

	/**
	 * le Panel pour l'afficheur
	 */
	private DrawingPanelParty party;
	private DrawingPanelPositioning positioning;
	private DrawingPanelSplashScreen splashScreen;
	private DrawingPanelMenu menuHome;
	private DrawingPanelCentury century;
	private DrawingPanelWaitingScreen waiting;
	private DrawingInstructions instructions;
	private JPanel panel;
	private GameController controller;
	private JFrame f;
	private Menu menu;
	/**
	 * la construction de l'interface graphique: JFrame avec panel pour le game
	 */
	public GraphicalInterface(BattleNavalePainter battleNavalePainter, GameController controller){
		f=new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.controller = controller;

		// attacher le panel contenant l'afficheur du game
		this.positioning = new DrawingPanelPositioning(battleNavalePainter , controller);
		this.party = new DrawingPanelParty(battleNavalePainter, controller);
		this.splashScreen = new DrawingPanelSplashScreen(battleNavalePainter, controller);
		this.menuHome = new DrawingPanelMenu(battleNavalePainter, controller);
		this.century = new DrawingPanelCentury(battleNavalePainter,controller);
		this.waiting = new DrawingPanelWaitingScreen(battleNavalePainter, controller);
		this.instructions=new DrawingInstructions(battleNavalePainter, controller);

        //Affichage plein ecran
		f.setPreferredSize(new Dimension(battleNavalePainter.getScreenWidth() , battleNavalePainter.getScreenHeight()));

		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		//panel.add(this.scorePanel );
		this.panel.add(this.splashScreen);
		f.setContentPane(panel);

		menu = new Menu(controller);
		f.setJMenuBar(menu);
        f.setResizable(false);
		f.pack();
		f.setVisible(true);
		f.getContentPane().setFocusable(true);
		f.getContentPane().requestFocus();
	}


	public void paintParty(boolean over,String s) {
		menu.setVisible(true);
		try {
			if (controller.getBattleNavaleGame().getClient().getServerGame().getMulti())
				menu.disableStateItem();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		this.menu.activateStateItem();
		this.panel.removeAll();
		this.panel.repaint();
		this.panel.add(this.party);
		this.party.drawGame(over,s);
		this.panel.updateUI();
	}

	public void paintPositioning(boolean over,String s) {
		menu.setVisible(true);
		this.menu.disableStateItem();
		this.panel.removeAll();
		this.panel.repaint();
		this.panel.add(this.positioning);
		this.positioning.drawGame();
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


	public void paintWaiting(int interface_number) {
		menu.setVisible(false);
		this.panel.removeAll();
		this.panel.repaint();
		this.panel.add(this.waiting);
		if (interface_number == 1)
			this.waiting.drawWaiting();
		else
			this.waiting.drawReady();
		this.panel.updateUI();
	}


	public void paintCentury() {
		menu.setVisible(false);
		this.panel.removeAll();
		this.panel.repaint();
		this.panel.add(this.century);
		this.century.drawGame();
		this.panel.updateUI();
	}
	public void paintInstructions() {
		menu.setVisible(false);
		this.panel.removeAll();
		this.panel.repaint();
		this.panel.add(this.instructions);
		this.instructions.drawGame();
		this.panel.updateUI();
	}

	public void paintMenu() {
		menu.setVisible(false);
		this.panel.removeAll();
		this.panel.repaint();
		this.panel.add(this.menuHome);
		this.menuHome.drawGame();
		this.panel.updateUI();
	}

}
