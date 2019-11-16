package player;

import board.Board;
import centuryFactory.boats.Boat;
import engine.Game;

public class Player {

    private Game game;
    private Board board;

    public Player(Game game) {
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



}
