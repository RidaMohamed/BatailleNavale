package model.player;

import engine.Game;
import model.board.Board;
import model.BattleNavaleGame;
import model.global.Constants;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Player implements Serializable {

    protected Game game;
    protected Board board;

    public Player(Game game) {
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
    public Game getGame() {
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
    public void attack() throws RemoteException {
    }


}
