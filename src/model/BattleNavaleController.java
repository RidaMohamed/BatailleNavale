package model;



import engine.GameController;
import engine.game_menu_home.DrawingInstructions;
import engine.game_menu_home.DrawingPanelMenu;
import model.century_factory.boats.Boat;
import model.century_factory.BoatFactoryXVCentury;
import model.century_factory.BoatFactoryXXCentury;
import model.global.Constants;
import model.global.Orientation;
import model.global.Position;
import model.global.Turn;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class BattleNavaleController implements GameController {
		private BattleNavaleGame battleNavaleGame;
		private Boat selected_boat = null;
		private Position selected_boat_init_pos = null;
		private BufferedImage img;
		//private DrawingInstructions;

	/**
	 * Simple constructor game controller
	 * @param battleNavaleGame
	 */
		public BattleNavaleController(BattleNavaleGame battleNavaleGame) {
			this.battleNavaleGame = battleNavaleGame;
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			if(battleNavaleGame.isFinished() == -2){
				if (Constants.rect_oneplayer.contains(e.getPoint())){
					getBattleNavaleGame().initialize();
					getBattleNavaleGame().setIsFinished(-1);
				}
				else if(Constants.rect_multiplayer.contains(e.getPoint())){
				}else if(Constants.rect_retour.contains(e.getPoint())){
					System.out.println("retour");
					getBattleNavaleGame().setIsFinished(-2);
				}
				else if(Constants.rect_instructions.contains(e.getPoint())){
					//getBattleNavaleGame().initialize();
					getBattleNavaleGame().setIsFinished(-5);
				}

				else if(Constants.rect_load.contains(e.getPoint())){
					try {
						getBattleNavaleGame().getHumanPlayer().getBoard().getBoats().clear();
						getBattleNavaleGame().getMachinePlayer().getBoard().getBoats().clear();
						getBattleNavaleGame().getFileManager().load();
					    getBattleNavaleGame().setIsFinished(1);
					} catch (IOException e1) {
						e1.printStackTrace();
				}
			}
			else if(Constants.rect_quit.contains(e.getPoint())){
				System.exit(0);
			}
			}
			else if(battleNavaleGame.isFinished()==-1){
				if(Constants.rect_xxcentury.contains(e.getPoint())) {
                    battleNavaleGame.setCentury(new BoatFactoryXXCentury());
                    battleNavaleGame.setIsFinished(0);
                }
                else if(Constants.rect_xvcentury.contains(e.getPoint())) {
                    battleNavaleGame.setCentury(new BoatFactoryXVCentury());
                    battleNavaleGame.setIsFinished(0);
                }
				getBattleNavaleGame().createBoats();
			}
			else if (battleNavaleGame.isFinished()==0){
				if(Constants.rect_random.contains(e.getPoint())){
					battleNavaleGame.moveBoats();
				}else if(Constants.rect_ok.contains(e.getPoint())){
					battleNavaleGame.setIsFinished(1);
				}else {
					int x = (e.getX() - e.getX() % Constants.CASE_WIDTH) / Constants.CASE_WIDTH - 7;
					int y = (e.getY() - e.getY() % Constants.CASE_HEIGHT + Constants.CASE_HEIGHT) / Constants.CASE_HEIGHT - 3;

					for (Boat boat : battleNavaleGame.getHumanPlayer().getBoard().getBoats()) {
						if (boat.isOnCase(x, y)) {
							Orientation current_orientation = boat.getOrientation();
							if (current_orientation == Orientation.HORIZONTAL)
								boat.setOrientation(Orientation.VERTICAL);
							else
								boat.setOrientation(Orientation.HORIZONTAL);

							if (!battleNavaleGame.getHumanPlayer().getBoard().isPosOk(boat)) {
								boat.setOrientation(current_orientation);
							}

							break;
						}
					}
				}


			}
			else if (battleNavaleGame.isFinished() == 1 && battleNavaleGame.getTurn() == Turn.PlayerTurn){
				//if we are playing then the action is attacked player2
				int x = (e.getX() - e.getX() % Constants.CASE_WIDTH )/ Constants.CASE_WIDTH;
				int y = (e.getY() - e.getY() % Constants.CASE_HEIGHT +  Constants.CASE_HEIGHT) / Constants.CASE_HEIGHT;

				if (!(x >= 1 && x <= Constants.WIDTH) || !(y >= 1 && y <= Constants.HEIGHT))
					return;
				//calling attack human after getting the human
				battleNavaleGame.getHumanPlayer().attack(x , y);
				if (battleNavaleGame.getTurn() == Turn.MachineTurn) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					this.battleNavaleGame.getMachinePlayer().attack();
				}
			}
		}

	@Override
	public void mousePressed(MouseEvent e) {
	}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(selected_boat != null && selected_boat_init_pos != null){
				if (!getBattleNavaleGame().getHumanPlayer().getBoard().isPosOk(selected_boat)){
					selected_boat.setPostion(selected_boat_init_pos.getX() , selected_boat_init_pos.getY());
				}
				selected_boat = null;
				selected_boat_init_pos = null;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		public BattleNavaleGame getBattleNavaleGame() {
			return battleNavaleGame;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			int x = (e.getX() - e.getX() % Constants.CASE_WIDTH) / Constants.CASE_WIDTH - 7;
			int y = (e.getY() - e.getY() % Constants.CASE_HEIGHT + Constants.CASE_HEIGHT) / Constants.CASE_HEIGHT - 3;

			if (selected_boat == null){
				for (Boat boat : getBattleNavaleGame().getHumanPlayer().getBoard().getBoats()){
					if (boat.isOnCase(x , y)) {
						selected_boat_init_pos = boat.getPosition();
						selected_boat = boat;
						break;
					}
				}
			}else {
				selected_boat.setPostion(x, y);
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}