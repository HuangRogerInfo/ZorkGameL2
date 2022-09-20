package zork;
import java.util.ArrayList;
import java.util.HashSet;

public class Chien extends AbstractAnimal implements Adoptable
{
    boolean libre;
    Direction sortieProposee;

    public Chien(String nom_p, Piece Piece_p){
        super(nom_p, 100, 100, 50, Piece_p);
        libre = true;
        sortieProposee=null;
    }
    
    /**
     * Rend un chien libre ou non selon le parametre booléen
     * 
     * @param b le booleen qui decide si le chien est libre ou non
     */
    public void setLibre(boolean b){
        this.libre= b;
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
        return 5;
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
        set1.add(typeAliment.ANIMAL);
        set1.add(typeAliment.VEGETAL);
        return set1;
    }

   //fonction de l'interface adoptable

    /**
	 * Renvoie true si cet animal est libre.
	 * 
	 * @return true si cet animal est libre, false sinon.
	 * 
	 * @pure
	 */
    public boolean estLibre(){
        return this.libre;
    }

    /**
	 * Fait de cet animal un animal adopté.
	 * 
	 * @return true si cet animal était libre, false sinon.
	 * 
	 * @ensures !estLibre();
	 * @ensures \result <==> (estLibre() != \old(estLibre()));
	 */
    public boolean adoption(){
        if(estLibre() ){
            libre = false;
            return true;
        }
        return false;
    }

    /**
	 * Libère cet animal.
	 * 
	 * @return true si cet animal était précédemment adopté, false sinon.
	 * 
	 * @ensures estLibre();
	 * @ensures \result <==> (estLibre() != \old(estLibre()));
	 */
    public boolean liberation(){
        if(estLibre()){
            return false; 
        }
        libre = true;
        return true;
    }

    /**
	 * Propose ou impose la sortie spécifiée pour la pièce spécifiée. Lorsque cet
	 * animal est adopté cette sortie proposée sera la sortie choisie (par la
	 * méthode choisirSortie) pour le prochain déplacement de cet animal, si ce
	 * déplacement se fait à partir de cette pièce.
	 * 
	 * @param p la pièce pour laquelle une sortie est proposée
	 * @param d sortie proposée pour la pièce spécifiée
	 * 
	 * @throws NullPointerException si l'un des arguments spécifiés est null
	 * @throws IllegalArgumentException si la pièce spécifiée ne possède pas de sortie dans la direction spécifiée
	 * 
	 * @requires p != null;
	 * @requires d != null;
	 * @requires p.pieceSuivante(d) != null;
	 * @ensures getSortieProposee(p) == d;
	 */
    public Direction getSortieProposee(Piece p){
        return sortieProposee;
    }

    /**
	 * Renvoie une sortie proposée pour la pièce spécifiée. La sortie renvoyée est
	 * celle proposée par le dernier appel à proposerSortie pour la pièce spécifiée,
	 * ou null si cet animal s'est déplacé (appel à la méthode deplacerDepuis)
	 * depuis le dernier appel à proposerSortie.
	 * 
	 * @param p pièce dont on veut connaitre la sortie proposée.
	 * @return sortie proposée pour la pièce spécifiée ou null si aucune sortie n'a
	 *         été proposée depuis le dernier déplacement.
	 * 
	 * @throws NullPointerException si l'un des arguments spécifiés est null
	 * 
	 * @requires p != null;
	 * @ensures (\result != null) ==> (p.pieceSuivante(\result) != null);
	 * @ensures !estLibre() ==> (\result == choisirSortie(p));
	 * 
	 * @pure
	 */
    public void proposerSortie(Piece p, Direction d){
        this.sortieProposee = d; 
    }
    
    /**
	 * {@inheritDoc}
	 * @param p la pièce à partir de laquelle a lieu le déplacement
	 * @return la nouvelle pièce dans laquelle se trouve cet animal ou null si le
	 *         déplacement n'a pas eu lieu.
	 * 
	 * @throws NullPointerException     si l'argument spécifié est null
	 * @throws IllegalStateException    si cet animal est mort
	 * @throws IllegalArgumentException si cet animal n'est pas présent dans la
	 *                                  pièce spécifiée avant appel de cette méthode
	 * 
	 * @requires p != null;
	 * @requires estVivant();
	 * @requires p.contientAnimal(this);
	 * @ensures getSortieProposee(p) == null;
	 */
	@Override
    public Piece deplacerDepuis(Piece p) throws NullPointerException, IllegalStateException {
        if(p == null){
            throw new NullPointerException("deplacer depuis piece null!");
        }
        if(!(this.estVivant())){
            throw new IllegalStateException("deplacer un chien mort!");
        }
        if(! (this.getpieceActuelle().getDescription().equals(p.getDescription())) ){
            throw new IllegalArgumentException("Chien ne peut pas se deplacer depuis cette piece, car il n'est pas dans cette piece!");
        }

        Direction A = choisirSortie(p);
        if(A == null){ 
            System.out.println("(Chien) " + this.getNom() + " ne s'est pas deplace"+ " <" + this.getCapitalVie() +"pv"+ ">");
            return null;
        }
        while(p.pieceSuivante(A) == null){
            A = choisirSortie(p);
        }
        
        p.retirerAnimal(this);
        p.pieceSuivante(A).ajouterAnimal(this);

        System.out.println("(Chien) " + this.getNom() + " passe de " + this.getpieceActuelle().getDescription() + " a " + p.pieceSuivante(A).getDescription()+ " <" + this.getCapitalVie() +"-"+ this.getCoutDeplacement()+"pv" + ">");
        this.setPieceActuelle(p.pieceSuivante(A));
        this.setCapitalVie(this.getCapitalVie() - this.getCoutDeplacement());

        if(!(this.estVivant())){
            this.mourirDans(this.getpieceActuelle());
            System.out.println(this.getNom() + " est mort a cause de l'action deplacer");
            for(Aliment al: this.getListObjets()){
                this.getpieceActuelle().ajouterA(al);
                this.getListObjets().remove(al);
            }
        }
        return this.getpieceActuelle();
    }
    
    /**
	 * {@inheritDoc}
	 * 
	 * Lorsque cet animal est adopté, la sortie choisie doit impérativement être
	 * celle renvoyée par la méthode getSortieProposee, l'entitée ayant adopté cet
	 * animal doit donc imposer le choix en appelant la méthode proposerSortie avant
	 * d'effectuer un déplacement à l'aide de la méthode deplacerDepuis. Si cet
	 * animal est libre, il n'y a aucune obligation à ce que le choix effectué
	 * prenne en compte la proposition de sortie renvoyée par la méthode
	 * getSortieProposee.
	 * 
	 * 
	 * @requires p != null;
	 * @ensures !estLibre() ==> (\result == getSortieProposee(p));
	 * 
	 * @pure
	 */
	@Override
    public Direction choisirSortie(Piece p) throws NullPointerException {
        if(p == null){
            throw new NullPointerException("Piece null");
        }

        if( !(this.estLibre()) ){
            return sortieProposee;
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
     * Renvoie true si l'ajout d'un aliment à l'inventaire du chien est possible, false sinon 

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
     * Renvoie true si le retrait d'un aliment à l'inventaire du chien est possible, false sinon 

     * @param A l'aliment à retirer
     * @return true si le retrait et possible false sinon
     */
    public boolean retraitPossible(Aliment A){
        return true;
    }

}
