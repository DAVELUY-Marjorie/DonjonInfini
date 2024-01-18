import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Classe publique permettant de choisir une langue pour le jeu
 *
 * Elle se sert de l'action du joueur pour 
 * modifier la valeur d'une variable. 
 * Elle implémente donc ActionListener.
 * @author Mab
 * @version 1.00
 */
public class ChoixLangue implements ActionListener{
	private JFrame langue = new JFrame();

	
/**
 * La méthode publique ChoixLangue est le constructeur de la classe ChoixLangue.
 * Elle permet la construction de la fenêtre qui propose le choix de la langue.
 */
	public ChoixLangue(){
		// On configure la fenêtre de choix de langue
		this.langue.setLayout(new GridLayout(2,1));
    this.langue.setSize(270,120);
		this.langue.setTitle(" DI - Language");
		this.langue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// On place la fenêtre au milieu de l'écran
    this.langue.setLocationRelativeTo(null);

		// On crée un JLabel et on l'ajoute à la fenêtre
		this.langue.add(new JLabel("<html>Choisir la langue :<br>Choose language:</html>", JLabel.CENTER));
		

		// On crée les choix de langue
		// On leur met le ChoixLangue en ActionListener
		JButton fr = new JButton("Français");
		JButton eng = new JButton("English");

		fr.addActionListener(this);
		eng.addActionListener(this);

		JLabel jl = new JLabel();
		jl.setLayout(new FlowLayout());
		jl.add(fr);
		jl.add(eng);
		this.langue.add(jl);
		
		// On affiche la fenêtre
    this.langue.setVisible(true);
	}



	
	public void actionPerformed(ActionEvent e){
		// On ferme la fenêtre de choix de langue
		this.langue.dispose();
		
		// On crée et configure la fenêtre de jeu
    JFrame fenetre = new JFrame();
		fenetre.setLayout(new GridLayout(1,1));
    fenetre.setSize(600,600);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// On place la fenêtre au milieu de l'écran
    fenetre.setLocationRelativeTo(null);

		// On crée un controleur avec la valeur de la langue en argument
		Controleur c;
		if(e.getActionCommand().equals("Français")){
			fenetre.setTitle(" Donjon Infini");
			c = new Controleur(0);
		}
		else{
			fenetre.setTitle(" Infinite Dungeon");
			c = new Controleur(1);
		}
		
		/* On ajoute le Controleur à la fenêtre, 
			visuellement et en tant que listener */
		fenetre.add(c);
		fenetre.addKeyListener(c);
		
		fenetre.addWindowListener(new EcouteFrame(c));

		// On affiche la fenêtre
    fenetre.setVisible(true);
  }

}