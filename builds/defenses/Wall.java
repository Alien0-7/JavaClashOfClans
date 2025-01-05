package Grafica.JavaClashOfClans.builds.defenses;

import Grafica.JavaClashOfClans.builds.Build;

import javax.imageio.ImageIO;
import java.io.File;

public class Wall extends Build {
    public Wall() {
        setName("Wall");
        setTypeCost("gold");
        setBaseCost(50);
        try {
            setBuildImg(ImageIO.read(new File("Grafica/JavaClashOfClans/assets/wall.png")));
        }catch (Exception ignored){}

    }
}