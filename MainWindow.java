package Grafica.JavaClashOfClans;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
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
}
