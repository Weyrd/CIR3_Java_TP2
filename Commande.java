public class Commande {
    public String nom;
    
    public int table;
    public boolean isPlat;
    public long timeStamp;
    public boolean isComplete = false;

    Commande(String nom, boolean isPlat, int table) {
        this.nom = nom;
        this.isPlat = isPlat;
        this.timeStamp = System.currentTimeMillis();
        this.table = table;
    }

    // function that return difference between this.timeStamp and currentTimeStamp
    public long getTimestampDiff() {
        // add one hour to timestamp
        return System.currentTimeMillis() - (this.timeStamp + 3600000);
    }

    // java timestamp to formatted string utc+2
    public String getTime() {
        return new java.text.SimpleDateFormat("mm:ss").format(new java.util.Date(getTimestampDiff()));
    }
}
