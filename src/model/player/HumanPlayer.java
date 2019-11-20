package model.player;

import model.board.Board;
import model.centuryFactory.boats.Boat;
import model.BattleNavaleGame;
import model.global.Position;

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
                //adding the hited pos to shoot list of model.board
                game.getHumanPlayer().board.addPosAttacked(new Position(x,y), "Boat" );
                //adding all the hited pos to shoot list of model.board
                if (boat.isDistruct()){
                    ArrayList<Position> positions = boat.getCases();
                    for (int k = 0 ; k < positions.size() ; k++){
                        game.getHumanPlayer().board.addPosAttacked(positions.get(k), "Boat" );
                    }
                    boat.deletePositions();
                    board.deleteBoat(boat);
                }
            }
        }

        if (missedShot){
            game.getHumanPlayer().board.addPosAttacked(new Position(x,y), "null" );
            this.missedShots++;
        }
        else{
            this.scoreHits++;
        }
    }

    /**
     * Methode to get Humaine player data
     * Boats positions, orientation, score, missed shot
     */
    public StringBuilder getData(){
        StringBuilder str = new StringBuilder();
        List<Boat> boatList = this.board.getBoats();
        //boats numbers
        str.append("boats numbres," + String.valueOf(boatList.size()));

        for (Boat b: boatList) {
            str.append(
                    b.getOrientation() +"\n"+
                    b.getBoatHealth() + "\n"+
                    String.valueOf(b.getSize()) +"\n"
            );

            List<Position> l = b.getCases();
            str.append("PosLast," + String.valueOf( b.getCases().size()) + "\n");
            for (int i = 0 ; i < l.size(); i++)
                str.append(l.get(i).getX() + "\n" + l.get(i).getY() + "\n");
        }
        str.append(String.valueOf(this.missedShots)+"\n"+
                   String.valueOf(this.scoreHits) +"\n");
        return str;
    }

    public void setScoreHits(int scoreHits) {
        this.scoreHits = scoreHits;
    }

    public void setMissedShots(int missedShots) {
        this.missedShots = missedShots;
    }
}
