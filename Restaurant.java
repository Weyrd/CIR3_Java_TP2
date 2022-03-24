//import models.*;
//import models.boissons.*;
//import models.employes.*;
//import models.plats.*;

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
    //public List<Client> clients = new ArrayList<>();

    public void generateClient(){
        //this.clients.add(new Client());eex
    }

    public static void main(String[] args) {
        System.out.println("-- Start --");
        JFrame frame = init();
        System.out.println("-- Fin Init --");

        while(true){
            display(frame);
        }
    }


    public static void display(JFrame frame){
        //JLabel textField = new JLabel("Quel écran souhaitez vous afficher ?");
        //frame.add(textField, BorderLayout.NORTH);

    }



    public static JFrame init(){
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
        frame.setLayout(layout);  


        JPanel main_screen = main_screen();
        
        //change frame screen
        frame.setContentPane(main_screen);
        
        return frame;
    }

    public static KeyListener keyListener(){
        System.out.println("    - keyListener");
        KeyListener listener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent event) {
                printEventInfo("Key Pressed", event);
            }
            @Override
            public void keyReleased(KeyEvent event) {
                //printEventInfo("Key Released", event);
            }
            
            @Override
            public void keyTyped(KeyEvent event) {
                //printEventInfo("Key Typed", event);
            }
            private void printEventInfo(String str, KeyEvent e) {
                System.out.println(str + " : " + e.getKeyChar());

            }
        };
        return listener;
    }
    
    public static JPanel main_screen(){
        JPanel screen = new JPanel(new FlowLayout());

        JLabel textField = new JLabel("Quel écran souhaitez vous afficher ?");
        screen.add(textField, BorderLayout.NORTH);

        return screen;
    }
}
