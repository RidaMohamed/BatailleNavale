package start;

import model.centuryFactory.BoatFactoryXXCentury;
import model.centuryFactory.boats.Boat;
import model.BattleNavaleGame;

import java.io.IOException;

public class Main {

    static public void main(String [] args){
        BattleNavaleGame game = new BattleNavaleGame();
        game.setCentury(new BoatFactoryXXCentury());
        game.createBoats();

        System.out.println("/////////////////////////////");
        // save
        game.getFileManager().save();

        // load
//        try {
//            game.getFileManager().load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("Human model.player boats");

        for (Boat boat : game.getHumanPlayer().getBoard().getBoats()){
            System.out.println("pos : "+boat.getPosition().getX() + "  " + boat.getPosition().getY() +
                    "  orientation: "+boat.getOrientation() + " size "+boat.getSize());
        }

        System.out.println("Machine model.player boats");

        for (Boat boat : game.getMachinePlayer().getBoard().getBoats()){
            System.out.println("pos : "+boat.getPosition().getX() + "  " + boat.getPosition().getY() +
                    "  orientation: "+boat.getOrientation() + " size "+boat.getSize());
        }

    }
}
