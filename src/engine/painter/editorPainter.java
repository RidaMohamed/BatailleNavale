package engine.painter;

import model.BattleNavaleGame;
import model.century_factory.boats.Boat;
import model.global.Constants;
import model.global.Orientation;
import model.global.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EditorPainter {

    private BattleNavaleGame game;

    public EditorPainter(BattleNavaleGame game) {
        this.game = game;
    }

    public BattleNavaleGame getGame() {
        return game;
    }

    public void draw(BufferedImage im) {

        try {
            BufferedImage boat1img = null;

            for (Boat boat : game.getHumanPlayer().getBoard().getBoats()) {
                im.getGraphics().setColor(Color.decode("#3498db"));
                if (boat.getOrientation() == Orientation.VERTICAL) {
                    switch (boat.getSize()){
                        case 2 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/boat2.png")); break;
                        case 3 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/boat3.png")); break;
                        case 4 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/boat4.png")); break;
                        case 5 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/boat5.png")); break;
                    }

                    im.getGraphics().drawImage(boat1img, (boat.getPosition().getX() + 4) * Constants.CASE_WIDTH +
                            Constants.CASE_WIDTH * Constants.WIDTH, boat.getPosition().getY() * Constants.CASE_HEIGHT +
                            2* Constants.CASE_HEIGHT, Constants.CASE_WIDTH, boat.getSize() * Constants.CASE_HEIGHT, null);
                }else {
                    switch (boat.getSize()){
                        case 2 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/boat2H.png")); break;
                        case 3 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/boat3H.png")); break;
                        case 4 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/boat4H.png")); break;
                        case 5 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/boat5H.png")); break;
                    }

                    im.getGraphics().drawImage(boat1img,  Constants.CASE_WIDTH * Constants.WIDTH,
                            boat.getPosition().getY() * Constants.CASE_HEIGHT + 2* Constants.CASE_HEIGHT,
                            boat.getSize() * Constants.CASE_WIDTH,  Constants.CASE_HEIGHT, null);
                }
            }

            BufferedImage image1 = ImageIO.read(this.getClass().getResourceAsStream("/point.png"));
            BufferedImage image2= ImageIO.read(this.getClass().getResourceAsStream("/croix.png"));

            for (Position pos : game.getHumanPlayer().getBoard().getShoots().keySet()) {
                im.getGraphics().setColor(Color.decode("#3498db"));
                if (game.getHumanPlayer().getBoard().getShoots().get(pos) == null)
                    im.getGraphics().drawImage(image1 , (pos.getX() )* Constants.CASE_WIDTH ,
                            pos.getY()* Constants.CASE_HEIGHT + Constants.CASE_HEIGHT * 3 , Constants.CASE_WIDTH ,
                            Constants.CASE_HEIGHT, null);
                else
                    im.getGraphics().drawImage(image2 , (pos.getX())* Constants.CASE_WIDTH ,
                            pos.getY()* Constants.CASE_HEIGHT , Constants.CASE_WIDTH , Constants.CASE_HEIGHT, null);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }




    }

}