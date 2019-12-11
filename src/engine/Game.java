package engine;

import model.global.Turn;
import model.player.Player;

import java.awt.image.BufferedImage;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 * <p>
 * un jeu qui peut evoluer (avant de se terminer) sur un plateau width x
 * height
 */
public interface Game extends Remote {

    /**
     * methode qui contient l'evolution du jeu en fonction de la commande
     *
     * @param userCmd commande utilisateur
     */
//    void evolve(Cmd userCmd);

    /**
     * @return true si et seulement si le jeu est fini
     */
    int isFinished()throws RemoteException;

    void draw(BufferedImage img) throws InterruptedException, RemoteException;

    Player getHumanPlayer() throws RemoteException;

    Player getMachinePlayer() throws RemoteException;

    void setTurn(Turn playerTurn)throws RemoteException;

    void initialize()throws RemoteException;

    void createBoats()throws RemoteException;
}
