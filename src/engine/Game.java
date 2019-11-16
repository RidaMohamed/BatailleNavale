package engine;

import global.Turn;
import player.HumanPlayer;
import player.MachinePlayer;

public class Game {

    private HumanPlayer humanPlayer;
    private MachinePlayer machinePlayer;
    protected Turn turn;

    public void initialize(){

    }

    public void createBoats(){

    }

    public void moveBoat(){

    }

    public void setCentury(){

    }

    public void setFactory(){

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
}
