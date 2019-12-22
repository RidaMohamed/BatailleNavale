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
import rmi.client.BattleNavaleClient;

import javax.naming.NamingException;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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

			if (battleNavaleGame.isFinished() == -2) {
				if (Constants.rect_oneplayer.contains(e.getPoint())) {
					this.battleNavaleGame.setMulti(false);
					getBattleNavaleGame().initialize();
					getBattleNavaleGame().setIsFinished(-1);
				} else if (Constants.rect_multiplayer.contains(e.getPoint())) {

					System.out.println("Multiplayer");
					this.battleNavaleGame.setMulti(true);

					try {
						this.battleNavaleGame.getClient().init();

						if (this.getBattleNavaleGame().getClient().getServerGame().getPlayer2() == null)
							this.battleNavaleGame.setPlayerId(1);
						else {
							this.battleNavaleGame.setPlayerId(2);
						}

						this.battleNavaleGame.setIsFinished(this.getBattleNavaleGame().getClient().getServerGame().isFinished());


					} catch (NamingException ex) {
						ex.printStackTrace();
					} catch (RemoteException ex) {
						ex.printStackTrace();
					} catch (NotBoundException ex) {
						ex.printStackTrace();
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}


				} else if (Constants.rect_load.contains(e.getPoint())) {
					System.out.println("Load");
					try {
						getBattleNavaleGame().getPlayer1().getBoard().getBoats().clear();
						getBattleNavaleGame().getMachinePlayer().getBoard().getBoats().clear();
						getBattleNavaleGame().getFileManager().load();
						getBattleNavaleGame().setIsFinished(1);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else if (Constants.rect_quit.contains(e.getPoint())) {
					System.exit(0);
				} else if (Constants.rect_retour.contains(e.getPoint())) {
				     System.out.println("retour");
				     getBattleNavaleGame().setIsFinished(-2);
			    }
			}else if (battleNavaleGame.isFinished() == -1) {
					if (Constants.rect_xxcentury.contains(e.getPoint())) {
						System.out.println("oui");
						battleNavaleGame.setCentury(new BoatFactoryXXCentury());
						battleNavaleGame.setIsFinished(0);
					} else if (Constants.rect_xvcentury.contains(e.getPoint())) {
						battleNavaleGame.setCentury(new BoatFactoryXVCentury());
						battleNavaleGame.setIsFinished(0);
					}

					battleNavaleGame.createBoats();

				} else if (battleNavaleGame.isFinished() == 0) {
					if (Constants.rect_random.contains(e.getPoint())) {
						battleNavaleGame.moveBoats();
					} else if (Constants.rect_ok.contains(e.getPoint())) {
						if (battleNavaleGame.getMulti()) {
							try {
								battleNavaleGame.getClient().getServerGame().addReadyPlayer();
								battleNavaleGame.setIsFinished(5);

							} catch (RemoteException ex) {
								ex.printStackTrace();
							}
						} else
							battleNavaleGame.setIsFinished(1);
					} else {
						int x = (e.getX() - e.getX() % Constants.CASE_WIDTH) / Constants.CASE_WIDTH - 7;
						int y = (e.getY() - e.getY() % Constants.CASE_HEIGHT + Constants.CASE_HEIGHT) / Constants.CASE_HEIGHT - 3;

						for (Boat boat : battleNavaleGame.getPlayer1().getBoard().getBoats()) {
							if (boat.isOnCase(x, y)) {
								Orientation current_orientation = boat.getOrientation();
								if (current_orientation == Orientation.HORIZONTAL)
									boat.setOrientation(Orientation.VERTICAL);
								else
									boat.setOrientation(Orientation.HORIZONTAL);

								if (!battleNavaleGame.getPlayer1().getBoard().isPosOk(boat)) {
									boat.setOrientation(current_orientation);
									System.out.println("not ok");
								} else
									System.out.println("ok");

								break;
							}
						}
					}


				} else if (!battleNavaleGame.getMulti() && battleNavaleGame.isFinished() == 1 && battleNavaleGame.getTurn() == Turn.PLAYER1) {
					//if we are playing then the action is attacked player2
					int x = (e.getX() - e.getX() % Constants.CASE_WIDTH) / Constants.CASE_WIDTH;
					int y = (e.getY() - e.getY() % Constants.CASE_HEIGHT + Constants.CASE_HEIGHT) / Constants.CASE_HEIGHT;

					if (!(x >= 1 && x <= Constants.WIDTH) || !(y >= 1 && y <= Constants.HEIGHT))
						return;

					try {
						battleNavaleGame.getPlayer1().attack(x, y);
					} catch (RemoteException ex) {
						ex.printStackTrace();
					}

					if (battleNavaleGame.getTurn() == Turn.PLAYER2) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						try {
							this.battleNavaleGame.getMachinePlayer().attack();
						} catch (RemoteException ex) {
							ex.printStackTrace();
						}
					}

				} else {
					try {
						System.out.println("attack ");
						System.out.println("playerId = " + battleNavaleGame.getPlayerId());
						System.out.println("Turn server  = " + battleNavaleGame.getClient().getServerGame().getTurn());
						System.out.println(battleNavaleGame.getClient().getServerGame().isFinished());

						if (battleNavaleGame.getMulti() && battleNavaleGame.getClient().getServerGame().isFinished() == 1 && ((battleNavaleGame.getClient().getServerGame().getTurn() == Turn.PLAYER1 && battleNavaleGame.getPlayerId() == 1) || (battleNavaleGame.getClient().getServerGame().getTurn() == Turn.PLAYER2 && battleNavaleGame.getPlayerId() == 2))) {
							//if we are playing then the action is attacked player2
							System.out.println("Attaque");

							int x = (e.getX() - e.getX() % Constants.CASE_WIDTH) / Constants.CASE_WIDTH;
							int y = (e.getY() - e.getY() % Constants.CASE_HEIGHT + Constants.CASE_HEIGHT) / Constants.CASE_HEIGHT;

							if (!(x >= 1 && x <= Constants.WIDTH) || !(y >= 1 && y <= Constants.HEIGHT))
								return;

							try {
								if (battleNavaleGame.getPlayerId() == 1)
									battleNavaleGame.getClient().getServerGame().attack_multi(battleNavaleGame.getPlayerId(), x, y);
								else
									battleNavaleGame.getClient().getServerGame().attack_multi(battleNavaleGame.getPlayerId(), x, y);
							} catch (RemoteException ex) {
								ex.printStackTrace();
							}


						}
					} catch (RemoteException ex) {
						ex.printStackTrace();
					}
				}
			}

	  @Override
	   public void mousePressed(MouseEvent e) {
	}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(selected_boat != null && selected_boat_init_pos != null){
				if (!getBattleNavaleGame().getPlayer1().getBoard().isPosOk(selected_boat)){
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
				for (Boat boat : getBattleNavaleGame().getPlayer1().getBoard().getBoats()){
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