package Grafica.JavaClashOfClans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopButton extends CardLayoutButton {
    public ShopButton(MainWindow mainWindow) {
        super("NEGOZIO", MainWindow.calcRightX(mainWindow, 25) - 150, MainWindow.calcRightY(mainWindow, 25) - 150, 150, 150);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.switchGameState("Shop");
            }
        });
    }

}