package Grafica.JavaClashOfClans;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class GamePanel extends JPanel {
    private BufferedImage tile1, tile2;
    private int spazioLinee;
    private int linee;
    private int padding;
    private final double cos35 = Math.cos(Math.toRadians(35));
    private final double sin35 = Math.sin(Math.toRadians(35));
    private Polygon[][] tiles;

    GamePanel(int spazioLinee, int linee, int padding, MainWindow mainWindow) {
        super(null); //? imposto il layout a null per permettere ai componenti aggiunti successivamente di mettersi alle posizioni x e y desiderate
        setBackground(Color.black);

        this.spazioLinee = spazioLinee;
        this.linee = linee;
        this.padding = padding;

        tiles = new Polygon[linee][linee];

        try {
            File img1 = new File("Grafica/JavaClashOfClans/assets/tile1.png");
            tile1 = ImageIO.read(img1);

            File img2 = new File("Grafica/JavaClashOfClans/assets/tile2.png");
            tile2 = ImageIO.read(img2);
        } catch (Exception ignored) {}

        //? aggiungo i bottoni
        //? utilizzo il metodo getBounds perché come scritto nelle docs garantisce prestazioni migliori nella memoria
        add(new ShopButton(mainWindow));


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(Color.GREEN);
        double xpoint = cos35 * spazioLinee;
        double ypoint = sin35 * spazioLinee;

        //? disegno le tiles
        for (int i = 1; i <= linee; i++) {
            int[][] lines_points = calculateLinesPoints(i, xpoint, ypoint);
            //? lines_points[0] = punti di linee che vanno da sud-est nord-ovest
            //? lines_points[1] = punti di linee che vanno da sud-ovest nord-est

            //?+ 2 px per l'altezza e larghezza perché così lo faccio grande quanto lo spazio interno e le linee della griglia
            for (int j = 1; j <= linee; j++) {
                g.drawImage(tile1, (int)((lines_points[0][0]+xpoint*j)-spazioLinee), (int)(lines_points[1][1]-ypoint*j), spazioLinee+2, (int)(ypoint*2)+2, null);
                g.drawImage(tile2, (int)(lines_points[0][0]+xpoint*j), (int)(lines_points[1][1]-ypoint*j), spazioLinee+2, (int)(ypoint*2)+2, null);
            }
        }

        //? disegno la griglia
        for (int i = 0; i <= linee; i++) {
            int[][] lines_points = calculateLinesPoints(i, xpoint, ypoint);
            //? lines_points[0] = punti di linee che vanno da sud-est nord ovest
            //? lines_points[1] = punti di linee che vanno da sud-ovest nord-est


            //? disegno le linee passando la x e y dei 2 punti
            g.drawLine(lines_points[1][0],lines_points[1][1],  lines_points[1][2],lines_points[1][3]);
            g.drawLine(lines_points[0][0],lines_points[0][1],  lines_points[0][2],lines_points[0][3]);

            //? creo l'array multidimensionale che poi mi servirà per capire in quale cella il mouse è posizionato
            for (int j = 0; j < linee; j++) {
                if (i != linee) {
                    tiles[i][j] = new Tile(spazioLinee, linee, padding, i, j);
                }
            }


        }

    }

    private int[][] calculateLinesPoints(int i, double xpoint, double ypoint){
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


    private void baseLoader(JPanel bg){
        //TODO base loader from random file or txt file
    }

    public void toggleMouseListener(boolean value) {
        if (!value && getMouseMotionListeners().length > 0) {
            removeMouseMotionListener(getMouseMotionListeners()[0]);
        } else {
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    int i = 0, j = 0;
                    boolean trovato = false;
                    for (i = 0; i < tiles.length && !trovato; i++) {
                        for (j = 0; j < tiles[i].length && !trovato; j++) {
                            if (tiles[i][j].contains(e.getX(), e.getY())){
                                trovato = true;
                            }
                        }
                    }

                    if (trovato) {
                        Graphics g = getGraphics();
                        if (g != null) {
                            g.setColor(Color.RED);
                            g.fillRect(tiles[i-1][j-1].xpoints[0], tiles[i-1][j-1].ypoints[0], spazioLinee, spazioLinee);
                        }
                    }

                }
            });

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    toggleMouseListener(false);
                    removeMouseListener(this);
                }
            });
        }

    }
}
