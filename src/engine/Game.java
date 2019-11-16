package engine;

import centuryFactory.BoatFactoryXVCentury;
import centuryFactory.BoatTimeFactory;
import global.Constant;
import global.Turn;
import player.HumanPlayer;
import player.MachinePlayer;

public class Game {

    private HumanPlayer humanPlayer;
    private MachinePlayer machinePlayer;
    private Turn turn;
    private BoatTimeFactory boatTimeFactory;


    public Game() {
        humanPlayer = new HumanPlayer(this);
        machinePlayer = new MachinePlayer(this);

    }



    public void initialize(){
    }

    public void createBoats(){
        for (int i = Constant.boat_length_size.length-1; i>= 1 ; i--){
            for (int j=0 ; j<Constant.boat_length_size[i]; j++){
                humanPlayer.getBoard().addBoat(boatTimeFactory.createBoat(i));
                machinePlayer.getBoard().addBoat(boatTimeFactory.createBoat(i));
            }
        }

    }

    public void moveBoat(){

    }

    public void setCentury(BoatTimeFactory timeFactory){
           this.boatTimeFactory = timeFactory;
    }

    public void setFactory(BoatTimeFactory timeFactory){
          this.boatTimeFactory = timeFactory;
    }

    public void getFileManager(){

    }

    public void setHumanPlayer(HumanPlayer humanPlayer) {
        this.humanPlayer = humanPlayer;
    }

    public void setMachinePlayer(MachinePlayer machinePlayer) {
        this.machinePlayer = machinePlayer;
    }

    public void setStrategy(MachinePlayer machinePlayer) {
        this.machinePlayer = machinePlayer;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public HumanPlayer getHumanPlayer() {
        return humanPlayer;
    }

    public MachinePlayer getMachinePlayer() {
        return machinePlayer;
    }
}
