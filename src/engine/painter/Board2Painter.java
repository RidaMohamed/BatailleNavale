package engine.painter;

import centuryFactory.boats.Position;
import engine.Game;
import global.Constant;
import model.BattleNavaleGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Board2Painter  {

    private BattleNavaleGame game;

    public Board2Painter(BattleNavaleGame game) {
        this.game = game;
    }

    public BattleNavaleGame getGame() {
        return game;
    }

    public void draw(BufferedImage im) {
        try {
            BufferedImage image1 = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/point.png"));
            BufferedImage image2= ImageIO.read(this.getClass().getResourceAsStream("/Ressources/croix.png"));

            for (Position pos : game.getMachinePlayer().getBoard().getShoots().keySet()) {
                im.getGraphics().setColor(Color.decode("#3498db"));
                if (game.getMachinePlayer().getBoard().getShoots().get(pos) == null)
                    im.getGraphics().drawImage(image1 , pos.getX()* Constant.CASE_WIDTH , pos.getY() * Constant.CASE_HEIGHT + 2*Constant.CASE_HEIGHT, Constant.CASE_WIDTH , Constant.CASE_HEIGHT, null);
                else
                    im.getGraphics().drawImage(image2 , pos.getX()* Constant.CASE_WIDTH , pos.getY() * Constant.CASE_HEIGHT + 2*Constant.CASE_HEIGHT , Constant.CASE_WIDTH , Constant.CASE_HEIGHT, null);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
