package Grafica.JavaClashOfClans.builds.traps;

import Grafica.JavaClashOfClans.MainWindow;
import Grafica.JavaClashOfClans.builds.Build;

public class Bomb extends Build {
    public Bomb() {
        setName("Bomb");
        setTypeCost("gold");
        setBaseCost(400);
        setSize("1x1");
        setImagePath(MainWindow.assetsPath + "/images/bomb.png");

    }
}
