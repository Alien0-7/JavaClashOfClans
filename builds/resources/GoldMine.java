package Grafica.JavaClashOfClans.builds.resources;

import Grafica.JavaClashOfClans.MainWindow;
import Grafica.JavaClashOfClans.builds.Build;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoldMine extends Build {
    private int goldGenerated, goldCapacity;
    private double goldStored;
    private Timer t;

    public GoldMine() {
        setName("Gold Mine");
        setTypeCost("elixir");
        setBaseCost(150);
        setSize("3x3");
        setImagePath(MainWindow.assetsPath +"/images/goldMine.png");

        goldGenerated = 200; //? all'ora
        goldCapacity = 1000;
        goldStored = 0;

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
                if (goldCapacity >= goldStored-((double) goldGenerated / (60 * 60))) {
                    goldStored += ((double) goldGenerated / (60 * 60));
                } else {
                    goldStored = goldCapacity;
                }

                if (goldCapacity == goldStored) {
                    setCanProduce(false);
                }

            }

        });

    }

    public int collect() {
        int oldGoldStored = (int) goldStored;
        goldStored = 0;
        setCanProduce(true);
        return oldGoldStored;
    }

    @Override
    public String toString() {
        return "GoldMine{" +
                "goldGenerated=" + goldGenerated +
                ", goldCapacity=" + goldCapacity +
                ", goldStored=" + goldStored +
                ", t.isRunning()=" + t.isRunning() +
                ", t=" + t +
                '}';
    }

    public double getGoldStored() {
        return goldStored;
    }
}
