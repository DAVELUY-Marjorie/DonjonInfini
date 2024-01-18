import java.util.Random;
import java.awt.*;

/**
 * Classe publique définissant les caractéristiques d'une Bombe
 * et permettant sa création.
 *
 * C'est un type d'ennemi. Elle hérite donc de CaseEnnemie.
 * @author Mab
 * @version 1.00
 */
public class Bombe extends CaseEnnemie {
  private int tour = 10; // nombre de tours avec la bombe sur le plateau 

/**
 * La méthode publique Bombe est le constructeur de la classe Bombe.
 * Elle permet la construction d'une nouvelle bombe 
 * avec une valeur à 70.
 */
	public Bombe(){
    super();

		// On décide de la vie du monstre à 70
		Random r = new Random();
		this.pv = 70;
		this.valeur = this.pv;
    this.icone = "icones/bombe/bombe10.png";
	}


	

	/**
 * La méthode publique getTour renvoie le nombre de tours avant l'explosion de la bombe.
 * @return Le nombre de tours avant l'explosion de la bombe
 */
  public int getTour(){
    this.tour--;
		this.icone = "icones/bombe/bombe"+this.tour+".png";
    return this.tour;
  }



	@Override
	public String getLabel(){
    // On renvoie le titre de la case
    if(this.langue == 1){
			return "Bomb";
		}
		
		return "Bombe";
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
