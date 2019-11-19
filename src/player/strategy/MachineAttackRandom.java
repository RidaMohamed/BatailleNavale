package player.strategy;

import board.Board;
import centuryFactory.boats.Boat;
import global.Position;
import player.MachinePlayer;
import player.Player;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class MachineAttackRandom implements StrategyMahcineAttack {

    @Override
    public void attack(MachinePlayer machinePLayer) {
        boolean b;
        Board board;
        int randX, randY;
        do {
            // Generating a random
             randX = (int)(Math.round(Math.random()*50));
             randY = (int)(Math.round(Math.random()*50));

            //get borad of humain player
             board = machinePLayer.getBoard();
            //Verfie is the postions is ok to attack
            b = board.isPosFree(randX , randY);
        }while(!b);

        List<Boat> boats = machinePLayer.getGame().getHumanPlayer().getBoard().getBoats();

        boolean missedShot = true;
        for ( Boat boat: boats  ) {
            if (boat.isOnCase(randX,randY)){
                missedShot = false ;
                boat.boatIsHit(randX,randY);
                //adding the hited pos to the borad shoot list
                board.addPosAttacked(randX,randY, "boat");
                //adding all the boat pos if he is destroyed to the borad shoot list
                if (boat.isDistruct()){
                    Map<Integer , Integer> position = boat.getPositionList();
                    for (Map.Entry<Integer, Integer> entry : position.entrySet()){
                        board.addPosAttacked(entry.getKey(), entry.getValue(), "boat");
                    }
                    boat.deletePositions();
                    board.deleteBoat(boat);
                }
            }
        }

        //case the machine missed the shot
        if (missedShot)
            board.addPosAttacked(randX,randY, "null");
    }
}
