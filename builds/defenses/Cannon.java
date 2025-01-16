package Grafica.JavaClashOfClans.builds.defenses;

import Grafica.JavaClashOfClans.MainWindow;
import Grafica.JavaClashOfClans.builds.Build;

public class Cannon extends Build {
    public Cannon() {
        setName("Cannon");
        setTypeCost("gold");
        setBaseCost(250);
        setSize("3x3");
        setImagePath(MainWindow.assetsPath +"/images/cannon.png");

    }
}
