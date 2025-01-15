package Grafica.JavaClashOfClans;

import Grafica.JavaClashOfClans.builds.Build;

import java.awt.*;
import java.io.Serializable;

public class Tile extends Polygon implements Serializable {
    private final double cos35 = Math.cos(Math.toRadians(35));
    private final double sin35 = Math.sin(Math.toRadians(35));
    private int spazioLinee, linee, padding;
    public Build build;
    public int[] xpoints = new int[4], ypoints = new int[4];

    public Tile(int spazioLinee, int linee, int padding, int i, int j) {
        this.spazioLinee = spazioLinee;
        this.linee = linee;
        this.padding = padding;
        //* modify using variables
        if (i != linee) {
            xpoints = new int[]{
                    (int) (cos35 * spazioLinee * linee + padding + cos35 * spazioLinee * j - cos35 * spazioLinee * i),
                    (int) (cos35 * spazioLinee * linee + padding + cos35 * spazioLinee * (j+1) - cos35 * spazioLinee * i),
                    (int) (cos35 * spazioLinee * linee + padding + cos35 * spazioLinee * j - cos35 * spazioLinee * i),
                    (int) (cos35 * spazioLinee * linee + padding + cos35 * spazioLinee * j - cos35 * spazioLinee - cos35 * spazioLinee * i)
            };
            ypoints = new int[]{
                    (int) (sin35 * spazioLinee * j + padding + sin35 * spazioLinee * i),
                    (int) (sin35 * spazioLinee * (j+1) + padding + sin35 * spazioLinee * i),
                    (int) (sin35 * spazioLinee * (j+2) + padding + sin35 * spazioLinee * i),
                    (int) (sin35 * spazioLinee * (j+1) + padding + sin35 * spazioLinee * i),
            };

            for (int k = 0; k < xpoints.length; k++) {
                addPoint(xpoints[k], ypoints[k]);
            }
        }









    }

    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < xpoints.length; i++) {
            str += "["+xpoints[i]+","+ypoints[i]+"],";

        }

        //? dal primo carattere fino al penultimo
        //? perché l'ultimo è una virgola
        return str.substring(0,str.length()-1);
    }

    public int getSpazioLinee() {
        return spazioLinee;
    }

    public int getLinee() {
        return linee;
    }

    public int getPadding() {
        return padding;
    }

    public void setBuild(Build build) {
        this.build = build;
    }

    public Build getBuild() {
        return build;
    }
}
