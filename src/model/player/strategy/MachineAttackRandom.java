package model.player.strategy;

import model.BattleNavaleGame;
import model.board.Board;
import model.century_factory.boats.Boat;
import model.global.Position;
import model.global.Constants;
import model.global.Turn;
import model.player.MachinePlayer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class MachineAttackRandom implements StrategyMahcineAttack {

    private BattleNavaleGame battleNavaleGame;

    public MachineAttackRandom(BattleNavaleGame battleNavaleGame) {
        this.battleNavaleGame = battleNavaleGame;
    }

    @Override
    public void attack(MachinePlayer machinePLayer) throws RemoteException {
        boolean b;
        Board board;
        int randX, randY;
        do {
            // Generating a random
            randX = (int) (Math.random()*(Constants.WIDTH )+1);
            randY = (int) (Math.random()*(Constants.HEIGHT)+1);

            //get borad of humain model.player
            board = battleNavaleGame.getPlayer1().getBoard();
            //Verfie is the postions is ok to attack
            b = board.isPosFree(randX , randY);
        }while(!b);

        List<Boat> boats = battleNavaleGame.getPlayer1().getBoard().getBoats();

        boolean missedShot = true;
        for (Boat boat: boats) {
            if (boat.isOnCase(randX,randY)){
                missedShot = false ;
                machinePLayer.setScoreHits(machinePLayer.getScoreHits()+1);
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
                   // board.deleteBoat(boat);
                }
            }
        }

        //case the machine missed the shot
        if (missedShot)
            board.addPosAttacked(new Position(randX,randY), false);

        battleNavaleGame.getPlayer1().getGame().setTurn(Turn.PLAYER1);
    }
}
