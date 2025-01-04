package Grafica.JavaClashOfClans.builds;

import javax.imageio.ImageIO;
import java.io.File;

public class Cannon extends Build {
    public Cannon() {
        setName("Cannon");
        setTypeCost("gold");
        setBaseCost(1000);
        try {
            setBuildImg(ImageIO.read(new File("Grafica/JavaClashOfClans/assets/Cannon.png")));
        }catch (Exception ignored){}

    }
}
