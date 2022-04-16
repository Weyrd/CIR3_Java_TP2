import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Stock {
    static HashMap<String, Integer> ingredients = new HashMap<>();
    static List<Plat> plats = new ArrayList<>();
    static List<Boisson> boissons = new ArrayList<>();

    public Stock() {
    }

    public static void addIngredient(String ingredient, int quantity) {
        ingredients.put(ingredient, ingredients.get(ingredient) + quantity);
    }

    public static void removeIngredient(String ingredient, int quantity) {
        ingredients.put(ingredient, ingredients.get(ingredient) - quantity);
    }

    public static int getNumberOf(String ingredient) {
        return ingredients.get(ingredient);
    }

    public static void readCarte() {
        JSONParser parser = new JSONParser();
        try {
            JSONObject a = (JSONObject) parser.parse(new FileReader("Carte.json"));
            
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

        // Set the default stock
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

    public static boolean isAvailable(Plat plat) {
        for (String ingredient : plat.ingredients.keySet()) {
            if (ingredients.get(ingredient) < plat.ingredients.get(ingredient)) {
                return false;
            }
        }
        return true;
    }
}
