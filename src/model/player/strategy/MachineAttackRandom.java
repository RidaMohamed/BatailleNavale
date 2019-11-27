package model.player.strategy;

import model.BattleNavaleGame;
import model.board.Board;
import model.centuryFactory.boats.Boat;
import model.global.Constant;
import model.global.Position;
import model.global.Turn;
import model.player.MachinePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MachineAttackRandom implements StrategyMahcineAttack {

    private final BattleNavaleGame battleNavaleGame;

    public MachineAttackRandom(BattleNavaleGame battleNavaleGame){
        this.battleNavaleGame = battleNavaleGame;
    }

    @Override
    public void attack() {
        boolean b;
        Board board;
        int randX, randY;
        do {
            // Generating a random
             randX = (int) (Math.random()*(Constant.WIDTH )+1);
             randY = (int) (Math.random()*(Constant.HEIGHT)+1);
            System.out.println("la machine a choisi " + randX + "  "+randY);
            //get borad of humain model.player
             board = this.battleNavaleGame.getHumanPlayer().getBoard();
            //Verfie is the postions is ok to attack
            b = board.isPosFree(randX , randY);
        }while(!b);

        List<Boat> boats = this.battleNavaleGame.getHumanPlayer().getGame().getHumanPlayer().getBoard().getBoats();

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

        this.battleNavaleGame.getHumanPlayer().getGame().setTurn(Turn.PlayerTurn);
    }
}
