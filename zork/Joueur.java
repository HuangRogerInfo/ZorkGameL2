package zork;
import java.util.ArrayList;

public class Joueur extends AbstractArrayListContainer<ObjetZork>{
     
	private final String nom;
	private Piece piece_actuelle;
     private int poidsInventaire;
     private int poidsMax;
     private int argent;

     public Joueur(String pseudo,Piece debut, int pdMax){
          super();
	     this.nom = pseudo;
          this.piece_actuelle= debut;
          this.poidsInventaire = 0;
          this.poidsMax= pdMax;
          this.argent = 0;
     }
      
     /**
      * Renvoie le pseudo du joueur
      * @return le pseudo du joueur
      */
     public String getPseudo(){
          return nom;
     }

     /**
      * Renvoie la piece actuelle du joueur
      * @return la piece actuelle du joueur
      */
     public Piece GetPiece(){
          return this.piece_actuelle;
     }

     /**
      * Renvoie le poids de l'inventaire du joueur
      * @return la poids de l'inventaire du joueur
      */
     public int getPoidsI(){
          return this.poidsInventaire;
     }

     /**
      * Renvoie le poids max de l'inventaire du joueur
      * @return le poids max de l'inventaire du joueur
      */
     public int getPoidsMax(){
          return this.poidsMax;
     }

     /**
      * Renvoie l'argent du joueur
      * @return l'argent du joueur
      */
     public int getArgent(){
          return argent;
     }

     /**
      * Renvoie le poids restant que le joueur peut porter
      * @return le poids restant que le joueur peut porter
      */
     public int getIRestant(){
          return this.poidsMax - this.poidsInventaire;
     }

     /**
      * Change l'argent du joueur en la nouvelle valeur donnée
      * @param n la nouvelle valeur de l'argent du joueur
      */
     public void setArgent(int n){
          this.argent = n;
     }

     /**
      * Change la piece actuelle du joueur en la nouvelle piece donnée
      * @param piece1 la nouvelle piece actuelle du joueur
      */
     public void setPiece(Piece piece1){
          this.piece_actuelle = piece1;
     }
     
     /**
      * Ajoute un chien au troupeau du joueur
      * @param A le chien à adopter
      */
     public void adopter(Chien A){
          A.setLibre(false);
     }

     /**
      * Retire un chien au troupeau du joueur
      * @param o le chien à liberer
      */
     public void liberer(Chien o){
          o.setLibre(true);
	}

     /** 
      * Renvoie true si l'ajout d'un objetZork à l'inventaire du joueur est possible, false sinon
      * @param oz l'objet à ajouter
      * @return true si l'ajout est possible, false sinon
      */
     public boolean ajoutPossible(ObjetZork oz){
          if(this.poidsInventaire + oz.getPoids() <= this.poidsMax){
               this.poidsInventaire += oz.getPoids();
               System.out.println( "L'objet a ete ajoute a votre inventaire");
               return true;
          }
          System.out.println("L'objet n'a pas pu etre ajoute car il depasse le poids max autorise");
          return false;
     }

     /** 
      * Renvoie true si le retrait d'un objetZork à l'inventaire du joueur est possible, false sinon 
      * @param oz l'objet a retirer 
      * @return true si le retrait est possible, false sinon
      */
     public boolean retraitPossible(ObjetZork oz){
          if(this.contient(oz)){
               poidsInventaire -= oz.getPoids();
               return true;
          }
          return false;
     }

     /** 
      * Renvoie true si le joueur contient un objetzork de description s, false sinon
      * @param s la chaine de caractère à trouver
      * @return true si l'objet a été trouvé, false sinon
      */
     public boolean contientS(String s){
		for(ObjetZork unObjet: this.getListObjets()){
			if(unObjet.getDescription().equals(s)){
				return true;
			}
		}
		return false;
	}

}


