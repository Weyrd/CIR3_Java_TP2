import java.util.*;


public abstract class Plat {
    String nom;
    int prix;
    HashMap<String, Integer> ingredients = new HashMap<>();

    public Plat(String nom, int prix, HashMap<String, Integer> ingredients) {
        this.nom = nom;
        this.prix = prix;
        this.ingredients = ingredients;
    }
}
