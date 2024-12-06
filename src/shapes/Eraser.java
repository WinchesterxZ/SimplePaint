/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

import icons.Icons;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import paintgui.Util;

/**
 *
 * @author Administrator
 */
public class Eraser extends Shape {
        private final ArrayList<Point> points = new ArrayList<>();

    
    public Eraser() {
        super(
                "Eraser",
                Util.resizeImage(Eraser.class.getResource(Icons.ERASER_ICON).getPath(), 30, 30));
    }
    

        @Override
            public void addPoint(Point point) {
            points.add(point);
        }
            
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(strokeWidth));

        g2d.setColor(Color.WHITE);
        if (points.size() == 1) {
                Point p = points.get(0);
                g2d.fillRect(p.x - 2, p.y - 2, 1 +(int)(strokeWidth), 1+ (int) (strokeWidth)); 
            }
          for (int i = 1; i < points.size(); i++) {
                Point p1 = points.get(i - 1);
                Point p2 = points.get(i);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
    }
}
