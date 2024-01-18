import java.awt.*;

/**
 * Classe publique définissant les caractéristiques d'une Lampe
 * et permettant sa création.
 *
 * C'est un type de Case bonus. Elle hérite donc de CaseBonus.
 * @author Mab
 * @version 1.00
 */
public class Lampe extends CaseBonus {

/**
 * La méthode publique Lampe est le constructeur de la classe Lampe.
 * Elle permet la construction d'une nouvelle Lampe.
 */
	public Lampe(){
    super();

		/* On décide du nombre de points que donne la lampe, 
			10 */
		this.valeur = 10;
    this.icone = "icones/lampe.png";
	}


	
	@Override
	public String getLabel(){
		// On renvoie le titre de la case
    if(this.langue == 1){
			return "Lamp";
		}
		
		return "Lampe";		
	}



	@Override
	public String getLabelPv(){
		// On renvoie le texte en bas de la case
    if(this.langue == 1){
			return "Battery : " + this.valeur;
		}

		return "Batterie : " + this.valeur;
	}
} 