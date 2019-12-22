package start;

import engine.GameController;
import engine.GameEngineGraphical;
import model.BattleNavalePainter;
import model.BattleNavaleController;
import model.century_factory.BoatFactoryXXCentury;
import model.century_factory.boats.Boat;
import model.BattleNavaleGame;

import java.rmi.RemoteException;


public class Main {

    static public void main(String [] args) throws InterruptedException, RemoteException {
        BattleNavaleGame game = null;
        try {
            game = new BattleNavaleGame();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        game.setCentury(new BoatFactoryXXCentury());

        // creation du jeu particulier et de son afficheur
        BattleNavalePainter battleNavalePainter = new BattleNavalePainter(game);
        GameController controller = new BattleNavaleController(game);

        GameEngineGraphical engine = new GameEngineGraphical(game, battleNavalePainter, controller);
        engine.run();


    }
}
