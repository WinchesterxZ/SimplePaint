/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintgui;

import icons.Icons;
import java.awt.BorderLayout;
import shapes.Shape;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import tools.Clear;
import tools.Redo;
import tools.Save;
import tools.Tools;
import tools.Undo;

/**
 *
 * @author Administrator
 */
class ToolBar extends JToolBar {

    static Color currentColor = Color.black;
    static int strokeWidth = 1;
    private JPanel brushPanel;
    private JPanel shapePanel;
    private JPanel colorPanel;
    private JPanel toolsPanel;
    private final JSlider strokeSlider;

    public ToolBar(PaintBoard paintBoard) {
        setPreferredSize(new Dimension(0, 150));
        setFloatable(false);
        strokeSlider = createStrokeSlider();
        strokeSlider.setVisible(false);
        add(createToolsPanel(paintBoard));
        addSeparator(new Dimension(20, 0));
        add(createBrushPanel(paintBoard));
        addSeparator(new Dimension(20, 0));
        add(createShapePanel(paintBoard));
        addSeparator(new Dimension(20, 0));
        add(createColorPanel());
        add(addCheckboxes(paintBoard));
        add(Box.createHorizontalStrut(20));
        add(strokeSlider);
    }

    private Box addCheckboxes(PaintBoard paintBoard) {
        Box checkboxesBox = Box.createVerticalBox();
        checkboxesBox.add(Box.createVerticalStrut(20));
        JCheckBox solid = new JCheckBox("Solid");
        JCheckBox dotted = new JCheckBox("Dotted");
        Font largerFont = new Font("Arial", Font.PLAIN, 18);
        solid.setFont(largerFont);
        dotted.setFont(largerFont);

        solid.setPreferredSize(new Dimension(200, 40));
        dotted.setPreferredSize(new Dimension(200, 40));
        solid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    paintBoard.setIsSolid(true);
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    paintBoard.setIsSolid(false);

                }
            }
        });
        
         dotted.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    paintBoard.setIsDotted(true);
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    paintBoard.setIsDotted(false);

                }
            }
        });
        checkboxesBox.add(solid);
        checkboxesBox.add(Box.createVerticalStrut(10));  // Gap between checkboxes
        checkboxesBox.add(dotted);

        return checkboxesBox;
    }

    private JPanel createToolsPanel(PaintBoard paintBoard) {
        toolsPanel = new JPanel();
        toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.Y_AXIS));

        JPanel toolsGridPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        for (Tools tool : Util.loadTools()) {
            JButton brushButton = createIconButton(tool.getIcon());
            brushButton.addActionListener(e -> {
                if (tool instanceof Undo) {
                    paintBoard.setShapes(Undo.undo(paintBoard.getShapes()));
                    System.err.println("Undo Pressed");
                } else if (tool instanceof Redo) {
                    paintBoard.setShapes(Redo.redo(paintBoard.getShapes()));
                } else if (tool instanceof Clear) {
                    paintBoard.setShapes(Clear.clear(paintBoard.getShapes()));
                } else {
                    Save.savePanelAsImage(this, paintBoard);
                }
            });
            toolsGridPanel.add(brushButton);
        }

        toolsPanel.add(toolsGridPanel);
        toolsPanel.add(Box.createVerticalStrut(10));
        JLabel label = new JLabel("Tools", JLabel.CENTER);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        toolsPanel.add(label);

        return toolsPanel;
    }

    private JPanel createBrushPanel(PaintBoard paintBoard) {
        brushPanel = new JPanel();
        brushPanel.setLayout(new BoxLayout(brushPanel, BoxLayout.Y_AXIS));

        JPanel brushGridPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        for (Shape brush : Util.loadBrushs()) {
            JButton brushButton = createIconButton(brush.getIcon());
            brushButton.addActionListener(e -> {
                paintBoard.setCurrentShape(brush);
                strokeSlider.setValue(strokeWidth);
                strokeSlider.setVisible(true);
            });
            brushGridPanel.add(brushButton);
        }

        brushPanel.add(brushGridPanel);
        brushPanel.add(Box.createVerticalStrut(10));
        JLabel label = new JLabel("Brushes", JLabel.CENTER);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        brushPanel.add(label);

        return brushPanel;
    }

    private JPanel createShapePanel(PaintBoard paintBoard) {
        shapePanel = new JPanel();
        shapePanel.setLayout(new BoxLayout(shapePanel, BoxLayout.Y_AXIS));

        JPanel shapeGridPanel = new JPanel(new GridLayout(2, 8, 5, 5));

        for (Shape shape : Util.loadShapes()) {
            JButton shapeButton = createIconButton(shape.getIcon());
            shapeButton.addActionListener(e -> {
                paintBoard.setCurrentShape(shape);
                strokeSlider.setValue(strokeWidth);
                strokeSlider.setVisible(true); // Show slider
            });
            shapeGridPanel.add(shapeButton);
        }

        shapePanel.add(shapeGridPanel);
        shapePanel.add(Box.createVerticalStrut(10));
        JLabel label = new JLabel("Shapes", JLabel.CENTER);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        shapePanel.add(label);

        return shapePanel;
    }

    private JPanel createColorPanel() {
        colorPanel = new JPanel();
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));

        JPanel gridAndColorPanel = new JPanel();
        gridAndColorPanel.setLayout(new BorderLayout(10, 10));

        JButton colorIndicator = new JButton();
        colorIndicator.setBackground(Color.BLACK);
        colorIndicator.setPreferredSize(new Dimension(40, 20));
        colorIndicator.setFocusable(true);
        colorIndicator.setBorderPainted(true);
        colorIndicator.setContentAreaFilled(true);

        gridAndColorPanel.add(colorIndicator, BorderLayout.WEST);

        JPanel colorsGridPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        for (Color color : Util.loadColors()) {
            JButton colorButton = new JButton();
            colorButton.setBackground(color);
            colorButton.setPreferredSize(new Dimension(40, 40));
            colorButton.addActionListener(e -> {
                colorIndicator.setBackground(color);
                currentColor = color;
                System.out.println("Selected color: " + color);
            });
            colorsGridPanel.add(colorButton);
        }
        gridAndColorPanel.add(colorsGridPanel, BorderLayout.CENTER);

        JButton colorPickerBtn = new JButton((Icon) new ImageIcon(getClass().getResource(Icons.COLOR_ICON).getPath()));
        colorPickerBtn.setContentAreaFilled(false);
        colorPickerBtn.addActionListener(e -> {
            Color chosenColor = JColorChooser.showDialog(colorPanel, "Pick a Color", colorIndicator.getBackground());
            if (chosenColor != null) {
                // Update the color indicator
                colorIndicator.setBackground(chosenColor);
                currentColor = chosenColor;
                System.out.println("Chosen color: " + chosenColor);
            }
        });
        gridAndColorPanel.add(colorPickerBtn, BorderLayout.EAST);

        colorPanel.add(gridAndColorPanel);

        colorPanel.add(Box.createVerticalStrut(10));
        JLabel label = new JLabel("Colors", JLabel.CENTER);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        colorPanel.add(label);

        return colorPanel;
    }

    private JButton createIconButton(ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(40, 40));
        button.setContentAreaFilled(true); // Fill the button area with color
        return button;
    }

    private JSlider createStrokeSlider() {
        JSlider slider = new JSlider(1, 20, 1);
        slider.setPreferredSize(new Dimension(150, 40));
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener((ChangeEvent e) -> {
            strokeWidth = slider.getValue();
            System.out.println("Stroke width set to: " + strokeWidth);

        });
        return slider;
    }

}
