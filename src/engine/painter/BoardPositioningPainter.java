package engine.painter;

import engine.gamepositioning.OkButton;
import engine.gamepositioning.RandomButton;
import model.BattleNavaleGame;
import model.century_factory.boats.Boat;
import model.global.Constants;
import model.global.Orientation;
import model.global.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BoardPositioningPainter {
    private BattleNavaleGame game;
    private RandomButton randomButton;
    private OkButton okButton;

    public BoardPositioningPainter(BattleNavaleGame game) {
        this.game = game;
        this.randomButton = new RandomButton();
        this.okButton = new OkButton();
    }

    public BattleNavaleGame getGame() {
        return game;
    }

    public void draw(BufferedImage im) {
        randomButton.paint(im.getGraphics());
        okButton.paint(im.getGraphics());
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

                    im.getGraphics().drawImage(boat1img, (boat.getPosition().getX() * 40) + 285, boat.getPosition().getY() * Constants.CASE_HEIGHT + 2* Constants.CASE_HEIGHT, Constants.CASE_WIDTH, boat.getSize() * Constants.CASE_HEIGHT, null);
                }else {
                    switch (boat.getSize()){
                        case 2 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/boat2H.png")); break;
                        case 3 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/boat3H.png")); break;
                        case 4 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/boat4H.png")); break;
                        case 5 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/boat5H.png")); break;
                    }
                    im.getGraphics().drawImage(boat1img, (boat.getPosition().getX() * 40) + 285, boat.getPosition().getY() * Constants.CASE_HEIGHT + 2* Constants.CASE_HEIGHT, boat.getSize() * Constants.CASE_WIDTH,  Constants.CASE_HEIGHT, null);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
