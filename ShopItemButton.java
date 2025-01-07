package Grafica.JavaClashOfClans;

import Grafica.JavaClashOfClans.builds.Build;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopItemButton extends JButton {
    ShopItemButton(MainWindow mainWindow, Build build) {
        super("place it");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.switchGameState("Game");
                mainWindow.getGamePanel().toggleMouseListener(true, build);

            }
        });
    }
}
