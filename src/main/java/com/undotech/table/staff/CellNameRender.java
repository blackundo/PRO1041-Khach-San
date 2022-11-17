package com.undotech.table.staff;

import com.undotech.entity.NhanVien;
import com.undotech.utils.XImage;
import java.util.List;
import javaswingdev.swing.table.cell.Cell;
import javax.swing.ImageIcon;

/**
 *
 * @author blackundo
     */
public class CellNameRender extends Cell {

    public CellNameRender(NhanVien data) {
        initComponents();
        jLabel1.setText(data.toString());
        try {
            imageAvatar1.setImage(XImage.read(data.getHinhAnh()));
        } catch (NullPointerException e) {
//            e.printStackTrace();
//            imageAvatar1.setImage(new ImageIcon(getClass().getResource("/com/undotech/icons/icon8_globe.png")));
        }
//        imageAvatar1.setImage(new ImageIcon(getClass().getResource("/com/undotech/icons/"+data.getHinhAnh())));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageAvatar1 = new javaswingdev.swing.ImageAvatar();
        jLabel1 = new javax.swing.JLabel();

        imageAvatar1.setBorderSize(2);
        imageAvatar1.setBorderSpace(1);
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/undotech/icons/icon8_globe.png"))); // NOI18N

        jLabel1.setText("name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(259, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javaswingdev.swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
