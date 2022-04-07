import models.*;
import models.employes.*;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Restaurant {

    public static JFrame frame;
    public static List<Commande> commandes = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("-- Start --");
        init();
        System.out.println("-- Fin Init --");

        Stock.readCarte();
        Stock.printStock();
        Employes.initEmployes();

    }

    public static void init() {
        System.out.println("    - windows");
        JFrame frame = new JFrame("Restaurant");
        frame.setSize(600, 500);
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

    public static void switch_between_screens(int key) {
        // get curent screen name
        String current_screen = getFrame().getContentPane().getName();

        if (current_screen.equals("main_screen") || !current_screen.equals("main_screen")) {
            // switch case with the variable key
            switch (key) {
                case 1:
                    getFrame().setContentPane(commande_screen());
                    getFrame().setName("commande_screen");
                    break;
                // case 3 cuisine screen
                case 2:
                    getFrame().setContentPane(cuisine_screen());
                    getFrame().setName("cuisine_screen");
                    break;
                // case 4 bar screen
                case 3:
                    getFrame().setContentPane(bar_screen());
                    getFrame().setName("bar_screen");
                    break;
                // case 5 monitoring screen
                case 4:
                    getFrame().setContentPane(monitoring_screen());
                    getFrame().setName("monitoring_screen");
                    break;
                case 5:
                    getFrame().setContentPane(main_screen());
                    getFrame().setName("main_screen");
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
                // printEventInfo("Key Pressed", event);
            }

            @Override
            public void keyReleased(KeyEvent event) {
                // printEventInfo("Key Released", event);
            }

            @Override
            public void keyTyped(KeyEvent event) {
                printEventInfo("Key Typed", event);
            }

            private void printEventInfo(String str, KeyEvent e) {
                System.out.println(str + " : " + e.getKeyChar());
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
        System.out.println("    - main_screen loaded");

        JPanel screen = new JPanel(new FlowLayout());
        screen.setName("main_screen");
        JLabel textField = new JLabel(
                "<html>Quel écran voulez-vous afficher ? <br/>1. Ecran prise de commande<br/>2. Ecran cuisine<br/>3. Ecran bar<br/>4. Ecran monitoring<br/><br/>Esc. Retour au menu principal<br/>Q. Quit</html>");
        screen.add(textField, BorderLayout.NORTH);

        return screen;
    }

    // new function that return a JPanel for Cuisine
    public static JPanel cuisine_screen() {
        System.out.println("    - cuisine_screen loaded");
        JPanel screen = new JPanel(new FlowLayout());

        JLabel textField = new JLabel("Cuisine screen.");
        screen.add(textField, BorderLayout.NORTH);

        screen.setName("cuisine_screen");
        // display all commandes
        for (Commande commande : commandes) {
            if (commande.isPlat) {
                JButton button = new JButton(commande.nom + " : " + commande.getTime());
                button.setBackground(new Color(50, 155, 50));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Arial", Font.PLAIN, 14));
                screen.add(button);
            }
        }

        return screen;
    }


    // new funtion that return a jpanel for Bar
    public static JPanel bar_screen()  {
        System.out.println("    - bar_screen loaded");

        JPanel screen = new JPanel(new FlowLayout());
        screen.setName("bar_screen");
        JLabel textField = new JLabel("Ecran bar.");
        screen.add(textField, BorderLayout.NORTH);
        for (Commande commande : commandes) {
            if (!commande.isPlat) {
                JButton button = new JButton(commande.nom + " : " + commande.getTime());
                button.setBackground(new Color(50, 50, 155));
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Arial", Font.PLAIN, 14));
                button.setPreferredSize(new Dimension(150, 40));
                screen.add(button);
            }
        }

        return screen;
    }

    // new function that return a jpanel for Monitoring
    public static JPanel monitoring_screen() {
        System.out.println("    - monitoring_screen loaded");

        JPanel screen = new JPanel(new FlowLayout());
        screen.setName("monitoring_screen");
        JLabel textField = new JLabel("Ecran monitoring.");
        screen.add(textField, BorderLayout.NORTH);

        JLabel textField2 = new JLabel("Sélection employés du jour :");
        screen.add(textField2);


        List<Employe> employesDuJour = new ArrayList<>();
        // display all employes
        for (Employe employe : Employes.getAllEmployes()) {
            JButton button = new JButton(employe.nom + " " + employe.prenom);
            if (employe.streak < 3 || employe.type == EmployesEnum.MANAGER) {
                button.setBackground(Color.GREEN);
                // toggle the color of the button on click
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (button.getBackground().equals(Color.GREEN)) {
                            button.setBackground(Color.CYAN);
                            employesDuJour.add(employe);
                        } else {
                            button.setBackground(Color.GREEN);
                            employesDuJour.remove(employe);
                        }
                    }
                });
            } else {
                button.setBackground(new Color(255, 50, 50));
                // disable button
                button.setEnabled(false);
            }
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            screen.add(button);
        }
        // button de confirmation
        JButton button = new JButton("Confirmer");
        button.setBackground(new Color(50, 155, 50));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        // onclick
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Employes.checkEmployesDuJour(employesDuJour))
                // focus frame
                getFrame().requestFocus();
            }
        });
        screen.add(button);

        return screen;
    }

    // new function that return a jpanel for Prise de commande
    public static JPanel commande_screen() {
        System.out.println("    - commande_screen loaded");

        JPanel screen = new JPanel(new FlowLayout());
        screen.setName("commande_screen");
        JLabel textField = new JLabel("Ecran prise de commande.");
        screen.add(textField, BorderLayout.NORTH);

        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(1000000, 7));
        screen.add(separator);

        for (Plat plat : Stock.plats) {
            JButton button = new JButton(plat.nom);
            button.setBackground(new Color(50, 155, 50));
            button.setForeground(Color.WHITE);
            button.setPreferredSize(new Dimension(150, 40));
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            // onclick
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    commandes.add(new Commande(plat.nom, true));
                    // focus frame
                    getFrame().requestFocus();
                }
            });
            screen.add(button);
        }

        JSeparator separator2 = new JSeparator();
        separator2.setPreferredSize(new Dimension(1000000, 1));
        screen.add(separator2);

        for (Boisson boisson : Stock.boissons) {
            JButton button = new JButton(boisson.nom);
            button.setBackground(new Color(50, 50, 155));
            button.setForeground(Color.WHITE);
            button.setPreferredSize(new Dimension(150, 40));
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            // onclick
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    commandes.add(new Commande(boisson.nom, false));
                    // focus frame
                    getFrame().requestFocus();
                }
            });
            screen.add(button);
        }

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
