
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.io.BufferedWriter;
import java.util.List;  


public class Facture { 
    //class var, list of Commande
    private static List<Commande> commandes;
    //var tableNumber
    private static int tableNumber;
    // java timestamp to formatted string utc+2
    public static String getTime(long timestamp) {
        return new java.text.SimpleDateFormat("dd-MM-yyyy - hh-mm-ss").format(new java.util.Date(timestamp));
    }

    public static void printFacture(){ 
        try {  
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\Factures\\" + getTime(commandes.get(0).timeStamp) + ".txt");
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter writer = new BufferedWriter(osw);

            //string var 
            String output="";
                // for each commades
                int totalPrice = 0;
                output += "┌─────────────────────────────────┐\n│            Table N°"+tableNumber+"            │\n├─────────────────────────────────┤\n";
                for(Commande c : commandes){
                    for (Plat p : Stock.plats) {
                        if (c.nom == p.nom) {
                            //p.prix to str
                            String price = Integer.toString(p.prix);
                            output += "│ - " + p.nom + " ".repeat(24 - p.nom.length()) + p.prix + "€" + " ".repeat(5 - price.length()) + "│\n";
                            totalPrice += p.prix;
                        }
                    }
                    for (Boisson p : Stock.boissons) {
                        if (c.nom == p.nom) {
                            //p.prix to str
                            String price = Integer.toString(p.prix);
                            output += "│ - " + p.nom + " ".repeat(24 - p.nom.length()) + p.prix + "€" + " ".repeat(5 - price.length()) + "│\n";
                            totalPrice += p.prix;
                        }
                    }
                }
                String totalPriceStr = String.valueOf(totalPrice);
                output += "│                                 │\n│ Prix Total                "+totalPrice+"€" + " ".repeat(5 - totalPriceStr.length()) +"│\n└─────────────────────────────────┘";
                writer.write(output);
                writer.close();    
            }catch(Exception e){System.out.println(e);}    

            System.out.println("Facture de la table N°" + tableNumber + " générée");    
        }
    //constructor that takes a list of Commande
    public Facture(List<Commande> commandeList, int tableNumber){
        Facture.commandes = commandeList;
        Facture.tableNumber = tableNumber;
    }
}  