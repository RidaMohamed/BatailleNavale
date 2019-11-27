package model.centuryFactory;

import model.centuryFactory.boats.Boat;
import model.centuryFactory.boats.Croiseur;
import model.centuryFactory.boats.Galion;

public class BoatFactoryXXCentury implements BoatTimeFactory {

    /**
     *
     * @param len
     * @return
     */
    @Override
    public Boat createBoat(int len) {
        if (len == 4)
            return new Croiseur( len);
        else
            return new Galion( len );
    }
}
