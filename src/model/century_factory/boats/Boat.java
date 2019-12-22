package model.century_factory.boats;

import model.global.Orientation;
import model.global.Position;

import java.io.Serializable;
import java.util.ArrayList;

public class Boat implements Serializable {

    private int boatHealth;
    private int x;
    private int y;
    private int size;
    private Orientation orientation;
    private ArrayList<Position> cases;

    /**
     * Constructor for simple start game
     * @param size
     * @param boatHealth
     */
    public Boat( int size , int boatHealth) {
        this.size = size;
        this.boatHealth = boatHealth;
        this.cases = new ArrayList<>();
    }

    /**
     * Constructor used for loading game
     * @param size
     * @param boatHealth
     * @param x
     * @param y
     */
    public Boat( int size , int boatHealth, int x, int y) {
        this.size = size;
        this.boatHealth = boatHealth;
        this.cases = new ArrayList<>();
        this.x = x;
        this.y = y;
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
        for (Position pos : cases)
            if (pos.getX() == x && pos.getY() == y)
                return true;
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
        this.cases.remove(new Position(x,y));
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
        this.cases.clear();
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
        cases = new ArrayList<>();
        Position pos = new Position(x , y);
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

    public int getBoatHealth() {
        return boatHealth;
    }

    public ArrayList<Position> getCases() {
        return cases;
    }

    public void setCases(ArrayList<Position> cases) {
        this.cases = cases;
    }

    public  Position getPosition(){
        return new Position(this.x , this.y);
    }

}
