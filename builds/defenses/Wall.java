package Grafica.JavaClashOfClans.builds.defenses;

import Grafica.JavaClashOfClans.MainWindow;
import Grafica.JavaClashOfClans.builds.Build;

public class Wall extends Build {
    public Wall() {
        setName("Wall");
        setTypeCost("gold");
        setBaseCost(50);
        setSize("1x1");
        setImagePath(MainWindow.assetsPath +"/images/wall.png");

    }
}
