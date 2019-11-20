package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.centuryFactory.BoatTimeFactory;
import model.global.Constant;
import model.global.Turn;
import model.player.HumanPlayer;
import model.player.MachinePlayer;
import model.save.FileManager;

public class BattleNavaleGame {

	private HumanPlayer humanPlayer;
	private MachinePlayer machinePlayer;
	private Turn turn;
	private BoatTimeFactory boatTimeFactory;
	private FileManager fileManager;


	public BattleNavaleGame(String source) {
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				System.out.println(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
		}
		humanPlayer = new HumanPlayer(this);
		machinePlayer = new MachinePlayer(this);
		fileManager = new FileManager(this);
	}


	public BattleNavaleGame() {
		humanPlayer = new HumanPlayer(this);
		machinePlayer = new MachinePlayer(this);
		fileManager = new FileManager(this);
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

	public FileManager getFileManager() {
		return fileManager;
	}

	public void setFactory(BoatTimeFactory timeFactory){
		this.boatTimeFactory = timeFactory;
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
