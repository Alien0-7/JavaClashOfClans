package Grafica.JavaClashOfClans;

import Grafica.JavaClashOfClans.builds.Build;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

public class GamePanel extends JPanel {
    private BufferedImage tile1, tile2, tile3, tile4, bg;
    private int spazioLinee, linee, padding;
    private MainWindow mainWindow;
    private final double cos35 = Math.cos(Math.toRadians(35));
    private final double sin35 = Math.sin(Math.toRadians(35));

    private User user;

    //? variabili per le build nuove
    int colTemp = 0, rowTemp = 0;
    private Build newBuild;
    private boolean drawNewBuild;
    private Tile newTileBuild;

    GamePanel(int spazioLinee, int linee, int padding, MainWindow mainWindow) {
        super(null); //? imposto il layout a null per permettere ai componenti aggiunti successivamente di mettersi alle posizioni x e y desiderate
        setBackground(Color.black);

        this.spazioLinee = spazioLinee;
        this.linee = linee;
        this.padding = padding;
        this.mainWindow = mainWindow;
        this.user = new User(linee);

        //? inizializzo l'array multidimensionale che poi mi servirà per capire in quale cella il mouse è posizionato
        for (int i = 0; i < linee; i++) {
            for (int j = 0; j < linee; j++) {
                if (user.getTiles()[i][j] == null) {
                    user.getTiles()[i][j] = new Tile(spazioLinee, linee, padding, i, j);
                }
            }
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

            File bgimg = new File(MainWindow.assetsPath +"/images/background.png");
            bg = ImageIO.read(bgimg);
        } catch (Exception ignored) {}

        //? aggiungo i bottoni
        add(new ShopButton(mainWindow));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int bgWidth = bg.getWidth()*MainWindow.calcRightY(mainWindow,0)/bg.getHeight();

        g.drawImage(bg, -(bgWidth - MainWindow.calcRightX(mainWindow, 0))/2,0, bgWidth,MainWindow.calcRightY(mainWindow,0) ,null);

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

        if (drawNewBuild && newTileBuild != null && newBuild != null){
            //? disegno le tile delle build che sto per andare a inserire nella base
            String size = newBuild.getSize();
            int width = Integer.parseInt(size.split("x")[0]);
            int height = Integer.parseInt(size.split("x")[1]);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    g.drawImage(tile3,
                            (int)(newTileBuild.xpoints[0] - spazioLinee + cos35*spazioLinee*j - cos35*spazioLinee*i),
                            (int)(newTileBuild.ypoints[0] + sin35*spazioLinee*j + sin35*spazioLinee*i),
                            spazioLinee+2, (int) (sin35 * spazioLinee * 2)+2, null);

                    g.drawImage(tile4,
                            (int)(newTileBuild.xpoints[0] + cos35*spazioLinee*j - cos35*spazioLinee*i),
                            (int)(newTileBuild.ypoints[0] + sin35*spazioLinee*j + sin35*spazioLinee*i),
                            spazioLinee+2, (int) (sin35 * spazioLinee * 2)+2, null);
                }
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
                switch (user.getTiles()[i][j].typeOfBuild.toLowerCase()){
                    case "bomb":
                    case "archer tower":
                    case "cannon":
                    case "wall":
                        g.drawImage(tile3, user.getTiles()[i][j].xpoints[0]-spazioLinee, user.getTiles()[i][j].ypoints[0], spazioLinee+2, (int)(spazioLinee*sin35*2)+2, null);
                        g.drawImage(tile4, user.getTiles()[i][j].xpoints[0], user.getTiles()[i][j].ypoints[0], spazioLinee+2, (int)(spazioLinee*sin35*2)+2, null);
                        break;
                    default:
                        g.drawImage(tile1, user.getTiles()[i][j].xpoints[0]-spazioLinee, user.getTiles()[i][j].ypoints[0], spazioLinee+2, (int)(spazioLinee*sin35*2)+2, null);
                        g.drawImage(tile2, user.getTiles()[i][j].xpoints[0], user.getTiles()[i][j].ypoints[0], spazioLinee+2, (int)(spazioLinee*sin35*2)+2, null);

                }

            }
        }
    }

    public void toggleMouseListener(boolean value, Build build) {
        if (!value && getMouseMotionListeners().length > 0) {
            drawNewBuild = false;
            removeMouseMotionListener(getMouseMotionListeners()[0]);
        } else {
            this.newBuild = build;
            drawNewBuild = true;
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    GamePanel.this.mouseMoved(e, build);
                }
            });

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    GamePanel.this.mouseClicked(build);
                    removeMouseListener(this);
                }
            });
        }

    }
    private void mouseMoved(MouseEvent e, Build build) {
        boolean trovato = false;
        Tile oldTile = new Tile(spazioLinee,linee,padding, colTemp, rowTemp);
        for (colTemp = 0; colTemp < user.getTiles().length; colTemp++) {
            for (rowTemp = 0; rowTemp < user.getTiles()[colTemp].length; rowTemp++) {
                if (user.getTiles()[colTemp][rowTemp].contains(e.getX(), e.getY())) {
                    if (colTemp + Integer.parseInt(build.getSize().split("x")[0]) > user.getTiles().length){
                        colTemp -= colTemp + Integer.parseInt(build.getSize().split("x")[0]) - user.getTiles().length;
                    }
                    if (rowTemp + Integer.parseInt(build.getSize().split("x")[1]) > user.getTiles()[colTemp].length) {
                        rowTemp -= rowTemp + Integer.parseInt(build.getSize().split("x")[1]) - user.getTiles()[colTemp].length;
                    }
                    trovato = true;
                    break;
                }
            }
            if (trovato) {
                break;
            }
        }


        if (trovato) {
            Tile currentTile = new Tile(spazioLinee,linee,padding, colTemp, rowTemp);
            if (!currentTile.toString().equals(oldTile.toString())){
                oldTile = currentTile;
                if (newTileBuild == null || !oldTile.toString().equals(newTileBuild.toString())){
                    newTileBuild = oldTile;
                }
                repaint();


            }
        }
    }
    private void mouseClicked(Build build) {
        toggleMouseListener(false, null);


        for (int i = colTemp; i < colTemp +Integer.parseInt(build.getSize().split("x")[0]); i++) {
            for (int j = rowTemp; j < rowTemp +Integer.parseInt(build.getSize().split("x")[1]); j++) {
                user.getTiles()[i][j].typeOfBuild = build.getName();
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream(user.getFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
        } catch (Exception ex){
            System.out.println("Error while trying to save the \"user.dat\" file!\n"+ex);
        }
    }

}
