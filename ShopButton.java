package Grafica.JavaClashOfClans;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopButton extends JButton {
    public ShopButton(int x, int y, MainWindow mainWindow) {
        super("NEGOZIO");

        setBounds(x - 150, y - 150, 150, 150);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.switchCard("Shop");
            }
        });
    }
}

