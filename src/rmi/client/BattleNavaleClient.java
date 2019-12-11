package rmi.client;

import engine.Game;
import model.BattleNavaleGame;
import model.century_factory.boats.Boat;
import model.player.Player;

import javax.naming.NamingException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class BattleNavaleClient {
    public static void main(String[] args) throws NamingException, RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        System.out.println("RMI registry bindings");
        String[] e = registry.list();

        for(String s : e){
            System.out.println(s);
        }

        String remoteObjectName = "game";
        Game battleNavaleGame = (Game) registry.lookup(remoteObjectName);
        battleNavaleGame.initialize();
        battleNavaleGame.createBoats();


        System.out.println(battleNavaleGame.getHumanPlayer().getBoard().getBoats());

    }
}
