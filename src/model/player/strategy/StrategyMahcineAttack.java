package model.player.strategy;

import model.BattleNavaleGame;
import model.player.MachinePlayer;

public interface StrategyMahcineAttack {

    BattleNavaleGame battleNavaleGame = null;
    public void attack(MachinePlayer machinePlayer);
}
