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
import javax.swing.ImageIcon;
import paintgui.Util;

/**
 *
 * @author Administrator
 */
public class Rectangle extends Shape {

    public Rectangle() {
        super(
                "Rectangle",
                Util.resizeImage(Rectangle.class.getResource(Icons.RECTANGLE_ICON).getPath(), 30, 30)
        );
    }

    public Rectangle(int startX, int startY, int endX, int endY, float strokeWidth, Color color, boolean isSolid, boolean isDotted) {
        super(startX, startY, endX, endY, strokeWidth, color, isSolid, isDotted);
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
        if (isSolid) {
            g2d.fillRect(Math.min(startX, endX), Math.min(startY, endY),
                    Math.abs(endX - startX), Math.abs(endY - startY));
        } else {
            g2d.drawRect(Math.min(startX, endX), Math.min(startY, endY),
                    Math.abs(endX - startX), Math.abs(endY - startY));
        }

    }

}
