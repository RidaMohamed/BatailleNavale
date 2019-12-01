package engine.painter;

import model.BattleNavaleGame;
import model.global.Constant;
import model.global.Position;

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
            BufferedImage image1 = ImageIO.read(this.getClass().getResourceAsStream("/nottouched.png"));
            BufferedImage image2= ImageIO.read(this.getClass().getResourceAsStream("/touched.png"));

            for (Position pos : game.getMachinePlayer().getBoard().getShoots().keySet()) {
                im.getGraphics().setColor(Color.decode("#3498db"));
                if (!game.getMachinePlayer().getBoard().getShoots().get(pos))
                    im.getGraphics().drawImage(image1 , pos.getX()* Constant.CASE_WIDTH ,
                            pos.getY() * Constant.CASE_HEIGHT + 2*Constant.CASE_HEIGHT, Constant.CASE_WIDTH ,
                            Constant.CASE_HEIGHT, null);
                else
                    im.getGraphics().drawImage(image2 , pos.getX()* Constant.CASE_WIDTH ,
                            pos.getY() * Constant.CASE_HEIGHT + 2*Constant.CASE_HEIGHT , Constant.CASE_WIDTH ,
                            Constant.CASE_HEIGHT, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}