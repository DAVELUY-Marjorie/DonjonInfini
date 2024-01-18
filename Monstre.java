import java.util.Random;
import java.awt.*;

/**
 * Classe publique définissant les caractéristiques d'un Monstre 
 * normal et permettant sa création.
 *
 * C'est un type d'ennemi. Elle hérite donc de CaseEnnemie.
 * @author Mab
 * @version 1.00
 */
public class Monstre extends CaseEnnemie {

/**
 * La méthode publique Monstre est le constructeur de la classe Monstre.
 * Elle permet la construction d'un nouveau monstre 
 * avec une valeur de 10 à 60 de 10 en 10.
 */
	public Monstre(){
    super();

		/* On décide de la vie du monstre, 
			une dizaine entre 10 et 60 */
		Random r = new Random();
		this.pv = (r.nextInt(6) + 1) * 10;
		this.valeur = this.pv;
    this.icone = "icones/monstre.png";
	}


	
	@Override
	public String getLabel(){
    // On renvoie le titre de la case
    if(this.langue == 1){
			return "Monster";
		}
		
		return "Monstre";
	}



	@Override
	public String getLabelPv(){
    // On renvoie le texte en bas de la case
		if(this.langue == 1){
			return "Life : " + this.pv;
		}
		
		return "Vie : " + this.pv;
	}
}