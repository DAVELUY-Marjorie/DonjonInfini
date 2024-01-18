import java.util.Random;
import java.awt.*;
import java.io.*;

/**
 * Classe publique définissant les caractéristiques de l'Or
 * et permettant sa création.
 *
 * C'est un type de Case Bonus. Elle hérite donc de CaseBonus.
 * @author Mab
 * @version 1.00
 */
public class Or extends CaseBonus {

	
/**
 * La méthode publique Or sans argument est un constructeur de la classe Or.
 * Elle permet la construction d'une nouvelle arme 
 * avec une valeur aléatoire entre 10 et 50 de 10 en 10.
 */
	public Or(){
    super();

		/* On décide du nombre de points que donne l'or, 
			une dizaine entre 10 et 50 */
		Random r = new Random();
		this.valeur = (r.nextInt(5) + 1) * 20;
    this.icone = "icones/or.png";
	}
	
/**
 * La méthode publique Or avec un argument est un constructeur 
 * de la classe Or (surcharge).
 * Elle permet la construction d'un nouveau Or avec une valeur donnée en argument.
 * @param val valeur qu'on veut que l'or prenne
 */
	public Or(int val){
    super();
		this.valeur = val;
    this.icone = "icones/or.png";
	}



	
	@Override
	public String getLabel(){
		// On renvoie le titre de la case
    if(this.langue == 1){
			return "Gold";
		}
		
		return "Or";
	}


	@Override
	public String getLabelPv(){
		// On renvoie le texte en bas de la case
    if(this.langue == 1){
			return this.valeur + " $";
		}

		return this.valeur + " €";
	}
} 