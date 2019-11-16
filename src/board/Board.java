package board;

import centuryFactory.boats.Boat;
import centuryFactory.boats.Position;
import global.Constant;
import global.Orientation;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private List<Boat> boats;
    private Map<Position , Boat> shoots;


    public Board() {
        boats = new ArrayList<>();
        shoots = new HashMap<>();
    }

    /**
     * Add boat to bard
     * @param boat
     */

    public void addBoat(Boat boat){
        setBoatPosition(boat);
        boats.add(boat);

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
                Max = Constant.WIDTH - boat.getSize();
                x = Min + (int) (Math.random() * ((Max - Min) + 1));
                Max = 10;
                y = Min + (int) (Math.random() * ((Max - Min) + 1));
                boat.setPostion(x , y);

            } else {
                boat.setOrientation(Orientation.VERTICAL);
                Min = 1;
                Max = Constant.HEIGHT - boat.getSize();
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

        for (Boat b : boats){
            Rectangle rectangle1 , rectangle2;
            if (boat.getOrientation() == Orientation.HORIZONTAL) {
                rectangle1 = new Rectangle((boat.getPosition().getX()-1)*Constant.CASE_WIDTH , (boat.getPosition().getY()-1)*Constant.CASE_HEIGHT , (boat.getSize()+1)*Constant.CASE_WIDTH, Constant.CASE_HEIGHT*3);
            }else
                rectangle1 = new Rectangle((boat.getPosition().getX()-1)*Constant.CASE_WIDTH , (boat.getPosition().getY()-1)*Constant.CASE_HEIGHT , Constant.CASE_WIDTH*3 , (boat.getSize()+1)*Constant.CASE_HEIGHT);


            if (b.getOrientation() == Orientation.HORIZONTAL) {
                rectangle2 = new Rectangle((b.getPosition().getX()-1)*Constant.CASE_WIDTH , (b.getPosition().getY()-1)*Constant.CASE_HEIGHT , (b.getSize()+1)*Constant.CASE_WIDTH, Constant.CASE_HEIGHT*3);
            }else
                rectangle2 = new Rectangle((b.getPosition().getX()-1)*Constant.CASE_WIDTH , (b.getPosition().getY()-1)*Constant.CASE_HEIGHT , Constant.CASE_WIDTH*3 , (b.getSize()+1)*Constant.CASE_HEIGHT);


            if (rectangle1.intersects(rectangle2))
                setBoatPosition(boat);


        }
        return true;
    }


    public List<Boat> getBoats() {
        return boats;
    }
}
