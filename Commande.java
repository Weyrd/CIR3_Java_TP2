public class Commande {
    public String nom;
    public boolean isPlat;
    public long timeStamp;

    //function that return difference between this.timeStamp and currentTimeStamp
    public long getTimestampDiff() {
        //add one hour to timestamp

        return System.currentTimeMillis() - (this.timeStamp + 3600000);
    }
    //java timestamp to formatted string utc+2
    public String getTime() {
        return new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date(getTimestampDiff()));
    }
    



    Commande(String nom, boolean isPlat) {
        this.nom = nom;
        this.isPlat = isPlat;
        this.timeStamp = System.currentTimeMillis();
    }
}
