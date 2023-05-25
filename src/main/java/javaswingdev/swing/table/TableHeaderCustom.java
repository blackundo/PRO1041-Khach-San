/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaswingdev.swing.table;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author blackundo
 */
public class TableHeaderCustom extends DefaultTableCellRenderer{
    public TableHeaderCustom(){
        setPreferredSize(new Dimension(0, 35));
//        setBackground(new Color(200, 200, 200));
//        setForeground(new Color(60, 60, 60));
        setBackground(new Color(58, 73, 227));
        setForeground(new Color(255, 255, 255));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(100, 100, 100));
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }
    
    
}
