import java.util.*;
import models.employes.*;

public class Employes {
    static List<Employe> employes = new ArrayList<>();

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
}
