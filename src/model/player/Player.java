package model.player;

import model.board.Board;
import model.BattleNavaleGame;
import model.global.Constants;

public class Player {

    protected BattleNavaleGame game;
    protected Board board;
    protected int pv;

    public Player(BattleNavaleGame game) {
        this.game = game;
        this.board = new Board();
        this.pv = Constants.boat_length_size.length;
    }

    /**
     * getting the borad which contains
     * the machine model.player boats positions
     */
    public Board getBoard(){
         return this.board;
    }

    /**
     * getting the Battle Navale Game class
     * player know the Battle Navale Game class
     * @return
     */
    public BattleNavaleGame getGame() {
        return game;
    }


    public StringBuilder getData(){
        StringBuilder str = new StringBuilder();
        str.append("/");
        return str;
    }

    public int getPv() {
        return pv;
    }

    public void subPv(){
        this.pv--;
        if (this.pv == 0)
            this.getGame().setIsFinished(-2);
    }
}
