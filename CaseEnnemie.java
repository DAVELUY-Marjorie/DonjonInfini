import java.util.Random;

/**
 * Classe publique définissant les caractéristiques d'une CaseEnnemie
 *
 * C'est un type de case , elle hérite donc de Case.
 * @author Mab
 * @version 1.00
 */
public class CaseEnnemie extends Case{

    protected int pv; // nombre de pv total


	
/**
 * La méthode publique newRandomCase 
 * permet de renvoyer une nouvelle Case aléatoire avec 4 chances
 * sur 10 que ce soit un ennemie et 6/10 une case bonus.
 * @return Une nouvelle case aléatoire.
 */
  public static Case newRandomCase(){
    Random r = new Random();
		
    //On défini une valeur entre 0 et 19
		int cpt = r.nextInt(20);
		
    //Si elle est entre 0 et 9 on retourne un nouveau Monstre (10/20)
		if(cpt < 10){
			return new Monstre();
		}
			
		else{
			
      if(cpt < 16){
        //Si elle est entre 10 et 15 on retourne un nouveau Sorcier (6/20)
				return new Sorcier();
      }
			
			else{
        if(cpt < 19){
        //Si elle est entre 16 et 18 on retourne un nouveau Sorcier (3/20)
        return new ChamPoison();
        }
				
				else{
          //Si elle est à 19 on retourne une nouvelle Bombe (1/20)
          return new Bombe();
        }
      }
		}    
	}



	
  //on remplit ces fonctions car on doit les définir mais elles ne servent pas
	@Override
	public String getLabel(){
    String string="erreur";
  
    return string;
  }



	
	@Override
	public String getLabelPv(){
    String string="Erreur getLabelPv";

    return string;
  }
}