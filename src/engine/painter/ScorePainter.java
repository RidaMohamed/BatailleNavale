package engine.painter;

import engine.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ScorePainter {

    private Game game;

    public ScorePainter(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void draw(Graphics g) {
        //TODO get ALL BOATS
        try {
            BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/point.png"));
            //g.drawImage(image , game.getBoat().getX() * 40, game.getBoat().getY() * 40, 40, 40, null);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
