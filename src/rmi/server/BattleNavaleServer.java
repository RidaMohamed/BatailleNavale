package rmi.server;

import engine.Game;
import model.BattleNavaleGame;
import model.century_factory.BoatFactoryXVCentury;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BattleNavaleServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException{
        System.out.println("Constructing server implementation...");
        BattleNavaleGame battleNavaleGame = new BattleNavaleGame();
        battleNavaleGame.setFactory(new BoatFactoryXVCentury());
        System.out.println("Binding server implementation to registry...");
        Registry registry = LocateRegistry.getRegistry();
        registry.bind("game",battleNavaleGame);
        System.out.println("Waiting for invocations from clients...");


    }
}
