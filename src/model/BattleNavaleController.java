package model;



import engine.GameController;
import model.global.Constant;
import model.global.Position;
import model.global.Turn;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

	public class BattleNavaleController implements GameController {

		private BattleNavaleGame battleNavaleGame;

		public BattleNavaleController(BattleNavaleGame battleNavaleGame) {
			this.battleNavaleGame = battleNavaleGame;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (battleNavaleGame.isFinished() == 1 && battleNavaleGame.getTurn() == Turn.PlayerTurn){
				//if we are playing then the action is attacked player2
				int x = (e.getX() - e.getX() % Constant.CASE_WIDTH )/ Constant.CASE_WIDTH;
				int y = (e.getY() - e.getY() % Constant.CASE_HEIGHT +  Constant.CASE_HEIGHT) / Constant.CASE_HEIGHT;

				if (!(x >= 1 && x <= Constant.WIDTH) || !(y >= 1 && y <= Constant.HEIGHT))
					return;

				battleNavaleGame.getHumanPlayer().attack(x , y);

				if (battleNavaleGame.getTurn() == Turn.MachineTurn) {
					try {
						Thread.sleep(2000);
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