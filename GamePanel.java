package Grafica.JavaClashOfClans;

import Grafica.JavaClashOfClans.builds.Build;
import Grafica.JavaClashOfClans.builds.resources.ElixirCollector;
import Grafica.JavaClashOfClans.builds.resources.GoldMine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private BufferedImage tile1, tile2, tile3, tile4, tile5, tile6, gold, elixir, bg;
    private int spazioLinee, linee, padding;
    private MainWindow mainWindow;
    private final double cos35 = Math.cos(Math.toRadians(35));
    private final double sin35 = Math.sin(Math.toRadians(35));

    private User user;
    private Timer resourceRepaintTimer;
    private ResourcePanel resourceGoldPanel, resourceElixirPanel;


    //? variabili per le build nuove
    int colTemp = 0, rowTemp = 0;
    private Build newBuild;
    private boolean drawNewBuild, newBuildCollide, paintedElixirIcon, paintedGoldIcon;
    private Tile newTileBuild;
    private MouseMotionListener newBuildMouseMotionListener, resourcesMouseMotionListener;


    GamePanel(int spazioLinee, int linee, int padding, MainWindow mainWindow) {
        super(null); //? imposto il layout a null per permettere ai componenti aggiunti successivamente di mettersi alle posizioni x e y desiderate
        setBackground(Color.black);

        this.spazioLinee = spazioLinee;
        this.linee = linee;
        this.padding = padding;
        this.mainWindow = mainWindow;
        this.user = new User();

        if (user.getFile().exists()) {
            try {
                user.initFromFile(user.getFile(), this);
            } catch (Exception e) {
                System.out.println("[ERROR] "+"Error while loading \"user.dat\" file!\n" + e);
                System.out.println("Creating new user...");
                user = new User(spazioLinee, linee, padding);
            }
        } else {
            user = new User(spazioLinee, linee, padding);
        }

        try {
            File img1 = new File(MainWindow.assetsPath +"/images/tile1.png");
            tile1 = ImageIO.read(img1);

            File img2 = new File(MainWindow.assetsPath +"/images/tile2.png");
            tile2 = ImageIO.read(img2);

            File img3 = new File(MainWindow.assetsPath +"/images/tile3.png");
            tile3 = ImageIO.read(img3);

            File img4 = new File(MainWindow.assetsPath +"/images/tile4.png");
            tile4 = ImageIO.read(img4);

            File img5 = new File(MainWindow.assetsPath +"/images/tile5.png");
            tile5 = ImageIO.read(img5);

            File img6 = new File(MainWindow.assetsPath +"/images/tile6.png");
            tile6 = ImageIO.read(img6);

            File bgimg = new File(MainWindow.assetsPath +"/images/background.png");
            bg = ImageIO.read(bgimg);

            File goldImg = new File(MainWindow.assetsPath +"/images/gold.png");
            gold = ImageIO.read(goldImg);

            File elixirImg = new File(MainWindow.assetsPath +"/images/elixir.png");
            elixir = ImageIO.read(elixirImg);
        } catch (Exception ignored) {}

        //? aggiungo i bottoni
        add(new ShopButton(mainWindow));

        //? aggiungo le risorse in alto a destra
        this.resourceGoldPanel = new ResourcePanel(mainWindow, gold, new Color(229,191,11) ,user.getGold(), user.getMaxGold(), 200, 30, 25, 25);
        this.resourceElixirPanel = new ResourcePanel(mainWindow, elixir, new Color(189,39,192) ,user.getElixir(), user.getMaxElixir(), 200, 30, 25, 25+30+25);
        add(resourceGoldPanel);
        add(resourceElixirPanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int bgWidth = bg.getWidth()*MainWindow.calcBottomY(mainWindow,0)/bg.getHeight();
        g.drawImage(bg, -(bgWidth - MainWindow.calcRightX(mainWindow, 0))/2,0, bgWidth,MainWindow.calcBottomY(mainWindow,0) ,null);


        g.setColor(Color.GREEN);
        double xpoint = cos35 * spazioLinee;
        double ypoint = sin35 * spazioLinee;

        //? disegno le tiles
        baseLoader(g);

        //? disegno la griglia
        for (int i = 0; i <= linee; i++) {
            int[][] lines_points = calculateLinesPoints(i, xpoint, ypoint);
            //? lines_points[0] = punti di linee che vanno da sud-est nord ovest
            //? lines_points[1] = punti di linee che vanno da sud-ovest nord-est


            //? disegno le linee passando la x e y dei 2 punti
            g.drawLine(lines_points[1][0],lines_points[1][1],  lines_points[1][2],lines_points[1][3]);
            g.drawLine(lines_points[0][0],lines_points[0][1],  lines_points[0][2],lines_points[0][3]);

        }

        //? disegno le costruzioni
        buildLoader(g);

        if (drawNewBuild && newTileBuild != null && newBuild != null) {
            //? disegno le tile delle build che sto per andare a inserire nella base
            String size = newBuild.getSize();
            int width = Integer.parseInt(size.split("x")[0]);
            int height = Integer.parseInt(size.split("x")[1]);
            BufferedImage newTile1 = tile3, newTile2 = tile4;

            g.drawImage(newBuild.getBuildImg(), coordsBuildImagesCalc(newBuild)[0], coordsBuildImagesCalc(newBuild)[1], null);

            if (newBuildCollide) {
                newTile1 = tile5;
                newTile2 = tile6;
            }

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    g.drawImage(newTile1,
                            (int)(newTileBuild.xpoints[0] - spazioLinee + cos35*spazioLinee*j - cos35*spazioLinee*i),
                            (int)(newTileBuild.ypoints[0] + sin35*spazioLinee*j + sin35*spazioLinee*i),
                            spazioLinee+2, (int) (sin35 * spazioLinee * 2)+2, null);

                    g.drawImage(newTile2,
                            (int)(newTileBuild.xpoints[0] + cos35*spazioLinee*j - cos35*spazioLinee*i),
                            (int)(newTileBuild.ypoints[0] + sin35*spazioLinee*j + sin35*spazioLinee*i),
                            spazioLinee+2, (int) (sin35 * spazioLinee * 2)+2, null);
                }
            }




        }




        if (user.calcTotalElixir() >= 10){
            for (Build b :user.getBuildsPlacedByName("Elixir Collector")){
                ElixirCollector ec = (ElixirCollector) b;
                int x = ec.getTiles()[0].xpoints[0] - elixir.getWidth()/2;
                int y = ec.getTiles()[0].ypoints[0] - elixir.getHeight() - 10;

                g.drawImage(elixir, x, y, null);
            }
        }

        if (user.calcTotalGold() >= 10){
            for (Build b :user.getBuildsPlacedByName("Gold Mine")){
                GoldMine ec = (GoldMine) b;
                int x = ec.getTiles()[0].xpoints[0] - gold.getWidth()/2;
                int y = ec.getTiles()[0].ypoints[0] - gold.getHeight() - 10;

                g.drawImage(gold, x, y, null);
            }
        }
    }

    private int[][] calculateLinesPoints(int i, double xpoint, double ypoint) {
        //? i = linea della griglia numero i (prima(=0) e ultima(=linee) => bordi)
        //? x = coseno di 35 gradi * spazio tra le linee
        //? y = seno di 35 gradi * spazio tra le linee
        //? punti della linea che vanno da sud-ovest a nord-est

        int[] line_sw_ne = new int[]{(int) (xpoint*(linee+i)),  //? xA + x * i => x * (max + i)
                (int) (ypoint*(linee*2-i)),                     //? yA - y * i => y * (max*2 - i)
                (int) (xpoint*i),                               //? xB * i
                (int) (ypoint*(linee-i))};                      //? yB - y * i => y * (max + i)


        //? punti della linea che vanno da sud-est a nord-ovest
        int[] line_se_nw = new int[]{(int) (xpoint*(linee-i)),  //? xA - x * i => x * (max - i)
                (int) (ypoint*(linee*2-i)),                     //? yA - y * i => y * (max*2 - i)
                (int) (xpoint*(linee*2-i)),                     //? xD - x * i => x * (max*2 - i)
                (int) (ypoint*(linee-i))};                      //? yD - y * i => y * (max - i)

        //? aggiungo il padding
        if (padding > 0){
            for (int j = 0; j < line_sw_ne.length; j++) {
                line_sw_ne[j] += padding;
                line_se_nw[j] += padding;
            }
        }

        //? creo un array multidimensionale per ritornare i due array in uno
        int[][] returned_array = new int[2][line_sw_ne.length];
        for (int j = 0; j < line_sw_ne.length; j++) {
            returned_array[0][j] = line_se_nw[j];
            returned_array[1][j] = line_sw_ne[j];
        }

        return returned_array;
    }
    private void baseLoader(Graphics g) {
        //?+ 2px per l'altezza e larghezza di ogni tile perché così aggiungo lo spazio occupato dalle linee della griglia ai lati dell'immagine
        for (int i = 0; i < user.getTiles().length; i++) {
            for (int j = 0; j < user.getTiles()[i].length; j++) {
                Tile currentTile = user.getTiles()[i][j];

                if (currentTile.getBuild() == null) {
                    g.drawImage(tile1, currentTile.xpoints[0] - spazioLinee, currentTile.ypoints[0], spazioLinee + 2, (int) (spazioLinee * sin35 * 2) + 2, null);
                    g.drawImage(tile2, currentTile.xpoints[0], currentTile.ypoints[0], spazioLinee + 2, (int) (spazioLinee * sin35 * 2) + 2, null);
                } else {
                    g.drawImage(tile3, currentTile.xpoints[0]-spazioLinee, currentTile.ypoints[0], spazioLinee+2, (int)(spazioLinee*sin35*2)+2, null);
                    g.drawImage(tile4, currentTile.xpoints[0], currentTile.ypoints[0], spazioLinee+2, (int)(spazioLinee*sin35*2)+2, null);
                }

            }
        }
    }
    private void buildLoader(Graphics g){
        for (Build build : user.getBuildsPlaced()) {
            if (build.getBuildImg() != null) {
                g.drawImage(build.getBuildImg(), coordsBuildImagesCalc(build)[0], coordsBuildImagesCalc(build)[1], null);
            }
        }
    }

    private int[] coordsBuildImagesCalc(Build build){
        int x, y;
        switch (build.getName()) {
            case "Archer Tower":
                //? punto y più basso dell'immagine più in alto rispetto al centro
                x = build.getTiles()[build.getTiles().length-1].xpoints[0] - (build.getBuildImg().getWidth() / 2);
                y = build.getTiles()[build.getTiles().length-1].ypoints[0] - build.getBuildImg().getHeight();
                break;
            default:
                //? punto y più basso dell'immagine al centro dell'ultima tile
                x = build.getTiles()[build.getTiles().length - 1].xpoints[2] - build.getBuildImg().getWidth()/2;
                y = build.getTiles()[build.getTiles().length - 1].ypoints[2] - build.getBuildImg().getHeight();
        }

        return new int[]{x, y};
    }

    //--- New Build Listener
    public void newBuildToggleMouseListener(boolean value, Build build) {
        if (!value && newBuildMouseMotionListener != null) {
            drawNewBuild = false;
            removeMouseMotionListener(newBuildMouseMotionListener);
            newBuildMouseMotionListener = null;
        } else {
            this.newBuild = build;
            drawNewBuild = true;
            newBuildMouseMotionListener = new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    GamePanel.this.newBuildMouseMoved(e, build);
                }
            };
            addMouseMotionListener(newBuildMouseMotionListener);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    GamePanel.this.newBuildMouseClicked(build);
                    removeMouseListener(this);
                }
            });
        }

    }
    private void newBuildMouseMoved(MouseEvent e, Build build) {
        boolean found = false;
        for (colTemp = 0; colTemp < user.getTiles().length; colTemp++) {
            for (rowTemp = 0; rowTemp < user.getTiles()[colTemp].length; rowTemp++) {
                if (user.getTiles()[colTemp][rowTemp].contains(e.getX(), e.getY())) {
                    if (colTemp + Integer.parseInt(build.getSize().split("x")[0]) > user.getTiles().length){
                        colTemp -= colTemp + Integer.parseInt(build.getSize().split("x")[0]) - user.getTiles().length;
                    }
                    if (rowTemp + Integer.parseInt(build.getSize().split("x")[1]) > user.getTiles()[colTemp].length) {
                        rowTemp -= rowTemp + Integer.parseInt(build.getSize().split("x")[1]) - user.getTiles()[colTemp].length;
                    }
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }


        if (found) {
            newTileBuild = new Tile(spazioLinee, linee, padding, colTemp, rowTemp);

            boolean collide = false;
            for (int i = 0; i < Integer.parseInt(build.getSize().split("x")[0]); i++) {
                for (int j = 0; j < Integer.parseInt(build.getSize().split("x")[1]); j++) {
                    if (user.getTiles()[colTemp+i][rowTemp+j].getBuild() != null) {
                        collide = true;
                        break;
                    }
                }
            }

            newBuildCollide = collide;

            setNewBuildTiles(build, false);

            repaint();
        }
    }
    private void newBuildMouseClicked(Build build) {
        if (!newBuildCollide) {
            newBuildToggleMouseListener(false, null);

            setNewBuildTiles(build, true);

            if (build.getName().equals("Gold Mine")){
                GoldMine gm = (GoldMine) build;
                gm.setCanProduce(true);
                int x1 = gm.getTiles()[0].xpoints[0];
                int x2 = gm.getTiles()[2].xpoints[1];
                int x3 = gm.getTiles()[6].xpoints[3];
                int x4 = gm.getTiles()[8].xpoints[2];
                int y1 = gm.getTiles()[0].ypoints[0];
                int y2 = gm.getTiles()[2].ypoints[1];
                int y3 = gm.getTiles()[6].ypoints[3];
                int y4 = gm.getTiles()[8].ypoints[2];
                user.getCollectGoldTiles().add(new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4}, 4));
                resourcesToggleMouseListener(true, "gold");
            } else if (build.getName().equals("Elixir Collector")){
                ElixirCollector ec = (ElixirCollector) build;
                ec.setCanProduce(true);
                int x1 = ec.getTiles()[0].xpoints[0];
                int x2 = ec.getTiles()[2].xpoints[1];
                int x3 = ec.getTiles()[6].xpoints[3];
                int x4 = ec.getTiles()[8].xpoints[2];
                int y1 = ec.getTiles()[0].ypoints[0];
                int y2 = ec.getTiles()[2].ypoints[1];
                int y3 = ec.getTiles()[6].ypoints[3];
                int y4 = ec.getTiles()[8].ypoints[2];
                user.getCollectElixirTiles().add(new Polygon(new int[]{x1,x2,x3,x4},new int[]{y1,y2,y3,y4}, 4));
                resourcesToggleMouseListener(true, "elixir");
            }


            user.getBuildsPlaced().add(build);
            user.save(user);

            repaint();

            reloadItemsShop();
        }
    }

    private void setNewBuildTiles(Build build, boolean saveBuildShadow) {
        int buildWidth = Integer.parseInt(build.getSize().split("x")[0]);
        int buildHeight = Integer.parseInt(build.getSize().split("x")[1]);
        Tile[] buildTiles = new Tile[buildHeight*buildWidth];

        for (int i = colTemp; i < colTemp + buildWidth; i++) {
            for (int j = rowTemp; j < rowTemp + buildHeight; j++) {
                if (saveBuildShadow) {
                    user.getTiles()[i][j].setBuild(build);
                }
                buildTiles[((i-colTemp)*buildWidth)+(j-rowTemp)] = user.getTiles()[i][j];
            }
        }
        build.setTiles(buildTiles);
    }

    private void reloadItemsShop() {
        mainWindow.shopPanel = new ShopPanel(mainWindow);
        mainWindow.getContentPane().add(mainWindow.shopPanel, "Shop");
    }

    //--- Resources Listener
    public void resourcesToggleMouseListener(boolean value, String type){
        if (!value){
            removeMouseMotionListener(resourcesMouseMotionListener);
            if (this.resourceRepaintTimer != null){
                resourceRepaintTimer.stop();
            }
        } else {
            this.resourceRepaintTimer = new Timer(15, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (user.calcTotalElixir() >= 10 && paintedElixirIcon) {
                        repaint();
                        paintedElixirIcon = true;
                    } else {
                        paintedElixirIcon = false;
                    }

                    if (user.calcTotalGold() >= 10 && paintedGoldIcon) {
                        repaint();
                        paintedGoldIcon = true;
                    } else {
                        paintedGoldIcon = false;
                    }
                }
            });

            resourceRepaintTimer.start();
            resourcesMouseMotionListener = new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    ArrayList<Polygon> resourcesTiles = user.calcResourcesTiles(type);
                    GamePanel.this.resourcesMouseMoved(e, type, resourcesTiles);
                }
            };
            addMouseMotionListener(resourcesMouseMotionListener);

        }
    }
    private void resourcesMouseMoved(MouseEvent e, String type, ArrayList<Polygon> rt){

        for (Polygon tiles: rt) {
            if (tiles.contains(e.getX(), e.getY()) && getMouseListeners().length == 0) {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        GamePanel.this.resourcesMouseClicked(type);
                        removeMouseListener(this);
                    }
                });
            }
        }
    }
    private void resourcesMouseClicked(String type){
        if (type.equals("gold")) {
            if (user.calcTotalGold() >= 10) {
                double totalGold = 0;
                for (Build build : user.getBuildsPlacedByName("Gold Mine")) {
                    GoldMine gm = (GoldMine) build;
                    totalGold += gm.collect();
                }
                user.addGold((int) totalGold);
                this.resourceGoldPanel.aaa(user.getMaxGold(), user.getGold());
                repaint();
            }

        } else if (type.equals("elixir")) {
            if (user.calcTotalElixir() >= 10) {
                double totalElixir = 0;
                for (Build build : user.getBuildsPlacedByName("Elixir Collected")) {
                    ElixirCollector ec = (ElixirCollector) build;
                    totalElixir += ec.collect();
                }

                user.addElixir((int)totalElixir);
                this.resourceElixirPanel.aaa(user.getMaxElixir(), user.getElixir());
                repaint();
            }

        }
    }

}
