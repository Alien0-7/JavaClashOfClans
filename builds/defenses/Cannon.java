package Grafica.JavaClashOfClans.builds.defenses;

import Grafica.JavaClashOfClans.MainWindow;
import Grafica.JavaClashOfClans.builds.Build;

import javax.imageio.ImageIO;
import java.io.File;

public class Cannon extends Build {
    public Cannon() {
        setName("Cannon");
        setTypeCost("gold");
        setBaseCost(250);
        setSize("3x3");
        try {
            setBuildImg(ImageIO.read(new File(MainWindow.assetsPath +"/images/cannon.png")));
        }catch (Exception ignored){}

    }
}
