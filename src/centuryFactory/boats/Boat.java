package centuryFactory.boats;

import global.Orientation;
import global.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Boat {

    private HashMap<Integer , Integer> positionList;
    private int boatHealth;
    private int x;
    private int y;
    private int size;
    private Orientation orientation;
    private ArrayList<Position> cases;

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

    /**
     * this methode look if the (x,y) is one of
     * the boats positions
     * @param x
     * @param y
     * @return
     */
    public boolean isOnCase(int x, int y){
        if (positionList.size()!= 0 && positionList.containsKey(x) )
            return true;
        else
            return false;
    }

    /**
     * this methode is called to decrease the health
     * of the boat
     * @param x
     * @param y
     */
    public void boatIsHit(int x , int y){
        decreaseHealth();
        //delete the hited position
        //from positionList of the boat
        //to optimize the search for the next attack
        this.positionList.remove(x,y);
    }

    /**
     * Dcrease Health of the BOAT
     */
    public void decreaseHealth(){
        this.boatHealth--;
    }

    /**
     * Look if the boat is distructed or not
     */
    public boolean isDistruct(){
        if(this.boatHealth==0)
            return true;
        else
            return false;
    }

    /**
     * This methode is called when the boat is
     * distruct to delete the rest of positions from
     * boat list positions
     */
    public void deletePositions(){
        this.positionList.clear();
    }

    public Map<Integer, Integer> getPositionList() {
        return positionList;
    }

    public  Position getPosition(){
        return new Position(this.x , this.y);
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
