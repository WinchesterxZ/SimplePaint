/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class Tools {
    protected String name;
    protected ImageIcon icon;

    public Tools(String name, ImageIcon icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public ImageIcon getIcon() {
        return icon;
    }
    
    
    
}
