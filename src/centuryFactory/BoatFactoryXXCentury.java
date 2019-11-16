package centuryFactory;

import centuryFactory.boats.Boat;
import centuryFactory.boats.Croiseur;
import centuryFactory.boats.Galion;

public class BoatFactoryXXCentury implements BoatTimeFactory {
    @Override
    public Boat createBoat(int len) {
        if (len == 4)
            return new Croiseur( len);
        else
            return new Galion( len );
    }
}
