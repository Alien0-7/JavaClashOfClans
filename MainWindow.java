package Grafica.JavaClashOfClans;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private CardLayout cardLayout;

    public MainWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400,50, 1185, 950);
        setResizable(false);

        //? inizializzazione del layout
        cardLayout = new CardLayout();
        getContentPane().setLayout(cardLayout);


        //? aggiunta dei pannelli al pannello con CardLayout
        GamePanel gamePanel = new GamePanel(15, 44, 50, this);
        ShopPanel shopPanel = new ShopPanel(this);

        getContentPane().add(gamePanel, "Game");
        getContentPane().add(shopPanel, "Shop");

        setVisible(true);
    }

    void switchCard(String cardName) {
        cardLayout.show(getContentPane(), cardName);
    }


}
