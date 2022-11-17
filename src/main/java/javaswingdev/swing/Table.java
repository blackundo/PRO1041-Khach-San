package javaswingdev.swing;

import java.awt.Color;
import java.awt.Component;
import javaswingdev.swing.scroll.ScrollBar;
import javaswingdev.swing.table.TableCustom;
import javaswingdev.swing.table.TableHeaderCustom;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.TableCellRenderer;


/**
 *
 * @author blackundo
 */
public class Table extends TableCustom{
    
    public Table(){
    
    }
    
    public void addTableStyle(JScrollPane scroll){
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
        setBorder(null);
        scroll.setBorder(null);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBar(new ScrollBar());
        JPanel panel = new JPanel();
        panel.setBackground(new Color(214, 214, 214));
        setForeground(new Color(60, 60, 60));
        setSelectionForeground(new Color(50, 50, 50));
        setSelectionBackground(new Color(255, 255, 255));
        getTableHeader().setDefaultRenderer(new TableHeaderCustom());
        setRowHeight(47);
        setShowHorizontalLines(true);
        setGridColor(new Color(214, 214, 214));
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
    }
    
    
    //xen kẻ màu mỗi dòng table
//    if(row%2==0){
//                com.setBackground(new Color(60, 60, 60));
//            }else{
//                com.setBackground(new Color(136, 136, 136));
//            }
    
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component com = super.prepareRenderer(renderer, row, column);
        if (!isCellSelected(row, column)) {
            com.setBackground(new Color(214, 214, 214));
        }
        return com;
    }
    
    
    
}
