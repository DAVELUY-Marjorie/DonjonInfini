import java.util.Random;
import java.awt.*;
import javax.swing.*;

/**
 * Classe publique définissant les caractéristiques d'une Jarre/Amphore
 * et permettant sa création.
 *
 * C'est un type de Case bonus. Elle hérite donc de CaseBonus.
 * @author Mab
 * @version 1.00
 */
public class Jarre extends CaseBonus {
	private int tresor; // ce que contient la jarre
	// 0 -> un piège
	// 1 -> bonus d'attaque
	// 2 -> bonus de vie
	// 3 -> bonus d'or
	
	
/**
 * La méthode publique Jarre est le constructeur de la classe Jarre.
 * Elle permet la construction d'une nouvelle Jarre/Amphore.
 */
	public Jarre(){
    super();

		/* On décide de la valeur de la jarre, 
			une dizaine entre 20 et 40 */
		Random r = new Random();
		this.valeur = (r.nextInt(3) + 2) * 10;

		/* On décide du contenu de la jarre, 
			une chance sur 2 d'avoir un piège */
		if(r.nextInt(2)==0){
			this.tresor = 0;
		}
			
		else{
			this.tresor = r.nextInt(3) + 1;
		}
		
    this.icone = "icones/jarre.png";
	}



	@Override
	public String getLabel(){
    // On renvoie le titre de la case
    if(this.langue == 1){
			return "Amphora";
		}
		
		return "Amphore";
	}



	@Override
	public String getLabelPv(){
    // On renvoie le texte en bas de la case
    if(this.langue == 1){
			return "Value : " + this.valeur;
		}
		
		return "Valeur : " + this.valeur;
	}



	
/**
 * La méthode publique getTresor permet de récupérer la valeur de tresor.
 * Renvoie 0 pour un piège, 1 pour un bonus d'attaque,
 * 2 pour un bonus de vie et 3 pour un bonus d'or
 * @return La valeur de la variable tresor
 */
	public int getTresor(){
		return this.tresor;
	}
}