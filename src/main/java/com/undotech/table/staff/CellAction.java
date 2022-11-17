package com.undotech.table.staff;

import com.undotech.dao.NhanVienDAO;
import com.undotech.entity.NhanVien;
import javaswingdev.swing.table.TableCustom;
import javaswingdev.swing.table.cell.TableCustomCell;
import javaswingdev.swing.table.model.TableRowData;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class CellAction extends TableCustomCell {

    public CellAction() {
        initComponents();
    }

    private void addEvent(TableCustom table, TableRowData data, int row) {
        cmdEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (data.isEditing()) {
                    table.cancelEditRowAt(row);
                    cmdEdit.setIcon(new ImageIcon(getClass().getResource("/com/undotech/icons/edit.png")));
                } else {
                    table.editRowAt(row);
                    cmdEdit.setIcon(new ImageIcon(getClass().getResource("/com/undotech/icons/cancel.png")));
                }
            }
        });
        cmdDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String id = ((NhanVien)data).getMaNV();
                    new NhanVienDAO().delete(id);
                    table.deleteRowAt(getRow(), true);
                } catch (Exception e) {
                    System.err.println(e);
                }
                
            }
        });
    }

    private void checkIcon(TableRowData data) {
        if (data.isEditing()) {
            cmdEdit.setIcon(new ImageIcon(getClass().getResource("/com/undotech/icons/cancel.png")));
        } else {
            cmdEdit.setIcon(new ImageIcon(getClass().getResource("/com/undotech/icons/edit.png")));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdEdit = new javaswingdev.swing.Button();
        cmdDelete = new javaswingdev.swing.Button();

        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/undotech/icons/edit.png"))); // NOI18N
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });

        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/undotech/icons/delete.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmdDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdEditActionPerformed

    @Override
    public void setData(Object o) {

    }

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public Component createComponentCellRender(TableCustom table, TableRowData data, int row, int column) {
        CellAction cell = new CellAction();
        cell.checkIcon(data);
        cell.addEvent(table, data, row);
        return cell;
    }

    @Override
    public Component createComponentCellRenderOnEditor(TableCustom table, TableRowData data, int row, int column) {
        return null;
    }

    @Override
    public TableCustomCell createComponentCellEditor(TableCustom table, TableRowData data, Object o, int row, int column) {
        CellAction cell = new CellAction();
        cell.checkIcon(data);
        cell.addEvent(table, data, row);
        return cell;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javaswingdev.swing.Button cmdDelete;
    private javaswingdev.swing.Button cmdEdit;
    // End of variables declaration//GEN-END:variables
}
