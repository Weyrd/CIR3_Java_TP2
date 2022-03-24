package models.employes;

public abstract class Employe {
    String nom;
    String prenom;
    int salaire;

    Employe(String nom, String prenom, int salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
    }
}
