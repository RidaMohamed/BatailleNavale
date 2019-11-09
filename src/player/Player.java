package player;

import board.Board;
import centuryFactory.boats.Boat;

public class Player {

    public int boatsNumber;
    private Board board;

    public Player(int boatsNumber, Board board, int num) {
        this.boatsNumber = boatsNumber;
        this.board = board;
        this.boatsNumber = num;
    }

    /**
     * Adding boat to the boats of machine player
     * @param boat
     */
    public void addBoat(Boat boat){

    }

    /**
     * Methode to get Mahcine player data
     * Boats positions , Number of boats
     */
    public String getData(){

        return "";
    }

    /**
     * getting the borad which contains
     * the machine player positions
     */
    public void getBoard(){

    }
}
