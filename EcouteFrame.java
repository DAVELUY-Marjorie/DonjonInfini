import java.awt.event.*;
import java.io.*;

/**
* Classe publique permettant de savoir si la fenetre
* est fermée si c'est le cas la sauvegarde se lance.
* @author Mab
* @version 1.00
*/
public class EcouteFrame implements WindowListener{
	private Controleur c;

/**
 * La méthode publique EcouteFrame est le constructeur de la classe 
 * EcouteFrame.
 * Elle permet de lancer la sauvegarde du controleur mis en argument.
 * @param co Le controleur qu'on veut sauvegarder.
 */
	public EcouteFrame(Controleur co){
		this.c = co;
	}

	
	public void windowClosing(WindowEvent e){
		this.c.backUp();
	}  

	
	public void windowDeactivated(WindowEvent evenement){}
	public void windowDeiconified(WindowEvent evenement){}
	public void windowIconified(WindowEvent evenement){}
	public void windowOpened(WindowEvent evenement){}
	public void windowActivated(WindowEvent evenement){}
	public void windowClosed(WindowEvent evenement){}
}