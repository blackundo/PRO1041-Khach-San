package com.undotech.room;

import com.undotech.notification.Notification;
import com.undotech.ui.MainJFrame;
import java.awt.Dimension;

public class RoomLayer extends javax.swing.JComponent {

    public RoomLayer() {
        initComponents();
    }

    public void setModel(ModelRoom model) {
        String desc = model.getDescription();
        if (desc.length()>150) {
            desc = desc.substring(0, 150)+"...";
        }
        txt.setText(desc);
        lbName.setText(model.getName());
        lbPrice.setText(model.getPrice());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new com.undotech.room.TextPaneCenter();
        button1 = new com.undotech.room.Button();
        lbName = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();

        txt.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        button1.setText("ĐẶT");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        lbName.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lbName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbName.setText("Name");

        lbPrice.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lbPrice.setForeground(new java.awt.Color(255, 0, 0));
        lbPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPrice.setText("$0.00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(lbPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(90, 90, 90))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbName)
                .addGap(60, 60, 60)
                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(lbPrice)
                .addGap(18, 18, 18)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        new Notification(MainJFrame.getMain(), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Đặt thành công ^^").showNotification();

    }//GEN-LAST:event_button1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.undotech.room.Button button1;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPrice;
    private com.undotech.room.TextPaneCenter txt;
    // End of variables declaration//GEN-END:variables
}
