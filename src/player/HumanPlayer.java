package player;

import board.Board;
import centuryFactory.boats.Boat;
import global.Position;
import model.BattleNavaleGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HumanPlayer extends Player {

    private int scoreHits ;
    private int missedShots ;

    public HumanPlayer(BattleNavaleGame game) {
        super(game);
        this.scoreHits = 0;
        this.missedShots = 0;
    }

    /**
     *
     */
    public void inscreseScoreHits(){this.scoreHits++; }

    /**
     *
     */
    public void inscreseMissedShots(){ this.missedShots++;}

    /**
     * Le joueur humaine attack la position X et Y
     * @param x
     * @param y
     */
    private void attack(int x, int y ){

        Board board = game.getMachinePlayer().board;
        List<Boat> boats = board.getBoats();
        Boat boat ;
        boolean missedShot = true;

        for (int i = 0; i< boats.size();i++){
            boat =boats.get(i);
            if (boat.isOnCase(x,y)){
                missedShot = false;
                boat.boatIsHit(x,y);
                //adding the hited pos to shoot list of board
                game.getHumanPlayer().board.addPosAttacked(x, y, "Boat" );
                //adding all the hited pos to shoot list of board
                if (boat.isDistruct()){
                    Map<Integer , Integer> position = boat.getPositionList();
                    for (Map.Entry<Integer, Integer> entry : position.entrySet()){
                        game.getHumanPlayer().board.addPosAttacked(x, y, "Boat" );
                    }
                    boat.deletePositions();
                    board.deleteBoat(boat);
                }
            }
        }

        if (missedShot){
            game.getHumanPlayer().board.addPosAttacked(x, y, "null" );
            this.missedShots++;
        }
        else{
            this.scoreHits++;
        }
    }

}
