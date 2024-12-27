package Grafica.JavaClashOfClans;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopButton extends JButton {
    ShopButton(JPanel parentPanel) {
        super();
        setText("NEGOZIO");
        int x = parentPanel.getBounds().width+50, y = parentPanel.getBounds().height-50;
        setBounds(x,y,150,150);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchCard();
            }
        });
    }

    private void switchCard() {
        System.out.println("AA");
    }
}
