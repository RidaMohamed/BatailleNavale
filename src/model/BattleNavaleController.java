package model;



import engine.GameController;
import model.global.Constants;
import model.global.Turn;

import java.awt.event.MouseEvent;

	public class BattleNavaleController implements GameController {

		private BattleNavaleGame battleNavaleGame;

		public BattleNavaleController(BattleNavaleGame battleNavaleGame) {
			this.battleNavaleGame = battleNavaleGame;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (battleNavaleGame.isFinished()==0){
				if(Constants.rect_random.contains(e.getPoint())){
					System.out.println("oui");
					try {
						battleNavaleGame.moveBoats();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
				if(Constants.rect_ok.contains(e.getPoint())){
					battleNavaleGame.setIsFinished(1);
				}
			}
			if (battleNavaleGame.isFinished() == 1 && battleNavaleGame.getTurn() == Turn.PlayerTurn){
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
	}