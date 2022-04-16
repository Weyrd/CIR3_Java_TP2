import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class UI {

    // Fonction pour créer un bouton
    public static JButton createButton(String text, Color color, Color textColor) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(textColor);
        button.setBorder(new EmptyBorder(5, 10, 5, 10));
        button.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
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
}
