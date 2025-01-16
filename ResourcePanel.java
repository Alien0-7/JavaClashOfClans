package Grafica.JavaClashOfClans;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ResourcePanel extends JPanel {
    private Color resourceColor;
    private int widthImg, heightImg, paddingInternal, width, height, paddingY, resourceValue, resourceMaxValue;
    private JLabel valueLabel;

    public ResourcePanel(MainWindow mainWindow, BufferedImage resourceImg, Color resourceColor, int resourceValue, int resourceMaxValue, int width, int height, int paddingX, int paddingY) {
        int rightX = MainWindow.calcRightX(mainWindow, paddingX);

        setLayout(null);
        setOpaque(false); //? rendere il pannello con il background trasparente
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBounds(rightX-width, paddingY, width, height);

        this.resourceMaxValue = resourceMaxValue;
        this.resourceValue = resourceValue;
        this.resourceColor = resourceColor;
        this.paddingY = paddingY;
        this.paddingInternal = 10;
        this.width = width;
        this.height = height;
        this.widthImg = resourceImg.getWidth();
        this.heightImg = resourceImg.getHeight();

        JPanel resourceImage = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(resourceImg, 0, 0, null);
            }
        };

        resourceImage.setBounds(width-(widthImg+paddingInternal), (height-heightImg)/2+1, widthImg, height);
        resourceImage.setOpaque(false);

        this.valueLabel = new JLabel(resourceValue+" ");
        valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        valueLabel.setForeground(Color.white);
        valueLabel.setBounds(0, 0, width-(widthImg+paddingInternal), height);

        add(resourceImage);
        add(valueLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(resourceColor);
        int widthPercInPixel = resourceValue*width/resourceMaxValue;
        g.fillRect(width-widthPercInPixel,0,widthPercInPixel,height);
    }

    public void recreate(int max, int value) {
        this.resourceMaxValue = max;
        this.resourceValue = value;
        valueLabel.setText(value+" ");
        repaint();
    }
}
