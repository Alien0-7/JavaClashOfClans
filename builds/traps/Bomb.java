package Grafica.JavaClashOfClans.builds.traps;

import Grafica.JavaClashOfClans.builds.Build;

import javax.imageio.ImageIO;
import java.io.File;

public class Bomb extends Build {
    public Bomb() {
        setName("Bomb");
        setTypeCost("gold");
        setBaseCost(400);
        try {
            setBuildImg(ImageIO.read(new File("Grafica/JavaClashOfClans/assets/bomb.png")));
        }catch (Exception ignored){}

    }
}
