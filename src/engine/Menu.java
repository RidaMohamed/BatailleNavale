package engine;

import javax.swing.*;
import java.awt.*;

public class Menu extends JMenuBar {
    private JMenu file , strategy;
    private JMenuItem save , quit , random , cross;

    Menu(){
        file = new JMenu("File");
        strategy = new JMenu("Strategy");
        add(file);
        add(strategy);
        save = new JMenuItem("Save");
        quit = new JMenuItem("Quit");

        random = new JMenuItem("Random");
        cross = new JMenuItem("Cross");

        file.add(save);
        file.add(quit);

        strategy.add(random);
        strategy.add(cross);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth() , getHeight());
    }



}
