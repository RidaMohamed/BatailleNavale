package start;

import model.centuryFactory.BoatFactoryXXCentury;
import model.centuryFactory.boats.Boat;
import model.BattleNavaleGame;

public class Main {

    static public void main(String [] args){
        BattleNavaleGame game = new BattleNavaleGame();
        game.setCentury(new BoatFactoryXXCentury());
        game.createBoats();

        System.out.println("Human model.player boats");

        for (Boat boat : game.getHumanPlayer().getBoard().getBoats()){
            System.out.println("pos : "+boat.getPosition().getX() + "  " + boat.getPosition().getY() + "  orientation: "+boat.getOrientation() + " size"+boat.getSize());
        }

        System.out.println("Machine model.player boats");

        for (Boat boat : game.getMachinePlayer().getBoard().getBoats()){
            System.out.println("pos : "+boat.getPosition().getX() + "  " + boat.getPosition().getY() + "  orientation: "+boat.getOrientation() + " size"+boat.getSize());
        }

        game.getFileManager().save();

    }
}
