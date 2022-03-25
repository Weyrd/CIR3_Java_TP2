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

        while(true){
           display(getFrame());
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

        //if current screen is main_screen then sicth to manager_screen
        if(current_screen.equals("main_screen")){
            //switch case with the variable key
            switch(key){
                case 1:
                    getFrame().setContentPane(manager_screen());
                    getFrame().setName("manager_screen");
                    break;
            }
            
        }
        //else if current screen is manager_screen then switch to main_screen
        else if(current_screen.equals("manager_screen")){            
            switch (key) {
                case 1:
                
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
        int n = 23;
        JLabel textField = new JLabel("Quel écran souhaitez vous afficher ?\n1. Manager\nQ." + n);
        screen.add(textField, BorderLayout.NORTH);

        return screen;
    }

    public static JPanel manager_screen() {
        System.out.println("    - manager_screen loaded");

        JPanel screen = new JPanel(new FlowLayout());
        screen.setName("manager_screen");
        JLabel textField = new JLabel("Ecran manager.");
        screen.add(textField, BorderLayout.NORTH);

        return screen;
    }
}
