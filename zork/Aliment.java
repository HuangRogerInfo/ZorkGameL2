package zork;
public class Aliment extends ObjetZork{
    private final typeAliment familleAliment; 
    private final int valeurNutritive;


    public Aliment(int poids, int prix, String nom, typeAliment familleAliment, int valeur){
        super(poids, nom, prix);
        this.familleAliment = familleAliment;
        this.valeurNutritive = valeur;
    }

    public typeAliment getfamilleAliment(){
        return this.familleAliment;
    }

    public int getValeurNutritive(){
        return this.valeurNutritive;
    }

    public boolean equals(Object o){
        if(!(o instanceof Aliment)){
            return false;
        }

        Aliment A = (Aliment) o;
        
        if(this.getDescription().equals(A.getDescription()) && (this.familleAliment.equals(A.familleAliment) ) && (this.valeurNutritive == A.valeurNutritive)){
            return true;
        }
        return false;
    }

}