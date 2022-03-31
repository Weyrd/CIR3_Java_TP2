import java.util.*;
import models.employes.*;

public class Employes {
    static List<Employe> employes = new ArrayList<>();
    static List<Employe> employesDuJour = new ArrayList<>();

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

    public static void pickEmployesDuJour() {
        employesDuJour.clear();
        int cuisiniersCount = 4;
        int serveursCount = 2;
        int managerCount = 1;
        int barmanCount = 1;
        for (Employe employe : employes) {
            if (employe.streak < 3) {
                if (employe.type == EmployesEnum.CUISINIER && cuisiniersCount > 0) {
                    cuisiniersCount--;
                    employesDuJour.add(employe);
                } else if (employe.type == EmployesEnum.SERVEUR && serveursCount > 0) {
                    serveursCount--;
                    employesDuJour.add(employe);
                } else if (employe.type == EmployesEnum.MANAGER && managerCount > 0) {
                    managerCount--;
                    employesDuJour.add(employe);
                } else if (employe.type == EmployesEnum.BARMAN && barmanCount > 0) {
                    barmanCount--;
                    employesDuJour.add(employe);
                }
            }
        }
        if (cuisiniersCount > 0 || serveursCount > 0 || managerCount > 0 || barmanCount > 0) {
            throw new RuntimeException("Il manque des employés, le restaurant ne peux pas ouvrir.");
        }
    }
}
