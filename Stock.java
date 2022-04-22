import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import static java.util.Map.entry;

public class Stock {
    // Liste des ingrédients présent dans le stock
    static HashMap<String, Integer> ingredients = new HashMap<>();

    // Liste des plats de la carte
    static List<Plat> plats = new ArrayList<>();

    // Liste des boissons de la carte
    static List<Boisson> boissons = new ArrayList<>();

    /**
     * Ajoute un certain nombre d'ingrédients dans la liste des ingrédients.
     * 
     * @param ingredient Nom de l'ingrédient.
     * @param quantity   Quantité d'ingrédient à ajouter.
     */
    public static void addIngredient(String ingredient, int quantity) {
        ingredients.put(ingredient, ingredients.get(ingredient) + quantity);
    }

    /**
     * Supprime un certain nombre d'ingrédients dans la liste des ingrédients.
     * 
     * @param ingredient Nom de l'ingrédient.
     * @param quantity   Quantité d'ingrédient à supprimer.
     */
    public static void removeIngredient(String ingredient, int quantity) {
        ingredients.put(ingredient, ingredients.get(ingredient) - quantity);
    }

    /**
     * Renvoie la quantité d'un ingrédient dans la liste des ingrédients.
     * 
     * @param ingredient Nom de l'ingrédient.
     * @return Quantité de l'ingrédient.
     */
    public static int getNumberOf(String ingredient) {
        return ingredients.get(ingredient);
    }

    /**
     * Cette fonction génère le stock par défaut et la carte des plats et boissons.
     */
    public static void init() {
        // Stock par défaut
        ingredients.put("salade", 10);
        ingredients.put("tomate", 10);
        ingredients.put("oignon", 10);
        ingredients.put("champignon", 10);
        ingredients.put("pain", 10);
        ingredients.put("steak", 10);
        ingredients.put("pate", 10);
        ingredients.put("tortilla", 10);
        ingredients.put("fromage", 10);
        ingredients.put("chorizo", 10);
        ingredients.put("poulet", 10);
        ingredients.put("riz", 10);
        ingredients.put("noix", 10);

        // Création de la carte
        plats.add(new Plat("Salade de tomate", 9, new HashMap<>(Map.ofEntries(
                entry("salade", 1),
                entry("tomate", 1)))));
        plats.add(new Plat("Salade au poulet", 11, new HashMap<>(Map.ofEntries(
                entry("salade", 1),
                entry("tomate", 1),
                entry("steak", 1)))));
        plats.add(new Plat("Salade nature", 9, new HashMap<>(Map.ofEntries(
                entry("salade", 1)))));
        plats.add(new Plat("Potage aux noix", 8, new HashMap<>(Map.ofEntries(
                entry("noix", 3)))));
        plats.add(new Plat("Potage tomate", 8, new HashMap<>(Map.ofEntries(
                entry("tomate", 3)))));
        plats.add(new Plat("Potage champignon", 8, new HashMap<>(Map.ofEntries(
                entry("champignon", 3)))));
        plats.add(new Plat("Burger de tomate", 15, new HashMap<>(Map.ofEntries(
                entry("tomate", 1),
                entry("steak", 1),
                entry("salade", 1),
                entry("pain", 1)))));
        plats.add(new Plat("Burger non vegan", 15, new HashMap<>(Map.ofEntries(
                entry("steak", 1),
                entry("salade", 1),
                entry("pain", 1)))));
        plats.add(new Plat("Burger nature", 15, new HashMap<>(Map.ofEntries(
                entry("salade", 1),
                entry("pain", 1)))));
        plats.add(new Plat("Pizza tomate", 12, new HashMap<>(Map.ofEntries(
                entry("tomate", 1),
                entry("pate", 1),
                entry("fromage", 1)))));
        plats.add(new Plat("Pizza champignon", 12, new HashMap<>(Map.ofEntries(
                entry("champignon", 1),
                entry("pate", 1),
                entry("fromage", 1)))));
        plats.add(new Plat("Pizza fromage chorizo", 12, new HashMap<>(Map.ofEntries(
                entry("fromage", 1),
                entry("pate", 1),
                entry("chorizo", 1)))));
        plats.add(new Plat("Fajitas poulet", 11, new HashMap<>(Map.ofEntries(
                entry("riz", 1),
                entry("poulet", 1),
                entry("tortilla", 1)))));
        plats.add(new Plat("Fajitas steak", 11, new HashMap<>(Map.ofEntries(
                entry("riz", 1),
                entry("steak", 1),
                entry("tortilla", 1)))));

        boissons.add(new Boisson("Limonade", 4));
        boissons.add(new Boisson("Cidre doux", 5));
        boissons.add(new Boisson("Biere sans alcool", 5));
        boissons.add(new Boisson("Jus de fruit", 1));
        boissons.add(new Boisson("Eau", 0));

    }

    /**
     * Génère le stock et la carte des plats et des boissons à partir d'un fichier
     * JSON.
     * 
     * @param path Chemin du fichier JSON.
     * @deprecated Il nous a été déconseillé d'utiliser un fichier JSON pour la
     *             carte. Il vaut mieux utiliser la fonction Stock.init().
     */
    public static void initFromJSON(String path) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject a = (JSONObject) parser.parse(new FileReader(path));

            // First we read the plats
            JSONArray platsArray = (JSONArray) a.get("Plats");
            for (Object o : platsArray) {
                JSONObject plat = (JSONObject) o;

                // Get name
                String nom = plat.get("nom").toString();
                // Get price
                int prix = ((Long) plat.get("prix")).intValue();
                // Get ingredients
                HashMap<String, Integer> ingredientsMap = new HashMap<>();
                JSONObject ingredients = (JSONObject) plat.get("ingredients");
                for (Object ingredient : ingredients.keySet()) {
                    ingredientsMap.put(ingredient.toString(), ((Long) ingredients.get(ingredient)).intValue());
                }

                // Add it to the array
                plats.add(new Plat(nom, prix, ingredientsMap));
            }
            // Then we read the boissons
            JSONArray boissonsArray = (JSONArray) a.get("Boissons");
            for (Object o : boissonsArray) {
                JSONObject boisson = (JSONObject) o;

                // Get name
                String nom = boisson.get("nom").toString();
                // Get price
                int prix = ((Long) boisson.get("prix")).intValue();

                // Add it to the array
                boissons.add(new Boisson(nom, prix));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Stock par défaut
        ingredients.put("salade", 10);
        ingredients.put("tomate", 10);
        ingredients.put("oignon", 10);
        ingredients.put("champignon", 10);
        ingredients.put("pain", 10);
        ingredients.put("steak", 10);
        ingredients.put("pate", 10);
        ingredients.put("tortilla", 10);
        ingredients.put("fromage", 10);
        ingredients.put("chorizo", 10);
        ingredients.put("poulet", 10);
        ingredients.put("riz", 10);
        ingredients.put("noix", 10);
    }

    /**
     * Affiche la carte et le stock dans la console (debug uniquement).
     */
    public static void printStock() {
        System.out.println("Plats : ");
        for (Plat plat : plats) {
            System.out.println("\t" + plat.nom + " : " + plat.prix + " -> " + plat.ingredients);
        }
        System.out.println("Boissons : ");
        for (Boisson boisson : boissons) {
            System.out.println("\t" + boisson.nom + " : " + boisson.prix);
        }
        System.out.println("Stock : ");
        for (String ingredient : ingredients.keySet()) {
            System.out.println("\t" + ingredient + " : " + ingredients.get(ingredient));
        }
    }

    /**
     * Retourne vrai si le stock actuel d'ingrédients permet de faire un plat.
     * 
     * @param plat Le plat à tester.
     * @return Vrai si le plat peut être fait, faux sinon.
     */
    public static boolean isAvailable(Plat plat) {
        for (String ingredient : plat.ingredients.keySet()) {
            if (ingredients.get(ingredient) < plat.ingredients.get(ingredient)) {
                return false;
            }
        }
        return true;
    }
}
