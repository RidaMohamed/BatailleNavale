package engine;


import model.BattleNavaleGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Horatiu Cirstea
 * 
 * controleur qui envoie des commandes au jeu 
 * 
 */


public interface GameController extends  MouseListener {

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 * 
	 * @return commande faite par le joueur
	 */

	@Override
    void mouseClicked(MouseEvent e);

    @Override
    void mousePressed(MouseEvent e);

    @Override
    void mouseReleased(MouseEvent e);

    @Override
    void mouseEntered(MouseEvent e);

    @Override
    void mouseExited(MouseEvent e);

    BattleNavaleGame getBattleNavaleGame();

}
