import java.util.Random;
import java.awt.*;

/**
 * Classe publique définissant les caractéristiques d'un ChamPoison 
 * (Champignon vénéneux) et permettant sa création.
 *
 * C'est un type d'ennemi. Elle hérite donc de CaseEnnemie.
 * @author Mab
 * @version 1.00
 */
public class ChamPoison extends CaseEnnemie {

	
/**
 * La méthode publique ChamPoison est le constructeur de la classe ChamPoison.
 * Elle permet la construction d'un nouveau champignon avec une valeur de 20 ou 40.
 */
	public ChamPoison(){
    super();

		// On décide de la vie du monstre, 20 ou 40
		Random r = new Random();
		this.pv = (r.nextInt(1) + 1) * 20;
		this.valeur = this.pv;
    this.icone = "icones/planteT.png";
	}



	
	@Override
	public String getLabel(){
    // On renvoie le titre de la case
    if(this.langue == 1){
			return "Spore";
		}
		return "ChamPoison";
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
