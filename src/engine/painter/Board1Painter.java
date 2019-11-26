package engine.painter;

import centuryFactory.boats.Boat;
import centuryFactory.boats.Position;
import engine.Game;
import global.Constant;
import global.Orientation;
import model.BattleNavaleGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

public class Board1Painter {

    private BattleNavaleGame game;

    public Board1Painter(BattleNavaleGame game) {
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
                        case 2 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/boat2.png")); break;
                        case 3 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/boat3.png")); break;
                        case 4 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/boat4.png")); break;
                        case 5 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/boat5.png")); break;
                    }


                    im.getGraphics().drawImage(boat1img, (boat.getPosition().getX() + 4) * Constant.CASE_WIDTH + Constant.CASE_WIDTH * Constant.WIDTH, boat.getPosition().getY() * Constant.CASE_HEIGHT + 2*Constant.CASE_HEIGHT, Constant.CASE_WIDTH, boat.getSize() * Constant.CASE_HEIGHT, null);
                }else {
                    switch (boat.getSize()){
                        case 2 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/boat2H.png")); break;
                        case 3 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/boat3H.png")); break;
                        case 4 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/boat4H.png")); break;
                        case 5 : boat1img = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/boat5H.png")); break;
                    }

                    im.getGraphics().drawImage(boat1img, (boat.getPosition().getX() + 4) * Constant.CASE_WIDTH + Constant.CASE_WIDTH * Constant.WIDTH, boat.getPosition().getY() * Constant.CASE_HEIGHT + 2*Constant.CASE_HEIGHT, boat.getSize() * Constant.CASE_WIDTH,  Constant.CASE_HEIGHT, null);

                }
                }


            BufferedImage image1 = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/point.png"));
            BufferedImage image2= ImageIO.read(this.getClass().getResourceAsStream("/Ressources/croix.png"));

            for (Position pos : game.getHumanPlayer().getBoard().getShoots().keySet()) {
                im.getGraphics().setColor(Color.decode("#3498db"));
                if (game.getHumanPlayer().getBoard().getShoots().get(pos) == null)
                    im.getGraphics().drawImage(image1 , (pos.getX() + 4)* Constant.CASE_WIDTH , pos.getY()* Constant.CASE_HEIGHT + Constant.CASE_HEIGHT * 3 , Constant.CASE_WIDTH , Constant.CASE_HEIGHT, null);
                else
                    im.getGraphics().drawImage(image2 , (pos.getX()+ 4 )* Constant.CASE_WIDTH , pos.getY()* Constant.CASE_HEIGHT , Constant.CASE_WIDTH , Constant.CASE_HEIGHT, null);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }




    }

}
