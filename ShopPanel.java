package Grafica.JavaClashOfClans;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopPanel extends JPanel {
    ShopPanel(MainWindow mainWindow) {

        //* This code is an example code to see if button works, so it'll be modified
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Benvenuto nel negozio", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);

        JButton btn = new JButton("X");
        btn.setBackground(Color.RED);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.switchCard("Game");
            }
        });
        add(btn, BorderLayout.NORTH);
    }
}