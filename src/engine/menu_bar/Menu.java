package engine.menu_bar;

import engine.Game;
import engine.GameController;
import model.player.strategy.MachineAttackRandom;
import model.player.strategy.MachineCrossAttack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar {

    private JMenu file , strategy;
    private JMenuItem save , quit_party, quit_game , random , cross;
    private GameController controller;
    public Menu(GameController controller){
        this.controller = controller;

        file = new JMenu("File");
        strategy = new JMenu("Strategy");
        add(file);
        add(strategy);
        save = new JMenuItem("Save party");
        quit_party = new JMenuItem("Back to home");
        quit_game = new JMenuItem("Quit Game");

        random = new JMenuItem("Random Attack");
        cross = new JMenuItem("Cross Attack");

        file.add(save);
        file.add(quit_party);
        file.add(quit_game);

        strategy.add(random);
        strategy.add(cross);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getBattleNavaleGame().getFileManager().save();
            }
        });

        quit_party.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getBattleNavaleGame().setIsFinished(-2);
            }
        });

        quit_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getBattleNavaleGame().getMachinePlayer().setStrategy(new MachineAttackRandom(controller.getBattleNavaleGame()));
            }
        });

        cross.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.getBattleNavaleGame().getMachinePlayer().setStrategy(new MachineCrossAttack(controller.getBattleNavaleGame()));
            }
        });




    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth() , getHeight());
    }


    public void disableStateItem(){
        this.save.setEnabled(false);
        this.strategy.setEnabled(false);
    }

    public void activateStateItem(){
        this.save.setEnabled(true);
        this.strategy.setEnabled(true);
    }


}
