package player;

import board.Board;

public class HumanPlayer extends Player {

    public int ScoreHits ;
    public int MissedShots ;


    public HumanPlayer(int boatsNumber, Board board,int num) {
        super(boatsNumber ,board, num );
        ScoreHits = 0;
        MissedShots = 0;
    }

    /**
     *
     */
    public void inscreseScoreHits(){

    }

    /**
     *
     */
    public void inscreseMissedShots(){

    }

}
