package engine.painter;

import java.awt.image.BufferedImage;

public  class Painter implements engine.GamePainter {

    private Board1Painter board1Panter;
    private Board2Painter board2Painter;
    private editorPainter editorPainter;
    private int screenWidth , getScreenHeight;

    public Painter(Board1Painter board1Panter, Board2Painter board2Painter){
        this.board1Panter = board1Panter;
        this.board2Painter = board2Painter;

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

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getGetScreenHeight() {
        return getScreenHeight;
    }

    public void setGetScreenHeight(int getScreenHeight) {
        this.getScreenHeight = getScreenHeight;
    }

    @Override
    public void draw(BufferedImage image) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
