package Grafica.JavaClashOfClans;

import javax.swing.*;

public abstract class CardLayoutButton extends JButton {
    CardLayoutButton(String text, int x, int y, int width, int height) {
        super(text);
        setBounds(x,y,width,height);
    }

    CardLayoutButton(String text) {
        super(text);
    }
}
