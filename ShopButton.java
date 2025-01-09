package Grafica.JavaClashOfClans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopButton extends CardLayoutButton {
    public ShopButton(MainWindow mainWindow) {
        super("NEGOZIO", calcX(mainWindow) - 150, calcY(mainWindow) - 150, 150, 150);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.switchGameState("Shop");
            }
        });
    }


    private static int calcX(MainWindow mainWindow) {
        int border;
        int padding = 25;
        if (mainWindow.getInsets().left == 0) {
            border = 8;
        } else {
            border = mainWindow.getInsets().left;
        }
        //? utilizzo il metodo getBounds perché come scritto nelle docs garantisce prestazioni migliori nella memoria
        return mainWindow.getBounds().width - border - padding;
    }

    private static int calcY(MainWindow mainWindow) {
        int border;
        int padding = 25;
        if (mainWindow.getInsets().left == 0) {
            border = 31;
        } else {
            border = mainWindow.getInsets().left;
        }
        //? utilizzo il metodo getBounds perché come scritto nelle docs garantisce prestazioni migliori nella memoria
        return mainWindow.getBounds().height - border - padding;
    }

}