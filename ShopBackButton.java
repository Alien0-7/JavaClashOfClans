package Grafica.JavaClashOfClans;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopBackButton extends Button {
    static int paddingX = 25, paddingY = 25;

    public ShopBackButton(MainWindow mainWindow) {
        super("X", paddingX, paddingY, 50, 50);
        setBackground(Color.RED);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.switchGameState("Game");
            }
        });
    }

}