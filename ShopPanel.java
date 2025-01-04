package Grafica.JavaClashOfClans;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel itemsPanel;
    ShopPanel(MainWindow mainWindow) {

        //shop top panel
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(null);
        topPanel.setPreferredSize(new Dimension(0,100)); //set 100 px of height
        topPanel.add(new ShopBackButton(mainWindow)); //add back button

        //shop main panel =>
        //section panel and items panel
        JPanel mainPanel = new JPanel(new BorderLayout());


        JPanel sectionPanel = new JPanel(new GridLayout(1 ,5));
        sectionPanel.setPreferredSize(new Dimension(0,100)); //set 100 px of height

        //* - count how many files there are of builds and change the limit of i with the counter
        //* - create an array of "counter" elements and fill it with the name of files to create the button
        cardLayout = new CardLayout();
        itemsPanel = new JPanel(cardLayout);

        for (int i = 0; i < 1; i++) {
            sectionPanel.add(new ShopSectionButton(this, "defenses"));
            itemsPanel.add(new ShopItemsPanel("defenses", mainWindow.getBounds().width, mainWindow.getBounds().height, 2, 5), "defenses");
        }

        mainPanel.add(sectionPanel, BorderLayout.NORTH);
        mainPanel.add(itemsPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    void switchSection(String section) {
        cardLayout.show(itemsPanel, section);
    }
}