import java.util.*;

public class Stock {
    static EnumMap<Ingredients, Integer> ingredients = new EnumMap<>(Ingredients.class);

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
}
