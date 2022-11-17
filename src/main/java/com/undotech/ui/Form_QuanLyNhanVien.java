package com.undotech.ui;

import com.undotech.dao.NhanVienDAO;
import com.undotech.entity.NhanVien;
import com.undotech.notification.Notification;
import com.undotech.table.staff.CellAction;
import com.undotech.table.staff.CellAddress;
import com.undotech.table.staff.CellEmail;
import com.undotech.table.staff.CellName;
import com.undotech.table.staff.CellRole;
import com.undotech.table.staff.CellTel;
import com.undotech.table.staff.Cell_ID;
import java.awt.Frame;
import java.util.List;



/**
 *
 * @author blackundo
 */
public class Form_QuanLyNhanVien extends javax.swing.JPanel {

    private static Frame f;

    /**
     * Creates new form Form_QuanLyNhanVien
     */
    
    NhanVienDAO dao = new NhanVienDAO();
    public Form_QuanLyNhanVien() {
        initComponents();
        tbl_staff.addTableStyle(jScrollPane1);
        init_table();
    }
    
    private void init_table(){
        tbl_staff.addTableCell(new Cell_ID(), 0);
        tbl_staff.addTableCell(new CellName(), 1);
        tbl_staff.addTableCell(new CellRole(), 2);
        tbl_staff.addTableCell(new CellTel(), 3);
        tbl_staff.addTableCell(new CellEmail(), 4);
        tbl_staff.addTableCell(new CellAddress(), 5);
        tbl_staff.addTableCell(new CellAction(), 6);
//        table1.addRow(new ModelStaff(new ModelName("Ra", new ImageIcon(getClass().getResource("/com/undotech/icons/icon8_globe.png")), ""),
//                "Male", 18, "Raven123@gmail.com", "010 000 000"), false);
//        try {
//            List<ModelStaff> list = dao.selectBySqlform();
//            for (ModelStaff nv : list) {
////                Object[] rows = {nv.getTenNV(),nv.getVaiTro(),nv.getSoDT(),nv.getEmail(),nv.getDiaChi()};
//                tbl_staff.addRow(nv, false);
//            }
//        } catch (Exception e) {
//        }

    new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<NhanVien> list = dao.selectBySqlform();
                    for (NhanVien nv : list) {

                        //sf.add(new ModelStaff(new ModelName(nv.getTenNV(), nv.getHinhAnh()),nv.getVaiTro(),nv.getSoDT(),nv.getEmail(),nv.getDiaChi()));

        //                Object[] rows = {nv.getTenNV(),nv.getVaiTro(),nv.getSoDT(),nv.getEmail(),nv.getDiaChi()};
                        tbl_staff.addRow( nv, false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_staff = new javaswingdev.swing.Table();
        button1 = new javaswingdev.swing.Button();

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản lý nhân viên");
        jLabel1.setToolTipText("");

        tbl_staff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MãNV", "Họ và Tên", "Chức vụ", "Số điện thoại", "Email", "Địa chỉ", "Hành động"
            }
        ));
        jScrollPane1.setViewportView(tbl_staff);
        if (tbl_staff.getColumnModel().getColumnCount() > 0) {
            tbl_staff.getColumnModel().getColumn(0).setMinWidth(50);
            tbl_staff.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbl_staff.getColumnModel().getColumn(0).setMaxWidth(50);
            tbl_staff.getColumnModel().getColumn(1).setPreferredWidth(200);
            tbl_staff.getColumnModel().getColumn(2).setMinWidth(115);
            tbl_staff.getColumnModel().getColumn(2).setPreferredWidth(115);
            tbl_staff.getColumnModel().getColumn(2).setMaxWidth(115);
            tbl_staff.getColumnModel().getColumn(4).setPreferredWidth(120);
            tbl_staff.getColumnModel().getColumn(5).setPreferredWidth(150);
            tbl_staff.getColumnModel().getColumn(6).setMinWidth(80);
            tbl_staff.getColumnModel().getColumn(6).setPreferredWidth(80);
            tbl_staff.getColumnModel().getColumn(6).setMaxWidth(80);
        }

        button1.setBackground(new java.awt.Color(204, 204, 204));
        button1.setText("Thêm");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE))
                        .addGap(35, 35, 35))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        //tbl_staff.insertRowWithEdit(new ModelStaff(new ModelName("",null,""),"Quản lý","","",""),0, true);
        new Notification(MainJFrame.getMain(), Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "Đã thêm").showNotification();
        tbl_staff.insertRowWithEdit(new NhanVien(null, "", "", "", "", "", "", "", ""), 0, true);

    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javaswingdev.swing.Button button1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javaswingdev.swing.Table tbl_staff;
    // End of variables declaration//GEN-END:variables
}
