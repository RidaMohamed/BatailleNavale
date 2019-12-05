package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.century_factory.BoatTimeFactory;
import model.century_factory.boats.Boat;
import model.global.Constants;
import model.global.Turn;
import model.player.HumanPlayer;
import model.player.MachinePlayer;
import save.FileManager;
import model.player.strategy.MachineAttackRandom;



public class BattleNavaleGame {

	private HumanPlayer humanPlayer;
	private MachinePlayer machinePlayer;
	private Turn turn;
	private BoatTimeFactory boatTimeFactory;
	private FileManager fileManager;
	private int isFinished;

	/**
	 * Simple Constructor
	 * To load the game
	 * @param source
	 */
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
		humanPlayer   = new HumanPlayer(this);
		machinePlayer = new MachinePlayer(this);
		machinePlayer.setStrategy( new MachineAttackRandom());
		fileManager   = new FileManager(this);
	}


	/**
	 * Simple Constructor
	 */
	public BattleNavaleGame() {
		humanPlayer = new HumanPlayer(this);
		machinePlayer = new MachinePlayer(this);
		machinePlayer.setStrategy( new MachineAttackRandom());
		fileManager = new FileManager(this);
		isFinished = 1;
		turn = Turn.PlayerTurn;

	}

	public void initialize(){
	}

	/**
	 *
	 */
	public void createBoats(){
		for (int i = 0 ; i<Constants.boat_length_size.length  ; i++){
			humanPlayer.getBoard().addBoat(boatTimeFactory.createBoat(Constants.boat_length_size[i]));
			machinePlayer.getBoard().addBoat(boatTimeFactory.createBoat(Constants.boat_length_size[i]));
		}
	}

	/**
	 *
	 */
	public void moveBoats() throws InterruptedException {
		humanPlayer.getBoard().getBoats().clear();
		Thread.sleep(200);
		for (int i = 0 ; i<Constants.boat_length_size.length  ; i++){
			humanPlayer.getBoard().addBoat(boatTimeFactory.createBoat(Constants.boat_length_size[i]));
		}
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

	public Turn getTurn() {
		return turn;
	}

	public HumanPlayer getHumanPlayer() {
		return humanPlayer;
	}

	public MachinePlayer getMachinePlayer() {
		return machinePlayer;
	}

	public int isFinished() {
		return isFinished;
	}

	public void setIsFinished(int isFinished) {
		this.isFinished = isFinished;
	}
}
