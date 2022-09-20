package zork;
import java.util.ArrayList;

/* Un container ou conteneur en français sera toute classe qui contient des objetsZork
   Exemple : Chaque joueur a un inventaire d'E / Chaque piece contient des objetsZork */

public interface Container<E extends ObjetZork> {

    /*Methodes associées à un conteneur */
    public ArrayList<E> getListObjets();

    public boolean ajouter(E oz);
    public boolean retirer(E oz);

    public boolean contient (E oz);
    public int contientCombienDe(E oz);
    public int getnbObjets();
    
    public boolean ajoutPossible(E oz);
    public boolean retraitPossible(E oz);
}