package Grafica.JavaClashOfClans;

import Grafica.JavaClashOfClans.builds.Build;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ShopItemPanel extends JPanel {
    private int width, height;
    private BufferedImage gold, elixir;

    ShopItemPanel(Build build, int width, MainWindow mainWindow) {
        super(null);

        try {
            File goldImg = new File(MainWindow.assetsPath +"/images/gold.png");
            gold = ImageIO.read(goldImg);
            File elixirImg = new File(MainWindow.assetsPath +"/images/elixir.png");
            elixir = ImageIO.read(elixirImg);
        } catch (Exception ignored) {}


        JLabel nameLabel = new JLabel(build.getName(), SwingConstants.CENTER);
        nameLabel.setBounds(0,0,width,50);
        add(nameLabel);



        JPanel costPanel = new JPanel();
        costPanel.setBounds(20,50,width-(20*2), 50);
        costPanel.setBorder(BorderFactory.createLineBorder(new Color(66,49,137)));

        JLabel baseCost = new JLabel(build.getBaseCost()+"");
        baseCost.setFont(new Font("", Font.BOLD, 20));
        costPanel.add(baseCost);

        JPanel typeCostImagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (build.getTypeCost().equals("gold")) {
                    g.drawImage(gold, 0, 0, 25, 25, this);
                } else if (build.getTypeCost().equals("elixir")) {
                    g.drawImage(elixir, 0, 0, 25, 25, this);
                }
            }
        };

        //? imposto le dimensioni del pannello che contiene l'immagine perché se no non si modella in base alla grandezza dell'immagine
        typeCostImagePanel.setPreferredSize(new Dimension(25, 25));

        costPanel.add(typeCostImagePanel);
        add(costPanel);




        JPanel buildImagePanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(build.getBuildImg(), 0, 0, 150, 150, this);
            }

        };

        buildImagePanel.setBounds((width-150)/2,125,150,150);
        add(buildImagePanel);
        JButton btn = new ShopItemButton(mainWindow, build);
        btn.setBounds(20,300,width-(20*2),50);
        add(btn);


        setBorder(BorderFactory.createLineBorder(Color.black));
    }




}
