/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.undotech.ui;

import com.undotech.dao.DatPhongDAO;
import com.undotech.dao.DichVuDAO;
import com.undotech.dao.PhieuDichVuDAO;
import com.undotech.dao.PhongDAO;
import com.undotech.dao.ProcDAO;
import com.undotech.entity.DichVu;
import com.undotech.entity.PhieuDichVu;
import com.undotech.ui.Form_Profile.RoundedPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultButtonModel;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author blackundo
 */
public class addDichVuJDialog extends javax.swing.JDialog {
    ArrayList<DichVu> arrDV = new ArrayList<>();
    ArrayList<Object[]> arrDV2 = new ArrayList<>();
    DichVuDAO dvdao = new DichVuDAO();
    PhieuDichVuDAO pdvdao = new PhieuDichVuDAO();
    PhongDAO pdao = new PhongDAO();
    DatPhongDAO dpdao = new DatPhongDAO();
    String maphong = null;
    
    
    public addDichVuJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initDichVu();
        fillDichVu();
    }

    public addDichVuJDialog(String maphong, Frame owner, boolean modal) {
        super(owner, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.maphong = maphong;
        initDichVu();
        fillDichVu();
    }
    

    private void initDichVu(){
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(30);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(30);

        
        jLabel1.setText("Mã Phòng: " + maphong);
        int dvButtonWidth = 150;
        int dvButtonHeight = 150;
        arrDV.clear();
        pnlDV.removeAll();
        arrDV = (ArrayList)dvdao.selectAll();
        //lọc và xoá dv đã có trong phòng
        List<Integer> listid = new ArrayList<Integer>();
        List<PhieuDichVu> listDVInRoom = pdvdao.selectByRoomID(maphong);
        for (PhieuDichVu pdv : listDVInRoom) {
            listid.add(pdv.getMaDichVu());
        }
        int sapxep = arrDV.size();
        System.out.println(listid);
        if (sapxep % 3 == 0) {
            pnlDV.setLayout(new GridLayout(sapxep / 2, 3, 10, 10));
        } else {
            pnlDV.setLayout(new GridLayout(sapxep / 2 + 1, 3, 10, 10));
        }
        
        
        
        JButton[] btn = new JButton[arrDV.size()];
        for (int i = 0; i < arrDV.size(); i++) {
            btn[i] = new javax.swing.JButton();
            btn[i].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            int idDV = arrDV.get(i).getMaDV();
            String nameDV = arrDV.get(i).getTenDV();
            String priceDV = arrDV.get(i).getGiaDV() + "";
            String descDV = arrDV.get(i).getMoTaDV();
            String textDV = ("<html><div style=\"text-align: center;\"> " + arrDV.get(i).getTenDV() + "<br>(" + arrDV.get(i).getGiaDV()+ "VNĐ)<br>" + arrDV.get(i).getMoTaDV() +"</div></html>");
            btn[i].setText(textDV);
            btn[i].setToolTipText(idDV+"");
            btn[i].setMinimumSize(new Dimension(dvButtonWidth, dvButtonHeight));
            btn[i].setPreferredSize(new Dimension(dvButtonWidth, dvButtonHeight));
            btn[i].setMaximumSize(new Dimension(dvButtonWidth, dvButtonHeight));
            btn[i].setSize(new Dimension(dvButtonWidth, dvButtonHeight));

//            btn[i].setOpaque(true);
            btn[i].setHorizontalAlignment(JLabel.CENTER);
//            btn[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/HinhAnh/Icon_Trong.png")));

            final JButton itemDVButton = btn[i];
            btn[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //nút
                    if (itemDVButton.getBackground()==Color.red) {
                        itemDVButton.setBackground(Color.white);
                        return;
                    }
                    itemDVButton.setBackground(Color.red);
                    
                }
            });
            
            
            if (!listid.contains(idDV)) {
                pnlDV.add(btn[i]);
            }
            pnlDV.updateUI();
            
            
            
            btnThemDV.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    if(itemDVButton.getBackground()==Color.red){
//                        PhieuDichVu pdv = new PhieuDichVu(idDV,maphong);//số phòng đang test chưa viết
//                        pdvdao.insert(pdv);
//                        fillDichVu();
//                        initDichVu();
//                    }
                }
            });
            btnXoaDV.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(itemDVButton.getBackground()==Color.red){
//                        pdvdao.deleteBySerID(Integer.parseInt(itemDVButton.getToolTipText()));
//                        fillDichVu();
//                        initDichVu();


//                        
//                        pnlDV2.remove(itemDVButton);
//                        pnlDV.add(itemDVButton);
//                        pnlDV2.getComponentCount();
//                        int sapxep = pnlDV2.getComponentCount();
//                        if (sapxep % 3 == 0) {
//                            pnlDV2.setLayout(new GridLayout(sapxep / 2, 3, 10, 10));
//                        } else {
//                            pnlDV2.setLayout(new GridLayout(sapxep / 2 + 1, 3, 10, 10));
//                        }
//                        pnlDV.updateUI();
//                        pnlDV2.updateUI();
                    }
                }
            });
        }
//        pack();
    }

    private void fillDichVu(){
        int dvButtonWidth = 150;
        int dvButtonHeight = 150;
        arrDV2.clear();
        pnlDV2.removeAll();
        arrDV2 = (ArrayList)new ProcDAO().getTableVIPByROOMID(maphong);
        List<Object[]> tb = new ProcDAO().getTableVIPByROOMID(maphong);
        
        
        
        int sapxep = arrDV2.size();
        if (sapxep % 3 == 0) {
            pnlDV2.setLayout(new GridLayout(sapxep / 2, 3, 10, 10));
        } else {
            pnlDV2.setLayout(new GridLayout(sapxep / 2 + 1, 3, 10, 10));
        }
        JButton[] btn = new JButton[arrDV2.size()];
        for (int i = 0; i < arrDV2.size(); i++) {
            btn[i] = new javax.swing.JButton();
            btn[i].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            int idDV = (int) arrDV2.get(i)[1];
            String nameDV = arrDV2.get(i)[2] + "";//lấy obj thứ i và cột 3 trong obj của proc
            String priceDV = arrDV2.get(i)[3] + "";//3 là giá
            String descDV = arrDV2.get(i)[4] + "";//4 là mô tả
            String textDV = ("<html><div style=\"text-align: center;\"> " + nameDV + "<br>(" + priceDV+ "VNĐ)<br>" + descDV +"</div></html>");
            btn[i].setText(textDV);
            btn[i].setToolTipText(idDV+"");
            btn[i].setMinimumSize(new Dimension(dvButtonWidth, dvButtonHeight));
            btn[i].setPreferredSize(new Dimension(dvButtonWidth, dvButtonHeight));
            btn[i].setMaximumSize(new Dimension(dvButtonWidth, dvButtonHeight));
            btn[i].setSize(new Dimension(dvButtonWidth, dvButtonHeight));

//            btn[i].setOpaque(true);
            btn[i].setHorizontalAlignment(JLabel.CENTER);
//            btn[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/HinhAnh/Icon_Trong.png")));

            final JButton itemDVButton = btn[i];
            btn[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //nút
                    if (itemDVButton.getBackground()==Color.red) {
                        itemDVButton.setBackground(Color.white);
                        return;
                    }
                    itemDVButton.setBackground(Color.red);
                    
                }
            });
            
            pnlDV2.add(btn[i]);
            pnlDV2.updateUI();
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlDV = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlDV2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        colorBtn3 = new RoundedPanel(20,new Color(246, 65, 100));
        btnThemDV = new javaswingdev.swing.Button();
        colorBtn4 = new RoundedPanel(20,new Color(246, 65, 100));
        btnXoaDV = new javaswingdev.swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setText("Thêm dịch vụ");

        pnlDV.setBackground(new java.awt.Color(51, 255, 0));

        javax.swing.GroupLayout pnlDVLayout = new javax.swing.GroupLayout(pnlDV);
        pnlDV.setLayout(pnlDVLayout);
        pnlDVLayout.setHorizontalGroup(
            pnlDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 403, Short.MAX_VALUE)
        );
        pnlDVLayout.setVerticalGroup(
            pnlDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(pnlDV);

        pnlDV2.setBackground(new java.awt.Color(0, 51, 255));

        javax.swing.GroupLayout pnlDV2Layout = new javax.swing.GroupLayout(pnlDV2);
        pnlDV2.setLayout(pnlDV2Layout);
        pnlDV2Layout.setHorizontalGroup(
            pnlDV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 373, Short.MAX_VALUE)
        );
        pnlDV2Layout.setVerticalGroup(
            pnlDV2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(pnlDV2);

        colorBtn3.setBackground(new java.awt.Color(228, 229, 245));
        colorBtn3.setForeground(new java.awt.Color(255, 255, 255));
        colorBtn3.setOpaque(false);
        colorBtn3.setPreferredSize(new java.awt.Dimension(111, 27));

        btnThemDV.setBackground(new java.awt.Color(255, 102, 0));
        btnThemDV.setForeground(new java.awt.Color(255, 255, 255));
        btnThemDV.setText(">>>");
        btnThemDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout colorBtn3Layout = new javax.swing.GroupLayout(colorBtn3);
        colorBtn3.setLayout(colorBtn3Layout);
        colorBtn3Layout.setHorizontalGroup(
            colorBtn3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, colorBtn3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnThemDV, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );
        colorBtn3Layout.setVerticalGroup(
            colorBtn3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, colorBtn3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnThemDV, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        colorBtn4.setBackground(new java.awt.Color(228, 229, 245));
        colorBtn4.setForeground(new java.awt.Color(255, 255, 255));
        colorBtn4.setOpaque(false);
        colorBtn4.setPreferredSize(new java.awt.Dimension(111, 27));

        btnXoaDV.setBackground(new java.awt.Color(255, 102, 0));
        btnXoaDV.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaDV.setText("<<<");
        btnXoaDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout colorBtn4Layout = new javax.swing.GroupLayout(colorBtn4);
        colorBtn4.setLayout(colorBtn4Layout);
        colorBtn4Layout.setHorizontalGroup(
            colorBtn4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, colorBtn4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnXoaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );
        colorBtn4Layout.setVerticalGroup(
            colorBtn4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, colorBtn4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnXoaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 109, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(colorBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(colorBtn4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(20, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 227, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addComponent(colorBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(29, 29, 29)
                    .addComponent(colorBtn4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(53, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
            .addGroup(layout.createSequentialGroup()
                .addGap(375, 375, 375)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(27, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDVActionPerformed
        // TODO add your handling code here:
        Component[] com = pnlDV2.getComponents();
        for(int i = 0; i<pnlDV2.getComponentCount(); i++){
            if (com[i].getBackground() == Color.red) {
                JButton btn = (JButton) com[i];
                pdvdao.deleteBySerID(Integer.parseInt(btn.getToolTipText()));
                fillDichVu();
                initDichVu();
            }
        }
    }//GEN-LAST:event_btnXoaDVActionPerformed

    private void btnThemDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDVActionPerformed
        // TODO add your handling code here:
        Component[] com = pnlDV.getComponents();
        for(int i = 0; i<pnlDV.getComponentCount(); i++){
            if(com[i].getBackground()==Color.red){
                        JButton btn = (JButton) com[i];
                        PhieuDichVu pdv = new PhieuDichVu(Integer.parseInt(btn.getToolTipText()),maphong);//số phòng đang test chưa viết
                        pdvdao.insert(pdv);
                        fillDichVu();
                        initDichVu();
                    }
        }
    }//GEN-LAST:event_btnThemDVActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(addDichVuJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addDichVuJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addDichVuJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addDichVuJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                addDichVuJDialog dialog = new addDichVuJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javaswingdev.swing.Button btnThemDV;
    private javaswingdev.swing.Button btnXoaDV;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel colorBtn3;
    private javax.swing.JPanel colorBtn4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlDV;
    private javax.swing.JPanel pnlDV2;
    // End of variables declaration//GEN-END:variables
}
