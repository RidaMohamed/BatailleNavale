package start;

import engine.GameController;
import engine.GameEngineGraphical;
import engine.painter.Painter;
import model.BattleNavaleController;
import model.centuryFactory.BoatFactoryXXCentury;
import model.centuryFactory.boats.Boat;
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

    }
}
