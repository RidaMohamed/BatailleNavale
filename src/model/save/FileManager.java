package model.save;

import model.BattleNavaleGame;
import model.centuryFactory.boats.Boat;
import model.global.Orientation;
import model.global.Position;

import java.io.*;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private String dataH;
    private String dataM;
    private BattleNavaleGame game;

    public FileManager(BattleNavaleGame game) {
        this.game = game;
    }

    public void load() throws IOException {

        InputStream file = FileManager.class.getClassLoader().getResourceAsStream("gamesSaves.txt");
        InputStreamReader lecture = new InputStreamReader(file);
        BufferedReader buff = new BufferedReader(lecture);
        String line;
        Boat b;
        String[] boatsNumbers;
        String[] boatsNumbers1;
        int boatSize, X, Y, boatHealth;
        Orientation orientation;
        ArrayList<Position> list = new ArrayList<>();

        if ((line = buff.readLine()) != null){
             boatsNumbers = line.split(",");
            //Loading Humaine player data
            for(int i = 0; i<Integer.valueOf(boatsNumbers[1]);i++){
                line = buff.readLine();
                orientation = Orientation.valueOf(line);
                line = buff.readLine();
                boatHealth = Integer.valueOf(line);
                line = buff.readLine();
                boatSize = Integer.valueOf(line);
                line = buff.readLine();
                boatsNumbers1 = line.split(",");
                for (int j=0 ; j < Integer.valueOf(boatsNumbers1[1]); j++){
                    line = buff.readLine();
                    X = Integer.valueOf(line);
                    line = buff.readLine();
                    Y = Integer.valueOf(line);
                    list.add(new Position(X,Y));
                }
                b = new Boat(boatSize,boatHealth);
                b.setOrientation(orientation);
                b.setCases(list);
                game.getHumanPlayer().getBoard().addBoatToList(b);
            }
            //setting the score and the missedshot
            line = buff.readLine();
            int score = Integer.valueOf(line);
            line = buff.readLine();
            int missedShot = Integer.valueOf(line);
            game.getHumanPlayer().setScoreHits(score);
            game.getHumanPlayer().setMissedShots(missedShot);

            list = new ArrayList<>();
            //Loading Machine pLayer data
            boatsNumbers1 = line.split(",");
            for(int i = 0; i<Integer.valueOf(boatsNumbers1[1]);i++){
                line = buff.readLine();
                orientation = Orientation.valueOf(line);
                line = buff.readLine();
                boatHealth = Integer.valueOf(line);
                line = buff.readLine();
                boatSize = Integer.valueOf(line);
                line = buff.readLine();
                boatsNumbers = line.split(",");
                for (int j=0 ; j < Integer.valueOf(boatsNumbers[1]); j++){
                    line = buff.readLine();
                    X = Integer.valueOf(line);
                    line = buff.readLine();
                    Y = Integer.valueOf(line);
                    list.add(new Position(X,Y));
                }
                b = new Boat(boatSize,boatHealth);
                b.setOrientation(orientation);
                b.setCases(list);
                game.getMachinePlayer().getBoard().addBoatToList(b);
            }
        }
    }

    public void save (){
        //Getting the data
        this.dataH = game.getHumanPlayer().getData().toString();
        this.dataM = game.getMachinePlayer().getData().toString();

        //Writing the data on the saveFile
        try {
            FileWriter fw = new FileWriter("res/gameSaves.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            out.print(dataH);
            System.out.println(dataH);
            out.print(dataM);
            System.out.println(dataM);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

    }

}
