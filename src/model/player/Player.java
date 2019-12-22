package model.player;

import model.board.Board;
import model.BattleNavaleGame;
import model.global.Constants;

public class Player {

    protected BattleNavaleGame game;
    protected Board board;

    public Player(BattleNavaleGame game) {
        this.game = game;
        this.board = new Board();
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


    /**
     * Methode to get data to save the game party
     * @return
     */
    public StringBuilder getData(){
        StringBuilder str = new StringBuilder();
        str.append("/");
        return str;
    }

    /**
     * Methode to get data to save the game party
     * @return
     */
    public void attack(){
    }


}
