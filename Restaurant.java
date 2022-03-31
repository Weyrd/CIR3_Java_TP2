import models.*;
import models.employes.*;

import java.util.*;


import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class Restaurant {


    public static JFrame frame;

    //setter and getter for frame
    public static JFrame getFrame() {
        return frame;
    }
    //getter for frame 
    public static void setFrame(JFrame frame) {
        Restaurant.frame = frame;
    }
    public void generateClient(){
        //this.clients.add(new Client());eex
    }

    public static void main(String[] args) {
        System.out.println("-- Start --");
        init();
        System.out.println("-- Fin Init --");

        Stock.readCarte();

        while(true){
           //display(getFrame());
        }
    }


    public static void display(JFrame frame){
        //JLabel textField = new JLabel("Quel écran souhaitez vous afficher ?");
        //frame.add(textField, BorderLayout.NORTH);

    }



    public static void init(){
        System.out.println("    - windows");
        JFrame frame = new JFrame("Restaurant");
        frame.setSize(400, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // a debug
        frame.setBackground(Color.GRAY);

        //centrer sur l'écran
        frame.setLocationRelativeTo(null);

        //key listener
        frame.addKeyListener(keyListener());
        frame.setVisible(true);
        

        //Card 
        CardLayout layout = new CardLayout();
        layout.setHgap(10);
        layout.setVgap(10);


                
        //change frame screen
        frame.setContentPane(main_screen());
        
        setFrame(frame);
    }    


    public static void switch_between_screens(int key){
        //get curent screen name
        String current_screen = getFrame().getContentPane().getName();

        if(current_screen.equals("main_screen") || !current_screen.equals("main_screen")){
            //switch case with the variable key
            switch(key){
                case 1:
                    getFrame().setContentPane(commande_screen());
                    getFrame().setName("commande_screen");
                    break;
                //case 3 cuisine screen
                case 2:
                    getFrame().setContentPane(cuisine_screen());
                    getFrame().setName("cuisine_screen");
                    break;
                //case 4 bar screen
                case 3:
                    getFrame().setContentPane(bar_screen());
                    getFrame().setName("bar_screen");
                    break;
                //case 5 monitoring screen
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
    




    public static KeyListener keyListener(){
        System.out.println("    - keyListener");
        KeyListener listener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent event) {
                //printEventInfo("Key Pressed", event);
            }
            @Override
            public void keyReleased(KeyEvent event) {
                //printEventInfo("Key Released", event);
            }
            
            @Override
            public void keyTyped(KeyEvent event) {
                printEventInfo("Key Typed", event);
            }
            private void printEventInfo(String str, KeyEvent e) {
                System.out.println(str + " : " + e.getKeyChar());
                if(e.getKeyChar() == 'q'){
                    System.exit(0);
                }
                //if esc
                if(e.getKeyChar() == 27){
                    switch_between_screens(5);
                }
                //else if 1, 2, 3 or 4 pressed
                else if(e.getKeyChar() == '1' || e.getKeyChar() == '2' || e.getKeyChar() == '3' || e.getKeyChar() == '4'){
                    //call switch_between_screens with cast int of the key pressed
                    switch_between_screens(Integer.parseInt(String.valueOf(e.getKeyChar())));
                }


            }
        };
        return listener;
    }





    public static JPanel main_screen(){
        System.out.println("    - main_screen loaded");

        JPanel screen = new JPanel(new FlowLayout());
        screen.setName("main_screen");
        JLabel textField = new JLabel("<html>Quel écran voulez-vous afficher ? <br/>1. Ecran prise de commande<br/>2. Ecran cuisine<br/>3. Ecran bar<br/>4. Ecran monitoring<br/><br/>Esc. Retour au menu principal<br/>Q. Quit</html>");
        screen.add(textField, BorderLayout.NORTH);

        return screen;
    }

    //new function that return a JPanel for Cuisine
    public static JPanel cuisine_screen() {
        System.out.println("    - cuisine_screen loaded");

        JPanel screen = new JPanel(new FlowLayout());
        screen.setName("cuisine_screen");
        JLabel textField = new JLabel("Ecran cuisine.");
        screen.add(textField, BorderLayout.NORTH);

        return screen;
    }

    //new funtion that return a jpanel for Bar
    public static JPanel bar_screen() {
        System.out.println("    - bar_screen loaded");

        JPanel screen = new JPanel(new FlowLayout());
        screen.setName("bar_screen");
        JLabel textField = new JLabel("Ecran bar.");
        screen.add(textField, BorderLayout.NORTH);

        return screen;
    }

    //new function that return a jpanel for Monitoring
    public static JPanel monitoring_screen() {
        System.out.println("    - monitoring_screen loaded");

        JPanel screen = new JPanel(new FlowLayout());
        screen.setName("monitoring_screen");
        JLabel textField = new JLabel("Ecran monitoring.");
        screen.add(textField, BorderLayout.NORTH);

        return screen;
    }

    //new function that return a jpanel for Prise de commande
    public static JPanel commande_screen() {
        System.out.println("    - commande_screen loaded");

        JPanel screen = new JPanel(new FlowLayout());
        screen.setName("commande_screen");
        JLabel textField = new JLabel("Ecran prise de commande.");
        screen.add(textField, BorderLayout.NORTH);

        return screen;
    }
}
