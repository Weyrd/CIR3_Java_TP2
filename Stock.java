import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Stock {
    static EnumMap<Ingredients, Integer> ingredients = new EnumMap<>(Ingredients.class);
    static List<Plat> plats = new ArrayList<>();
    static List<Boisson> boissons = new ArrayList<>();

    public Stock() {
        ingredients.put(Ingredients.SALADE, 10);
        ingredients.put(Ingredients.TOMATE, 10);
        ingredients.put(Ingredients.OIGNON, 10);
        ingredients.put(Ingredients.CHAMPIGNON, 10);
        ingredients.put(Ingredients.PAIN, 10);
        ingredients.put(Ingredients.STEAK, 10);
        ingredients.put(Ingredients.PATE, 10);
        ingredients.put(Ingredients.FROMAGE, 10);
        ingredients.put(Ingredients.CHORIZO, 10);
    }

    public static void addIngredient(Ingredients ingredient, int quantity) {
        ingredients.put(ingredient, ingredients.get(ingredient) + quantity);
    }

    public static void removeIngredient(Ingredients ingredient, int quantity) {
        ingredients.put(ingredient, ingredients.get(ingredient) - quantity);
    }

    public static int getNumberOf(Ingredients ingredient) {
        return ingredients.get(ingredient);
    }

    public static void readCarte() {
        JSONParser parser = new JSONParser();
        try {
            JSONObject a = (JSONObject) parser.parse(new FileReader("Carte.json"));
            JSONArray platsArray = (JSONArray) a.get("Plats");
            for (Object o : platsArray) {
                JSONObject plat = (JSONObject) o;
                
                Plat((String) plat.get("nom"), (int) plat.get("prix"), (HashMap<String, Integer>) plat.get("ingredients"));                
            }
            //same for boisson
            JSONArray boissonsArray = (JSONArray) a.get("Boissons");
            for (Object o : boissonsArray) {
                JSONObject boisson = (JSONObject) o;
                Boisson((String) boisson.get("nom"), (int) boisson.get("prix"));                
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void Boisson(String string, int i) {
	}

	private static void Plat(String string, int i, HashMap<String, Integer> hashMap) {
    }
}
