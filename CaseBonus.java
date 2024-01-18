import java.util.Random;

/**
 * Classe publique définissant les caractéristiques d'une CaseBonus
 *
 * C'est un type de case , elle hérite donc de Case.
 * @author Mab
 * @version 1.00
 */
public class CaseBonus extends Case{

	
  public static Case newRandomCase(){
    Random r = new Random();
    //On défini une valeur entre 0 et 19
		int cpt = r.nextInt(20);
    //Si elle est entre 0 et 7 on retourne un nouveau Or (7/20)
		if(cpt < 7){
			return new Or();
		}
		else{
      //Si elle est entre 8 et 14 on retourne une nouvelle Arme (6/20)
			if(cpt < 13){
				return new Arme();
			}
			else{
        //Si elle est entre 14 et 17 on retourne une nouvelle Potion (5/20)
				if(cpt < 18){
					return new Potion();
				}
				else{
					//Si elle est entre 18 et 19 on retourne une nouvelle Jarre (2/20)
					if(r.nextInt(4) > 0){
						return new Jarre();
					}
					else{
						return new Lampe();
					}
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