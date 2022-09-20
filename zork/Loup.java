package zork;
import java.util.Set;
import java.util.HashSet;

public class Loup extends AbstractAnimal implements Chasseur{

    public Loup(String nom_p, Piece Piece_p){
        super(nom_p, 100, 100, 50, Piece_p);
    }

    //Fonctions de l'interface animal

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
        return 15;
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

    //Fonctions de l'interface Chasseur

    /**
	 * Renvoie le coût de la chasse pour cet animal. La valeur renvoyée est le
	 * nombre de points de vie dont le capital vie de cet animal sera diminué à chaque
	 * action de chasse au cours de laquelle il tue un animal.
	 * 
	 * @return le coût de la chasse pour cet animal
	 * 
	 * @pure
	 */
    public int getCoutChasse(){
        return 20;
    }
    
    /**
	 * Choisit une proie dans la pièce spécifiée. Le choix se fait parmi les animaux
	 * dont cet animal chasseur est prédateur.
	 * 
	 * @param p pièce dans laquelle la proie doit être choisie
	 * @return la proie choisie ou null.
	 * 
	 * @throws NullPointerException si la pièce spécifiée est null
	 * 
	 * @requires p != null;
	 * @ensures (\result != null) ==> this.peutChasser(\result);
	 * @ensures (\result != null) ==> p.contientAnimal(\result);
	 * 
	 * @pure
	 */
    public Animal choisirProie(Piece p){
        if(p == null){
            throw new NullPointerException("choisir Proie dans piece null!");
        }

        if(p.getNbAnimal() == 0){
            return null;
        }

        int test = 0;
        Animal meilleurAnimal = new Chevre("uneChevre", this.getpieceActuelle());

        for(Animal unAnimal : this.getpieceActuelle().getListAnimaux() ){
            if(this.peutChasser(unAnimal)){
                if(unAnimal.getValeurNutritive() >= meilleurAnimal.getValeurNutritive()){
                    meilleurAnimal = unAnimal;
                    test = 1;
                }
            }
        }
        
        if(test==1){return meilleurAnimal;}
        return null;
    }
    
    /**
	 * Renvoie l'ensemble des espèces animales que cet animal peut chasser.
	 * 
	 * @return l'ensemble des classes d'animaux dont cet animal peut chasser les
	 *         instances
	 * 
	 * @pure
	 */
    public Set< Class<? extends Animal> > getRegimeAlimentaireChasse(){
        Set< Class<? extends Animal> > set1 = new HashSet< Class<? extends Animal> >();
        set1.add ( Chevre.class );
        return set1;
    }

    /**
	 * Renvoie true si cet animal est prédateur de l'animal spécifié.
	 * 
	 * @param ani l'animal dont on souhaite savoir si ce chasseur est prédateur
	 * @return true si cet animal est prédateur de l'animal spécifié, false sinon
	 * 
	 * @ensures (ani == null) ==> !\result;
	 * @ensures (ani != null) ==> (\result <==> getRegimeAlimentaireChasse().contains(ani.getClass()));
	 * 
	 * @pure
	 */
    public boolean peutChasser(Animal ani){
        if(getRegimeAlimentaireChasse().contains(ani.getClass())){
            return true;
        }
        return false;
    }

    /**
	 * Choisit une proie dans la pièce spécifiée, la tue et la mange. La proie
	 * choisie est l'animal renvoyé par choisirProie. Dans le cas où la méthode
	 * choisirProie renvoie une valeur non null, une fois la proie tuée, le capital
	 * vie de cet animal est diminué du coût de la chasse (cf. getCoutChasse()), il
	 * est alors possible que cet animal meurt et dans ce cas la proie reste morte
	 * dans la pièce et pourra être manger plus tard par un autre prédateur. Dans le
	 * cas où la proie choisie était déjà morte, le captial vie de cet animal n'est
	 * pas diminué du coût de la chasse.
	 * 
	 * @param p la piece dans laquelle cet animal chasse
	 * @return la proie qui a été mangée ou null si aucune proie n'a pu être choisie
	 *         dans la pièce spécifiée
	 * 
	 * @throws NullPointerException  si la pièce spécifiée est null
	 * @throws IllegalStateException si cet animal est mort
	 * 
	 * @requires this.estVivant();
	 * @requires p != null;
	 * @ensures \result == \old(choisirProie(p));
	 * @ensures (\result != null) ==> !\result.estVivant();
	 * @ensures (\result != null) ==> peutChasser(\result);
	 * @ensures ((\result != null) && !estVivant()) ==> p.contientAnimal(\result);
	 * @ensures (\result != null) && (estVivant() && \old(choisirProie(p).estVivant()) 
	 * 		==> (getCapitalVie() 
	 * 			== Math.min(\old(getCapitalVie() + choisirProie(p).getValeurNutritive() - getCoutChasse()),
	 * 						getMaxCapitalVie()));
	 * @ensures (\result != null) && (estVivant() && !\old(choisirProie(p).estVivant()) 
	 * 		==> (getCapitalVie() 
	 * 			== Math.min(\old(getCapitalVie() + choisirProie(p).getValeurNutritive()), 
	 * 						getMaxCapitalVie()));
	 * @ensures (this.estVivant()) ==> !p.contientAnimal(\result);
	 * @ensures ((\result != null) && this.estVivant()) 
	 * 		==> (p.getNbObjets() == \old(p.getNbObjets() + choisirProie(p).getNbAliments()));
	 * @ensures ((\result != null) && !this.estVivant()) 
	 * 		==> (p.getNbObjets() == \old(p.getNbObjets() + getNbAliments() + choisirProie(p).getNbAliments()));
	 */
    public Animal chasser(Piece p){

        if(p == null){
            throw new NullPointerException("chasser dans piece null!");
        }

        if(!(this.estVivant())){
            throw new IllegalStateException("le loup est mort il ne peut pas chasser!");
        }
        
        if( this.choisirProie(p) == null ){ return null; }

        Animal Proie = choisirProie(p);
        
        if( this.getCapitalVie() - this.getCoutChasse() <= 0 ){
            System.out.println("(Loup) " + this.getNom() + " est mort en chassant..." + " <" + this.getCapitalVie() +"-"+ this.getCoutChasse()+"pv" + ">");
            this.mourirDans(p);
            Proie.mourirDans(p);
            return Proie;
        }

        if( Proie.estVivant() ){
            System.out.println("(Loup) "+ this.descriptionCourte() +" a devore sa proie " + Proie.getNom()+ " <" + this.getCapitalVie() +"-"+ this.getCoutChasse()  + "+" + Proie.getValeurNutritive()+"pv" + ">");
            Proie.mourirDans(p);
            this.setCapitalVie( (this.getCapitalVie() - this.getCoutChasse()) );
            this.setCapitalVie( this.getCapitalVie() + Proie.getValeurNutritive() );
            this.getpieceActuelle().retirerAnimal(Proie);
        }

        else{
            System.out.println("(Loup) "+ this.descriptionCourte() +" a devore le cadavre de sa proie " + Proie.getNom() + " <" + this.getCapitalVie() +"+"+ Proie.getValeurNutritive()+"pv" + ">");
            this.setCapitalVie( this.getCapitalVie() + Proie.getValeurNutritive() );
            this.getpieceActuelle().retirerAnimal(Proie);
        }
        return Proie;
     
    }

     /** 
     * Renvoie true si l'ajout d'un aliment à l'inventaire du loup est possible, false sinon 

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
     * Renvoie true si le retrait d'un aliment à l'inventaire du loup est possible, false sinon 

     * @param A l'aliment à retirer
     * @return true si le retrait et possible false sinon
     */
    public boolean retraitPossible(Aliment A){
        return true;
    }
}