package zork;
public class ObjetZork {
	private int poids;
     private String description;
     private int prix;

	 /*  Crée l'objet */

	public ObjetZork() {
	     poids =Integer.MAX_VALUE;
	     description = "Je suis un objet generique";
	}
         
     public ObjetZork(int poids_p){
          poids = poids_p;
          description = "Je suis un objet potentiellement transportable";       
     }
       
     public ObjetZork(String description_p){
          poids = Integer.MAX_VALUE;
          description = description_p;
     }
         
     public ObjetZork(int poids_p, String description_p, int prix_p){
          poids = poids_p;
          description = description_p;
          prix = prix_p;
     }

      /*  Crée toutes les méthodes */
      
     public int getPrix(){
          return prix;
     }
	 
	public int getPoids(){
           return poids;
        }

	public String getDescription(){
    	   return description;
	}
   
     public boolean estTransportable(){
          return poids !=Integer.MAX_VALUE;
     }
  
     public void setDescription(String description_p){
          description = description_p;
     }

	public boolean equals(Object o){

    	if(!(o instanceof ObjetZork))
        return false;
  
    	ObjetZork A = (ObjetZork) o;
    	return (this.getPoids() == A.getPoids()) && (this.getDescription().equals(A.getDescription()));
     }
     
     public String toString()
     {
          return this.description + "(" + this.poids + "kgs/" + this.prix +"euros)";
     } 
     
}
