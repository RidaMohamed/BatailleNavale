package engine.painter;

import model.BattleNavaleGame;
import model.global.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public  class Painter {

    private Board1Painter board1Panter;
    private Board2Painter board2Painter;
    private int screenWidth , screenHeight;

    public Painter(BattleNavaleGame battleNavaleGame){
        this.board1Panter = new Board1Painter(battleNavaleGame);
        this.board2Painter = new Board2Painter(battleNavaleGame);
        this.screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    }

    public Board1Painter getBoard1Panter() {
        return board1Panter;
    }

    public Board2Painter getBoard2Painter() {
        return board2Painter;
    }


    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return this.screenHeight;
    }


    public void draw(BufferedImage nextImage) {
    }
}
