package Grafica.JavaClashOfClans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopSectionButton extends CardLayoutButton {

    ShopSectionButton(ShopPanel shopPanel, String text){
        super(text);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shopPanel.switchSection(text);
            }
        });
    }


}
