package model.board;

import model.century_factory.boats.Boat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.global.Position;
import model.global.Constants;
import model.global.Orientation;

import java.awt.*;
import java.util.List;

public class Board {

    private Map<Position , Boolean> shoots;
    private List<Boat> boats;
    private int stayBoat;

    /**
     * Simple Constructor
     */
    public Board() {
        boats = new ArrayList<>();
        shoots = new HashMap<>();
        this.stayBoat = 0;
    }

    /**
     * Add created boat by the
     * boatTimeFactory to board
     * @param boat
     */
    public void addBoat(Boat boat){
        setBoatPosition(boat);
        boats.add(boat);
        this.stayBoat ++;
    }

    /**
     * set Position of boat
     * @param boat
     */
    public void setBoatPosition(Boat boat){
        int Min = 1;
        int Max = 2;
        int x , y;
        int orientation;
        do {
            orientation= Min + (int) (Math.random() * ((Max - Min) + 1));
            if (orientation == 1) {
                boat.setOrientation(Orientation.HORIZONTAL);
                Min = 1;
                Max = Constants.WIDTH - boat.getSize();
                x = Min + (int) (Math.random() * ((Max - Min) + 1));
                Max = 10;
                y = Min + (int) (Math.random() * ((Max - Min) + 1));
                boat.setPostion(x , y);

            } else {
                boat.setOrientation(Orientation.VERTICAL);
                Min = 1;
                Max = Constants.HEIGHT - boat.getSize();
                y = Min + (int) (Math.random() * ((Max - Min) + 1));
                Max = 10;
                x = Min + (int) (Math.random() * ((Max - Min) + 1));
                boat.setPostion(x , y);
            }
        }while (!isPosOk(boat));
    }

    /**
     * ckeck if position of boat is good
     * @param boat
     * @return
     */
    public boolean isPosOk(Boat boat) {
        int posX = boat.getPosition().getX();
        int posY = boat.getPosition().getY();

        if (posX <1 || posX > Constants.WIDTH ||posY <1 || posY > Constants.HEIGHT) {
            return false;
        }else if (boat.getOrientation() == Orientation.HORIZONTAL && (posX + boat.getSize()) > (Constants.WIDTH +1 )){
            return false;
        }else if(boat.getOrientation() == Orientation.VERTICAL && (posY + boat.getSize()) > (Constants.HEIGHT +1 )) {
            return false;
        }

        for (Boat b : boats){
            if (b == boat)
                continue;
            Rectangle rectangle1 , rectangle2;
            if(b==boat)
                continue;
            if (boat.getOrientation() == Orientation.HORIZONTAL) {
                rectangle1 = new Rectangle((boat.getPosition().getX())* Constants.CASE_WIDTH , (boat.getPosition().getY())* Constants.CASE_HEIGHT , (boat.getSize())* Constants.CASE_WIDTH, Constants.CASE_HEIGHT);
            }else
                rectangle1 = new Rectangle((boat.getPosition().getX())* Constants.CASE_WIDTH , (boat.getPosition().getY())* Constants.CASE_HEIGHT , Constants.CASE_WIDTH , (boat.getSize())* Constants.CASE_HEIGHT);
            if (b.getOrientation() == Orientation.HORIZONTAL) {
                rectangle2 = new Rectangle((b.getPosition().getX()-1)* Constants.CASE_WIDTH , (b.getPosition().getY()-1)* Constants.CASE_HEIGHT , (b.getSize()+2)* Constants.CASE_WIDTH, Constants.CASE_HEIGHT*3);
            }else
                rectangle2 = new Rectangle((b.getPosition().getX()-1)* Constants.CASE_WIDTH , (b.getPosition().getY()-1)* Constants.CASE_HEIGHT , Constants.CASE_WIDTH*3 , (b.getSize()+2)* Constants.CASE_HEIGHT);
            if (rectangle1.intersects(rectangle2))
                return false;
        }
        return true;
    }

    /**
     * Check if the position is not allready attackec
     * @return
     */
    public Boolean isPosFree (int x , int y){
        for (Position pos : shoots.keySet())
            if (pos.getX() == x && pos.getY() == y)
                return false;
         return true;
    }

    /**
     * Methode returns all the boats of
     * the player
     * @return
     */
    public List<Boat> getBoats() {
        return boats;
    }

    /**
     *
     * @param p
     * @param isOnBoat
     */
    public void addPosAttacked(Position p, Boolean isOnBoat) {
        this.shoots.put(p, isOnBoat);
    }

    /**
     * This methode delete the distrcut boat from
     * the list of model.board
     * @param b
     */
    public void deleteBoat(Boat b) {
        this.boats.remove(b);
    }

    public void addBoatToList(Boat boat){
        boats.add(boat);
    }

    public Map<Position, Boolean> getShoots() {
        return this.shoots;
    }

    public int boatsHealth(){
        int s= 0;
        for(Boat boat : boats){
            s = s +boat.getBoatHealth();
        }
        return s;
    }
}
