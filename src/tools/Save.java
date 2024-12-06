/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import icons.Icons;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import paintgui.Util;

/**
 *
 * @author Administrator
 */
public class Save extends Tools{
    
    
    public Save() {
                super("Save",  Util.resizeImage(Save.class.getResource(Icons.SAVE_ICON
                ).getPath(), 30, 30));

    }
  public static void savePanelAsImage(Component parent, JPanel panel) {
        try {
            // Create a BufferedImage from the JPanel's content
            BufferedImage panelImage = new BufferedImage(
                    panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB
            );

            // Render the JPanel to the BufferedImage
            panel.paint(panelImage.getGraphics());

            // Save the image using a JFileChooser
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Image");
            int userSelection = fileChooser.showSaveDialog(parent);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                ImageIO.write(panelImage, "png", new File(fileToSave.getAbsolutePath() + ".png"));
                JOptionPane.showMessageDialog(parent, "Image saved successfully!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parent, "Error saving image: " + ex.getMessage());
        }
    }
    
}
