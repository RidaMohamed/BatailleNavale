package engine.painter;

import model.BattleNavaleGame;

import java.awt.image.BufferedImage;

public  class Painter {

    private Board1Painter board1Painter;
    private Board2Painter board2Painter;
    private BoardPositioningPainter boardPositioningPainter;
    private int screenWidth , screenHeight;

    public Painter(BattleNavaleGame battleNavaleGame){
        this.board1Painter = new Board1Painter(battleNavaleGame);
        this.board2Painter = new Board2Painter(battleNavaleGame);
        this.boardPositioningPainter = new BoardPositioningPainter(battleNavaleGame);
        this.screenWidth = 1050;
        this.screenHeight = 700;
    }

    public Board1Painter getBoard1Painter() {
        return board1Painter;
    }

    public Board2Painter getBoard2Painter() {
        return board2Painter;
    }

    public BoardPositioningPainter getBoardPositioningPainter() {
        return boardPositioningPainter;
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