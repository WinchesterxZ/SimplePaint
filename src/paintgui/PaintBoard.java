package paintgui;

import shapes.Pencil;
import java.awt.BasicStroke;
import shapes.Shape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.swing.JPanel;
import shapes.Eraser;
import shapes.Line;
import shapes.Oval;
import shapes.Rectangle;
import shapes.Square;

/**
 *
 * @author Administrator
 */
public class PaintBoard extends JPanel {

    private Graphics2D g2d;
    private Shape currentShape;
    private int startX, startY, endX, endY;
    private ArrayList<Shape> shapes;
    private Shape newShape;
    private boolean isToolPressed = false;
    private boolean isSolid = false;
    private boolean isDotted = false;

    public PaintBoard() {
        shapes = new ArrayList<>();
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
                if (currentShape instanceof Pencil) {
                    newShape = new Pencil();
                    newShape.setColor(ToolBar.currentColor);
                    newShape.setStrokeWidth((float) ToolBar.strokeWidth);
                    newShape.addPoint(e.getPoint());
                    newShape.setIsDotted(isDotted);
                    shapes.add(newShape);
                } else if (currentShape instanceof Eraser) {
                    newShape = new Eraser();
                    System.out.println("Eraser");
                    newShape.setStrokeWidth((float) ToolBar.strokeWidth);
                    newShape.addPoint(e.getPoint());
                    newShape.setIsDotted(isDotted);

                    shapes.add(newShape);
                } else {
                    currentShape.setStartX(startX);
                    currentShape.setStartY(startY);
                    currentShape.setColor(ToolBar.currentColor);
                    currentShape.setStrokeWidth((float) ToolBar.strokeWidth);
                    currentShape.setIsSolid(isSolid);
                    currentShape.setIsDotted(isDotted);
                }

            }

            @Override

            public void mouseReleased(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                if (!(currentShape instanceof Pencil || currentShape instanceof Eraser)) {
                    currentShape.setEndX(endX);
                    currentShape.setEndY(endY);
                    if (currentShape instanceof Line) {
                        newShape = new Line(startX, startY, endX, endY, ToolBar.strokeWidth, ToolBar.currentColor, isSolid, isDotted);
                    } else if (currentShape instanceof Rectangle) {
                        newShape = new Rectangle(startX, startY, endX, endY, ToolBar.strokeWidth, ToolBar.currentColor, isSolid, isDotted);
                    } else if (currentShape instanceof Square) {
                        newShape = new Square(startX, startY, endX, endY, ToolBar.strokeWidth, ToolBar.currentColor, isSolid, isDotted);
                    } else {
                        newShape = new Oval(startX, startY, endX, endY, ToolBar.strokeWidth, ToolBar.currentColor, isSolid, isDotted);
                    }
                    shapes.add(newShape);
                }
                repaint();
            }
        }
        );

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();

                if (currentShape instanceof Pencil || currentShape instanceof Eraser) {
                    newShape.addPoint(e.getPoint());

                } else {
                    currentShape.setEndX(endX);
                    currentShape.setEndY(endY);
                }
                repaint();

            }
        });
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
        isToolPressed = true;
        repaint();

    }

    public void setCurrentShape(Shape shape) {
        this.currentShape = shape;
    }

    public void setIsSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }

    public void setIsDotted(boolean isDotted) {
        this.isDotted = isDotted;
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.drawImage(canvas, 0, 0, null);
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (currentShape != null) {
            System.err.println("Size: " + shapes.size());
            shapes.forEach((Shape s) -> {
                s.draw(g2d);
            });
            if (!(currentShape instanceof Pencil || currentShape instanceof Eraser)) {
                if (isToolPressed) {
                    isToolPressed = false;
                    return;
                }
                currentShape.draw(g2d);
            }
            g2d.dispose();
        }

    }
}
