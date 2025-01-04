package Grafica.JavaClashOfClans;

import Grafica.JavaClashOfClans.builds.Build;
import Grafica.JavaClashOfClans.builds.Cannon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShopItemsPanel extends JPanel {
    String section;
    ArrayList<Build> builds;
    int width, rows, cols;
    ShopItemsPanel(String section, int width, int height, int rows, int cols){
        super(new GridLayout(rows,cols));
        this.section = section;
        this.builds = setArrayItems();
        this.width = width;
        this.rows = rows;
        this.cols = cols;

        if (builds.size() == 0) {
            System.out.println("[ERROR] " + "Error while trying to set up the section of the store");
        } else {
            for (int i = 0; i < 10; i++) {
                if (builds.size() > i) {
                    add(new ItemPanel(builds.get(i), width/cols, (height-200)/rows));
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
            arrayItems.add(new Cannon());
            arrayItems.add(new Cannon());
            arrayItems.add(new Cannon());
            arrayItems.add(new Cannon());
            arrayItems.add(new Cannon());
            arrayItems.add(new Cannon());
            arrayItems.add(new Cannon());
            arrayItems.add(new Cannon());
            arrayItems.add(new Cannon());
            return arrayItems;
        } else if (section.equals("traps")){
            //* open traps file and fill arraylist with all them
            return arrayItems;
        } else {
            return arrayItems;
        }
    }
}
