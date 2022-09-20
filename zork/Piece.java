package zork;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 *  Une piece dans un jeu d'aventure. 
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.
 *
 *  Une "Piece" represente un des lieux dans lesquels se déroule l'action du
 *  jeu. Elle est reliée a au plus quatre autres "Piece" par des sorties. Les
 *  sorties sont étiquettées "nord", "est", "sud", "ouest". Pour chaque
 *  direction, la "Piece" possède une référence sur la piece voisine ou null
 *  s'il n'y a pas de sortie dans cette direction.
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.2
 * @since      August 2000
 */

public class Piece extends AbstractArrayListContainer<ObjetZork> {
	
	private ArrayList<Animal> animaux;
	private ArrayList<Aliment> aliments;
	private String description;
	// mémorise les sorties de cette piece.
	private  Map<Direction, Piece> sorties;

	/**
	 * Initialise une piece décrite par la chaine de caractères spécifiée.
	 * Initialement, cette piece ne possède aucune sortie. La description fournie
	 * est une courte phrase comme "la bibliothèque" ou "la salle de TP".
	 *
	 * @param description Description de la piece.
	 */
	public Piece(String description) {
		super();
		this.description = description;
		sorties = new HashMap<Direction, Piece>();
		animaux = new ArrayList<Animal>();
		aliments = new ArrayList<Aliment>();
	}

	public Piece( String description,  ObjetZork tab[]) {
		super();
		this.description = description;
		sorties = new HashMap<Direction, Piece>();
		animaux = new ArrayList<Animal>();
		aliments = new ArrayList<Aliment>();
		for ( ObjetZork oz : tab) {
			this.ajouter(oz);
		}
	}

	/**
	 * Définie les sorties de cette piece. A chaque direction correspond ou bien une
	 * piece ou bien la valeur null signifiant qu'il n'y a pas de sortie dans cette
	 * direction.
	 *
	 * @param nord  La sortie nord
	 * @param est   La sortie est
	 * @param sud   La sortie sud
	 * @param ouest La sortie ouest
	 */
	public void setSorties( Piece nord,  Piece est,  Piece sud,  Piece ouest) {
		if (nord != null) {
			sorties.put(Direction.NORD, nord);
		}
		if (est != null) {
			sorties.put(Direction.EST, est);
		}
		if (sud != null) {
			sorties.put(Direction.SUD, sud);
		}
		if (ouest != null) {
			sorties.put(Direction.OUEST, ouest);
		}
	}

	/**
	 * Renvoie la description de cette piece (i.e. la description spécifiée lors de
	 * la création de cette instance).
	 *
	 * @return Description de cette piece
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Renvoie une description de cette piece mentionant ses sorties et directement
	 * formatée pour affichage, de la forme:
	 * 
	 * <pre>
	 *  Vous etes dans la bibliothèque.
	 *  Sorties: nord ouest
	 * </pre>
	 * 
	 * Cette description utilise la chaine de caractères renvoyée par la méthode
	 * descriptionSorties pour décrire les sorties de cette piece.
	 *
	 * @return Description affichable de cette piece
	 */
	public String descriptionLongue() {
		return "Vous etes dans " + description + ".\n" + descriptionSorties();
	}

	/**
	 * Renvoie une description des sorties de cette piece, de la forme:
	 * 
	 * <pre>
	 *  Sorties: nord ouest
	 * </pre>
	 * 
	 * Cette description est utilisée dans la description longue d'une piece.
	 *
	 * @return Une description des sorties de cette pièce.
	 */
	public String descriptionSorties() {
		String resulString = "Sorties:";
		for ( Direction sortie : sorties.keySet()) {
			resulString += " " + sortie;
		}
		return resulString;
	}

	/**
	 * Renvoie la piece atteinte lorsque l'on se déplace a partir de cette piece
	 * dans la direction spécifiée. Si cette piece ne possède aucune sortie dans
	 * cette direction, renvoie null.
	 *
	 * @param direction La direction dans laquelle on souhaite se déplacer
	 * @return Piece atteinte lorsque l'on se déplace dans la direction spécifiée ou
	 *         null.
	 */
	public Piece pieceSuivante( Direction d) {
		return sorties.get(d);
	}

	public Map<Direction, Piece> getSorties(){
		return this.sorties;
	}
	
	/**
	 * Renvoie une ArrayList des animaux de la piece
	 */
	public ArrayList<Animal> getListAnimaux(){
		return this.animaux;
	}

	/**
	 * Renvoie le nombre d'animal de piece
	 */
	public int getNbAnimal() {
		return getListAnimaux().size();
	}

	/**
	 * Ajoute un animal passé en argument à l'ArrayList de la piece
	 */
	public void ajouterAnimal(Animal o) {
		animaux.add(o);
	}

	/**
	 * Retire un animal passé en argument à l'ArrayList de la piece
	 */
	public void retirerAnimal(Animal o) {
		animaux.remove(o);
	}

	/**
	 * Renvoie une ArrayList des chiens de la piece
	 */
	public ArrayList<Chien> getListChiens() {
		ArrayList<Chien> retour = new ArrayList<Chien>();

		for ( Animal elem : this.animaux) {
			if (elem instanceof Chien){
				Chien unChien = (Chien) elem;
				retour.add(unChien);
			}
		}
		return retour;
	}

	/**
	 * Renvoie une ArrayList des chevres de la piece
	 */
	public ArrayList<Chevre> getListChevres() {
		ArrayList<Chevre> retour = new ArrayList<Chevre>();

		for ( Animal elem : this.animaux) {
			if (elem instanceof Chevre){
				Chevre uneChevre = (Chevre) elem;
				retour.add(uneChevre);
			}
		}
		return retour;
	}
	
	/**
	 * Renvoie le nombre de chiens qu'il y a dans la piece
	 */
	public int getnbChiens() {
		return getListChiens().size();
	}
	
	/**
	 * Renvoie le nombre de chevres qu'il y a dans la piece
	 */
	public int getnbChevres(){
		return getListChevres().size();
	}

	/**
	 * Renvoie un String des chiens qu'il y a dans la piece
	 */
	public String descriptionChiens() {

		String retour = "Des chiens rodent dans la piece... ";
		int compteur = 0;
		int test = 0;
		for (Animal unAnimal : this.animaux) {
			if (unAnimal instanceof Chien){
				Chien elem = (Chien) unAnimal;
				if(elem.estLibre()){
					if (compteur == 0) {
						retour += " " + elem.getNom();
						compteur++;
					} else {
						retour += ", " + elem.getNom();
					}
				}
			}
		}
		return retour;
	}

	/**
	 * Renvoie le nombre d'aliments dans la piece
	 */
	public int getNbAliments() {
		return aliments.size();
	}

	/**
	 * Renvoie une arrayList des aliments de la piece
	 */
	public ArrayList<Aliment> getListAliments() {
		return aliments;
	}

	/**
	 * Ajoute un aliment à l'arrayList d'aliments de la piece
	 */
	public void ajouterA(Aliment a){
		aliments.add(a);
	}

	/**
	 * Retire un aliment à l'arrayList d'aliments de la piece
	 */
	public void retirerA(Aliment a){
		aliments.remove(a);
	}

	/**
	 * Renvoie un String des objets qu'il y a dans la piece
	 */
	public String descriptionObjets() {
		String retour = "Objets presents dans la piece :";
		int compteur = 0;
		for ( ObjetZork elem : getListObjets()) {
			if (compteur == 0) {
				retour += " " + elem.getDescription() + " [" + elem.getPoids() + " kg / " + elem.getPrix()+ " $]";
				compteur++;
			} else {
				retour += ", " + elem.getDescription() + " [" + elem.getPoids() + " kg / " + elem.getPrix() + " $]";
			}
		}
		return retour;
	}

	/** 
	 * Renvoie true si la piece contient un objetzork de description s, false sinon
	 */
	public boolean contientS(String s){
		for(ObjetZork unObjet: this.getListObjets()){
			if(unObjet.getDescription().equals(s)){
				return true;
			}
		}
		return false;
	}

	/** 
      * Renvoie true si l'ajout d'un objetZork à l'inventaire de la piece est possible, false sinon
      * @param oz l'objet à ajouter
      * @return true si l'ajout est possible, false sinon
      */
	public boolean ajoutPossible(ObjetZork oz) {
		return true;
	}

	/** 
      * Renvoie true si le retrait d'un objetZork à l'inventaire de la piece est possible, false sinon 
      * @param oz l'objet a retirer 
      * @return true si le retrait est possible, false sinon
      */
	public boolean retraitPossible(ObjetZork oz) {
		return true;
	}

}

