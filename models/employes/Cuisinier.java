package models.employes;

public class Cuisinier extends Employe {
    public Cuisinier(String nom, String prenom, int salaire) {
        super(nom, prenom, salaire);
        this.type = EmployesEnum.CUISINIER;
    }
}
