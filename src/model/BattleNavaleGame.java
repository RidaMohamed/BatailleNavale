package model;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import engine.Game;
import model.century_factory.BoatFactoryXVCentury;
import model.century_factory.BoatTimeFactory;
import model.century_factory.boats.Boat;
import model.global.Constants;
import model.global.Turn;
import model.player.HumanPlayer;
import model.player.MachinePlayer;
import model.player.strategy.MachineCrossAttack;
import rmi.client.BattleNavaleClient;
import save.FileManager;



public class BattleNavaleGame extends UnicastRemoteObject implements Game {

	private HumanPlayer player1;
	private HumanPlayer player2;
	private MachinePlayer machinePlayer;
	private Turn turn;
	private BoatTimeFactory boatTimeFactory;
	private FileManager fileManager;
	private int isFinished;
	private BattleNavaleClient client;

	/**
	 * Simple Constructor
	 * To load the game
	 * @param source
	 */
	public BattleNavaleGame(String source)throws RemoteException {
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
		player1  = new HumanPlayer(this);
		machinePlayer = new MachinePlayer(this);
		machinePlayer.setStrategy( new MachineCrossAttack(this));
		fileManager   = new FileManager(this);
		turn = Turn.PlayerTurn;
		client = new BattleNavaleClient();
	}


	/**
	 * Simple Constructor
	 */


	public BattleNavaleGame()throws RemoteException {
		this.player1 = null;
		this.player1 = null;
		this.machinePlayer = null;
		fileManager   = new FileManager(this);
		turn = Turn.PlayerTurn;
		isFinished = -3;
		client = new BattleNavaleClient();

	}


	public void initializeOnePlayer() throws RemoteException {
		player1 = new HumanPlayer(this);
		machinePlayer = new MachinePlayer(this);
		machinePlayer.setStrategy( new MachineCrossAttack(this));
		boatTimeFactory = new BoatFactoryXVCentury();
		fileManager = new FileManager(this);
		turn = Turn.PlayerTurn;

	}


	public void join()throws RemoteException {
		if (this.player1 == null) {
			this.player1 = new HumanPlayer(this);
			isFinished = -4;
		} else if (this.player2 == null){
			this.player2 = new HumanPlayer(this);
			createBoats();
			isFinished = 0;
	     }

	}

	/**
	 *
	 */
	public void createBoats()throws RemoteException {
		for (int i = 0 ; i<Constants.boat_length_size.length  ; i++){
			player1.getBoard().addBoat(boatTimeFactory.createBoat(Constants.boat_length_size[i]));
			if (player2 != null)
				player2.getBoard().addBoat(boatTimeFactory.createBoat(Constants.boat_length_size[i]));
             else
			    machinePlayer.getBoard().addBoat(boatTimeFactory.createBoat(Constants.boat_length_size[i]));
		}
		fileManager.save();
	}

	/**
	 *
	 */
	public void moveBoats() {
		for(Boat b : player1.getBoard().getBoats()){
			player1.getBoard().setBoatPosition(b);
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

	public void setplayer1(HumanPlayer player1) {
		this.player1 = player1;
	}

	public void setplayer2(HumanPlayer player2) {
		this.player2 = player2;
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

	public HumanPlayer getPlayer1() {
		return player1;
	}

	public HumanPlayer getPlayer2() {
		return player2;
	}

	public MachinePlayer getMachinePlayer() {
		return machinePlayer;
	}

	public int isFinished() {
		return isFinished;
	}

	@Override
	public void draw(BufferedImage img) throws InterruptedException {

	}

	public BattleNavaleClient getClient() {
		return client;
	}

	public void setIsFinished(int isFinished) {
		this.isFinished = isFinished;
	}
}
