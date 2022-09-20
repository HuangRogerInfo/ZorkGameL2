package zork;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public abstract class AbstractAnimal extends AbstractArrayListContainer <Aliment> implements Animal{
    private String nom;
    private int pv;
    private int pvMax;
    private int valeurNutritive;
    private Piece pieceActuelle;

    public AbstractAnimal( String nom, int pv, int pvMax, int valeurNutritive, Piece p){
        this.nom = nom;
        this.pvMax = pvMax;
        this.pv = pv;
        this.valeurNutritive = valeurNutritive;
        this.pieceActuelle = p;
    }

    /**
     * Change la piece actuelle de l'animal en la piece donnée en argument
     * @param p la nouvelle piece actuelle de l'animal
     */
    public void setPieceActuelle(Piece p){
        this.pieceActuelle = p;
    }

    /**
     * retourne la piece actuelle de l'animal
     * @return la piece actuelle de l'animal
     */
    public Piece getpieceActuelle(){
        return pieceActuelle;
    }

    /**
     * Change le capital vie de l'animal en la valeur donnée
     * @param a le nouveau capital vie
     */
    public void setCapitalVie(int a){
        pv = a;
    }

    //FONCTION INTERFACE ANIMAL

    /**
	 * Renvoie le nom de cet animal.
	 * @return le nom de cet animal
	 * 
	 * @pure
	 */
    public String getNom(){
        return this.nom;
    }
    
   /**
	 * Renvoie le nombre de points de vie de cet animal.
	 * 
	 * @return le capital vie de cet animal
	 * 
	 * @pure
	 */
    public int getCapitalVie(){
        return this.pv;
    }

    /**
	 * Renvoie une description longue de cet animal incluant son nom et les aliments
	 * contenus dans sa réserve.
	 * 
	 * @return une description longue de cet animal
	 * 
	 * @ensures \result.contains(getNom());
	 * 
	 * @pure
	 */
    public String descriptionLongue(){
        String A = this.descriptionCourte();
        A+= "| Reserve : ";
        for(Aliment al: this.getListObjets()){
            A+= al.getDescription() + ";" ;
        }
        return A;
    }

    /**
	 * Renvoie le nombre de points de vie maximal de cet animal.
	 * 
	 * @return le capital vie maximal de cet animal
	 * 
	 * @pure
	 */
    public int getMaxCapitalVie(){
        return this.pvMax;
    }

    /**
	 * Renvoie true si cet animal est vivant.
	 * 
	 * @return true si cet animal est vivant, false sinon
	 * 
	 * @pure
	 */
    public boolean estVivant(){
        if(getCapitalVie() > 0){
            return true;
        }
        return false;
    }

    /**
	 * Fait mourir cet animal et dépose tous les aliments de sa réserve dans cette
	 * pièce.
	 * 
	 * @param p la pièce dans laquelle cet animal meurt
	 * 
	 * @requires p != null;
	 * @ensures !estVivant();
	 * @ensures reserveEstVide();
	 * @ensures p.getNbObjets() == \old(p.getNbObjets() + getNbAliments());
	 * @ensures (\forall Aliment al; \old(contient(al)); p.contientObjet(al));
	 * 
	 * @throws NullPointerException si la pièce spécifiée est null
	 */
    public void mourirDans(Piece p){
        this.pv = 0;
        for(Aliment al: this.getListObjets()){
            p.ajouterA(al);
        }
        this.getListObjets().clear();
    }

    /**
	 * Renvoie la valeur nutritive de cet animal. Cette valeur est prise en compte
	 * dans le cas où cet animal serait mangé.
	 * 
	 * @return la valeur nutritive de cet animal
	 * 
	 * @pure
	 */
    public int getValeurNutritive(){
        return valeurNutritive;
    }

    /**
	 * Renvoie true si cet animal peut manger l'aliment spécifié.
	 * 
	 * @param al aliment dont on souhaite savoir s'il peut être mangé par cet animal
	 * @return true si cet animal peut manger l'aliment spécifié, false sinon.
	 * 
	 * @ensures (al == null) ==> !\result;
	 * @ensures (al != null) ==> (\result <==> getRegimeAlimentaire().contains(al.getType()));
	 * 
	 * @pure
	 */
    public boolean peutManger(Aliment al){
        for(typeAliment unType: this.getRegimeAlimentaire()){
            if(al.getfamilleAliment().equals(unType)){
                return true;
            }
        }
        return false;
    }
    
    /**
	 * Renvoie le nombre d'aliments présents dans la réserve de cet animal.
	 * 
	 * @return le nombre d'aliments présents dans la réserve de cet animal
	 * 
	 * @ensures \result >= 0;
	 * 
	 * @pure
	 */
    public int getNbAliments(){
        return this.getnbObjets();
    }

    /**
	 * Renvoie le nombre d'exemplaires de l'objet spécifié présents dans la réserve
	 * de cet animal.
	 * 
	 * @param obj l'objet dont on souhaite connaitre le nombre d'exemplaires dans la
	 *            réserve de cet animal
	 * @return le nombre d'exemplaires de l'objet spécifié
	 * 
	 * @ensures !(obj instanceof Aliment) ==> (\result == 0);
	 * @ensures \result >= 0;
	 * @ensures \result <= getNbAliemnts();
	 * @ensures (\result > 0) ==> contient(obj);
	 * 
	 * @pure
	 */
    public int getNbOccurences(Object obj){
        int compteur = 0;
        if(obj instanceof Aliment){
            Aliment A = (Aliment) obj;
            for(Aliment al: this.getListObjets()){
                if ( al.equals(A) ){
                    compteur++;
                }
            }
            return compteur;
        }
        return 0;
    }

    /**
	 * Renvoie true si l'objet spécifié est présent dans la réserve de cet animal.
	 * 
	 * @param obj l'objet dont on souhaite savoir s'il est présent dans la réserve
	 *            de cet animal
	 * @return true si la réserve de cet animal contient l'objet spécifié, false
	 *         sinon
	 * 
	 * @ensures !(obj instanceof Aliment) ==> !\result;
	 * @ensures \result ==> (getNbOccurences() > 0);
	 * 
	 * @pure
	 */
    public boolean contient(Object obj){
        if(! (obj instanceof Aliment) ){
            return false;
        }
        Aliment A = (Aliment) obj;
        return this.contient(A);
    }

    /**
	 * Renvoie true si la réserve de cet animal est vide.
	 * 
	 * @return true si la réserve de cet animal est vide
	 * 
	 * @ensures \result <==> (getNbAliments() == 0);
	 * 
	 * @pure
	 */
    public boolean reserveEstVide(){
        if(this.getnbObjets() == 0){
            return true;
        }
        return false;
    }

    /**
	 * Choisit dans la piece spécifiée un aliment à ramasser. Les classes
	 * implémentant cette interface sont entièrement libres des critères de choix de
	 * l'aliment, il n'est pas imposé qu'un aliment soit choisi.
	 * 
	 * 
	 * @param p la pièce dans laquelle l'aliment doit être choisi
	 * @return l'aliment choisi ou null si aucun aliment n'a été choisi.
	 * 
	 * @throws NullPointerException si l'argument spécifié est null
	 * 
	 * @requires p != null;
	 * @ensures (\result != null) ==> p.contientObjet(\result);
	 * 
	 * @pure
	 */
    public Aliment choisirAliment(Piece p) throws NullPointerException {
        if(p == null){
            throw new NullPointerException("Piece null");
        }
        
        if(p.getNbAliments() == 0){
            return null;
        }

        int test = 0;
        Aliment meilleurAliment = new Aliment(10, 10, "test", typeAliment.ANIMAL, 0);
        for(typeAliment unType : this.getRegimeAlimentaire()){
            for(Aliment al: p.getListAliments()){
                if(al.getfamilleAliment().equals(unType) ){
                    if(al.getValeurNutritive()>meilleurAliment.getValeurNutritive()){
                        meilleurAliment = al;
                        test = 1;
                    }
                }
            }
        }
        if(test==1){
            return meilleurAliment;
        }
        return null;  
    }

    /**
	 * Ramasse dans la pièce spécifié l'aliment renvoyé par la méthode
	 * choisirAliment. Si cette action a pour effet de tuer cet animal le contenu de
	 * sa réserve est transféré dans la pièce spécifié.
	 * 
	 * @param p la pièce dans laquelle un aliment doit être ramassé.
	 * @return l'aliment ramassé ou null si aucun aliment n'a été ramassé
	 * 
	 * @throws NullPointerException  si l'argument spécifié est null
	 * @throws IllegalStateException si cet animal est mort
	 * 
	 * @requires p != null;
	 * @requires estVivant();
	 * @ensures (\result == null) ==> (getNbAliments() == \old(getNbAliments()));
	 * @ensures (\result == null) ==> (getCapitalVie() == \old(getCapitalVie()));
	 * @ensures ((\result != null) && estVivant()) ==> contient(\result);
	 * @ensures ((\result != null) && estVivant()) ==> (getNbAliments() == \old(getNbAliments() + 1));
	 * @ensures ((\result != null) && estVivant()) ==> (p.getNbObjets() == \old(p.getNbObjets() - 1));
	 * @ensures ((\result != null) && estVivant()) ==> (getCapitalVie() == \old(getCapitalVie() - getCoutRamasser()));
	 * @ensures ((\result != null) && !estVivant()) ==> reserveEstVide();
	 * @ensures ((\result != null) && !estVivant()) ==> (p.getNbObjets() == \old(p.getNbObjets() + getNbAliments()));
	 */
    public Aliment ramasserDans(Piece p) throws NullPointerException, IllegalStateException {
        if(p == null){
            throw new NullPointerException("Piece null");
        }
        if(!(this.estVivant())){
            throw new IllegalStateException("Animal mort");
        }

        Aliment A = choisirAliment(p);
        if(A==null){
            return null;
        }

        int pvAvant =this.pv;
        this.pv -= this.getCoutRamasser();
        

        //si l'animal meurt a cause de l'action ramasser
        if( !(this.estVivant() )){
            System.out.println(this.getNom() + " est mort a cause de l'action ramasser"+ " <" + pvAvant +"-"+ this.getCoutRamasser()+"pv" + ">" );
            mourirDans(this.pieceActuelle);
            return A;
        }
    
        if(this.ajouter(A)){
        System.out.println("(" + this.getClass().getSimpleName() + ") " +this.nom + " a ramasse l'aliment " + A.getDescription()+ " <" + pvAvant +"-"+ this.getCoutRamasser()+"pv" + ">" );
        this.pieceActuelle.retirerA(choisirAliment(p));
        }
        return A; 
    }

    /**
	 * Déplace cet animal vers une pièce voisine de la pièce spécifiée. Le
	 * déplacement a lieu dans la direction renvoyée par la méthode choisirSortie, si
	 * choisirSortie renvoie null aucun déplacement n'a lieu. Si le déplacement a
	 * lieu, le capital vie de cet animal est diminué de la valeur renvoyée par la
	 * méthode getCoutDeplacement. Seul un animal vivant peut se déplacer. Si ce
	 * déplacement a pour effet de tuer cet animal, l'animal est déplacé puis le
	 * contenu de sa réserve est transféré dans la pièce de destination renvoyé.
	 * 
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
	 * @ensures (\result == null) <==> \old(choisirSortie(p) == null);
	 * @ensures (\result == null) ==> p.contientAnimal(this);
	 * @ensures (\result == null) ==> (getCapitalVie() == \old(getCapitalVie()));
	 * @ensures (\result != null) ==> (\result == p.pieceSuivante(\old(choisirSortie(p)));
	 * @ensures (\result != null) ==> \result.contientAnimal(this);
	 * @ensures (\result != null) ==> !p.contientAnimal(this);
	 * @ensures ((\result != null) && estVivant()) ==> (getCapitalVie() == (\old(getCapitalVie()) - getCoutDeplacement()));
	 * @ensures ((\result != null) && !estVivant()) ==> reserveEstVide();
	 * @ensures ((\result != null) && !estVivant()) ==> (\result.getNbObjets() == \old(p.pieceSuivante(choisirSortie(p)).getNbObjets() + getNbAliments()));
	 */
    public Piece deplacerDepuis(Piece p) throws NullPointerException, IllegalStateException {
        if(p == null){
            throw new NullPointerException("deplacer depuis piece null!");
        }
        if(!(this.estVivant())){
            throw new IllegalStateException("deplacer un animal mort!");
        }
        if(! (this.pieceActuelle.getDescription().equals(p.getDescription())) ){
            throw new IllegalArgumentException("L'animal ne peut pas se deplacer depuis cette piece car il n'y est pas!");
        }

        Direction A = choisirSortie(p);
        if(A == null){ 
            System.out.println("(" + this.getClass().getSimpleName() + ") " + this.getNom() + " ne s'est pas deplace"+ " <" + this.getCapitalVie() +"pv"+ ">");
            return null;
        }
        while(p.pieceSuivante(A) == null){
            A = choisirSortie(p);
        }

        p.retirerAnimal(this);
        p.pieceSuivante(A).ajouterAnimal(this);

        System.out.println( "(" + this.getClass().getSimpleName() + ") " +  this.getNom() + " passe de " + this.pieceActuelle.getDescription() + " a " + p.pieceSuivante(A).getDescription() + " <" + this.getCapitalVie() +"-"+ this.getCoutDeplacement()+"pv" + ">");
        this.pieceActuelle = p.pieceSuivante(A);
        this.pv -= this.getCoutDeplacement();

        if(!(this.estVivant())){
            System.out.println(this.getNom() + " est mort a cause de l'action deplacer");
            this.mourirDans(pieceActuelle);
        }
        return pieceActuelle;
    }
    
    /**
	 * Renvoie un aliment présent dans la réserve de cet animal et correspondant au
	 * régime alimentaire de cet animal (cf. getRegimeAlimentaire). L'aliment
	 * renvoyé sera consommé lors de la prochaine action "manger" sous réserve
	 * qu'aucune modification n'ait eu lieu concernant le contenu de la réserve de
	 * cet animal. Il n'est pas imposé que cette méthode renvoie un aliment, y
	 * compris si un élément consommable est présent dans la réserve, les classes
	 * implémentant cette interface sont entièrement libre dans la définition des
	 * critères de choix de l'aliment.
	 * 
	 * 
	 * @return un aliment consommable par cet animal ou null
	 * 
	 * @ensures (\result != null) ==> peutManger(\result);
	 * @ensures (\result != null) ==> contient(\result);
	 * 
	 * @pure
	 */
    public Aliment getAliment(){

        // ne prend un aliment que lorsqu'il a moins de la moitié de sa vie max.
        if(this.getCapitalVie() <= (this.getMaxCapitalVie()/2)){
            for(Aliment al : this.getListObjets()){
                return al;
            }
        }
        return null;
    }

    /**
	 * Mange l'aliment renvoyé par getAliment. Si getAliment renvoie une valeur non
	 * null, supprime l'aliment renvoyé de la réserve de cet animal et ajoute sa
	 * valeur nutritive au capital vie de cet animal.
	 * 
	 * @return l'aliment qui a été mangé ou null sinon.
	 * 
	 * @throws IllegalStateException si cet animal est mort
	 * 
	 * @requires estVivant();
	 * @ensures estVivant();
	 * @ensures (\result != null) ==> \result.equals(\old(getAliment()));
	 * @ensures (\result != null) ==> (getNbOccurences(\result) == \old(getNbOccurences(getAliment()) - 1));
	 * @ensures (\result != null) ==> (getCapitalVie() == \old(getCapitalVie() + getAliment().getValeur()));
	 * @ensures (\result == null) ==> (\old(getAliment()) == null);
	 * @ensures (\result == null) ==> (getCapitalVie() == \old(getCapitalVie()));
	 * @ensures (\result == null) ==> (getNbAliments() == \old(getNbAliments()));
	 * 
	 */
    public Aliment manger() throws IllegalStateException {

        if(!(this.estVivant())){
            throw new IllegalStateException("Animal mort");
        }

        Aliment A = getAliment();
        if (A == null){ return null; }
        System.out.println("("+ this.getClass().getSimpleName() +") " + this.nom + " a mange " + A.getDescription() + " <" + this.getCapitalVie() +"+"+ A.getValeurNutritive()+"pv" + ">" );
        this.pv+= A.getValeurNutritive();
        this.retirer(A);
        return A;
    }

    /**
     * Renvoie un nombre aleatoire
     * @return un nombre aleatoire entre 1 et 5
     */
    public int Random(){
        Random random = new Random();
        int nbRandom = random.nextInt(5);
        return nbRandom;
    }

     public Iterator<Aliment> iterator() {
        return this.getListObjets().iterator();
    }

}