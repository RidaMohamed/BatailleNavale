package model.player.strategy;

import model.BattleNavaleGame;
import model.player.MachinePlayer;

public class MachineCrossAttack implements StrategyMahcineAttack {
    private BattleNavaleGame battleNavaleGame;

    public MachineCrossAttack(BattleNavaleGame battleNavaleGame){
        this.battleNavaleGame = battleNavaleGame;
    }

    @Override
    public void attack() {

    }
}
