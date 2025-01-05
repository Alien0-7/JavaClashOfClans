package Grafica.JavaClashOfClans.builds.defenses;

import Grafica.JavaClashOfClans.builds.Build;

import javax.imageio.ImageIO;
import java.io.File;

public class ArcherTower extends Build {
    public ArcherTower() {
        setName("Archer Tower");
        setTypeCost("gold");
        setBaseCost(1000);
        try {
            setBuildImg(ImageIO.read(new File("Grafica/JavaClashOfClans/assets/archerTower.png")));
        }catch (Exception ignored){}

    }
}
