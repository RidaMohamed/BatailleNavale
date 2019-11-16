package player;

import engine.Game;

public class HumanPlayer extends Player {

    private int scoreHits ;
    private int missedShots ;


    public HumanPlayer(Game game) {
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
     *
     * @param x
     * @param y
     */
    private void attack(int x, int y ){
        int i = super.getBoard().attack(x,y);
        if (i ==1)
            this.scoreHits++;
        else
            this.missedShots++;
    }

}
