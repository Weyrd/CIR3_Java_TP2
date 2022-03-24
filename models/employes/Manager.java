package models.employes;

public class Manager extends Employe {
    public Manager(String nom, String prenom, int salaire) {
        super(nom, prenom, salaire);
        this.type = EmployesEnum.MANAGER;
    }
}
