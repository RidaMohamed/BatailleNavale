package model.player;

import model.BattleNavaleGame;
import model.centuryFactory.boats.Boat;
import model.global.Position;
import model.player.strategy.StrategyMahcineAttack;

import java.util.List;

public class MachinePlayer extends Player {


    public StrategyMahcineAttack strategyMahcineAttack;

    public MachinePlayer(BattleNavaleGame game) {
        super(game);
    }

    /**
     * setting the strategy game for
     * the machine model.player
     * @param strategyMahcineAttack
     */
    public void setStrategy(StrategyMahcineAttack strategyMahcineAttack){

        strategyMahcineAttack.attack(this);
    }

    /**
     * Methode to get Mahcine model.player data
     * Boats positions
     */
    public StringBuilder getData(){
        StringBuilder str = new StringBuilder();
        List<Boat> boatList = this.board.getBoats();
        //boats numbers
        str.append("boats numbres," + String.valueOf(boatList));
        for (Boat b: boatList) {
            str.append(
                    b.getOrientation() +"\n"+
                    b.getBoatHealth() + "\n"+
                    String.valueOf(b.getSize()) +"\n"
            );

            List<Position> l = b.getCases();
            for (int i = 0 ; i < l.size(); i++)
                str.append(l.get(i).getX() + "\n" + l.get(i).getY() + "\n");
        }
        return str;
    }

}
