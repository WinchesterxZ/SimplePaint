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
public class Clear  extends Tools{
    
    public Clear() {
                super("Clear",  Util.resizeImage(Clear.class.getResource(Icons.CLEAR_ICON).getPath(), 30, 30));

    }
    
       public static ArrayList<Shape> clear(ArrayList<Shape> shapes){
        if(!shapes.isEmpty()){
            shapes.clear();
            return shapes;
        }
        return shapes;
    }
    
}
