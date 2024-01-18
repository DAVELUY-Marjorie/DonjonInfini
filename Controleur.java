import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Classe publique gerant le déroulement du jeu.
 *
 * Elle gère les interactions avec l'utilisateur
 * via l'appui sur une touche.  
 * Elle implémente donc KeyListener.
 * @author Mab
 * @version 1.00
 */
public class Controleur extends JPanel implements KeyListener{
	public static final int Fr = 0;
	public static final int Ang = 1;

	private int langue;

	
	//Déclaration et initialisation des coordonnées du Héros à 0
  private int xHeros = 1;
  private int yHeros = 1;

	//Déclaration et initialisation du compteur de point à 0
  private int points = 0;

	//Déclaration et initialisation du tableau contenant les 9 Cases
	private Case[] cases = new Case[9];

	//Déclaration du plateau
	private VuePlateau plateau;

	//Déclaration et initialisation du meilleur score à 0
	private int best = 0;

	
	/**
	* La méthode publique Controleur est un constructeur de la classe Controleur.
	* Elle permet la construction d'un nouveau controleur, 
	* en récupérant les données sauvegardées 
	* ou en générant un nouveau plateau aléatoire
	* @param lng la valeur de la langue choisie
	*/
	public Controleur(int lng){
		super();
		this.setLayout(new GridLayout(1,1));

		for(int i=0;i < this.cases.length;i++){ 
			if(i != 4){
				this.cases[i] = Case.newRandomCase();
			}

			// On met le Héros au milieu
			else{
				this.cases[i] = new Heros();
			}
		}

		// Récupération de la sauvegarde
		
		try{
			FileInputStream fis = new FileInputStream("sauvegarde.bin");
			ObjectInputStream dis = new ObjectInputStream(fis);
			try{
				this.xHeros = dis.readInt();
				this.yHeros = dis.readInt();
				this.points = dis.readInt();
				for(int i=0;i < 9;i++){ 
					this.cases[i] = (Case) dis.readObject();
				}
				
			}catch(IOException e){
				System.out.println("Problème d'ouverture du fichier de sauvegarde");
			}catch(ClassNotFoundException e){
				System.out.println("Problème d'identification de la sauvegarde");
			}
			try{
				dis.close();
			}catch(IOException e){
				System.out.println("Problème de fermeture du fichier de sauvegarde");
			}
		}catch(FileNotFoundException e){
			// Création des cases aléatoires 
			//cas de première mise en route 
			//ou de suppression manuelle de la sauvegarde
			for(int i=0;i < this.cases.length;i++){ 
				if(i != 4){
					this.cases[i] = Case.newRandomCase();
				}
	
				// On met le Héros au milieu
				else{
					this.cases[i] = new Heros();
				}
			}
		}catch(IOException e){
			// normal en cas de nouvelle partie
				// Création des cases aléatoires 
		    for(int i=0;i < this.cases.length;i++){ 
					if(i != 4){
			      this.cases[i] = Case.newRandomCase();
					}
		
					// On met le Héros au milieu
					else{
						this.cases[i] = new Heros();
					}
				}
			}

		try{
			FileInputStream fis = new FileInputStream("record.bin");
			DataInputStream dis = new DataInputStream(fis);
			try{
				this.best = dis.readInt();
			}catch(IOException e){
				System.out.println("Problème d'ouverture du fichier de score");
			}
			try{
				dis.close();
			}catch(IOException e){
				System.out.println("Problème de fermeture du fichier de score");
			}
		}catch(FileNotFoundException e){
				//cas de première mise en route 
				//ou de suppression manuelle du fichier
		}

		this.langue = lng;
		for(int i=0; i<9; i++){
			this.cases[i].setLangue(this.langue);
		}

		// Création et affichage du plateau
		this.plateau = new VuePlateau(this.cases, this.points, this.best, this.langue);
		Heros h = (Heros) this.cases[xHeros + (yHeros*3)];
		this.plateau.setVisibles(h.getLampe(true), xHeros, yHeros); 
		this.add(this.plateau);
	}




	/**
	* La méthode privée rencontre change la position du héros sur le plateau.
	* 
	* Elle génère une nouvelle case.
	* Elle gère aussi l'augmentation des points
 	* et la rencontre avec une jarre.
	* @param xNew La nouvelle position x du Héros
 	* @param yNew La nouvelle position y du Héros
	* @param jf La fenêtre du jeu
	* @return Une chaîne de caractères décrivant la case
	*/
  private void rencontre(int xNew, int yNew, JFrame jf){
    //Définition d'une valeur de repère pour le parcours des cases
		int cNew= (yNew * 3) + xNew;
		int cHeros = (yHeros * 3) + xHeros;
		
		if(this.cases[cNew] instanceof CaseBonus){
      //Incrémentation des points de la valeur de la case 
      //lors d'une rencontre avec une CaseBonus
			this.points += this.cases[cNew].getValeur();
			this.plateau.upPoint(this.cases[cNew].getValeur());

			if(this.cases[cNew] instanceof Jarre){
				Jarre j = (Jarre) this.cases[cNew];
				String str;
				switch(j.getTresor()){
					case 0 : 
						if(this.langue ==1){
							str = "A spider bites you!\nYou lose " + j.getValeur() + " life points";
						}
						else{
							str = "Une araignée vous mord !\nVous perdez " + j.getValeur() + " points de vie";
						}
						break;
					case 1 :
						if(this.langue ==1){
							str = "You find a cup of coffee and drink it.\nYou feel much more energetic!\nYou win " + j.getValeur() + " damage points";
						}
						else{
							str = "Vous trouvez une tasse de café et vous la buvez.\nVous redoublez d'énergie !\nVous gagnez " + j.getValeur() + " points d'attaque";
						}
						break;
					case 2 : 
						if(this.langue ==1){
							str = "You find an apple and eat it.\nYou feel much better!\nYou win " + j.getValeur() + " life points";
						}
						else{
							str = "Vous trouvez une pomme et vous la mangez.\nVous vous sentez beaucoup mieux !\nVous gagnez " + j.getValeur() + " points de vie";
						}
						break;
					case 3 :
						if(this.langue ==1){
							str = "You find an ancien artefact\nIt must be worth a lot!\nYou win " + j.getValeur() + " points";
						}
						else{
							str = "Vous trouvez un artefact ancien.\nIl doit valoir une fortune !\nVous gagnez " + j.getValeur() + " points";
						}
						break;
					default :
						str = "Erreur";
						break;
				}
				JOptionPane.showMessageDialog(jf, str);
			}
		}

		

		// Actualisation du tableau cases[]
		this.cases[cNew] = this.cases[cHeros];
		this.cases[cHeros] = Case.newRandomCase();
		this.cases[cHeros].setLangue(this.langue);
		
		//Actualisation des coordonnées du Héros
		this.plateau.update(this.cases[cHeros], this.xHeros, this.yHeros);
		this.plateau.update(this.cases[cNew], xNew, yNew);
		this.xHeros = xNew;
		this.yHeros = yNew;

		if(this.best<this.points){
			this.plateau.updateRecord(this.points); 
		}

    //Actualisation du graphique du plateau
		this.plateau.repaint();
  }




	/**
	* La méthode publique keyPressed avec un argument lance le mouvement de l'utilisateur.
	* 
	* Elle vérifie que le mouvement est valide.
	* Elle lance la mort du Héros
 	* Elle gère les modifications dues 
	* à des cases spcécifiques
	* @param e la case pressée par l'utilisateur
	*/
@Override
	public void keyPressed(KeyEvent e) {
		// Récupération de la touche enfoncée
		int code = e.getKeyCode();

		// Récupération de l'indice du Héros dans le tableau cases
		int cHeros =(yHeros * 3) + xHeros;

		// Récupération du héros
		Heros h = (Heros) this.cases[cHeros];

		// Initialisation des nouvelles coordonnées du héros
		int xnew = this.xHeros;
		int ynew = this.yHeros;

		 // Si on enfonce la flèche gauche  et qu'on ne sort pas du plateau
		if((code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) && (xHeros - 1 >= 0)){
			xnew = xHeros-1;
		}
		
		// Si on enfonce la flèche droite  et qu'on ne sort pas du plateau
		if ((code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) && (xHeros + 1 <= 2)){
			xnew = xHeros+1;
		}
		
		// Si on enfonce la flèche haut  et qu'on ne sort pas du plateau
		if ((code == KeyEvent.VK_UP || code == KeyEvent.VK_KP_UP) && (yHeros - 1 >= 0)){
			ynew = yHeros-1;
		}

		 // Si on enfonce la flèche bas  et qu'on ne sort pas du plateau
		if ((code == KeyEvent.VK_DOWN || code == KeyEvent.VK_KP_DOWN) && (yHeros + 1 <= 2)){
			ynew = yHeros+1;
		}

		// Récupération du nouvel indice du Héros dans le tableau cases
		int cNew = (ynew * 3) + xnew;

		// Si le Héros n'est pas sorti des limites du plateau
		if (cNew != cHeros){

			// On voit s'il y a un champoison sur le plateau
			for (Case ca : cases){
				if(ca instanceof ChamPoison){
					h.perdrePv(ca.getValeur()/2);
				}else{
          if(ca instanceof Bombe){
						Bombe b = (Bombe) ca;
            if(b.getTour()==0){
							String bmb;
							if(this.langue == 1){
								bmb = "The bomb exploded";
							}
							else{
								bmb = "La bombe a explosé";
							}
							JOptionPane.showMessageDialog((JFrame) e.getComponent(), bmb);
              this.mourir((JFrame) e.getComponent());
            }
          }
        }
			}

			// bouge est vrai si le Héros n'est pas en train de tuer un Ennemi (donc qu'il se déplace) 
			boolean bouge = h.getValeur() < this.cases[cNew].getValeur() || !(this.cases[cNew] instanceof CaseEnnemie);

			// Si le Héros ne meurt pas
			if(h.rencontrer(this.cases[cNew])){
				// Si le Héros se déplace, on appelle rencontre pour le déplacement
				if (bouge){
					this.rencontre(xnew, ynew, (JFrame) e.getComponent());
				}

				// Si le Héros ne se déplace pas (qu'il tue un Monstre)
				else{
          if(this.cases[cNew] instanceof Monstre){
            // On crée une case Or à la place du Monstre de valeur équivalente
					this.cases[cNew] = new   Or(this.cases[cNew].getValeur());
          }else{
            if(this.cases[cNew] instanceof Sorcier){
              // On crée une case Potion à la place du Monstre de valeur équivalente
					this.cases[cNew] = new Potion(this.cases[cNew].getValeur());
            }else{
              if(this.cases[cNew] instanceof ChamPoison){
                this.cases[cNew] = new Or((this.cases[cNew].getValeur())/2);
              }else{
                if(this.cases[cNew] instanceof Bombe){
                this.cases[cNew] = new Arme(this.cases[cNew].getValeur());
                }
              }
            }
          }
					this.cases[cNew].setLangue(this.langue);
					this.plateau.update(this.cases[cNew], xnew, ynew);
				}
				// On remet à jour la visibilité des cases
				this.plateau.setVisibles(h.getLampe(false), this.xHeros, this.yHeros);
			}
			// Si le Héros meurt
			else{
				this.mourir((JFrame) e.getComponent());
			}
		}
	}



	
	
	// Les méthodes obligatoire d'un KeyListener qui ne sont pas utilisées ici
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}




	/**
	* La méthode privée mourir finit le jeu après la mort du Héros
	* 
	* Elle affiche le score en comparaison au record,
  * elle supprime une possible sauvegarde,
	* et ferme la fenêtre
	* @param jf la JFrame du jeu
	*/
	private void mourir(JFrame jf){
		String alerte;
		if(this.langue ==1){
			alerte = "Best score : " + this.best + "\nPoints : " +  this.points;
		}
		else{
			alerte = "Record : " + this.best + "\nPoints : " +  this.points;
		}

		if(this.best < this.points){
			if(this.langue ==1){
				alerte += "\n New best score !";
			}
			else{
				alerte += "\n Nouveau record !";
			}
			
			try{
				FileOutputStream fos = new FileOutputStream("record.bin");
				DataOutputStream dos = new DataOutputStream(fos);
			  try{
					dos.writeInt(this.points);
				}catch(IOException e){
					System.out.println("Mourir : Problème d'écriture du fichier de score");
				}
				try{
					dos.close();
				}catch(IOException e){
					System.out.println("Mourir : Problème de fermeture du fichier de score");
				}
	    }catch(IOException e){
				System.out.println("Mourir : Problème d'ouverture du fichier de score");
			}
		}

		// On renvoie le nombre de points
		JOptionPane.showMessageDialog(jf, alerte);

		// On ferme la fenêtre
		jf.dispose();

		//sauvegarde	
		try{
			new FileOutputStream("sauvegarde.bin").close();
		}catch(IOException e){
			System.out.println("Mourir : Problème de suppression de la sauvegarde");
		}
	}




	/**
	* La méthode public backUp sauvegarde l'état du plateau
	* 
	* Elle sauvegarde les coordonnées du héros,
 	* ses points,
	* et toutes les cases
	*/
	public void backUp() {
		try{
			FileOutputStream fos = new FileOutputStream("sauvegarde.bin");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.flush();
			
			try{
				oos.writeInt(this.xHeros);
				oos.writeInt(this.yHeros);
				oos.writeInt(this.points);
				for(int i=0; i<9; i++){
					oos.writeObject(this.cases[i]);
				}
						
			}catch(IOException ex){
				System.out.println("Backup : Problème d'enregistrement de la sauvegarde");
			}
			
			try{
				oos.close();
			}catch(IOException ex){
				System.out.println("Backup : Problème de fermeture du fichier de sauvegarde");
			}
		}catch(IOException ex){
			System.out.println("Backup : Problème d'ouverture du fichier de sauvegarde");
		}
	}
}