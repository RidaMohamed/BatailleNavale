package centuryFactory.boats;

import global.Orientation;

import java.util.ArrayList;

public class Boat {
    private int x;
    private int y;
    private int size;
    private Orientation orientation;
    private ArrayList<Position> cases;
    private int boatHealth;

    public Boat( int size , int boatHealth) {
        this.size = size;
        this.boatHealth = boatHealth;
        this.cases = new ArrayList<>();
    }


    public Orientation getOrientation() {
        return orientation;
    }

    public int getSize() {
        return size;
    }


    /**
     * set position of boat
     * @param x
     * @param y
     */
    public void setPostion(int x , int y){
        this.x = x;
        this.y  = y;
        Position pos = new Position(x , y);
        cases = new ArrayList<>();
        cases.add(pos);
        if (orientation == Orientation.HORIZONTAL){
            for (int i = 1 ; i<size ; i++){
                cases.add(new Position(pos.getX()+i , pos.getY()));
            }
        }else if (orientation == Orientation.VERTICAL){
            for (int i = 1 ; i<size ; i++){
                cases.add(new Position(pos.getX(), pos.getY()+i));
            }

        }
    }


    public  Position getPosition(){
        return new Position(this.x , this.y);
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
