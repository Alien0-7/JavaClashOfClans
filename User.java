package Grafica.JavaClashOfClans;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class User implements Serializable {
    private Tile[][] tiles;
    File f = new File(MainWindow.assetsPath +"/data/user.dat");

    public User(int linee) {
        tiles = new Tile[linee][linee];

        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            User user = (User) ois.readObject();

            this.setTiles(user.getTiles());
        } catch (Exception ignored){}
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public File getFile() {
        return f;
    }
}
