package model.player;

import model.board.Board;
import model.BattleNavaleGame;
import model.centuryFactory.boats.Boat;

import java.util.List;

public class Player {

    protected BattleNavaleGame game;
    protected Board board;

    public Player(BattleNavaleGame game) {
        this.game = game;
        this.board = new Board();
    }

    /**
     * getting the borad which contains
     * the machine model.player positions
     */
    public Board getBoard(){
         return this.board;
    }


    public BattleNavaleGame getGame() {
        return game;
    }
}
