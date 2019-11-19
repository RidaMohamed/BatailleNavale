package player;

import board.Board;
import centuryFactory.boats.Boat;
import model.BattleNavaleGame;

public class Player {

    protected BattleNavaleGame game;
    protected Board board;

    public Player(BattleNavaleGame game) {
        this.game = game;
        this.board = new Board();
    }

    /**
     * Methode to get Mahcine player data
     * Boats positions
     */
    public String getData(){
        return "";
    }

    /**
     * getting the borad which contains
     * the machine player positions
     */
    public Board getBoard(){
         return this.board;
    }


    public BattleNavaleGame getGame() {
        return game;
    }
}
