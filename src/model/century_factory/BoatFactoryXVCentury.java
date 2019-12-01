package model.century_factory;

import model.century_factory.boats.Boat;
import model.century_factory.boats.Croiseur;
import model.century_factory.boats.Galion;

public class BoatFactoryXVCentury implements BoatTimeFactory {

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
