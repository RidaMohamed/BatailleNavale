package engine.gameEditor;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class RectangleButton extends JPanel {

    Rectangle2D.Double rect;
    public RectangleButton() {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                System.out.println(checkRectArea(point));
                // Do whatever else you want here.
            }
        });
    }

    public boolean checkRectArea(Point point) {
        return rect.contains(point);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        rect = new Rectangle2D.Double(10, 10, 150, 75);
        g2.draw(rect);
        BufferedImage play = null;
        try {
            play = ImageIO.read(this.getClass().getResourceAsStream("/Ressources/play.png"));
            g.drawImage(play, (int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight(), null);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
