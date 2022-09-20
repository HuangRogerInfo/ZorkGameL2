package zork;
import java.util.ArrayList;

public class tavernier extends AbstractArrayListContainer<ObjetZork>
{
    private final String nom;

    public tavernier(String name){
        super();
        this.nom = name;
    }

    //FONCTIONS
    public void vendre(ObjetZork o, Joueur acheteur){
        if (acheteur.getPoidsI() +o.getPoids() <= acheteur.getPoidsMax() ){
            if( (acheteur.getArgent() - o.getPrix()) >= 0){
                acheteur.ajouter(o);
                acheteur.setArgent(acheteur.getArgent() - o.getPrix());
                System.out.println ("tavernier : Merci de votre achat !");
                this.retirer(o);
            }
            else{ System.out.println ("tavernier : Vous etes trop pauvre !");}
        }
        else{System.out.println ("tavernier : Vous n'avez pas assez de force pour porter cela"); }
    }

    public void acheter(ObjetZork o, Joueur vendeur){
        vendeur.setArgent(o.getPrix()+vendeur.getArgent() );
        System.out.println(o.getPrix() + " euros ont ete rajoute a votre porte monnaie");
        vendeur.retirer(o);
        this.ajouter(o);
    }
   
    public void afficherShop(){
        System.out.println("Voici ce que j'ai a vous vendre :" );
        ObjetZork Objet;
        for(int i = 0 ; i<this.getListObjets().size(); i++){
                Objet = (ObjetZork) this.getListObjets().get(i);
                System.out.println("-" + Objet.getDescription() + " (" + Objet.getPoids() + "kg)" + " | Prix : " + Objet.getPrix() + "euros" );
            }
    }

    public void bienvenue(Joueur joueur){
        System.out.println("Bonjour " + joueur.getPseudo() +", je suis "+ this.nom +" le tavernier. Je peux vous vendre des objets et aussi acheter les votres. Que voulez vous faire ?");
    }

    public boolean ajoutPossible(ObjetZork oz){
        return true;
    }

    public boolean retraitPossible(ObjetZork oz){
        return true;
    }

    public boolean contientS(String s){
		for(ObjetZork unObjet: this.getListObjets()){
			if(unObjet.getDescription().equals(s)){
				return true;
			}
		}
		return false;
	}
   
}