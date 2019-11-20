package model.player.strategy;

import model.board.Board;
import model.centuryFactory.boats.Boat;
import model.global.Position;
import model.player.MachinePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

            //get borad of humain model.player
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
                board.addPosAttacked(new Position(randX,randY), "boat");
                //adding all the boat pos if he is destroyed to the borad shoot list
                if (boat.isDistruct()){
                    ArrayList<Position> positions = boat.getCases();
                    for (int k = 0 ; k < positions.size() ; k++){
                        board.addPosAttacked(positions.get(k), "Boat" );
                    }
                    boat.deletePositions();
                    board.deleteBoat(boat);
                }
            }
        }

        //case the machine missed the shot
        if (missedShot)
            board.addPosAttacked(new Position(randX,randY), "null");
    }
}
