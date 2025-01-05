package Grafica.JavaClashOfClans.builds.defenses;

import Grafica.JavaClashOfClans.builds.Build;

import javax.imageio.ImageIO;
import java.io.File;

public class Cannon extends Build {
    public Cannon() {
        setName("Cannon");
        setTypeCost("gold");
        setBaseCost(250);
        try {
            setBuildImg(ImageIO.read(new File("Grafica/JavaClashOfClans/assets/cannon.png")));
        }catch (Exception ignored){}

    }
}