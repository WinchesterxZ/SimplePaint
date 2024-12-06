/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import icons.Icons;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import paintgui.Util;
import shapes.Shape;

/**
 *
 * @author Administrator
 */
public class Redo extends Tools {
    public static ArrayList<Shape> undoShapes;
    public Redo() {
                super("Redo",  Util.resizeImage(Redo.class.getResource(Icons.REDO_ACTIVE_ICON).getPath(), 30, 30));
                undoShapes = new ArrayList<>();

    }
    
     public static ArrayList<Shape> redo(ArrayList<Shape> shapes){
        if(!undoShapes.isEmpty()){
            shapes.add(undoShapes.get(undoShapes.size() -1));
            undoShapes.remove(undoShapes.size() -1);
            return shapes;
        }
        return shapes;
    }
}
