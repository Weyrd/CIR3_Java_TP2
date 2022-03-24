package models.employes;

public abstract class Employe {
    public EmployesEnum type;
    String nom;
    String prenom;
    int salaire;

    public Employe(String nom, String prenom, int salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
    }
}
