import models.employes.*;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Restaurant {

    public static JFrame frame; // Fenêtre principale
    public static List<Commande> commandes = new ArrayList<>();
    static int current_screen_id; // Ecran actuellement affiché
    static boolean dayStarted = false;

    public static void main(String[] args) {
        System.out.println("-- Start --");
        init();
        System.out.println("-- Fin Init --");

        Stock.init();
        Employes.initEmployes();
    }

    public static void init() {
        System.out.println("    - windows");
        JFrame frame = new JFrame("Restaurant");
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // a debug
        frame.setBackground(Color.GRAY);

        // centrer sur l'écran
        frame.setLocationRelativeTo(null);

        // key listener
        frame.addKeyListener(keyListener());
        frame.setVisible(true);

        // Card
        CardLayout layout = new CardLayout();
        layout.setHgap(10);
        layout.setVgap(10);

        // change frame screen
        frame.setContentPane(main_screen());

        setFrame(frame);
    }

    // function that create Facture of the table
    public static void createFacture(int tableNumber) {
        List<Commande> commandeList = new ArrayList<>();

        for (Commande commande : commandes) {
            if (commande.table == tableNumber) {
                commandeList.add(commande);

            }
        }
        commandes.removeAll(commandeList);
        Facture facture = new Facture(commandeList, tableNumber);
        facture.printFacture();
    }

    public static void switch_between_screens(int key) {
        current_screen_id = key;
        // get curent screen name
        String current_screen = getFrame().getContentPane().getName();

        if (current_screen.equals("main_screen") || !current_screen.equals("main_screen")) {
            // switch case with the variable key
            switch (key) {
                case 1:
                    getFrame().setContentPane(commande_screen());
                    getFrame().setName("commande_screen");
                    getFrame().setTitle("Commande");
                    break;
                // case 3 cuisine screen
                case 2:
                    getFrame().setContentPane(cuisine_screen());
                    getFrame().setName("cuisine_screen");
                    getFrame().setTitle("Cuisine");
                    break;
                // case 4 bar screen
                case 3:
                    getFrame().setContentPane(bar_screen());
                    getFrame().setName("bar_screen");
                    getFrame().setTitle("Bar");
                    break;
                // case 5 monitoring screen
                case 4:
                    getFrame().setContentPane(monitoring_screen());
                    getFrame().setName("monitoring_screen");
                    getFrame().setTitle("Monitoring");
                    break;
                case 5:
                    getFrame().setContentPane(main_screen());
                    getFrame().setName("main_screen");
                    getFrame().setTitle("Restaurant");
                    break;
            }

        }

        getFrame().revalidate();
        getFrame().repaint();

    }

    public static KeyListener keyListener() {
        System.out.println("    - keyListener");
        KeyListener listener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent event) {
            }

            @Override
            public void keyReleased(KeyEvent event) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'q') {
                    System.exit(0);
                }
                // if esc
                if (e.getKeyChar() == 27) {
                    switch_between_screens(5);
                }
                // else if 1, 2, 3 or 4 pressed
                else if (e.getKeyChar() == '1' || e.getKeyChar() == '2' || e.getKeyChar() == '3'
                        || e.getKeyChar() == '4') {
                    // call switch_between_screens with cast int of the key pressed
                    switch_between_screens(Integer.parseInt(String.valueOf(e.getKeyChar())));
                }

            }
        };
        return listener;
    }

    public static JPanel main_screen() {
        JPanel screen = new JPanel(new FlowLayout());
        screen.setName("main_screen");

        JLabel textField = new JLabel(
                "<html>Quel écran voulez-vous afficher ? <br/>1. Ecran prise de commande<br/>2. Ecran cuisine<br/>3. Ecran bar<br/>4. Ecran monitoring<br/><br/>Esc. Retour au menu principal<br/>Q. Quit</html>");
        screen.add(textField, BorderLayout.NORTH);

        return screen;
    }

    // new function that return a JPanel for Cuisine
    public static JPanel cuisine_screen() {
        JPanel screen = new JPanel(new FlowLayout());
        screen.setName("cuisine_screen");

        if (dayStarted == false) {
            JLabel textField2 = new JLabel("Veuillez choisir les employés du jour.");
            screen.add(textField2, BorderLayout.NORTH);
            return screen;
        }

        // display all commandes
        for (Commande commande : commandes) {
            if (commande.isPlat && commande.isComplete == false) {

                JButton button = UI.createButton(commande.nom + " : " + commande.getTime());
                screen.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        commande.isComplete = true;
                        switch_between_screens(2);
                        getFrame().requestFocus();
                    }
                });

                Timer SimpleTimer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        button.setText(commande.nom + " : " + commande.getTime());
                    }
                });
                SimpleTimer.start();
            }
        }

        return screen;
    }

    // new funtion that return a jpanel for Bar
    public static JPanel bar_screen() {
        JPanel screen = new JPanel(new FlowLayout());
        screen.setName("bar_screen");

        if (dayStarted == false) {
            JLabel textField2 = new JLabel("Veuillez choisir les employés du jour.");
            screen.add(textField2, BorderLayout.NORTH);
            return screen;
        }

        for (Commande commande : commandes) {
            if (!commande.isPlat && commande.isComplete == false) {

                JButton button = UI.createButton(commande.nom + " : " + commande.getTime());
                screen.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        commande.isComplete = true;
                        getFrame().requestFocus();
                        switch_between_screens(3);
                    }
                });

                Timer SimpleTimer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        button.setText(commande.nom + " : " + commande.getTime());
                    }
                });
                SimpleTimer.start();
            }
        }

        return screen;
    }

    // new function that return a jpanel for Monitoring
    public static JPanel monitoring_screen() {
        JPanel screen = new JPanel(new FlowLayout());
        screen.setName("monitoring_screen");

        JPanel newEmployeForm = UI.createForm();

        if (dayStarted == false) {
            JLabel textField2 = new JLabel("Sélection employés du jour :");
            screen.add(textField2);

            GridLayout layout = new GridLayout(0, 2);
            layout.setHgap(5);
            layout.setVgap(5);
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(layout);
            // fix the width of the panel
            buttonPanel.setPreferredSize(new Dimension(500, 375));
            screen.add(buttonPanel, BorderLayout.CENTER);

            List<Employe> employesDuJour = new ArrayList<>();
            // display all employes
            for (Employe employe : Employes.getAllEmployes()) {
                JButton button = UI.createButton(
                        employe.type + " : " + employe.nom.charAt(0) + ". " + employe.prenom + " - " + employe.streak
                                + " jour(s)");
                if (employe.streak < 3 || employe.type == EmployesEnum.MANAGER) {
                    employesDuJour.add(employe);
                    button.setBackground(new Color(20, 145, 72));
                    // toggle the color of the button on click
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (button.getBackground().equals(new Color(20, 145, 72))) {
                                button.setBackground(Color.GRAY);
                                employesDuJour.remove(employe);
                            } else {
                                button.setBackground(new Color(20, 145, 72));
                                employesDuJour.add(employe);
                            }
                            // focus frame
                            getFrame().requestFocus();
                        }
                    });
                } else {
                    button.setBackground(new Color(145, 20, 20));
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            getFrame().requestFocus();
                        }
                    });
                }
                button.setForeground(Color.WHITE);
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
                // border
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                buttonPanel.add(button);
            }
            // button de confirmation
            JButton button = UI.createButton("Confirmer");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Employes.checkEmployesDuJour(employesDuJour)) {
                        Employes.setEmployesDuJour(employesDuJour);
                        dayStarted = true;
                        switch_between_screens(4);
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous n'avez pas sélectionné tous les employés du jour.",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    // focus frame
                    getFrame().requestFocus();
                }
            });
            screen.add(button);
            // button pour passer la journée
            if (Employes.checkEmployesDuJour(employesDuJour) == false) {
                JButton skip = UI.createButton("Passer la journée par manque d'effectif");
                screen.add(skip, BorderLayout.SOUTH);
                skip.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Employes.endOfDay();
                        dayStarted = false;
                        switch_between_screens(4);
                        getFrame().requestFocus();
                    }
                });
            }
            screen.add(newEmployeForm);

            return screen;
        }

        // Selection deja faite
        else {

            // -------------------- //
            // Tableau des employés //
            // -------------------- //

            // Create a container
            JPanel container = new JPanel(new BorderLayout());

            JLabel textField2 = new JLabel("Employés du jour :", SwingConstants.CENTER);
            container.add(textField2, BorderLayout.NORTH);

            // En tete du tableau
            String[] columnNames = { "Nom",
                    "Prenom",
                    "Rôle",
                    "Streak",
                    "Salaire" };

            Object[][] data = new Object[Employes.getEmployesDuJour().size()][5];

            int i = 0;
            for (Employe employe : Employes.getEmployesDuJour()) {
                data[i] = new Object[5];
                data[i][0] = employe.nom;
                data[i][1] = employe.prenom;
                data[i][2] = employe.type;
                data[i][3] = employe.streak;
                data[i][4] = employe.salaire;
                i++;
            }
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
            container.add(scrollPane, BorderLayout.CENTER);
            container.setPreferredSize(new Dimension(500, 180));

            screen.add(container);

            // Button to delete an employee from the table
            JButton delete = UI.createButton("Supprimer");
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = table.getSelectedRow();
                    if (row == -1) {
                        JOptionPane.showMessageDialog(null, "Vous n'avez pas sélectionné d'employé.", "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        Employe employe = Employes.getEmployesDuJour().get(row);
                        Employes.removeEmploye(employe);
                        Employes.removeEmployeDuJour(employe);
                        switch_between_screens(4);
                    }
                    // focus frame
                    getFrame().requestFocus();
                }
            });
            screen.add(delete);

            // -------------------------- //
            // Form to add a new employee //
            // -------------------------- //

            screen.add(newEmployeForm);

            // ---------------- //
            // Tableau du stock //
            // ---------------- //

            // Create a container
            JPanel container2 = new JPanel();

            JLabel textField3 = new JLabel("<html>Stock actuel : ", SwingConstants.CENTER);
            container2.add(textField3, BorderLayout.NORTH);

            // En tete du tableau
            String[] columnNames2 = { "Ingrédient",
                    "Quantité" };

            Object[][] data2 = new Object[Stock.ingredients.size()][2];

            int i2 = 0;
            for (String ingredient : Stock.ingredients.keySet()) {
                data2[i2] = new Object[2];
                data2[i2][0] = ingredient;
                data2[i2][1] = Stock.getNumberOf(ingredient);
                i2++;
            }

            final JTable table2 = new JTable(data2, columnNames2);
            table2.setFocusable(false);
            table2.setPreferredScrollableViewportSize(new Dimension(500, 70));
            table2.setFillsViewportHeight(true);

            // Table model not editable
            DefaultTableModel model2 = new DefaultTableModel(data2, columnNames2) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table2.setModel(model2);

            JScrollPane scrollPane2 = new JScrollPane(table2);
            // set height to display all table content
            scrollPane2.setPreferredSize(new Dimension(500, 150));
            container2.add(scrollPane2, BorderLayout.CENTER);
            container2.setPreferredSize(new Dimension(500, 180));

            screen.add(container2, BorderLayout.CENTER);

            JButton button = UI.createButton("Fin de la journée");
            screen.add(button, BorderLayout.SOUTH);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Employes.endOfDay();
                    dayStarted = false;
                    switch_between_screens(4);
                    getFrame().requestFocus();
                }
            });

            return screen;
        }

    }

    // new function that return a jpanel for Prise de commande
    static boolean isMenuActive = false;
    static int menuPlat = 7, menuBoisson = 7;
    public static JPanel commande_screen() {
        JPanel screen = new JPanel(new FlowLayout());
        screen.setName("commande_screen");

        if (dayStarted == false) {
            JLabel textField2 = new JLabel("Veuillez choisir les employés du jour.");
            screen.add(textField2, BorderLayout.NORTH);
            return screen;
        }

        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(1000000, 1));
        screen.add(separator);

        // Combo box for the table
        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.addItem("Table 1");
        comboBox.addItem("Table 2");
        comboBox.addItem("Table 3");
        comboBox.addItem("Table 4");
        comboBox.addItem("Table 5");

        screen.add(comboBox, BorderLayout.SOUTH);

        // Button to create the bill
        JButton billButton = UI.createButton("Créer la facture", Color.ORANGE);
        screen.add(billButton, BorderLayout.SOUTH);

        billButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get the selected table
                int table = comboBox.getSelectedIndex() + 1;
                // get the bill
                createFacture(table);
                // focus frame
                getFrame().requestFocus();
            }
        });

        if (isMenuActive) {
            comboBox.setEnabled(false);
            billButton.setEnabled(false);
        }


        // Button to create the bill
        JButton menuButton = UI.createButton("Commander le menu 100 ans", Color.PINK);
        screen.add(menuButton, BorderLayout.SOUTH);
        
        JLabel menuText = new JLabel("");
        if (isMenuActive) {
            menuText.setText("Menu 100 ans : " + menuPlat + " plats restants, " + menuBoisson + " boissons restantes.");;
        }

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isMenuActive = true;
                menuText.setText("Menu 100 ans : " + menuPlat + " plats restants, " + menuBoisson + " boissons restantes.");
                // refresh frame
                switch_between_screens(1);               
                // focus frame
                getFrame().requestFocus();
            }
        });


        JSeparator separator2 = new JSeparator();
        separator2.setPreferredSize(new Dimension(1000000, 7));
        screen.add(separator2);

        for (Plat plat : Stock.plats) {
            JButton button = UI.createButton(plat.nom);
            screen.add(button);

            // onclick
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    commandes.add(new Commande(plat.nom, true, comboBox.getSelectedIndex() + 1, isMenuActive && menuPlat > 0));
                    if (isMenuActive && menuPlat > 0) {
                        menuPlat--;
                        menuText.setText("Menu 100 ans : " + menuPlat + " plats restants, " + menuBoisson + " boissons restantes.");
                        if (menuPlat == 0 && menuBoisson == 0) {
                            isMenuActive = false;
                            menuBoisson = 7;
                            menuPlat = 7;
                            menuText.setText("");
                            switch_between_screens(1);
                        }
                    } 

                    // focus frame
                    getFrame().requestFocus();
                    // get all ingredients
                    // Delete corresponding plat.ingredients from commande in class Stock
                    HashMap<String, Integer> ingredients = new HashMap<>();

                    for (Plat p : Stock.plats) {
                        if (plat.nom == p.nom) {
                            ingredients = p.ingredients;
                        }
                    }

                    for (String ingredient : ingredients.keySet()) {
                        Stock.removeIngredient(ingredient, ingredients.get(ingredient));
                    }
                    if (Stock.isAvailable(plat) == false) {
                        switch_between_screens(1);
                        button.setEnabled(false);
                    }
                }
            });
            screen.add(button);
            if (Stock.isAvailable(plat) == false) {
                button.setEnabled(false);
            }
        }

        JSeparator separator3 = new JSeparator();
        separator3.setPreferredSize(new Dimension(1000000, 1));
        screen.add(separator3);

        for (Boisson boisson : Stock.boissons) {

            JButton button = UI.createButton(boisson.nom, Color.BLUE);
            screen.add(button);

            // onclick
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    commandes.add(new Commande(boisson.nom, false, comboBox.getSelectedIndex() + 1, isMenuActive && menuBoisson > 0));
                    if (isMenuActive && menuBoisson > 0) {
                        menuBoisson--;
                        menuText.setText("Menu 100 ans : " + menuPlat + " plats restants, " + menuBoisson + " boissons restantes.");
                        if (menuPlat == 0 && menuBoisson == 0) {
                            isMenuActive = false;
                            menuBoisson = 7;
                            menuPlat = 7;
                            menuText.setText("");
                            switch_between_screens(1);
                        }
                    }
                    // focus frame
                    getFrame().requestFocus();
                }

            });
            screen.add(button);
        }

        JSeparator separator4 = new JSeparator();
        separator4.setPreferredSize(new Dimension(1000000, 1));
        screen.add(separator4);

        screen.add(menuText, BorderLayout.SOUTH);

        return screen;
    }

    // getter for frame
    public static JFrame getFrame() {
        return frame;
    }

    // setter for frame
    public static void setFrame(JFrame frame) {
        Restaurant.frame = frame;
    }

}
