package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import centuryFactory.BoatTimeFactory;
import engine.Game;
import global.Clicks;
import global.Constant;
import global.Turn;
import player.HumanPlayer;
import player.MachinePlayer;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class BattleNavaleGame implements Game {

	private HumanPlayer humanPlayer;
	private MachinePlayer machinePlayer;
	private Turn turn;
	private BoatTimeFactory boatTimeFactory;

	/**
	 * constructeur avec fichier source pour le help
	 *
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


	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(Clicks commande) {
		System.out.println("Execute "+commande);
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		// le jeu n'est jamais fini
		return false;
	}

}
