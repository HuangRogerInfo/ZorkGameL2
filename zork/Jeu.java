package zork;
import java.util.ArrayList;
/**
 *  Classe principale du jeu "Zork". <p>
 *
 *  Zork est un jeu d'aventure très rudimentaire avec une interface en mode
 *  texte: les joueurs peuvent juste se déplacer parmi les différentes pieces.
 *  Ce jeu nécessite vraiment d'etre enrichi pour devenir intéressant!</p> <p>
 *
 *  Pour jouer a ce jeu, créer une instance de cette classe et appeler sa
 *  méthode "jouer". </p> <p>
 *
 *  Cette classe crée et initialise des instances de toutes les autres classes:
 *  elle crée toutes les pieces, crée l'analyseur syntaxique et démarre le jeu.
 *  Elle se charge aussi d'exécuter les commandes que lui renvoie l'analyseur
 *  syntaxique.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.2
 * @since      March 2000
 */

public class Jeu {
	private AnalyseurSyntaxique analyseurSyntaxique;
	private Piece pieceCourante;
	private Joueur joueurCourant;
	private ArrayList <Piece> ensPieces;
	private ArrayList <ObjetZork> ensObjets;
	private ArrayList <Animal> ensAnimal;
	private ArrayList <Loup> ensLoup;
	private tavernier tv;

	/**
	 *  Crée le jeu et initialise la carte du jeu (i.e. les pièces).
	 */
	public Jeu() {
		ensPieces = new ArrayList <Piece>();
		ensObjets = new ArrayList <ObjetZork>();
		ensAnimal = new ArrayList <Animal>();
		ensLoup = new ArrayList <Loup>();
		creerTout();
		analyseurSyntaxique = new AnalyseurSyntaxique();
	}

	/**
	 *  Crée toutes les pieces et relie leurs sorties les unes aux autres / objet / joueur / aliments / animaux
	 */
	public void creerTout() {

		//Création du Joueur
		Joueur Kiproko;
		Kiproko = new Joueur("Kiproko", pieceCourante, 40);

		//Création des objets à vendre
		ObjetZork RubisRouge= new ObjetZork(10, "rubisRouge", 200);
		ObjetZork Sac = new ObjetZork(10, "ObjetInutile", 1);

		//Création du tavernier
		tavernier tavernier1 = new tavernier("Gerard");
		tavernier1.ajouter(RubisRouge);
		tavernier1.ajouter(Sac);

		//Création des pieces
		Piece debut = new Piece("Entree du donjon");
		Piece couloir = new Piece("Couloir");
		Piece Taverne = new Piece("Taverne");
		Piece Fosse = new Piece("Fosse a ordures");
		Piece salleGarde = new Piece("salle de garde");
		Piece fin = new Piece("salle du trone");
		Piece cave = new Piece("cave");
		Piece latrine = new Piece("latrine");
		Piece entreLoup = new Piece("EntreDesLoups");
		Piece gardeManger = new Piece("garde-manger");
		Piece cuisine = new Piece("cuisine");
		Piece prairie = new Piece("prairie");
		Piece plaine =  new Piece("plaine");
		Piece foret = new Piece("foret noire");

		//Création des objets des pieces
		ObjetZork RubisVert = new ObjetZork(5, "rubisVert", 200);
		ObjetZork babiole = new ObjetZork(10, "babiole", 20); 
		ObjetZork lingotOr = new ObjetZork(30, "lingotOR", 180);
		ObjetZork chandelier = new ObjetZork(30, "chandelier", 100);
		ObjetZork spatule = new ObjetZork(30, "spatule", 80);
		ObjetZork RubisBleu = new ObjetZork(5, "rubisBleu", 200);

		//ajout des objets dans les pieces
		cuisine.ajouter(RubisVert);
		cuisine.ajouter(chandelier);
		cave.ajouter(lingotOr);
		couloir.ajouter(babiole);
		prairie.ajouter(spatule);
		couloir.ajouter(RubisBleu);

		//Création des aliments
		Aliment banane = new Aliment(10, 10, "une banane", typeAliment.VEGETAL, 15);
		Aliment pomme = new Aliment(10, 10, "une pomme", typeAliment.VEGETAL, 10);
		Aliment poire = new Aliment(10, 10, "une poire", typeAliment.VEGETAL, 10);
		Aliment viande = new Aliment(10, 10, "de la viande", typeAliment.ANIMAL, 20);
		Aliment steack = new Aliment(10, 10, "un steack", typeAliment.ANIMAL, 25);
		Aliment saucisse = new Aliment(10, 10, "une saucisse ", typeAliment.ANIMAL, 20);
		Aliment raisin = new Aliment(10, 10, "du raisin", typeAliment.VEGETAL, 5);
		Aliment reste = new Aliment(10,10, "reste de viande",typeAliment.ANIMAL, 10);

		//ajout des aliments dans les pieces 
		salleGarde.ajouterA(banane);
		plaine.ajouterA(pomme);
		prairie.ajouterA(poire);
		gardeManger.ajouterA(viande);
		gardeManger.ajouterA(steack);
		gardeManger.ajouterA(saucisse);
		couloir.ajouterA(raisin);
		cave.ajouterA(reste);

		//Création des chevres
		Chevre chevre1 = new Chevre("Paulette", plaine);
		Chevre chevre2 = new Chevre("Pantoufle", entreLoup);
		Chevre chevre3 = new Chevre("Peggy", prairie);

		//ajout des chevres dans les pieces
		cuisine.ajouterAnimal(chevre1);
		prairie.ajouterAnimal(chevre2);
		prairie.ajouterAnimal(chevre3);
		
		//Création des loups
		Loup loup1 = new Loup("Hans", entreLoup);
		Loup loup2 = new Loup("Brutus", entreLoup);

		//ajout des loups dans les pieces
		entreLoup.ajouterAnimal(loup1);
		entreLoup.ajouterAnimal(loup2);

		//Création des chiens
		Chien chien1 = new Chien("Scoobydoo", Fosse);

		//ajout des chiens dans les pieces
		Fosse.ajouterAnimal(chien1);

		// initialise les sorties des pieces
		debut.setSorties(couloir, null, null, null);
		couloir.setSorties(Taverne, null, debut, null);
		Taverne.setSorties(Fosse, null, couloir, null);
		Fosse.setSorties(latrine,null, Taverne, gardeManger);
		salleGarde.setSorties(null, cave, latrine, foret);
		latrine.setSorties(salleGarde, null, Fosse, null);
		fin.setSorties(null, null, Fosse, null);

		gardeManger.setSorties(null, Fosse, null, cuisine);
		cuisine.setSorties(prairie,gardeManger, null, null);
		cave.setSorties(null, entreLoup, null, salleGarde);
		entreLoup.setSorties(null, null,null,cave);

		prairie.setSorties(plaine, null, cuisine, null);
		plaine.setSorties(null, foret, prairie, null);
		foret.setSorties(null, salleGarde, null, plaine);

		// le jeu commence au debut
		pieceCourante = debut;
		// le joueur Courant
		joueurCourant = Kiproko;

		//ensemble des pieces
		ensPieces.add(debut);
		ensPieces.add(couloir);
		ensPieces.add(Taverne);
		ensPieces.add(Fosse);
		ensPieces.add(salleGarde);
		ensPieces.add(fin);
		ensPieces.add(cave);
		ensPieces.add(entreLoup);
		ensPieces.add(gardeManger);
		ensPieces.add(cuisine);
		ensPieces.add(prairie);
		ensPieces.add(plaine);
		ensPieces.add(foret);
		ensPieces.add(latrine);

		//ensemble des animaux
		ensAnimal.add(chien1);
		ensAnimal.add(loup1);
		ensAnimal.add(chevre1);
		ensAnimal.add(chevre2);
		ensAnimal.add(chevre3);
		ensAnimal.add(loup2);

		//ensemble des objets
		ensObjets.add(RubisVert);
		ensObjets.add(RubisRouge);
		ensObjets.add(RubisBleu);
		ensObjets.add(Sac);
		ensObjets.add(babiole);
		ensObjets.add(spatule);
		ensObjets.add(lingotOr);
		ensObjets.add(chandelier);

		//tavernier
		tv = tavernier1;
	}


	/**
	 *  Pour lancer le jeu. Boucle jusqu'a la fin du jeu.
	 */
	public void jouer() {
		afficherMsgBienvennue();

		// Entrée dans la boucle principale du jeu. Cette boucle lit
		// et exécute les commandes entrées par l'utilisateur, jusqu'a
		// ce que la commande choisie soit la commande "quitter"
		boolean termine = false;
		while (!termine) {
			Commande commande = analyseurSyntaxique.getCommande();
			termine = traiterCommande(commande);
		}
		System.out.println("Merci d'avoir joué.  Au revoir.");
	}


	/**
	 *  Affiche le message d'accueil pour le joueur.
	 */
	public void afficherMsgBienvennue() {
		System.out.println();
		System.out.println("Bienvenue dans le monde de Zork !");
		System.out.println("Zork est un nouveau jeu d'aventure, terriblement ennuyeux.");
		System.out.println("REGLES DU JEU");
		System.out.println("Trouvez les 3 rubis du donjon pour debloquer une porte vers la sortie");
		System.out.println("Vous pouvez acheter et vendre les objets.");  
		System.out.println("Les chiens presents dans le jeu peuvent etre adoptes, et vous aident a transporter les objets trop lourds");
		System.out.println("Note : Attention faites bien en sorte que les rubis soient sur vous et non sur vos chiens pour finir le jeu");
		System.out.println("Tapez 'aide' si vous avez besoin d'aide.");
		System.out.println();
		System.out.println(pieceCourante.descriptionLongue());
	}

	/**
	 *  Exécute la commande spécifiée. Si cette commande termine le jeu, la valeur
	 *  true est renvoyée, sinon false est renvoyée
	 *
	 * @param  commande  La commande a exécuter
	 * @return           true si cette commande termine le jeu ; false sinon.
	 */
	public boolean traiterCommande(Commande commande) {
		if (commande.estInconnue()) {
			System.out.println("Je ne comprends pas ce que vous voulez...");
			return false;
		}

		String motCommande = commande.getMotCommande();
		String secondMot = commande.getSecondMot();

		//le rôle de chaque commande
		if (motCommande.equals("aide")) {
			afficherAide();
		}

		if (motCommande.equals("meschiens")) {
			descriptionMesChiens();
		}

		if(motCommande.equals("adopter")){
			
			if(commande.aSecondMot()){
				adopter(secondMot);
			}
			else{
				System.out.println("adopter qui ?");
			}
		}

		if (motCommande.equals("profil")) {
			afficherProfil();
		}

		if (motCommande.equals("vendre")){
			if(commande.aSecondMot()){
				vendre(secondMot);
			}
			else{
				System.out.println("vendre quoi ?");
			}
		}

		if (motCommande.equals("acheter")) {
			if(commande.aSecondMot()){
				acheter(secondMot);
			}
			else{
				System.out.println("acheter quoi ?");
			}	
		}
		
		if (motCommande.equals("inventaire")) {
			afficherInventaire();
		}

		if(motCommande.equals("prendre")){
			if(commande.aSecondMot()){
				prendre(secondMot);
			}
			else{
				System.out.println("prendre quoi ?");
			}
		}

		if(motCommande.equals("liberer")){
			if(commande.aSecondMot()){
				liberer(secondMot);
			}
			else{
				System.out.println("liberer qui ?");
			}
		}

		else if (motCommande.equals("aller")) {
			deplacerVersAutrePiece(commande);
		}

		else if (motCommande.equals("quitter")) {
			if (commande.aSecondMot()) {
				System.out.println("Quitter quoi ?");
			} else {
				return true;
			}
		}
		return false;
	}


	// implementations des commandes utilisateur:
	
	/**
	* Commande "meschiens" : affiche la liste des chiens du joueur
	*/

	public void descriptionMesChiens(){
		if(getListChienJoueur().size() == 0){
			System.out.println("Vous n'avez pas de chiens.");
		}
		else{
			String s = "Mes chiens : ";
			for(Chien unChien: getListChienJeu()){
				if (!(unChien.estLibre())){
					s= s+ unChien.getNom() + " ";
				} 
			}
			System.out.println(s);
		}
	}
	
	/**
	 * Commande "profil" : Affiche le profil du joueur (argent, capacite inventaire)
	 */
	public void afficherProfil(){
		System.out.println( "Pseudo: " + joueurCourant.getPseudo() + "\n" + "Porte monnaie: " + joueurCourant.getArgent()+ " euros" +"\n" + "Capacite de l'inventaire: " + joueurCourant.getPoidsMax() + "kgs" + " (vous pouvez encore porter " + joueurCourant.getIRestant() + "kgs)" + "\n" );
	}

	/**
	 * Commande "prendre" : Ajoute un objet à l'inventaire du joueur si c'est possible
	 *
	 */
	public void prendre(String ObjetAPrendre){

		if (pieceCourante.getListObjets().size() == 0){
			System.out.println("Il n'y a rien a prendre !");
			return;
		}

		if(pieceCourante.contientS(ObjetAPrendre)){
			int pos = trouverObjetZork(ObjetAPrendre);
			if(joueurCourant.ajouter(ensObjets.get(pos))){
				pieceCourante.retirer(ensObjets.get(pos));
			}
			return;
		}
		System.out.println("Il n'y a pas de "+ ObjetAPrendre + " dans la piece");
	}

	/**
	 * Commande "acheter" : Achete un objet et l'ajoute à l'inventaire si c'est possible
	 *
	 */
	public void acheter(String ObjetAcheter){

		if( pieceCourante != ensPieces.get(2) ){
			System.out.println("Cette commande n'est disponible que dans la taverne");
			return;
		}

		if (tv.getnbObjets() == 0){
			System.out.println("Il n'y a plus rien a vendre ici");
			return;
		}

		if(tv.contientS(ObjetAcheter)){
			int pos = trouverObjetZork(ObjetAcheter);
			tv.vendre(ensObjets.get(pos), joueurCourant );
			return;
		}
		System.out.println("Il n'y a pas de "+ ObjetAcheter + " a vendre");
	}

	/**
	 * Commande "vendre" : vend un objet et le retire de l'inventaire si c'est possible
	 *
	 */
	public void vendre(String ObjetVendre){

		if( pieceCourante != ensPieces.get(2) ){
			System.out.println("Cette commande n'est disponible que dans la taverne");
			return;
		}

		if(joueurCourant.getnbObjets() == 0){
			System.out.println("Vous n'avez rien a me vendre");
			return;
		}

		/*si le joueur a l'objet dans son inventaire*/
		if(joueurCourant.contientS(ObjetVendre)){
			int pos = trouverObjetZork(ObjetVendre);
			tv.acheter(ensObjets.get(pos), joueurCourant );
			return;
		}

		System.out.println("Vous n'avez pas de "+ ObjetVendre + " dans votre inventaire ou sur vos chiens");
		
	}

	/**
	 * Commande "adopter" : le joueur adopte un chien X si c'est possible
	 *
	 */
	public void adopter(String ChienAdopter){
		if (pieceCourante.getListChiens().size() == 0){
			System.out.println("pas de chien ici");
			return;
		}
		for(Chien unChien : this.pieceCourante.getListChiens()){
			if(unChien.getNom().equals(ChienAdopter)){
				if(unChien.estLibre()){
					joueurCourant.adopter(unChien);
					System.out.println(unChien.getNom() + " est maintenant votre chien");
					return;
				}
				System.out.println(unChien.getNom() + " est deja votre chien");
				return;
			}
		}
		System.out.println("Qui est "+ ChienAdopter + " ?");
	}


	/**
	 * Commande "liberer" : libere le chien du joueur, donc le retire le l'array list du joueur et l'ajoute a l'array list de la piece courante
	 *
	 */
	
	public void liberer(String s){

		if (getListChienJoueur().size() == 0){
			System.out.println("Vous n'avez pas de chien!");
			return;
		}

		if( pieceCourante == ensPieces.get(2) ) { 
			System.out.println("Interdit de liberer des chiens dans la taverne");
			return;
		}

		for(Chien unChien : getListChienJoueur()){
			if( unChien.getNom().equals(s) ){
				joueurCourant.liberer(unChien);
				System.out.println(unChien.getNom() + " est libre.");
				return;
			}
		}
		System.out.println("Qui est " + s +" ?");
	}

	/**
	 * Commande "inventaire" : Affiche l'inventaire du joueur + inventaire de ses chiens
	 */
	public void afficherInventaire(){

		if(joueurCourant.getListObjets().size() == 0){
			System.out.println("Vous n'avez aucun objet");
		}
		else{
			for(ObjetZork unObjet: joueurCourant.getListObjets()){
				System.out.println("*" + unObjet);
			}
		}
	}


	/**
	 *  Commande "aide" : Affichage de l'aide. Affiche notament la liste des commandes utilisables.
	 */
	public void afficherAide() {
		System.out.println("Vous etes perdu. Vous etes seul. Vous errez.");
		System.out.println();
		System.out.println("Les commandes reconnues sont:");
		analyseurSyntaxique.afficherToutesLesCommandes();
	}
	
	/**
	 *  Commande "aller" : 
	 *  Tente d'aller dans la direction spécifiée par la commande, et effectue les actions du jeu à 
	 *  chaque deplacement.
	 *  Si la piece courante possède une sortie dans cette direction, la piece correspondant a
	 *  cette sortie devient la piece courante, dans les autres cas affiche un
	 *  message d'erreur.
	 *
	 * @param  commande  Commande dont le second mot spécifie la direction a suivre
	 */
	public void deplacerVersAutrePiece(Commande commande) {
		if (!commande.aSecondMot()) {
			// si la commande ne contient pas de second mot, nous ne
			// savons pas ou aller..
			System.out.println("Aller où ?");
			return;
		}

		String direction = commande.getSecondMot();
		Direction d = null;
		try {
		    d = Direction.valueOf(direction);
		  } catch (IllegalArgumentException e) {
		      System.out.println("Pour indiquer une direction entrez une des chaînes de caractères suivantes:");
		      for (Direction dok : Direction.values()) {
		          System.out.println("-> \"" + dok + "\"");
		      }
		      return;
		  }

		// Tentative d'aller dans la direction indiquée.
		Piece pieceSuivante = pieceCourante.pieceSuivante(d);

		if (pieceSuivante == null) {
			System.out.println("Il n'y a pas de porte dans cette direction!");
		}
		else {
			if(resteAnimauxVivant()){
				System.out.println("[INFORMATION DES ACTIONS SUR LA CARTE]" );
			}

			for(Chien unChien : getListChienJeu()){
				try {
					actionChien(unChien, d);
				}catch( IllegalStateException e ){
					//Les animaux morts ignorent cette fonction
				}
			}
			for(Chevre uneChevre : getListChevre()){
				try{
					actionChevre(uneChevre);
				}catch( IllegalStateException e ){
					//Les animaux morts ignorent cette fonction
				}
			}
			for(Loup unLoup : getListLoup()){
				try{
					actionLoup(unLoup);
				}catch( IllegalStateException e ){
					//Les animaux morts ignorent cette fonction
				}
			}

			this.pieceCourante = pieceSuivante;
			joueurCourant.setPiece(pieceCourante);

			if (pieceCourante == ensPieces.get(2)){
				affichageTaverne();
			}
			else if (pieceCourante == ensPieces.get(5)){
				affichageFin();
			}
			else{
				affichageGenerique();
			}
		}
	}

	//FONCTION PROGRAMMEUR

	/**
	 * Renvoie la position d'un objet de description s dans l'ensemble des objets
	 */
	public int trouverObjetZork(String s){
		for(int i =0; i<ensObjets.size(); i++ ){
			ObjetZork objet = (ObjetZork) ensObjets.get(i);
			if(objet.getDescription().equals(s)){
				return i;
			}
		}
		return -1;
	}

	/**
	 * Renvoie la liste de chien du jeu
	 */
	public ArrayList<Chien> getListChienJeu(){
		Chien chien;
		ArrayList<Chien> listeChien = new ArrayList<Chien>();
		for(Animal unAnimal: ensAnimal){
			if (unAnimal instanceof Chien){
				chien = (Chien) unAnimal;
				listeChien.add(chien);
			}
		}
		return listeChien;
	}

	/**
	 * Renvoie la liste de chien du joueur
	 */
	public ArrayList<Chien> getListChienJoueur(){
		ArrayList<Chien> listeChien = new ArrayList<Chien>();
		for(Chien unChien: getListChienJeu()){
			if (!(unChien.estLibre())){
				listeChien.add(unChien);
			}
		}
		return listeChien;
	}

	/**
	 * Renvoie la liste de chevre du joueur
	 */
	public ArrayList<Chevre> getListChevre(){
		Chevre chevre;
		ArrayList<Chevre> listeChevre = new ArrayList<Chevre>();
		for(Animal unAnimal: ensAnimal){
			if (unAnimal instanceof Chevre){
				chevre = (Chevre) unAnimal;
				listeChevre.add(chevre);
			}
		}
		return listeChevre;
	}

	/**
	 * Renvoie la liste de loup du jeu
	 */
	public ArrayList<Loup> getListLoup(){
		Loup loup;
		ArrayList<Loup> listeLoup = new ArrayList<Loup>();
		for(Animal unAnimal: ensAnimal){
			if (unAnimal instanceof Loup){
				loup = (Loup) unAnimal;
				listeLoup.add(loup);
			}
		}
		return listeLoup;
	}

	/**
	 * Renvoie true si il reste encore un animal vivant sinon renvoie false
	 */
	boolean resteAnimauxVivant(){
		for(Animal unAnimal : ensAnimal){
			if (unAnimal.estVivant()){
				return true;
			}
		}
		return false;
	}

	/**
	 * Renvoie true si il existe un chien libre dans une piece p sinon renvoie false
	 */
	boolean existeChienLibre(Piece p){
		for(Chien unChien : p.getListChiens()){
			if (unChien.estLibre()){
				return true;
			}
		}
		return false;
	}

	/**
	 * Affichage du message de la taverne
	 */
	void affichageTaverne(){
		System.out.println(".................. vous avancez ...................");

				if(joueurCourant.contient(ensObjets.get(0)) &&  joueurCourant.contient(ensObjets.get(1)) && joueurCourant.contient(ensObjets.get(2))){
					ensPieces.get(4).setSorties(ensPieces.get(5), null, ensPieces.get(3), null);
					System.out.println(" \n "+"*GRACE A VOS 3 RUBIS UNE PORTE AU NORD DE LA SALLE DE GARDE S'EST DEBLOQUEE*");
				}
	
				System.out.println("\n" +pieceCourante.descriptionLongue());
				System.out.println(" \n [CONTENU DE LA PIECE]");
				tv.bienvenue(joueurCourant);
				tv.afficherShop();
				System.out.println("\n [Veuillez taper une commande]");
	}

	/**
	 * Affichage du message de fin de jeu
	 */

	void affichageFin(){
		System.out.println(".................. vous avancez ...................");
				System.out.println("\n" +pieceCourante.descriptionLongue()+ "\n");
				System.out.println(" VOUS AVEZ GAGNE ! ");
				System.exit(0);
	}

	/**
	 * Affichage du message generique à chaque changement de piece
	 */
	void affichageGenerique(){
		System.out.println(".................. vous avancez ...................");
				
				//débloquer une sortie si le joueur a un objet + affichage d'un message lui informant
				
				if(joueurCourant.contient(ensObjets.get(0)) &&  joueurCourant.contient(ensObjets.get(1)) && joueurCourant.contient(ensObjets.get(2))){
					ensPieces.get(4).setSorties(ensPieces.get(5), null, ensPieces.get(3), null);
					System.out.println("\n"+ "*GRACE A VOS 3 RUBIS UNE PORTE AU NORD DE LA SALLE DE GARDE S'EST DEBLOQUEE*");
				}

				System.out.println("\n" +pieceCourante.descriptionLongue() );
				System.out.println(" \n [CONTENU DE LA PIECE]");

				if(pieceCourante.getListObjets().size()>0 || existeChienLibre(pieceCourante) ){
					if(pieceCourante.getListObjets().size()>0){
					System.out.println(pieceCourante.descriptionObjets());
					}
					if(existeChienLibre(pieceCourante)){
					System.out.println(pieceCourante.descriptionChiens());
					}
				}
				else{ System.out.println("Salle vide...");}
				System.out.println(" \n [Veuillez taper une commande]");
	}

	/**
	 * Effectue les actions generiques que doit faire un chien passé en argument à chaque changement de piece
	 */
	void actionChien(Chien unChien, Direction d){
		unChien.proposerSortie(pieceCourante,d);
		unChien.deplacerDepuis(unChien.getpieceActuelle());
		unChien.ramasserDans(unChien.getpieceActuelle());
		unChien.manger();
	}

	/**
	 * Effectue les actions generiques que doit faire une chevre passé en argument à chaque changement de piece
	 */
	void actionChevre(Chevre uneChevre){
		uneChevre.deplacerDepuis(uneChevre.getpieceActuelle());
		uneChevre.ramasserDans(uneChevre.getpieceActuelle());
		uneChevre.manger();
	}

	/**
	 * Effectue les actions generiques que doit faire un loup passé en argument à chaque changement de piece
	 */
	void actionLoup(Loup unLoup){
		unLoup.deplacerDepuis(unLoup.getpieceActuelle());
		unLoup.ramasserDans(unLoup.getpieceActuelle());
		unLoup.manger();
		unLoup.chasser(unLoup.getpieceActuelle());
	}

}