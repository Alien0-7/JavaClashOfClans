package Grafica.JavaClashOfClans.builds;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class Build {
    String name, typeCost, size;
    int baseCost;
    BufferedImage buildImg;

    protected Build(){
        try {
            File buildImage = new File("Grafica/JavaClashOfClans/assets/"+getName()+".png");
            buildImg = ImageIO.read(buildImage);
        } catch (Exception ignored) {}
    }

    public String getName() {
        return name;
    }

    public int getBaseCost() {
        return baseCost;
    }

    public String getTypeCost() {
        return typeCost;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setBaseCost(int baseCost) {
        this.baseCost = baseCost;
    }

    public void setTypeCost(String typeCost) {
        this.typeCost = typeCost;
    }

    public BufferedImage getBuildImg() {
        return buildImg;
    }

    public void setBuildImg(BufferedImage buildImg) {
        this.buildImg = buildImg;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
