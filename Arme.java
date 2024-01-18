import java.util.Random;
import java.awt.*;

/**
 * Classe publique définissant les caractéristiques d'une Arme
 * et permettant sa création.
 *
 * C'est un type de CaseBonus. Elle hérite donc de CaseBonus.
 * @author Mab
 * @version 1.00
 */
public class Arme extends CaseBonus {

/**
 * La méthode publique Arme sans argument est un constructeur de la classe Arme.
 * Elle permet la construction d'une nouvelle arme
 * avec une valeur aléatoire entre 10 et 70 de 10 en 10.
 */
	public Arme(){
    super();

		/* On décide du nombre de dégâts que fait l'arme, 
			une dizaine entre 10 et 70 */
		Random r = new Random();
		this.valeur = (r.nextInt(7) + 1) * 10;
    this.icone = "icones/arme.png";
	}




	
/**
 * La méthode publique Arme avec un argument est un constructeur 
 * de la classe Arme (surcharge).
 * Elle permet la construction d'une nouvelle arme avec une valeur donnée.
 * @param val valeur qu'on veut que l'arme prenne
 */
	public Arme(int val){
    super();
		this.valeur = val;
    this.icone = "icones/arme.png";
	}



	@Override
	public String getLabel(){
		// On renvoie le titre de la case
		if(this.langue == 1){
				return "Weapon";
		}
		return "Arme";
	}


	@Override
	public String getLabelPv(){
		// On renvoie le texte en bas de la case
    if(this.langue == 1){
			return "Damage : " + this.valeur;
		}
		
		return "Attaque : " + this.valeur;
	}
}