package engine.painter;

import model.BattleNavaleGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ScorePainter {

    private BattleNavaleGame game;

    public ScorePainter(BattleNavaleGame game) {
        this.game = game;
    }

    public BattleNavaleGame getGame() {
        return game;
    }

    public void draw(Graphics g) {
        //TODO get ALL BOATS
        try {
            BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream("/point.png"));
            //g.drawImage(image , game.getBoat().getX() * 40, game.getBoat().getY() * 40, 40, 40, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}