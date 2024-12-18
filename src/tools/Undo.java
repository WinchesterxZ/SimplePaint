/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import icons.Icons;
import java.util.ArrayList;
import paintgui.Util;
import shapes.Shape;
/**
 *
 * @author Administrator
 */
public class Undo extends Tools {
    public Undo() {
        super("Undo",  Util.resizeImage(Undo.class.getResource(Icons.UNDO_ACTIVE_ICON).getPath(), 30, 30));
    }

    
    public static ArrayList<Shape> undo(ArrayList<Shape> shapes){
        if(!shapes.isEmpty()){
            Redo.undoShapes.add(shapes.get(shapes.size() - 1));
            shapes.remove(shapes.size() - 1);
            return shapes;
        }
        return shapes;
    }


}
