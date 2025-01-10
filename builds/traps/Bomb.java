package Grafica.JavaClashOfClans.builds.traps;

import Grafica.JavaClashOfClans.MainWindow;
import Grafica.JavaClashOfClans.builds.Build;

import javax.imageio.ImageIO;
import java.io.File;

public class Bomb extends Build {
    public Bomb() {
        setName("Bomb");
        setTypeCost("gold");
        setBaseCost(400);
        setSize("1x1");
        try {
            setBuildImg(ImageIO.read(new File(MainWindow.assetsPath +"/images/bomb.png")));
        }catch (Exception ignored){}

    }
}
