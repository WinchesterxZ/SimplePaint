package paintgui;

import shapes.Eraser;
import shapes.Pencil;
import shapes.Line;
import shapes.Oval;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Square;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import tools.Clear;
import tools.Redo;
import tools.Save;
import tools.Tools;
import tools.Undo;

public class Util {

    public static ArrayList<Shape> loadShapes() {
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Line());
        shapes.add(new Rectangle());
        shapes.add(new Square());
        shapes.add(new Oval());
        return shapes;
    }

    public static ArrayList<Shape> loadBrushs() {
        ArrayList<Shape> brushes = new ArrayList<>();
        brushes.add(new Pencil());
        brushes.add(new Eraser());
        return brushes;
    }

    public static ArrayList<Tools> loadTools() {
        ArrayList<Tools> tools = new ArrayList<>();
        tools.add(new Undo());
        tools.add(new Redo());
        tools.add(new Save());
        tools.add(new Clear());

        return tools;
    }

    public static ArrayList<Color> loadColors() {
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.ORANGE);
        colors.add(Color.PINK);
        colors.add(Color.MAGENTA);
        colors.add(Color.CYAN);
        colors.add(Color.GRAY);
        colors.add(Color.DARK_GRAY);
        colors.add(Color.LIGHT_GRAY);
        colors.add(Color.BLACK);
        colors.add(Color.WHITE);
        colors.add(new Color(128, 0, 128)); // Purple
        colors.add(new Color(165, 42, 42)); // Brown
        colors.add(new Color(255, 105, 180)); // Hot Pink
        return colors;
    }

    public static ImageIcon resizeImage(String path, int w, int h) {
        Image scaledImage = new ImageIcon(path).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

 
}
