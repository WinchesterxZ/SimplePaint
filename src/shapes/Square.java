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
import paintgui.Util;

/**
 *
 * @author Administrator
 */
public class Square extends Shape {

    public Square() {
        super(
                "Square",
                Util.resizeImage(Square.class.getResource(Icons.SQUARE_ICON).getPath(), 30, 30));
    }

    public Square(int startX, int startY, int endX, int endY, float strokeWidth, Color color, boolean isSolid, boolean isDotted) {
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

        // int side = Math.min(Math.abs(endX - startX), Math.abs(endY - startY));
        int side = Math.abs(endX - startX);
        int halfSide = side / 2;
        if (isSolid) {
            g2d.fillRect(Math.min(startX, endX), Math.min(startY, endY), halfSide, halfSide);

        } else {
            g2d.drawRect(Math.min(startX, endX), Math.min(startY, endY), halfSide, halfSide);

        }

    }

}
