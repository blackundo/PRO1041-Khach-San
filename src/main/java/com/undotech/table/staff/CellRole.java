package com.undotech.table.staff;

import javaswingdev.swing.table.TableCustom;
import javaswingdev.swing.table.cell.TableCustomCell;
import javaswingdev.swing.table.model.TableRowData;

public class CellRole extends TableCustomCell {

    public CellRole() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        group = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jrQuanLy = new javax.swing.JRadioButton();
        jrKeToan = new javax.swing.JRadioButton();
        jrNhanSu = new javax.swing.JRadioButton();
        jrLeTan = new javax.swing.JRadioButton();

        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chức Vụ:");

        group.add(jrQuanLy);
        jrQuanLy.setForeground(new java.awt.Color(51, 51, 51));
        jrQuanLy.setText("Quản lý");

        group.add(jrKeToan);
        jrKeToan.setForeground(new java.awt.Color(51, 51, 51));
        jrKeToan.setText("Kế toán");

        group.add(jrNhanSu);
        jrNhanSu.setForeground(new java.awt.Color(51, 51, 51));
        jrNhanSu.setText("Nhân sự");

        group.add(jrLeTan);
        jrLeTan.setForeground(new java.awt.Color(51, 51, 51));
        jrLeTan.setSelected(true);
        jrLeTan.setText("Lễ tân");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrQuanLy)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jrNhanSu, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jrLeTan, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jrKeToan, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrKeToan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrNhanSu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jrLeTan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void setData(Object o) {
        if (o.toString().equals("Quản lý")) {
            jrQuanLy.setSelected(true);
        } else if (o.toString().equals("Kế toán")) {
            jrKeToan.setSelected(true);
        } else if (o.toString().equals("Nhân sự")) {
            jrNhanSu.setSelected(true);
        } else {
            jrLeTan.setSelected(true);
        }
    }

    @Override
    public Object getData() {
        String role;
        if (jrQuanLy.isSelected()) {
            role = "Quản lý";
        } else if (jrKeToan.isSelected()) {
            role = "Kế toán";
        } else if (jrNhanSu.isSelected()) {
            role = "Nhân sự";
        }else{
            role = "Lễ tân";
        }
        return role;
    }

    @Override
    public TableCustomCell createComponentCellEditor(TableCustom tc, TableRowData trd, Object o, int i, int i1) {
        CellRole cell = new CellRole();
        cell.setData(o);
        return cell;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup group;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jrKeToan;
    private javax.swing.JRadioButton jrLeTan;
    private javax.swing.JRadioButton jrNhanSu;
    private javax.swing.JRadioButton jrQuanLy;
    // End of variables declaration//GEN-END:variables
}
