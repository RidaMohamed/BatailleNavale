package start;

import centuryFactory.BoatFactoryXXCentury;
import centuryFactory.boats.Boat;
import engine.Game;
import engine.GameController;
import engine.GameEngineGraphical;
import engine.GamePainter;
import engine.painter.Board1Painter;
import engine.painter.Board2Painter;
import engine.painter.Painter;
import model.BattleNavaleController;
import model.BattleNavaleGame;

public class Main {

    static public void main(String [] args) throws InterruptedException {
        BattleNavaleGame game = new BattleNavaleGame();
        game.setCentury(new BoatFactoryXXCentury());
        game.createBoats();

        System.out.println("Human player boats");

        for (Boat boat : game.getHumanPlayer().getBoard().getBoats()){
            System.out.println("pos : "+boat.getPosition().getX() + "  " + boat.getPosition().getY() + "  orientation: "+boat.getOrientation() + " size"+boat.getSize());
        }


        System.out.println("Machine player boats");

        for (Boat boat : game.getMachinePlayer().getBoard().getBoats()){
            System.out.println("pos : "+boat.getPosition().getX() + "  " + boat.getPosition().getY() + "  orientation: "+boat.getOrientation() + " size"+boat.getSize());
        }


        // creation du jeu particulier et de son afficheur
        Board1Painter board1Panter = new Board1Painter(game);
        Board2Painter board2Painter = new Board2Painter(game);
        Painter painter = new Painter(board1Panter , board2Painter);
        GameController controller = new BattleNavaleController(game);

        GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
        engine.run();

    }
}
