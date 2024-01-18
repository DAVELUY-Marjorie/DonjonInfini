import java.util.Random;
import java.awt.*;
import java.io.*;

/**
 * Classe abstraite définissant les caractéristiques d'une Case
 * Elle implémente Serializable pour permettre la sauvegarde du jeu
 * 
 * @author Mab
 * @version 1.00
 */
abstract class Case implements Serializable{
	protected int langue;
	
	protected int valeur;
	// pour les Monstres, nb de pv actuel
	// pour le Heros, son attaque

	
	protected String icone;

	

/**
 * La méthode publique getLabel permet de renvoyer l'intitulé de la case.
 * Elle permet aussi de renvoyer ce nom selon la langue sélectionnée.
 * @return Une chaîne de caractères décrivant la case
 */
	abstract public String getLabel();




	
/**
 * La méthode publique getLabelPv permet de renvoyer l'affichage
 * de la valeur de la case
 * en indiquant son rôle.
 * Elle permet aussi de renvoyer cette valeur selon la langue sélectionnée.
 * @return Une chaîne de caractères donnant son rôle et sa valeur.
 */
	abstract public String getLabelPv();




	
/**
 * La méthode publique getValeur permet de renvoyer la valeur numérique de la case
 * @return La valeur de la case.
 */
	public int getValeur(){
    // On renvoie la valeur de la case
		return this.valeur;
	}


	

	
/**
 * La méthode publique getIcone permet de renvoyer l'image de la case
 * @return L'image attribuée à la case.
 */
  public Image getIcone(){
    // On renvoie l'icone de la case
		return Toolkit.getDefaultToolkit().getImage(this.icone);
	}


	

	
/**
 * La méthode publique et statique newRandomCase 
 * permet de renvoyer une nouvelle Case aléatoire avec 4 chances
 * sur 10 que ce soit un ennemie et 6/10 une case bonus.
 * @return Une nouvelle case aléatoire.
 */
	public static Case newRandomCase(){
    //Renvoie une nouvelle case
		Random r = new Random();
    //On défini une valeur entre 0 et 9
		int cpt = r.nextInt(10);
    //Si cette valeur est plus petite que 4 on retourne une case ennemie
		if(cpt<4){
			return CaseEnnemie.newRandomCase();
		}
      //Sinon on retourne une case bonus
		else{
			return CaseBonus.newRandomCase();
		}
	}



	
/**
 * La méthode publique setLangue permet de choisir la langue de la case
 * @param lng la valeur de la langue (0 pour fr et 1 pour an)
 */
	public void setLangue(int lng){
		this.langue = lng;
	}
}