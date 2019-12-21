package rmi.client;

import engine.Game;
import model.BattleNavaleGame;
import model.board.Board;
import model.century_factory.boats.Boat;
import model.global.Turn;
import model.player.Player;

import javax.naming.NamingException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class BattleNavaleClient {

    private Game serverGame;


    public void init() throws NamingException, RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        System.out.println("RMI registry bindings");
        String[] e = registry.list();

        for(String s : e){
            System.out.println(s);
        }

        String remoteObjectName = "game";
        this.serverGame = (Game) registry.lookup(remoteObjectName);
        this.serverGame.join();

        if (serverGame.getPlayer1() != null && serverGame.getPlayer2() != null) {
            this.serverGame.createBoats();
            this.serverGame.setIsFinished(0);
        }


    }

    public Game getServerGame() {
        return serverGame;
    }
}
