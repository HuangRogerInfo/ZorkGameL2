package zork;
import java.util.HashSet;
import java.util.Random;

public class Chevre extends AbstractAnimal{

    public Chevre( String nom_p, Piece Piece_p){
        super(nom_p, 100, 100, 50, Piece_p);
    }

    /**
	 * Renvoie une description courte de cet animal incluant son nom.
	 * 
	 * @return une description courte de cet animal
	 * 
	 * @ensures \result.contains(getNom());
	 * 
	 * @pure
	 */
    public String descriptionCourte(){
        String A = getNom();
        return A;
    }
    
    /**
	 * Renvoie le cout d'un déplacement de cet animal. Lors de chaque déplacement de
	 * cet animal, son capital vie sera diminué de ce coût.
	 * 
	 * @return le cout d'un déplacement de cet animal
	 * 
	 * @pure
	 */
    public int getCoutDeplacement(){
        return 10;
    }

    /**
	 * Renvoie le cout d'une action "ramasser" de cet animal. Lors de chaque
	 * ramassage d'aliment effectué par cet animal, son capital vie sera diminué de
	 * ce coût.
	 * 
	 * @return le cout d'une action "ramasser" pour cet animal
	 * 
	 * @pure
	 */
    public int getCoutRamasser(){
        return 10;
    }

    /**
	 * Renvoie l'ensemble des types d'aliment que cet animal peut manger.
	 * 
	 * @return le régime alimentaire de cet animal
	 * 
	 * @ensures \result != null;
	 * @ensures !\result.isEmpty();
	 * @ensures (\forall Aliment al; al != null && \result.contains(al.getType()); peutManger(al));
	 * 
	 * @pure
	 */
    public HashSet<typeAliment> getRegimeAlimentaire(){
        HashSet<typeAliment> set1 = new HashSet<typeAliment>();
        set1.add(typeAliment.VEGETAL);
        return set1;
    }

    /**
	 * Choisit une des sorties de la pièce spécifiée. Les classes implémentant cette
	 * interface sont entièrement libres des critères de choix de la pièce, il n'est
	 * pas imposé qu'une sortie soit choisie.
	 * 
	 * 
	 * @param p la pièce dont une sortie doit être choisie
	 * @return une sortie de la pièce spécifiée ou null
	 * 
	 * @throws NullPointerException si l'argument spécifié est null
	 * 
	 * @requires p != null;
	 * @ensures (\result != null) ==> (p.pieceSuivante(\result) != null);
	 * 
	 * @pure
	 */
    public Direction choisirSortie(Piece p) throws NullPointerException {
        if(p == null){
            throw new NullPointerException("Piece null");
        }

        int a = Random();
        if(a == 0){
            return Direction.NORD;
        }
        else if(a == 1){
            return Direction.OUEST;
        }
        else if(a == 2){
            return Direction.EST;
        }
        else if(a == 3){
            return Direction.SUD;
        }
        return null;
    }

     /** 
     * Renvoie true si l'ajout d'un aliment à l'inventaire de la chevre est possible, false sinon 

     * @param A l'aliment à ajouter
     * @return true si l'ajout et possible false sinon
     */
    public boolean ajoutPossible(Aliment A){
        if(this.getnbObjets()>=3){
            return false;
        }
        return true;
    }

     /** 
     * Renvoie true si le retrait d'un aliment à l'inventaire de la chevre est possible, false sinon 

     * @param A l'aliment à retirer
     * @return true si le retrait et possible false sinon
     */
    public boolean retraitPossible(Aliment A){
        return true;
    }
    
}