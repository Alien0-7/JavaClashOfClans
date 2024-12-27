package Grafica.JavaClashOfClans;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow(int spazioLinee, int linee, int padding){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500,200, 985, 750);
        this.setLayout(new CardLayout());

        JPanel bg = new Background(spazioLinee, linee, padding);
        baseLoader(bg);
        bg.add(new ShopButton(bg));
        this.getContentPane().add(bg);
    }

    private void baseLoader(JPanel bg){

    }


}
