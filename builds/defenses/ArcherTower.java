package Grafica.JavaClashOfClans.builds.defenses;

import Grafica.JavaClashOfClans.MainWindow;
import Grafica.JavaClashOfClans.builds.Build;

public class ArcherTower extends Build {
    public ArcherTower() {
        setName("Archer Tower");
        setTypeCost("gold");
        setBaseCost(1000);
        setSize("3x3");
        setImagePath(MainWindow.assetsPath +"/images/archerTower.png");

    }
}
