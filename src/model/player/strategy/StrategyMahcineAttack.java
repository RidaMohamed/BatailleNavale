package model.player.strategy;

import model.BattleNavaleGame;
import model.player.MachinePlayer;

import java.rmi.RemoteException;

public interface StrategyMahcineAttack {

    public void attack(MachinePlayer machinePlayer) throws RemoteException;
    BattleNavaleGame battleNavaleGame = null;
}
