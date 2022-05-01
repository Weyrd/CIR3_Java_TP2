import java.util.*;
import models.employes.*;

public class Employes {
    static List<Employe> employes = new ArrayList<>();
    static List<Employe> employesDuJour = new ArrayList<>();

    public static void initEmployes() {
        employes.add(new Cuisinier("Pinateau", "Pierre", 1000));
        employes.add(new Cuisinier("Groux", "Louis", 1000));
        employes.add(new Cuisinier("Singeot", "Tanguy", 1000));
        employes.add(new Cuisinier("Duverger", "Maxime", 1000));
        employes.add(new Cuisinier("Giot", "Amaury", 1000));
        employes.add(new Barman("Lourenco", "Quentin", 2000));
        employes.add(new Barman("Rambeau", "Iémélian", 2000));
        employes.add(new Barman("Dessenne", "Corentin", 2000));
        employes.add(new Manager("Colin", "Vincent", 2000));
        employes.add(new Manager("Bertrand", "Ugo", 2000));
        employes.add(new Manager("Bouhelassa", "Samy", 2000));
        employes.add(new Serveur("Cousin", "Flora", 2000));
        employes.add(new Serveur("Klopocki", "Noé", 2000));
        employes.add(new Serveur("Verbeke", "Maxence", 2000));
        employes.add(new Serveur("Zhou", "Lucas", 2000));

        employes.get(3).streak = 3;
        employes.get(6).streak = 3;
        employes.get(9).streak = 3;
        employes.get(10).streak = 3;
    }

    public static void addEmploye(Employe employe) {
        employes.add(employe);
    }

    public static void removeEmploye(Employe employe) {
        employes.remove(employe);
    }

    public static List<Employe> getAllEmployes() {
        return employes;
    }

    public static List<Employe> getEmploye(EmployesEnum type) {
        List<Employe> list = new ArrayList<>();
        for (Employe employe : employes) {
            if (employe.type == type) {
                list.add(employe);
            }
        }
        return list;
    }

    public static List<Employe> getEmployesDuJour() {
        return employesDuJour;
    }

    public static void setEmployesDuJour(List<Employe> employesDuJour) {
        Employes.employesDuJour = employesDuJour;
    }

    public static boolean checkEmployesDuJour(List<Employe> employesDuJour) {
        int cuisiniersCount = 4;
        int serveursCount = 2;
        int managerCount = 1;
        int barmanCount = 1;
        for (Employe employe : employesDuJour) {
            if (employe.streak < 3 || employe.type == EmployesEnum.MANAGER) {
                if (employe.type == EmployesEnum.CUISINIER) {
                    cuisiniersCount--;
                } else if (employe.type == EmployesEnum.SERVEUR) {
                    serveursCount--;
                } else if (employe.type == EmployesEnum.MANAGER) {
                    managerCount--;
                } else if (employe.type == EmployesEnum.BARMAN) {
                    barmanCount--;
                }
            }
        }
        if (cuisiniersCount > 0 || serveursCount > 0 || managerCount > 0 || barmanCount > 0) {
            return false; // employés manquant
        } else {
            return true; // employés complets
        }
    }

    public static void addEmployeDuJour(Employe employe) {
        employesDuJour.add(employe);
    }

    public static void removeEmployeDuJour(Employe employe) {
        employesDuJour.remove(employe);
    }
}
