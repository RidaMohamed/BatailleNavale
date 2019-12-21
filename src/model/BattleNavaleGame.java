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
	private int playerId;
	private Boolean isMulti;
	private int readyPlayers;

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
		isMulti =false;
		readyPlayers = 0;
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
		isMulti = false;
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
			isFinished = -4;
		}
		//if(this.player1 != null && this.player2 != null)
		//this.createBoats();


	}

	public BoatTimeFactory getBoatTimeFactory() {
		return boatTimeFactory;
	}

	/**
	 *
	 */
	public void createBoats()throws RemoteException {
		System.out.println("create boats");
		System.out.println("player 1 = " + this.getPlayer1());
		System.out.println("player 2 = " + this.getPlayer2());

		for (int i = 0 ; i<Constants.boat_length_size.length  ; i++){
			this.player1.getBoard().addBoat(boatTimeFactory.createBoat(Constants.boat_length_size[i]));
			if (this.player2 != null)
				this.player2.getBoard().addBoat(boatTimeFactory.createBoat(Constants.boat_length_size[i]));
			else
				this.machinePlayer.getBoard().addBoat(boatTimeFactory.createBoat(Constants.boat_length_size[i]));
		}
		//fileManager.save();
	}

	@Override
	public int getPlayerId() throws RemoteException {
		return this.playerId;
	}

	@Override
	public void setPlayerId(int id) throws RemoteException {
		this.playerId = id;
	}

	/**
	 *
	 */
	public void moveBoats() {

			try {
				if (isMulti ) {
				client.getServerGame().moveBoats(playerId);
			    }
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		for(Boat b : getPlayer1().getBoard().getBoats()){
			getPlayer1().getBoard().setBoatPosition(b);
		}
	}


	@Override
	public void moveBoats(int playerId) {
		if (playerId == 1) {
			for (Boat b : getPlayer1().getBoard().getBoats()) {
				player1.getBoard().setBoatPosition(b);
			}
		}else{
			for (Boat b : getPlayer2().getBoard().getBoats()) {
				player2.getBoard().setBoatPosition(b);
			}
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
		if (isMulti && client.getServerGame() != null)

			try {
			if (playerId == 1) {
					return client.getServerGame().getPlayer1();

			}
			else
				  return client.getServerGame().getPlayer2();

			} catch (RemoteException e) {
			e.printStackTrace();
		}

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

	@Override
	public Boolean getMulti() {
		return isMulti;
	}

	@Override
	public void setMulti(Boolean multi) {
		isMulti = multi;
	}

	@Override
	public void addReadyPlayer(){
		this.readyPlayers++;
		if (readyPlayers == 2)
			setIsFinished(1);
	}


	@Override
	public int getReadyPlayers() {
		return readyPlayers;
	}
}
