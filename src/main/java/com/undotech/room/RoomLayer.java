package com.undotech.room;

import com.undotech.dao.PhongDAO;
import com.undotech.dao.TrangThaiPhongDAO;
import com.undotech.entity.Phong;
import com.undotech.entity.TrangThaiPhong;
import com.undotech.notification.Notification;
import com.undotech.ui.DatPhongDialog;
import com.undotech.ui.Form_DachSachPhong;
import com.undotech.ui.MainJFrame;
import static com.undotech.ui.MainJFrame.main;
import com.undotech.ui.ThanhToanJDialog;
import com.undotech.ui.addDichVuJDialog;
import java.awt.Dimension;
import javax.swing.JOptionPane;

public class RoomLayer extends javax.swing.JComponent {

    private ModelRoom model = null;
    PhongDAO pdao = new PhongDAO();
    TrangThaiPhongDAO statusdao = new TrangThaiPhongDAO();
    
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
        this.model = model;
        init();
    }

    public ModelRoom getModel() {
        return model;
    }
    
    void init(){
        if (!getModel().isRoomUse() || !getModel().isClean() || getModel().isRepair()) {
            button1.setVisible(false);
        }
    }
    
    void showPopupRoom(java.awt.event.MouseEvent evt,int flag){
        if(evt.isPopupTrigger()){
            //phòng đã sử dụng
            if (!getModel().isRoomUse()) {
                popupMenuRoomUsed.show(this, (flag==1) ? evt.getX()+80 : evt.getX(), (flag==1) ? evt.getY()+100 : evt.getY());
            }else{
                popupMenuRoomEmpty.show(this, (flag==1) ? evt.getX()+80 : evt.getX(), (flag==1) ? evt.getY()+100 : evt.getY());
            }
            //phòng đang dọn
            if(!getModel().isClean() && getModel().isRoomUse()){
                popupMenuRoomClean.show(this, (flag==1) ? evt.getX()+80 : evt.getX(), (flag==1) ? evt.getY()+100 : evt.getY());
            }
            //phòng đang sửa
            if(getModel().isRepair() && getModel().isRoomUse()){
                popupMenuRoomRepair.show(this, (flag==1) ? evt.getX()+80 : evt.getX(), (flag==1) ? evt.getY()+100 : evt.getY());
            }
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenuRoomUsed = new javax.swing.JPopupMenu();
        itemDV = new javax.swing.JMenuItem();
        itemCheckOut = new javax.swing.JMenuItem();
        popupMenuRoomClean = new javax.swing.JPopupMenu();
        cleaned = new javax.swing.JMenuItem();
        popupMenuRoomRepair = new javax.swing.JPopupMenu();
        repaired = new javax.swing.JMenuItem();
        popupMenuRoomEmpty = new javax.swing.JPopupMenu();
        roomDirty = new javax.swing.JMenuItem();
        roomBroken = new javax.swing.JMenuItem();
        txt = new com.undotech.room.TextPaneCenter();
        button1 = new com.undotech.room.Button();
        lbName = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        itemDV.setText("Thêm dịch vụ");
        itemDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDVActionPerformed(evt);
            }
        });
        popupMenuRoomUsed.add(itemDV);

        itemCheckOut.setText("Trả phòng");
        itemCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCheckOutActionPerformed(evt);
            }
        });
        popupMenuRoomUsed.add(itemCheckOut);

        cleaned.setText("Đã dọn xong");
        cleaned.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanedActionPerformed(evt);
            }
        });
        popupMenuRoomClean.add(cleaned);

        repaired.setText("Đã sửa phòng");
        repaired.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repairedActionPerformed(evt);
            }
        });
        popupMenuRoomRepair.add(repaired);

        roomDirty.setText("Phòng dơ");
        roomDirty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomDirtyActionPerformed(evt);
            }
        });
        popupMenuRoomEmpty.add(roomDirty);

        roomBroken.setText("Phòng cần sửa chữa");
        roomBroken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomBrokenActionPerformed(evt);
            }
        });
        popupMenuRoomEmpty.add(roomBroken);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        txt.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txt.setInheritsPopupMenu(true);
        txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtMousePressed(evt);
            }
        });

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

        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(lbPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        new Notification(MainJFrame.getMain(), Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Đặt thành công ^^").showNotification();
        System.out.println(getModel().getName());
        new DatPhongDialog(null, true, getModel()).setVisible(true);
        
    }//GEN-LAST:event_button1ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        //click chuot vao panel
        
        showPopupRoom(evt,0);
    }//GEN-LAST:event_formMousePressed

    private void txtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMousePressed
        showPopupRoom(evt,1);
    }//GEN-LAST:event_txtMousePressed

    private void itemCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCheckOutActionPerformed
        //thanh toan
//        pdao.updateBKIDDefault(new Phong(getModel().getId()));
        new ThanhToanJDialog(null,true,getModel()).setVisible(true);
//        main.showForm(new Form_DachSachPhong());
Form_DachSachPhong.form.fillPhong(Form_DachSachPhong.listCurrent);
    }//GEN-LAST:event_itemCheckOutActionPerformed

    private void cleanedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanedActionPerformed
        // TODO add your handling code here:
        statusdao.update(new TrangThaiPhong(getModel().getId(),true, getModel().isRepair()));
//        main.showForm(new Form_DachSachPhong());
Form_DachSachPhong.form.fillPhong(Form_DachSachPhong.listCurrent);
    }//GEN-LAST:event_cleanedActionPerformed

    private void repairedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repairedActionPerformed
        // TODO add your handling code here:
        statusdao.update(new TrangThaiPhong(getModel().getId(),getModel().isClean(), false));
//        main.showForm(new Form_DachSachPhong());
Form_DachSachPhong.form.fillPhong(Form_DachSachPhong.listCurrent);
    }//GEN-LAST:event_repairedActionPerformed

    private void roomDirtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomDirtyActionPerformed
        // TODO add your handling code here:
        statusdao.update(new TrangThaiPhong(getModel().getId(),false, getModel().isRepair()));
//        main.showForm(new Form_DachSachPhong());
Form_DachSachPhong.form.fillPhong(Form_DachSachPhong.listCurrent);
    }//GEN-LAST:event_roomDirtyActionPerformed

    private void roomBrokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomBrokenActionPerformed
        // TODO add your handling code here:
        statusdao.update(new TrangThaiPhong(getModel().getId(),getModel().isClean(), true));
//        main.showForm(new Form_DachSachPhong());
Form_DachSachPhong.form.fillPhong(Form_DachSachPhong.listCurrent);
    }//GEN-LAST:event_roomBrokenActionPerformed

    private void itemDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDVActionPerformed
        // Thêm dịch vụ
        new addDichVuJDialog(getModel().getId(),null,true).setVisible(true);
    }//GEN-LAST:event_itemDVActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.undotech.room.Button button1;
    private javax.swing.JMenuItem cleaned;
    private javax.swing.JMenuItem itemCheckOut;
    private javax.swing.JMenuItem itemDV;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JPopupMenu popupMenuRoomClean;
    private javax.swing.JPopupMenu popupMenuRoomEmpty;
    private javax.swing.JPopupMenu popupMenuRoomRepair;
    private javax.swing.JPopupMenu popupMenuRoomUsed;
    private javax.swing.JMenuItem repaired;
    private javax.swing.JMenuItem roomBroken;
    private javax.swing.JMenuItem roomDirty;
    private com.undotech.room.TextPaneCenter txt;
    // End of variables declaration//GEN-END:variables
}
