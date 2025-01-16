package Grafica.JavaClashOfClans.builds;

import Grafica.JavaClashOfClans.MainWindow;

public class TownHall extends Build{
    public TownHall() {
        setName("TownHall");
        setTypeCost("gold");
        setBaseCost(0);
        setSize("4x4");
        setImagePath(MainWindow.assetsPath + "/images/townHall.png");

    }
}
