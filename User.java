package Grafica.JavaClashOfClans;

import Grafica.JavaClashOfClans.builds.Build;
import Grafica.JavaClashOfClans.builds.TownHall;
import Grafica.JavaClashOfClans.builds.resources.ElixirCollector;
import Grafica.JavaClashOfClans.builds.resources.GoldMine;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class User implements Serializable {
    private Tile[][] tiles;
    private ArrayList<Build> buildsPlaced;
    private final File f = new File(MainWindow.assetsPath +"/data/user.dat");
    private int gold, elixir, maxGold = 1000, maxElixir = 1000;

    private ArrayList<Polygon> collectGoldTiles, collectElixirTiles;

    public User(int spazioLinee, int linee, int padding) {
        //? inizializzo le variabili dello user
        this.tiles = new Tile[linee][linee];
        this.buildsPlaced = new ArrayList<>();
        this.collectElixirTiles = new ArrayList<>();
        this.collectGoldTiles = new ArrayList<>();


        for (int i = 0; i < linee; i++) {
            for (int j = 0; j < linee; j++) {
                if (tiles[i][j] == null) {
                    tiles[i][j] = new Tile(spazioLinee, linee, padding, i, j);
                }
            }
        }


        //? imposto il municipio al centro del villaggio
        Build townHall = new TownHall();
        Tile[] t = new Tile[4*4];
        for (int i = linee/2-2; i < linee/2+2; i++) {
            for (int j = linee/2-2; j < linee/2+2; j++) {
                tiles[i][j].setBuild(townHall);
                t[((i-(linee/2-2))*4)+(j-(linee/2-2))] = tiles[i][j];
            }
        }
        townHall.setTiles(t);

        buildsPlaced.add(townHall);

        //? salvo il file contenente l'oggetto user
        save(this);
    }

    public User() {}

    public void initFromFile(File f, GamePanel gP) throws Exception {
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        User userInFile = (User) ois.readObject();

        this.setTiles(userInFile.getTiles());
        this.collectElixirTiles = userInFile.getCollectElixirTiles();
        this.collectGoldTiles = userInFile.getCollectGoldTiles();
        this.elixir = userInFile.getElixir();
        this.gold = userInFile.getGold();
        this.maxElixir = userInFile.getMaxElixir();
        this.maxGold = userInFile.getMaxGold();

        int goldMines=0, elixirCollector=0;
        ArrayList<Build> tempBuildsPlaced = userInFile.getBuildsPlaced();
        for (int i = 0; i < tempBuildsPlaced.size(); i++) {
            Build build = tempBuildsPlaced.get(i);
            //? start timers of builds
            if (build.getName().equals("Gold Mine")) {
                GoldMine gm = (GoldMine) build;
                gm.setCanProduce(true);
                tempBuildsPlaced.set(i,gm);
                goldMines++;
            } else if (build.getName().equals("Elixir Collector")) {
                ElixirCollector ec = (ElixirCollector) build;
                ec.setCanProduce(true);
                tempBuildsPlaced.set(i,ec);
                elixirCollector++;
            }
        }

        setBuildsPlaced(tempBuildsPlaced);
        if (goldMines > 0) {
            gP.resourcesToggleMouseListener(true, "gold");
        }
        if (elixirCollector > 0) {
            gP.resourcesToggleMouseListener(true, "elixir");
        }

        for (Build b: getBuildsPlaced()){
            b.setImagePath(b.getImagePath());
        }

        save(this);
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public ArrayList<Build> getBuildsPlaced() {
        return buildsPlaced;
    }

    public void setBuildsPlaced(ArrayList<Build> buildsPlaced) {
        this.buildsPlaced = buildsPlaced;
    }

    public File getFile() {
        return f;
    }

    public int getElixir() {
        return elixir;
    }

    public int getGold() {
        return gold;
    }

    public int getMaxGold() {
        return maxGold;
    }

    public int getMaxElixir() {
        return maxElixir;
    }
    public void save(User user){
        try {
            FileOutputStream fos = new FileOutputStream(user.getFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
        } catch (Exception ex) {
            System.out.println("Error while trying to save the \"user.dat\" file!\n" + ex);
        }
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void addElixir(int elixir) {
        this.elixir += elixir;
    }

    public ArrayList<Build> getBuildsPlacedByName(String buildName){
        ArrayList<Build> arrayList = new ArrayList<>();

        for (Build build: getBuildsPlaced()){
            if (build.getName().equals(buildName)){
                arrayList.add(build);
            }
        }

        return arrayList;
    }

    public double calcTotalElixir(){
        double totalElixir = 0;

        for (Build build: getBuildsPlacedByName("Elixir Collector")){
            ElixirCollector ec = (ElixirCollector) build;
            totalElixir += ec.getElixirStored();
        }

        return totalElixir;
    }

    public double calcTotalGold() {
        double totalGold = 0;

        for (Build build: getBuildsPlacedByName("Gold Mine")){
            GoldMine gm = (GoldMine) build;
            totalGold += gm.getGoldStored();
        }

        return totalGold;
    }

    public ArrayList<Polygon> calcResourcesTiles(String type) {
        ArrayList<Polygon> rt = new ArrayList<>();

        if (type.equals("gold")) {
            if (calcTotalGold() >= 10) {
                rt = collectGoldTiles;
            }

        } else if (type.equals("elixir")) {
            if (calcTotalElixir() >= 10) {
                rt = collectElixirTiles;
            }
        }

        return rt;
    }

    public ArrayList<Polygon> getCollectGoldTiles() {
        return collectGoldTiles;
    }

    public ArrayList<Polygon> getCollectElixirTiles() {
        return collectElixirTiles;
    }
}
