package Grafica.JavaClashOfClans.builds;

import Grafica.JavaClashOfClans.Tile;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Build implements Serializable {
    private String name, typeCost, size;
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

    public void setBuildImg(BufferedImage buildImg) {
        this.buildImg = buildImg;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    public int[] getCenterPoint(double cos, double sin, int spaziolinee) {
        int x,y;

        x = (int)(Integer.parseInt(getSize().split("x")[0])/2 * cos * spaziolinee + getTiles()[0].xpoints[0]);
        y = (int)(Integer.parseInt(getSize().split("x")[1])/2 * sin * spaziolinee + getTiles()[0].ypoints[0]);

        return new int[]{x,y};
    }
}