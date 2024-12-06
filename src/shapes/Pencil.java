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
import shapes.Line;

/**
 *
 * @author Administrator
 */
public class Pencil extends Shape {

    private final ArrayList<Point> points = new ArrayList<>();

    public Pencil() {
        super(
                "Pencil",
                Util.resizeImage(Pencil.class.getResource(Icons.PENCIL_ICON).getPath(), 30, 30));
    }

    public Pencil(int startX, int startY, int endX, int endY, float strokeWidth, Color color, boolean isSolid, boolean isDotted) {
        super(startX, startY, endX, endY, strokeWidth, color, isSolid, isDotted);
    }

    @Override
    public void addPoint(Point point) {
        points.add(point);
    }

    @Override
    public void draw(Graphics2D g2d) {

        if (isDotted) {
            float[] dashPattern = {3, 3};
            g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dashPattern, 0));
        } else {
            g2d.setStroke(new BasicStroke(strokeWidth));
        }

        g2d.setColor(color);
        if (points.size() == 1) {
            // Draw a dot for a single point
            Point p = points.get(0);
            g2d.fillOval(p.x - 2, p.y - 2, 1 + (int) (strokeWidth * 2), 1 + (int) (strokeWidth * 2)); // Small dot
        }
        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
        // g2d.drawLine(startX, startY, endX, endY);
    }

}
