import java.awt.*;

/**
 * Classe publique définissant les caractéristiques d'un Héros
 * et permettant sa création.
 *
 * C'est un type de Case. Elle hérite donc de Case.
 * @author Mab
 * @version 1.00
 */
public class Heros extends Case{
  private int pv = 200;  // nombre de pv actuel
	private int lampe = 0;

	
/**
 * La méthode publique Heros est le constructeur de la classe Heros.
 * Elle permet la construction d'un nouvel héros au centre du plateau.
 */
	public Heros(){
		// On met l'attaque du héros à 0
		this.valeur = 0;
		this.icone = "icones/heros/juste_heros.png";
	}




	
/**
 * La méthode publique rencontrer s'occupe de la rencontre avec une autre case.
 * 
 * Elle permet de gérer ce qu'il se passe quand le héros se déplace.
 *
 * @param c la case que le héros rencontre
 * @return true si le héros n'est pas mort false sinon
 */
  public boolean rencontrer(Case c){
		// renvoie true si le Héros n'est pas mort
		// renvoie false si Le Héros est mort
		
    int v = c.getValeur();
		
		// Si la case qu'on rencontre est un Monstre
		if(c instanceof CaseEnnemie) {
			// Si on n'a pas d'arme
			if(this.valeur == 0){
				// On perd des pv
        this.pv -= v;
			}

				// Si on a une arme
			else{
				// Si l'arme est plus forte que le monstre
	      if(this.valeur >= v){
					// L'arme pert de l'attaque
	        this.valeur -= v;
	      }

				// Si l'arme est moins forte que le monstre
				else{
					// On enlève des pv au monstre
					v -= this.valeur;

					// On met notre attaque à 0
					this.valeur = 0;

					// On perd autant de pv que les pv restants du monstre
					this.pv -= v;
				}
			}
		}

		else{
			// Si la case qu'on rencontre est une Potion
			if(c instanceof Potion) {
				// On augmente nos pv
				this.pv += v;
			}

			// Si la case qu'on rencontre est une Arme
			if(c instanceof Arme) {
				// Si l'attaque de l'arme est meilleure que notre attaque actuelle
				if(this.valeur < v){
					// On met notre attaque à celle de l'arme
					this.valeur = v;
				}
			}

			// Si la case qu'on rencontre est une Lampe
			if(c instanceof Lampe) {
				// On met notre compteur de lampe à 5
				this.lampe = 5;
			}
			
			// Si la case qu'on rencontre est une Jarre
			if(c instanceof Jarre){
				Jarre j = (Jarre) c;
				switch(j.getTresor()){
					// Si elle contient un piège on enlève de la vie
					case 0 : 
						this.pv -= v;
						break;

					// Si elle contient un café on rajoute de l'attaque
					case 1 :
						this.valeur += v;
						break;

					// Si elle contient une pomme on rajoute de la vie
					case 2 :
						this.pv += v;
						break;
				}
			}
		}

		// On renvoie true si le héros est en vie et false sinon
		return this.pv > 0;
  }




	@Override
	public String getLabel(){
		// On renvoie le titre de la case
		if(this.langue == 1){
			return "Hero";
		}
		
		return "Heros";
	}




	@Override
	public String getLabelPv(){
		// On renvoie le texte en bas de la case
		if(this.langue == 1){
			return "<html><p style='text-align:right;'>Life : " + this.pv + "<br>Damage : " + this.valeur + "</p></html>";
		}
		
		return "<html><p style='text-align:right;'>Vie : " + this.pv + "<br> Attaque : " + this.valeur + "</p></html>";
	}




	
/**
 * La méthode publique getLampe permet de savoir 
 * si nous sommes en possesion d'une lampe
 *
 * @param recupSauvegarde True si on charge une sauvegarde, false sinon 
 * @return true si on a une lampe false sinon
 */
	public boolean getLampe(boolean recupSauvegarde){
    if(!recupSauvegarde){
      this.lampe--;
    }
		return this.lampe > 0;
	}



	

/**
 * La méthode publique getIcone permet de renvoyer l'image de la case
 * Renvoie le heros avec ou sans lampe, avec ou sans arme
 * @return L'image attribuée à la case.
 */
	@Override
  public Image getIcone(){
    // On renvoie l'icone de la case
		if(this.lampe>0 && this.valeur>0){
			return Toolkit.getDefaultToolkit().getImage("icones/heros/epee_lampe.png");
		}
		if(this.lampe>0){
			return Toolkit.getDefaultToolkit().getImage("icones/heros/juste_lampe.png");
		}
		if(this.valeur>0){
			return Toolkit.getDefaultToolkit().getImage("icones/heros/juste_epee.png");
		}
		return Toolkit.getDefaultToolkit().getImage(this.icone);
	}




	
	/**
 * La méthode publique perdrePv permet de faire perdre des pv au Héros
 * @param degat Le nombre de pv à enlever au Héros
 */
	public void perdrePv(int degat){
		this.pv -= degat;
	}
}