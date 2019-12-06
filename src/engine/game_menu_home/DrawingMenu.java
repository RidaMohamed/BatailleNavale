package engine.game_menu_home;

import model.BattleNavalePainter;
import model.global.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawingMenu extends JPanel {
    private static final long serialVersionUID = 1L;

    private BattleNavalePainter painter;
    private int width, height;
    private OnePlayer playButton;
    private MultiPlayer MultiPlayerButton;
    private LoadPlayer LoadButton;
    private QuitGame QuitButton;

    public DrawingMenu(BattleNavalePainter painter) {
        super();
        this.width = Constants.CASE_WIDTH * Constants.WIDTH;
        this.height = Constants.CASE_HEIGHT * Constants.HEIGHT;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.painter=painter;
        playButton = new OnePlayer();
        MultiPlayerButton = new MultiPlayer();
        LoadButton = new LoadPlayer();
        QuitButton = new QuitGame();
    }

    public void draw(BufferedImage im){
        playButton.paint(im.getGraphics());
        MultiPlayerButton.paint(im.getGraphics());
        LoadButton.paint(im.getGraphics());
        QuitButton.paint(im.getGraphics());

    }

    public void paint(Graphics g) {
        super.paint(g);
    }
}
