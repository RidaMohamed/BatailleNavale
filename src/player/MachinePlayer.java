package player;

import board.Board;
import centuryFactory.boats.Boat;
import player.strategy.StrategyMahcineAttack;

public class MachinePlayer extends Player {


    public StrategyMahcineAttack strategyMahcineAttack;

    public MachinePlayer(int boatsNumber, Board board , int num ) {
        super(boatsNumber, board, num);
    }


    /**
     * setting the strategy game for
     * the machine player
     * @param strategyMahcineAttack
     */
    public void setStrategy(StrategyMahcineAttack strategyMahcineAttack){

    }
}
