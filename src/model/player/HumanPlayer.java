package model.player;

import model.board.Board;
import model.century_factory.boats.Boat;
import model.BattleNavaleGame;
import model.global.Constants;
import model.global.Orientation;
import model.global.Position;
import model.global.Turn;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class HumanPlayer extends Player {

    private int scoreHits ;
    private int missedShots ;
    /**
     * Simple constructor for Humaine player
     * @param game
     */
    public HumanPlayer(BattleNavaleGame game) {
        super(game);
        this.scoreHits = 0;
        this.missedShots = 0;
    }

    /**
     * humaine player attack the position X et Y
     * @param x
     * @param y
     */
    public void attack(int x, int y ) throws RemoteException {
        Board board = null ;

        if (game.getMulti()){
            if (game.getTurn() == Turn.PLAYER1){
                if (game.getPlayer2().getBoard().isPosFree(x , y))
                    board = game.getPlayer2().getBoard();
                else
                    return;
            }else if (game.getTurn() == Turn.PLAYER2){
                if (game.getPlayer1().getBoard().isPosFree(x , y))
                    board = game.getPlayer1().getBoard();
                else
                    return;
            }
        }else{
            board = game.getMachinePlayer().getBoard();
        }

        List<Boat> boats = board.getBoats();
        Boat boat ;
        boolean missedShot = true;

        for (int i = 0; i< boats.size();i++){
            boat =boats.get(i);
            if (boat.isOnCase(x,y)){
                missedShot = false;
                boat.boatIsHit(x,y);
                //adding the hited pos to shoot list of model.board
                board.addPosAttacked(new Position(x,y), true );
                //adding all the hited pos to shoot list of model.board
                if (boat.isDistruct()){
                    this.getGame().getMachinePlayer().subPv();
                    ArrayList<Position> positions = boat.getCases();
                    for (int k = 0 ; k < positions.size() ; k++){
                        board.addPosAttacked(positions.get(k), true );
                    }
                    boat.deletePositions();
                    //board.deleteBoat(boat);
                }
            }
        }

        if (missedShot){
            board.addPosAttacked(new Position(x,y), false );
            this.missedShots++;
        }
        else{
            this.scoreHits++;
        }


        if (game.getMulti()){
            if (game.getTurn() == Turn.PLAYER1){
                game.setTurn(Turn.PLAYER2);
            }else if (game.getTurn() == Turn.PLAYER2){
                game.setTurn(Turn.PLAYER1);
            }
        }else{
            game.setTurn(Turn.PLAYER2);
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
        str.append("boats numbres," + String.valueOf(boatList.size()) + "\n");

        for (Boat b: boatList) {
            int u ;
            if (b.getOrientation() == Orientation.HORIZONTAL)
                u = 1;
            else
                u = 0 ;
            str.append(
                    u +","+
                    b.getBoatHealth() + ","+
                    String.valueOf(b.getSize()) + "\n"
            );

            List<Position> l = b.getCases();
            str.append("PosLast," + String.valueOf( b.getCases().size()) + "\n");
            for (int i = 0 ; i < l.size(); i++)
                str.append(l.get(i).getX() + "/" + l.get(i).getY() + "\n");
        }
        str.append("Missedshot:" + String.valueOf(this.missedShots)+"\n"+
                   "ScoreHit:" + String.valueOf(this.scoreHits));
        return str;
    }

    /**
     * Setting the score hits after loading the game
     * @param scoreHits
     */
    public void setScoreHits(int scoreHits) {
        this.scoreHits = scoreHits;
    }

    /**
     * Setting the mised shot score after loading the game
     * @param missedShots
     */
    public void setMissedShots(int missedShots) {
        this.missedShots = missedShots;
    }

    public int getScoreHits() {
        return scoreHits;
    }

    public int getMissedShots() {
        return missedShots;
    }

    /**
     * Increase score hitted
     */
    public void inscreseScoreHits(){this.scoreHits++; }

    /**
     * Increaser the missed shots
     * score
     */
    public void inscreseMissedShots(){ this.missedShots++;}
}
