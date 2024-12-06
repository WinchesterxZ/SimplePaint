package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.ImageIcon;

public abstract class Shape {

    protected int startX, startY, endX, endY;
    protected float strokeWidth;
    protected Color color;
    protected String name;
    protected ImageIcon icon;
    protected boolean isSolid,isDotted;

    public Shape(int startX, int startY, int endX, int endY, float strokeWidth, Color color,boolean isSolid,boolean isDotted) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.strokeWidth = strokeWidth;
        this.color = color;
        this.isSolid = isSolid;
        this.isDotted = isDotted;
    }

    public void setIsDotted(boolean isDotted) {
        this.isDotted = isDotted;
    }

    
    public void setIsSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }


    
    public Shape(String name, ImageIcon icon) {
        this.name = name;
        this.icon = icon;
    }
   
 
    
    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    


    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
    
        public void addPoint(Point point) {
    }

    
    public abstract void draw(Graphics2D g2d);

}
