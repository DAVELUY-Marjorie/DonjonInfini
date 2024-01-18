import javax.swing.*;
import java.awt.*;

/**
 * Classe publique gerant l'affichage du plateau
 *
 * C'est un élément graphique.  
 * Elle hérite donc de JComponent.
 * @author Mab
 * @version 1.00
 */
public class VuePlateau extends JComponent{
  //Déclaration et initialisation du compteur de point à 0
  private int vuePoints = 0;

	private int record = 0;

	private JLabel vueRecord = new JLabel("Record : 0");
	
  //Déclaration et initialisation du texte des pts en haut à droite
	private JLabel pts = new JLabel("Points : 0");
	
  //Déclaration et initialisation du plateau contenant les 9 vues
  private VueCase[] vues = new VueCase[9];

	private int langue = 0;

	
 /**
 * La méthode publique VuePlateau est le constructeur de la classe VuePlateau.
 * Elle permet la construction d'une nouvelle VuePlateau 
 * qui affiche les Cases données en argument, dans la langue donnée.
 * Elle affiche aussi les points et le record.
  * @param cases les cases à afficher
	* @param points les points
	* @param best le record
	* @param lng la langue de l'affichage
  */
  public VuePlateau(Case[] cases, int points, int best, int lng){
		super();
		this.langue = lng;
		this.vuePoints = points;
		this.record = best;
		

		if(this.langue == 1){
			this.vueRecord = new JLabel("Best Score : 0");
		}
		//On met l'arrière plan en noir
		this.setBackground(Color.black);
		this.setOpaque(true);

    //Création d'une grille pour le plateau
    //(ligne,colonne,espacement horizontal,espacement vertical)
		this.setLayout(new GridLayout(4,3,5,5));
    //Ajout de 2 cases vides à gauche de la case Points/record
		this.add(new JLabel());
		this.add(new JLabel());		

		//Ajout d'un JPanel qui contient les points et le record
		JPanel nbs = new JPanel();
		nbs.setLayout(new GridLayout(2,1));
		nbs.setOpaque(false);
		//Ajout du JPanel à la fenêtre
		this.add(nbs);
		
    //Mise à droite des Points
		this.pts.setHorizontalAlignment(JLabel.RIGHT);
		this.pts.setVerticalAlignment(JLabel.CENTER);
    //Définition de la couleur et de la police du texte
		this.pts.setForeground(Color.white);
		this.pts.setFont(new Font("Arial", Font.BOLD, 15));
    //Ajout du JLabel au Jpanel
		nbs.add(this.pts);

		//Définition du visuel du record
		this.vueRecord.setHorizontalAlignment(JLabel.RIGHT);
		this.vueRecord.setVerticalAlignment(JLabel.CENTER);
		this.vueRecord.setFont(new Font("Arial", Font.BOLD, 15));
		
		if(this.vuePoints >= this.record){
				this.updateRecord(this.vuePoints);
		}
		else{
	    //Définition de la couleur et du texte
			this.vueRecord.setForeground(Color.white);
	    if(this.langue == 1){
				this.vueRecord.setText("Best Score : " + this.record);
			}else{
				this.vueRecord.setText("Record : " + this.record);
	    }
		}
		
    //Ajout du JLabel à la fenêtre
		nbs.add(this.vueRecord);

    //Création et affichage des cases dans le plateau
		for(int i=0;i < cases.length;i++){ 
			this.vues[i] = new VueCase(cases[i]);
			this.add(this.vues[i]);

    }
  }



	
	/**
	* La méthode publique upPoint permet d'augmenter le nombre de points affiché.
	* Elle remplace la valeur de l'attribut VuePoint
 	* par celle donnée en argument
	* @param c le nouveau nombre de points
  */
  public void upPoint(int c){
    this.vuePoints += c; //Ajoute c points à vuePoints
  }



	
  @Override
	// afficher chaque VueCase du tableau cases
	// et afficher le nombre de points actuel
  protected void paintComponent(Graphics g) {
    Graphics secondPinceau = g.create();

		// Si le VuePlateau est Opaque, on affiche son Background
    if (this.isOpaque()) {
      secondPinceau.setColor(this.getBackground());
      secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    //Mise à jour visuelle des points et du record à chaque tour
  	this.pts.setText("Points : " + this.vuePoints);
		
		for(VueCase prov : this.vues){
			prov.repaint();
		}
	}



	
		/**
	* La méthode publique updateRecord permet de changer la case affichée à une certaine position.
	* Elle remplace la Case affichée par la VueCase 
 	* de coordonnées x,y du tableau vues[] pa celle en argument.
	* @param nc la nouvelle Case à afficher
 	* @param x la position x de la Case
	* @param y la position y de la Case
  */
	public void update(Case nc, int x, int y){
		this.vues[(y*3)+x].setCase(nc);
		this.repaint();
	}




	/**
	* La méthode publique updateRecord permet d'actualiser le record.
	* Elle affiche en vert le nouveau record donné en argument
	* @param best le record
  */
	public void updateRecord(int best){
		// On change la valeur et la couleur du record
		this.record = best;
		this.vueRecord.setForeground(new Color(153, 255, 102));

		// On actualise le texte dans la bonne langue
    if(this.langue == 1){
			this.vueRecord.setText("Best Score : " + this.record);
		}else{
		this.vueRecord.setText("Record : " + this.record);
    }
		this.repaint();
	}



	
	/**
	* La méthode publique setVisibles permet d'actualiser la visibilité des cases.
	* Elle affiche toutes les cases si lampe donné en argument est vrai
	* Sinon elle affiche le Héros et les cases juste à côté de lui
	* @param lampe true si le Héros a une lampe
	* @param xH la position x du Héros
	* @param yH la position y du Héros
  */
	public void setVisibles(boolean lampe, int xH, int yH){
		// Pour chaque VueCase
		for(int y = 0; y<3; y++){
			for(int x = 0; x<3; x++){
				// si il y a une lampe
				// si c'est le heros
				// ou si elle est directement à coté du héros
				if(lampe || (x == xH && y == yH) || ((x == xH-1 || x == xH+1) && y == yH) || ((y == yH-1 || y == yH+1) && x == xH)){
					// la case est visible
					this.vues[x+(y*3)].setVisible(true);
				}

				// sinon elle est cachée
				else{
					this.vues[x+(y*3)].setVisible(false);
				}
				
				this.vues[x+(y*3)].repaint();
			}
		}
	}
}