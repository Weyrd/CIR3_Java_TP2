import models.employes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class UI {

    // Fonction pour créer un bouton
    public static JButton createButton(String text, Color color, Color textColor) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(textColor);
        button.setBorder(new EmptyBorder(5, 10, 5, 10));
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        return button;
    }

    // Surcharge de la fonction createButton
    public static JButton createButton(String text, Color color) {
        return createButton(text, color, Color.white);
    }

    // Surcharge de la fonction createButton
    public static JButton createButton(String text) {
        return createButton(text, new Color(50, 155, 50), Color.white);
    }

    // Fonction pour créer un formulaire d'ajout d'employé
    public static JPanel createForm() {
        JPanel form = new JPanel(new FlowLayout());
        form.setName("form");
        form.setPreferredSize(new Dimension(500, 100));
        form.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel nomField = new JLabel("Nom :");
        form.add(nomField);
        JTextField nom = new JTextField(11);
        form.add(nom);
        JLabel prenomField = new JLabel("Prenom :");
        form.add(prenomField);
        JTextField prenom = new JTextField(11);
        form.add(prenom);
        JLabel roleField = new JLabel("Rôle :");
        form.add(roleField);
        // Dropdown menu
        JComboBox<String> role = new JComboBox<String>();
        role.addItem("Cuisinier");
        role.addItem("Barman");
        role.addItem("Serveur");
        role.addItem("Manager");
        form.add(role);
        JLabel salaireField = new JLabel("Salaire :");
        form.add(salaireField);
        JTextField salaire = new JTextField(10);
        form.add(salaire);
        JButton add = createButton("Ajouter");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nom.getText().isEmpty() || prenom.getText().isEmpty() || salaire.getText().isEmpty()) {
                    // Check if salaire is a number
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas rempli tous les champs.", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        Integer.parseInt(salaire.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Le salaire doit être un nombre.", "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    switch (role.getSelectedItem().toString()) {
                        case "Cuisinier":
                            Employe employe = new Cuisinier(nom.getText(), prenom.getText(),
                                    Integer.parseInt(salaire.getText()));
                            Employes.addEmploye(employe);
                            Employes.addEmployeDuJour(employe);
                            break;
                        case "Barman":
                            Employe employe2 = new Barman(nom.getText(), prenom.getText(),
                                    Integer.parseInt(salaire.getText()));
                            Employes.addEmploye(employe2);
                            Employes.addEmployeDuJour(employe2);
                            break;
                        case "Serveur":
                            Employe employe3 = new Serveur(nom.getText(), prenom.getText(),
                                    Integer.parseInt(salaire.getText()));
                            Employes.addEmploye(employe3);
                            Employes.addEmployeDuJour(employe3);
                            break;
                        case "Manager":
                            Employe employe4 = new Manager(nom.getText(), prenom.getText(),
                                    Integer.parseInt(salaire.getText()));
                            Employes.addEmploye(employe4);
                            Employes.addEmployeDuJour(employe4);
                            break;
                    }
                    Restaurant.switch_between_screens(4);
                }
                // focus frame
                Restaurant.getFrame().requestFocus();
            }
        });
        // Set size of form
        form.setPreferredSize(new Dimension(500, 80));
        form.add(add);

        return form;
    }

    // Fonction pour créer un tableau
    public static JScrollPane createTable(Object[][] data, Object[] columnNames) {
        final JTable table = new JTable();
        table.setFocusable(false);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        // Table model not editable
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        // set height to display all table content
        scrollPane.setPreferredSize(new Dimension(500, 150));

        return scrollPane;
    }
}
