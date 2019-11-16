package centuryFactory;

import centuryFactory.boats.Boat;

public interface BoatTimeFactory {

    public Boat createBoat(int boatSize);
}
