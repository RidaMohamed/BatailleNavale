package model.centuryFactory;

import model.centuryFactory.boats.Boat;
import model.centuryFactory.boats.Croiseur;
import model.centuryFactory.boats.Galion;

public class BoatFactoryXVCentury implements BoatTimeFactory {
    public  Boat createBoat(int len){
        if (len == 3)
            return new Croiseur( len);
        else
            return new Galion( len );
    }
}
