package start;

import engine.GameController;
import engine.GameEngineGraphical;
import engine.painter.Painter;
import model.BattleNavaleController;
import model.century_factory.BoatFactoryXXCentury;
import model.century_factory.boats.Boat;
import model.BattleNavaleGame;


public class Main {

    static public void main(String [] args) throws InterruptedException {
        BattleNavaleGame game = new BattleNavaleGame();
        game.setCentury(new BoatFactoryXXCentury());
        game.createBoats();

        // creation du jeu particulier et de son afficheur
        Painter painter = new Painter(game);
        GameController controller = new BattleNavaleController(game);

        GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
        engine.run();


        System.out.println("Human player boats");

        for (Boat boat : game.getHumanPlayer().getBoard().getBoats()){
            System.out.println("pos : "+boat.getPosition().getX() + "  " + boat.getPosition().getY() + "  orientation: "+boat.getOrientation() + " size"+boat.getSize());
        }

        System.out.println("Machine model.player boats");

        for (Boat boat : game.getMachinePlayer().getBoard().getBoats()){
            System.out.println("pos : "+boat.getPosition().getX() + "  " + boat.getPosition().getY() +
                    "  orientation: "+boat.getOrientation() + " size "+boat.getSize());
        }

    }
}
