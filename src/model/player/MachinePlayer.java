package model.player;

import model.BattleNavaleGame;
import model.centuryFactory.boats.Boat;
import model.global.Orientation;
import model.global.Position;
import model.global.Turn;
import model.player.strategy.StrategyMahcineAttack;

import java.util.List;

public class MachinePlayer extends Player {


    public StrategyMahcineAttack strategyMahcineAttack;

    public MachinePlayer(BattleNavaleGame game, StrategyMahcineAttack strategyMahcineAttack) {
        super(game);
        this.strategyMahcineAttack = strategyMahcineAttack;
    }

    /**
     * setting the strategy game for
     * the machine model.player
     * @param strategyMahcineAttack
     */
    public void setStrategy(StrategyMahcineAttack strategyMahcineAttack){

        this.strategyMahcineAttack = strategyMahcineAttack;
    }

    /**
     * Attack boats of Human player
     */

    public void attack(){

        this.strategyMahcineAttack.attack();
        game.setTurn(Turn.PlayerTurn);
    }

    /**
     * Methode to get Mahcine model.player data
     * Boats positions
     */
    public StringBuilder getData(){
        StringBuilder str = new StringBuilder();
        List<Boat> boatList = this.board.getBoats();
        //boats numbers
        str.append("boats numbres," + String.valueOf(boatList.size()) + "\n");
        for (Boat b: boatList) {
            int u ;
            if (b.getOrientation() == Orientation.HORIZONTAL)
                u = 1;
            else
                u = 0 ;
            str.append(
                    u + "," +
                    b.getBoatHealth() + "," +
                    String.valueOf(b.getSize()) + "\n"
            );

            List<Position> l = b.getCases();
            str.append("PosLast," + String.valueOf(b.getCases().size()) + "\n");
            for (int i = 0 ; i < l.size(); i++)
                str.append(l.get(i).getX() + "/" + l.get(i).getY() + "\n");
        }
        return str;
    }

}
