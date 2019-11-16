package player;

import board.Board;
import centuryFactory.boats.Boat;
import engine.Game;
import player.strategy.StrategyMahcineAttack;

public class MachinePlayer extends Player {


    public StrategyMahcineAttack strategyMahcineAttack;

    public MachinePlayer(Game game) {
        super(game);
    }

    /**
     * setting the strategy game for
     * the machine player
     * @param strategyMahcineAttack
     */
    public void setStrategy(StrategyMahcineAttack strategyMahcineAttack){ }
}
