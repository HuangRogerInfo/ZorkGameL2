package zork;
import java.util.ArrayList;

//conteneur implémenté grâce aux arrayList
public abstract class AbstractArrayListContainer<E extends ObjetZork> implements Container<E>{

    private ArrayList<E> listeDeE;

    /*Constructeur*/
    public AbstractArrayListContainer(){
        listeDeE = new ArrayList<E>();
    }

    /*Getters*/
    public ArrayList<E> getListObjets() {
		return listeDeE;
    }

    /*Fonctions*/
    public boolean ajouter(E oz){
        if(ajoutPossible(oz)){
            listeDeE.add(oz);
            return true;
        }
        return false;
    }

    public boolean retirer(E oz){
        if(retraitPossible(oz)){
            return listeDeE.remove(oz);
        }
        return false;
    } 

    public boolean contient(E oz){
        return listeDeE.contains(oz);
    }

    public int contientCombienDe(E o){
		int res = 0;
		for(E obj: listeDeE){
			if(obj.equals(o))
					res++;
		}
		return res;
	}

    public int getnbObjets(){
        return listeDeE.size();
    }  
    
}