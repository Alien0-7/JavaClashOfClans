package Grafica.JavaClashOfClans;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    //! EDIT THIS PATH IF IMAGES DOESN'T APPEAR
    public static String assetsPath = "Grafica/JavaClashOfClans/assets";



    private CardLayout cardLayout;
    GamePanel gamePanel;
    ShopPanel shopPanel;

    public MainWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400,50, 1185, 950);
        setResizable(false);

        //? inizializzazione del layout
        cardLayout = new CardLayout();
        getContentPane().setLayout(cardLayout);

        //? aggiunta dei pannelli al pannello con CardLayout
        gamePanel = new GamePanel(15, 44, 50, this);
        shopPanel = new ShopPanel(this);

        getContentPane().add(gamePanel, "Game");
        getContentPane().add(shopPanel, "Shop");

        setVisible(true);
    }

    void switchGameState(String cardName) {
        cardLayout.show(getContentPane(), cardName);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    static int calcRightX(MainWindow mainWindow, int padding) {
        int border;
        if (mainWindow.getInsets().left == 0) {
            border = 8;
        } else {
            border = mainWindow.getInsets().left;
        }
        //? utilizzo il metodo getBounds perché come scritto nelle docs garantisce prestazioni migliori nella memoria
        return mainWindow.getBounds().width - border - padding;
    }

    static int calcBottomY(MainWindow mainWindow, int padding) {
        int border;
        if (mainWindow.getInsets().left == 0) {
            border = 31;
        } else {
            border = mainWindow.getInsets().left;
        }
        //? utilizzo il metodo getBounds perché come scritto nelle docs garantisce prestazioni migliori nella memoria
        return mainWindow.getBounds().height - border - padding;
    }
}
