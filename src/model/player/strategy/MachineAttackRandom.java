package model.player.strategy;

import model.board.Board;
import model.century_factory.boats.Boat;
import model.global.Position;
import model.global.Constant;
import model.global.Turn;
import model.player.MachinePlayer;

import java.util.ArrayList;
import java.util.List;

public class MachineAttackRandom implements StrategyMahcineAttack {

    @Override
    public void attack(MachinePlayer machinePLayer) {
        boolean b;
        Board board;
        int randX, randY;
        do {
            // Generating a random
            randX = (int) (Math.random()*(Constant.WIDTH )+1);
            randY = (int) (Math.random()*(Constant.HEIGHT)+1);

            //get borad of humain model.player
            board = machinePLayer.getGame().getHumanPlayer().getBoard();
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
                board.addPosAttacked(new Position(randX,randY), true);
                //adding all the boat pos if he is destroyed to the borad shoot list
                if (boat.isDistruct()){
                    ArrayList<Position> positions = boat.getCases();
                    for (int k = 0 ; k < positions.size() ; k++){
                        board.addPosAttacked(positions.get(k), true );
                    }
                    boat.deletePositions();
                    board.deleteBoat(boat);
                }
            }
        }

        //case the machine missed the shot
        if (missedShot)
            board.addPosAttacked(new Position(randX,randY), false);

        machinePLayer.getGame().getHumanPlayer().getGame().setTurn(Turn.PlayerTurn);
    }
}
