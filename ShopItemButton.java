package Grafica.JavaClashOfClans;

import Grafica.JavaClashOfClans.builds.Build;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopItemButton extends JButton {
    Build build;

    ShopItemButton(MainWindow mainWindow, Build build) {
        super("place it");
        this.build = build;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.getGamePanel().newBuildToggleMouseListener(true, ShopItemButton.this.build);
                mainWindow.switchGameState("Game");
            }
        });
    }
}
