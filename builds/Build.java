package Grafica.JavaClashOfClans.builds;

import Grafica.JavaClashOfClans.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

public abstract class Build implements Serializable {
    private String name, typeCost, size, imagePath;
    private int baseCost;
    private transient BufferedImage buildImg;
    private Tile[] tiles;

    //--- getter
    public String getName() {
        return name;
    }

    public int getBaseCost() {
        return baseCost;
    }

    public String getTypeCost() {
        return typeCost;
    }

    public String getSize() {
        return size;
    }

    public String getImagePath() {
        return imagePath;
    }

    public BufferedImage getBuildImg() {
        return buildImg;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    //--- setter
    public void setName(String name) {
        this.name = name;
    }

    public void setBaseCost(int baseCost) {
        this.baseCost = baseCost;
    }

    public void setTypeCost(String typeCost) {
        this.typeCost = typeCost;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        try {
            setBuildImg(ImageIO.read(new File(imagePath)));
        } catch (Exception ignored){}
    }

    public void setBuildImg(BufferedImage buildImg) {
        this.buildImg = buildImg;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }
}