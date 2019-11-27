package engine;

import javax.swing.*;

public class Menu extends JMenuBar {

    private JMenu file;
    private JMenuItem save , quit;

    Menu(){
        file = new JMenu("File");
        add(file);
        save = new JMenuItem("Save");
        quit = new JMenuItem("Quit");

        file.add(save);
        file.add(quit);

    }


}
