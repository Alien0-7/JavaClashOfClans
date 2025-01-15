package Grafica.JavaClashOfClans.builds.resources;

import Grafica.JavaClashOfClans.MainWindow;
import Grafica.JavaClashOfClans.Tile;
import Grafica.JavaClashOfClans.builds.Build;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

public class ElixirCollector extends Build {
    private boolean canProduce;
    private int elixirGenerated, elixirCapacity;
    private double elixirStored;
    private Timer t;

    public ElixirCollector(boolean canProduce) {
        this.canProduce = canProduce;
        setName("Elixir Collector");
        setTypeCost("gold");
        setBaseCost(150);
        setSize("3x3");
        try {
            setBuildImg(ImageIO.read(new File(MainWindow.assetsPath +"/images/elixirCollector.png")));
        }catch (Exception ignored){}

        elixirGenerated = 200;  //? all'ora
        elixirCapacity = 1000;
        elixirStored = 0;

        this.t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ElixirCollector.this.canProduce) {
                    if (elixirCapacity <= elixirStored) {
                        elixirStored += ((double) elixirGenerated / (60 * 60));
                    } else if (elixirStored + ((double) elixirGenerated / (60 * 60)) > elixirCapacity) {
                        elixirStored = elixirCapacity;
                    }

                    if (elixirCapacity <= elixirStored) {
                        ElixirCollector.this.t.start();
                    } else {
                        ElixirCollector.this.t.stop();
                    }

                    System.out.println("elixir=" + elixirStored + "/" + elixirCapacity);
                } else {
                    ElixirCollector.this.t.stop();
                }

            }
        });



    }

    public void setCanProduce(boolean canProduce) {
        if (canProduce){
            if (t.getActionListeners().length == 0){
                setupTimer();
            }
            t.start();
        } else {
            if (t.getActionListeners().length == 1) {
                t.stop();
            }
        }
    }

    private void setupTimer() {
        this.t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (elixirCapacity >= elixirStored-((double) elixirGenerated / (60 * 60))) {
                    elixirStored += ((double) elixirGenerated / (60 * 60));
                } else {
                    elixirStored = elixirCapacity;
                }

                if (elixirCapacity == elixirStored) {
                    setCanProduce(false);
                }

            }

        });

    }

    public int collect() {
        int oldElixirStored = (int) elixirStored;
        elixirStored = 0;
        setCanProduce(true);
        return oldElixirStored;
    }

    public double getElixirStored() {
        return elixirStored;
    }
}
