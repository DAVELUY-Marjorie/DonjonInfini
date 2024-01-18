import javax.swing.*;
import java.awt.*;

/**
 * Classe publique gerant l'affichage d'une case
 *
 * C'est un élément graphique.  
 * Elle hérite donc de JComponent.
 * @author Mab
 * @version 1.00
 */
public class VueCase extends JComponent {
	private static final Image cache = Toolkit.getDefaultToolkit().getImage("icones/brouillard.png");
	
	private Case id; 

	private boolean visible = false;

	private Image img;
	
  //Création du titre et de la valeur de la case
	private JLabel titre = new JLabel();
	private JLabel val = new JLabel();

	
	/**
 * La méthode publique VueCase est le constructeur de la classe VueCase.
 * Elle permet la construction d'une nouvelle Vuecase qui affiche la Case donnée en argument
 * @param ca la case à afficher
 */
	public VueCase(Case ca){
		this.id = ca;
    //Création de la grille contenant les textes
		this.setLayout(new GridLayout(2,1));

    //Mise en forme et ajout du Titre de la case
		this.titre.setHorizontalAlignment(JLabel.CENTER);
		this.titre.setVerticalAlignment(JLabel.TOP);
		this.titre.setForeground(Color.black);
		this.titre.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(this.titre);

    //Mise en forme et ajout de la valeur de la case
		this.val.setHorizontalAlignment(JLabel.RIGHT);
		this.val.setVerticalAlignment(JLabel.BOTTOM);
		this.val.setForeground(Color.black);
		this.val.setFont(new Font("Arial", Font.BOLD, 15));
		this.add(this.val);
	}

  
	
	
	@Override
	protected void paintComponent(Graphics p) {
		Graphics pinceau = p.create();

    //Récupération des informations de la case que représente la Vuecase
		String v = this.id.getLabelPv();
		Color c = new Color(255,255,255);

		if(this.getVisible()){
			this.val.setForeground(Color.black);
			this.titre.setForeground(Color.black);
			this.img = this.id.getIcone();
	    //Définition d'une couleur de fond en fonction de la classe de la Case
			if(this.id instanceof Monstre) {
					c = new Color(204,51,51);
			}
			else{
				if(this.id instanceof Potion) {
					c = new Color(179,132,202);
				}
				else{
					if(this.id instanceof Arme) {
					c = new Color(109,218,218);
					}
					else{
						if(this.id instanceof Or) {
							c = new Color(245,200,23);
						}else{
							if(this.id instanceof Sorcier) {
								c = new Color(102,153,102);
							}else{
								if(this.id instanceof ChamPoison){
									c = new Color(110,110,110);
								}else{
									if(this.id instanceof Bombe){
									c = new Color(243,114,32);
									}else{
										if(this.id instanceof Jarre){
											c = new Color(252,154,68);
										}
										else{
											if(this.id instanceof Lampe){
												c = new Color(0,0,0);
												this.val.setForeground(Color.white);
												this.titre.setForeground(Color.white);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			//Affichage des textes dans les JLabel correspondants
			this.titre.setText(this.id.getLabel());
			this.val.setText(v);
		}
		// Si la case n'est pas visible
		else{
			c = new Color(0,0,0);
			this.img = VueCase.cache;
			
			//On elève les textes des JLabel
			this.titre.setText("");
			this.val.setText("");
		}
  
    // Affichage de la case avec la couleur correspondante
		pinceau.setColor(c);
		pinceau.fillRect(0,0,this.getWidth(), this.getHeight());

		// Affichage de l'image si la case n'est pas trop petite
		if(this.getHeight()>60 && this.getWidth()>110){
		pinceau.drawImage(this.img, this.getHeight()/5, this.getHeight()/3, this.getHeight()/2, this.getHeight()/2, this);
		}
	}



	
	/**
 * La méthode publique setCase permet de changer la case affichée.
 * Elle remplace la case représentée par la VueCase par celle mise en paramètre.
 * @param c la nouvelle Case à afficher
 */
	public void setCase(Case c) {
    this.id = c;
		this.repaint();
	}

	
	/**
 * La méthode publique setVisible permet de changer la visibilité de la case.
 * Elle change la valeur de l'attribut visible à celle mise en argument.
 * @param visibility la nouvelle valeur de visible
 */
	public void setVisible(boolean visibility){
		this.visible = visibility;
		this.repaint();
	}



	
	/**
 * La méthode publique getVisible renvoie la valeur de
 * l'attribut visible à celle mise en argument.
 * @return la valeur de visible
 */
	public boolean getVisible(){
		return this.visible;
	}
}