package Grafica.JavaClashOfClans.builds.resources;

import Grafica.JavaClashOfClans.MainWindow;
import Grafica.JavaClashOfClans.builds.Build;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElixirCollector extends Build {
    private int elixirGenerated, elixirCapacity;
    private double elixirStored;
    private Timer t;

    public ElixirCollector() {
        setName("Elixir Collector");
        setTypeCost("gold");
        setBaseCost(150);
        setSize("3x3");
        setImagePath(MainWindow.assetsPath + "/images/elixirCollector.png");

        elixirGenerated = 200;  //? all'ora
        elixirCapacity = 1000;
        elixirStored = 0;

        setupTimer();
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
        this.t = new Timer(10, new ActionListener() {
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

    @Override
    public String toString() {
        return "Elixir Collector{" +
                "elixirGenerated=" + elixirGenerated +
                ", elixirCapacity=" + elixirCapacity +
                ", elixirStored=" + elixirStored +
                ", t.isRunning()=" + t.isRunning() +
                ", t=" + t +
                '}';
    }

    public double getElixirStored() {
        return elixirStored;
    }
}
