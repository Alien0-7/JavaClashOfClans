package Grafica.JavaClashOfClans;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class ShopPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel itemsPanel;
    ShopPanel(MainWindow mainWindow) {

        //? shop top panel
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(null);
        topPanel.setPreferredSize(new Dimension(0,100)); //? set 100 px of height
        topPanel.add(new ShopBackButton(mainWindow)); //? add back button

        //? shop main panel =>
        //? section panel and items panel
        JPanel mainPanel = new JPanel(new BorderLayout());


        JPanel sectionPanel = new JPanel(new GridLayout(1 ,5));
        sectionPanel.setPreferredSize(new Dimension(0,100)); //? set 100 px of height

        cardLayout = new CardLayout();
        itemsPanel = new JPanel(cardLayout);

        File buildFolder = new File(MainWindow.assetsPath + "/../builds");
        ArrayList<String> sections = sectionCounter(buildFolder);

        for (String section : sections) {
            sectionPanel.add(new ShopSectionButton(this, capitalize(section)));
            itemsPanel.add(new ShopContainerItemsPanel(section, mainWindow.getBounds().width, mainWindow.getBounds().height, 2, 5, mainWindow), capitalize(section));
        }

        mainPanel.add(sectionPanel, BorderLayout.NORTH);
        mainPanel.add(itemsPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    void switchSection(String section) {
        cardLayout.show(itemsPanel, section);
    }

    String capitalize(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    private ArrayList<String> sectionCounter(File buildFolder){
        //? ritorna l'ArrayList dei nomi delle cartelle che ci sono al suo interno

        ArrayList<String> sections = new ArrayList<>();
        try {
            if (buildFolder.listFiles().length != 0) {
                for (int i = 0; i < buildFolder.listFiles().length; i++) {
                    //? se il file non contiene il punto nel nome significa che è una cartella, cioè una sezione nello shop
                    if (!buildFolder.listFiles()[i].getName().contains(".")) {
                        sections.add(buildFolder.listFiles()[i].getName());
                    }
                }
            } else {
                System.out.println("non ci sono sezioni dello shop");
            }
        } catch (Exception e){
            System.out.println("errore nel conteggio delle sezioni dello shop!\n" + e + ", path="+buildFolder.getPath());
        }

        return sections;
    }

}