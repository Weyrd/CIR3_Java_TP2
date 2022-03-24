package models.employes;

public class Barman extends Employe {
    public Barman(String nom, String prenom, int salaire) {
        super(nom, prenom, salaire);
        this.type = EmployesEnum.BARMAN;
    }
}
