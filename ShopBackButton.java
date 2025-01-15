package Grafica.JavaClashOfClans;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopBackButton extends CardLayoutButton {
    static int paddingX = 25, paddingY = 25;

    public ShopBackButton(MainWindow mainWindow) {
        super("X", MainWindow.calcRightX(mainWindow, paddingX)-50, paddingY, 50, 50);
        setBackground(Color.RED);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.switchGameState("Game");
            }
        });
    }

}