package Grafica.JavaClashOfClans;

import javax.swing.*;

public abstract class Button extends JButton {
    Button(String text, int x, int y, int width, int height) {
        super(text);
        setBounds(x,y,width,height);
    }

    Button(String text) {
        super(text);
    }
}
