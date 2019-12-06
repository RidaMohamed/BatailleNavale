package model;



import engine.GameController;
import model.century_factory.boats.Boat;
import model.global.Constants;
import model.global.Orientation;
import model.global.Position;
import model.global.Turn;
import sun.plugin.liveconnect.OriginNotAllowedException;

import java.awt.event.MouseEvent;

	public class BattleNavaleController implements GameController {

		private BattleNavaleGame battleNavaleGame;
		private Boat selected_boat = null;
		private Position selected_boat_init_pos = null;

		public BattleNavaleController(BattleNavaleGame battleNavaleGame) {
			this.battleNavaleGame = battleNavaleGame;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (battleNavaleGame.isFinished()==0){
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
								System.out.println("not ok");
							}else
								System.out.println("ok");

							break;
						}


					}
				}


			}else if (battleNavaleGame.isFinished() == 1 && battleNavaleGame.getTurn() == Turn.PlayerTurn){
				//if we are playing then the action is attacked player2
				int x = (e.getX() - e.getX() % Constants.CASE_WIDTH )/ Constants.CASE_WIDTH;
				int y = (e.getY() - e.getY() % Constants.CASE_HEIGHT +  Constants.CASE_HEIGHT) / Constants.CASE_HEIGHT;

				if (!(x >= 1 && x <= Constants.WIDTH) || !(y >= 1 && y <= Constants.HEIGHT))
					return;

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