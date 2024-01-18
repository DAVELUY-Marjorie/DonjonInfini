import java.util.Random;

/**
 * Classe publique définissant les caractéristiques d'une Potion
 * et permettant sa création.
 *
 * C'est un type de Case Bonus. Elle hérite donc de CaseBonus.
 * @author Mab
 * @version 1.00
 */
public class Potion extends CaseBonus {

	
/**
 * La méthode publique Potion sans argument est un constructeur de la classe Potion.
 * Elle permet la construction d'une nouvelle potion
 * avec une valeur aléatoirentre 10 et 70 de 10 en 10.
 */
	public Potion(){
    super();

		/* On décide du nombre de pv que redonne la potion, 
			une dizaine entre 10 et 70 */
		Random r = new Random();
		this.valeur = (r.nextInt(7) + 1) * 10;
    this.icone = "icones/potion.png";
	}
	
/**
 * La méthode publique Potion avec un argument est un constructeur 
 * de la classe Potion (surcharge).
 * Elle permet la construction d'une nouvelle Potion avec une valeur donnée en argument.
 * @param val valeur qu'on veut que la potion prenne
 */
	public Potion(int val){
    super();
		this.valeur = val;
    this.icone = "icones/potion.png";
	}



	
	@Override
	public String getLabel(){
		// On renvoie le titre de la case
		return "Potion";
	}



	@Override
	public String getLabelPv(){
		// On renvoie le texte en bas de la case
    if(this.langue == 1){
			return "Heal : " + this.valeur;
		}
		
		return "Soin : " + this.valeur;
	}
} 