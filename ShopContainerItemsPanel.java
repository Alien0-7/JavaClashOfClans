package Grafica.JavaClashOfClans;

import Grafica.JavaClashOfClans.builds.*;
import Grafica.JavaClashOfClans.builds.defenses.ArcherTower;
import Grafica.JavaClashOfClans.builds.defenses.Cannon;
import Grafica.JavaClashOfClans.builds.defenses.Wall;
import Grafica.JavaClashOfClans.builds.resources.ElixirCollector;
import Grafica.JavaClashOfClans.builds.resources.GoldMine;
import Grafica.JavaClashOfClans.builds.traps.Bomb;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShopContainerItemsPanel extends JPanel {
    private String section;
    private ArrayList<Build> builds;
    private int width, rows, cols;
    ShopContainerItemsPanel(String section, int width, int height, int rows, int cols, MainWindow mainWindow){
        super(new GridLayout(rows,cols));
        this.section = section;
        this.builds = setArrayItems();
        this.width = width;
        this.rows = rows;
        this.cols = cols;

        if (builds.size() == 0) {
            System.out.println("[ERROR] " + "Error while trying to set up items in the sections of the store");
        } else {
            for (int i = 0; i < rows*cols; i++) {
                if (builds.size() > i) {
                    add(new ShopItemPanel(builds.get(i), width/cols, mainWindow));
                } else {
                    add(new JPanel());
                }
            }
        }
    }

    private ArrayList<Build> setArrayItems() {
        ArrayList<Build> arrayItems = new ArrayList<>();
        if (section.equals("defenses")){
            arrayItems.add(new Cannon());
            arrayItems.add(new ArcherTower());
            arrayItems.add(new Wall());

        } else if (section.equals("traps")) {
            arrayItems.add(new Bomb());

        } else if (section.equals("resources")) {
            arrayItems.add(new GoldMine());
            arrayItems.add(new ElixirCollector(false));
        }


        return arrayItems;
    }
}
