package Grafica.JavaClashOfClans;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopItemButton extends JButton {
    ShopItemButton(MainWindow mainWindow) {
        super("place it");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.switchGameState("Game");
                mainWindow.getGamePanel().toggleMouseListener(true);

            }
        });
    }
}
