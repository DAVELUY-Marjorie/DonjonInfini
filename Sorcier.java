import java.util.Random;
import java.awt.*;

/**
 * Classe publique définissant les caractéristiques d'un Sorcier 
 * et permettant sa création.
 *
 * C'est un type d'ennemi. Elle hérite donc de CaseEnnemie.
 * @author Mab
 * @version 1.00
 */
public class Sorcier extends CaseEnnemie {

/**
 * La méthode publique Sorcier est le constructeur de la classe Sorcier.
 * Elle permet la construction d'un nouveau sorcier 
 * avec une valeur aléatoire entre 40 et 70 de 10 en 10.
 */
	public Sorcier(){
    super();

		/* On décide de la vie du monstre, 
			une dizaine entre 40 et 70 */
		Random r = new Random();
		this.pv = (r.nextInt(3) + 4) * 10;
		this.valeur = this.pv;
    this.icone = "icones/sorcier.png";
	}



	@Override
	public String getLabel(){
    // On renvoie le titre de la case
    if(this.langue == 1){
			return "Wizard";
		}
		
		return "Sorcier";
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