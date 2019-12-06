package model;

import engine.GamePainter;
import engine.painter.Board1Painter;
import engine.painter.Board2Painter;
import model.BattleNavaleGame;
import model.global.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public  class BattleNavalePainter implements GamePainter {

    /**
     * la taille des cases
     */
    protected static final int WIDTH = 1050;
    protected static final int HEIGHT = 700;
    private Board1Painter board1Panter;
    private Board2Painter board2Painter;
    private BoardPositioningPainter boardPositioningPainter;

    public BattleNavalePainter(BattleNavaleGame battleNavaleGame){
        this.board1Panter = new Board1Painter(battleNavaleGame);
        this.board2Painter = new Board2Painter(battleNavaleGame);
        this.boardPositioningPainter = new BoardPositioningPainter(battleNavaleGame);
        this.screenWidth = 1050;
        this.screenHeight = 700;
    }

    public Board1Painter getBoard1Panter() {
        return board1Panter;
    }

    public Board2Painter getBoard2Painter() {
        return board2Painter;
    }

    public BoardPositioningPainter getBoardPositioningPainter() {
        return boardPositioningPainter;
    }

    public int getScreenWidth() {
        return WIDTH;
    }

    public int getScreenHeight() {
        return HEIGHT;
    }

    public void draw(BufferedImage nextImage) {
    }
}