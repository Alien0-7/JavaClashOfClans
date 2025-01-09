package Grafica.JavaClashOfClans.builds.defenses;

import Grafica.JavaClashOfClans.builds.Build;

import javax.imageio.ImageIO;
import java.io.File;

public class Wall extends Build {
    public Wall() {
        setName("Wall");
        setTypeCost("gold");
        setBaseCost(50);
        setSize("1x1");
        try {
            setBuildImg(ImageIO.read(new File("Grafica/JavaClashOfClans/assets/images/wall.png")));
        }catch (Exception ignored){}

    }
}
