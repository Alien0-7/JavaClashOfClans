package Grafica.JavaClashOfClans.builds.defenses;

import Grafica.JavaClashOfClans.MainWindow;
import Grafica.JavaClashOfClans.builds.Build;

import javax.imageio.ImageIO;
import java.io.File;

public class ArcherTower extends Build {
    public ArcherTower() {
        setName("Archer Tower");
        setTypeCost("gold");
        setBaseCost(1000);
        setSize("3x3");
        try {
            setBuildImg(ImageIO.read(new File(MainWindow.assetsPath+"/images/archerTower.png")));
        }catch (Exception ignored){}

    }
}
