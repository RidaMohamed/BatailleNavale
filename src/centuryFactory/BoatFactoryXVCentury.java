package centuryFactory;

import centuryFactory.boats.Boat;
import centuryFactory.boats.Croiseur;
import centuryFactory.boats.Galion;
import global.Orientation;

public class BoatFactoryXVCentury implements BoatTimeFactory {
    public  Boat createBoat(int len){
        if (len == 3)
            return new Croiseur( len);
        else
            return new Galion( len );
    }
}
