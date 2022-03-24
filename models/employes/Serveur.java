package models.employes;

public class Serveur extends Employe {
    public Serveur(String nom, String prenom, int salaire) {
        super(nom, prenom, salaire);
        this.type = EmployesEnum.SERVEUR;
    }
}
