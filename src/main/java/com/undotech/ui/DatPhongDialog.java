/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.undotech.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.raven.event.EventTimePicker;
import com.undotech.dao.DatPhongDAO;
import com.undotech.dao.KhachHangDAO;
import com.undotech.dao.PhongDAO;
import com.undotech.entity.DatPhong;
import com.undotech.entity.KhachHang;
import com.undotech.entity.Phong;
import com.undotech.room.ModelRoom;
import static com.undotech.ui.MainJFrame.main;
import com.undotech.utils.Auth;
import com.undotech.utils.EmailValidator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author blackundo
 */
public class DatPhongDialog extends javax.swing.JDialog {

    DatPhongDAO dpdao = new DatPhongDAO();
    PhongDAO pdao = new PhongDAO();
    KhachHangDAO khdao = new KhachHangDAO();
    private ModelRoom model = null;
    int flagKH = 0;
    private static final String EMAIL_PATTERN = 
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    
    public DatPhongDialog(java.awt.Frame parent, boolean modal, ModelRoom model) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        FlatLightLaf.setup();
        this.model = model;
        fillComboBoxName();
//        getForm();
        lblNumberRoom.setText(model.getId());
        setForm();
        
        cboName.getEditor().getEditorComponent().addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("đang viết");
//                System.out.println(cboName.getEditor().getItem());
                flagKH = 1;
                setEnabledForm(true);
                System.out.println(flagKH);
            }
            
        });
        
        cboName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
//                KhachHang kh = (KhachHang) e.getItem();
//          chỉ lắng nghe khi chọn còn cái dưới load app fillcbo cũng nge event
                if (!txtPhone.isEnabled() && e.getStateChange() == 1) {
                    flagKH = 0;
                    System.out.println(flagKH);
//                System.out.println(kh.getCMND());
                    setForm();
                }
//                System.out.println("hàm change: "+e.getStateChange());
                
            }
        });
        
    }

    public ModelRoom getModel() {
        return model;
    }
    
    
    void fillComboBoxName(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboName.getModel();
        model.removeAllElements();
        
        List<KhachHang> list = khdao.selectAll();
        for (KhachHang kh : list) {
            model.addElement(kh);
        }
//        cboName.setSelectedItem(null); //mở này nhớ xoá setform trên init chớ lỗi nha mày
    }
    
    void clearForm(){
        txtAddress.setText("");
        txtCMND.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        cboName.setSelectedItem(0);
    }
    
    void setEnabledForm(boolean key){
        txtPhone.setEnabled(key);
        txtEmail.setEnabled(key);
        txtCMND.setEnabled(key);
        txtAddress.setEnabled(key);
    }
    
    void setForm(){
        try {
            KhachHang kh = (KhachHang)cboName.getSelectedItem();
            txtPhone.setText(kh.getSDT());
            txtEmail.setText(kh.getEmail());
            txtCMND.setText(kh.getCMND());
            txtAddress.setText(kh.getDiaChi());
            setEnabledForm(false);
        } catch (java.lang.ClassCastException e) {
            System.out.println("không chuyển đc do chỉnh");
        }
        
    }
    
    

    DatPhong getForm(){
        DatPhong dp = new DatPhong();
        
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat formatStringToTime = new SimpleDateFormat("hh:mm aa");
        
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatStringToDate = new SimpleDateFormat("dd-MM-yyyy");
        
        SimpleDateFormat formatFinal = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
//        SimpleDateFormat formatFinal = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        
        
        
        dp.setNgatDatPhong(new Date());
        try {
            String checkIn = formatDate.format(formatStringToDate.parse(txtDateCheckIn.getText())) +" "
                +formatTime.format(formatStringToTime.parse(txtTimeCheckIn.getText()));
        
            String checkOut = formatDate.format(formatStringToDate.parse(txtDateCheckOut.getText())) +" "
                +formatTime.format(formatStringToTime.parse(txtTimeCheckOut.getText()));
            System.out.println(checkIn);
            dp.setCheckIn(Timestamp.valueOf(checkIn));
            
            dp.setCheckOut(Timestamp.valueOf(checkOut));
//            dp.setCheckOut(formatFinal.parse(checkOut));
        } catch (ParseException ex) {
//            ex.printStackTrace();
        }
        
        dp.setTongSoPhongDat(1);
        if (flagKH == 0) {
            dp.setMaKH(((KhachHang)cboName.getSelectedItem()).getMaKH());
        }else{
            //validator
//            int phone = 0,CMND = 0;
//            String email = null;
//            if(!txtPhone.getText().isEmpty() && !txtCMND.getText().isEmpty() && !txtEmail.getText().isEmpty()){
////                chạy
//                try {
//                    phone = Integer.parseInt(txtPhone.getText());
//                    CMND = Integer.parseInt(txtCMND.getText());
//                    if (txtEmail.getText().matches(EMAIL_PATTERN)) {
//                        email = txtEmail.getText();
//                    }else{
//                        JOptionPane.showMessageDialog(this,"Email sai");
//                    }
//                } catch (NumberFormatException e) {
//                    JOptionPane.showMessageDialog(this,"SĐT và CMND phải là số");
//                }
//            
//            } else {
//                JOptionPane.showMessageDialog(this,"SĐT và CMND và Email Không được để trống");
//            }
            khdao.insert(new KhachHang(String.valueOf(cboName.getEditor().getItem()),txtPhone.getText(),txtEmail.getText(),
                    txtCMND.getText(),txtAddress.getText()));
            KhachHang kh = khdao.selectTop1();
            dp.setMaKH(kh.getMaKH());
        }
        
        
        dp.setMaNV(Auth.user.getMaNV());
        dp.setMaPhong(getModel().getId());
        
        
//        String dateCheckIn = txtDateCheckIn.getText();
//        String TimeCheckIn = timePickerCheckIn.getSelectedTime();
        
        
//        try {
//            System.out.println(formatDate.format(formatStringToDate.parse(dateCheckIn)));
//            timePickerCheckIn.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                        System.out.println(formatTime.format(formatStringToTime.parse(timePickerCheckIn.getSelectedTime())));
//
//                }
//
//            });
//        } catch (ParseException ex) {
//                    Logger.getLogger(DatPhongDialog.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
        
        return dp;
    };
    
    void insert(){
        DatPhong dp = getForm();
//        String maPhong = getModel().getId();

        //vallide day
        Timestamp ci = dp.getCheckIn();
        Timestamp co = dp.getCheckOut();
        System.out.println("check in " + ci);
        System.out.println("check out " + co);
        System.out.println("so sánh: "+ci.compareTo(co));
        if (dp.getCheckIn().compareTo(dp.getCheckOut()) == -1) {
            dpdao.insert(dp);
            DatPhong latest = dpdao.selectTop1();
            pdao.updateBKID(new Phong(getModel().getId(), latest.getMaDatPhong()));
            main.showForm(new Form_DachSachPhong());
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this,"Ngày nhận phòng không được lớn hơn");
        }
//        dpdao.insert(dp);
//        DatPhong latest = dpdao.selectTop1();
//        pdao.updateBKID(new Phong(getModel().getId(), latest.getMaDatPhong()));
//        main.showForm(new Form_DachSachPhong());
//        this.dispose();
//        System.out.println(dp.getCheckIn());
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserCheckIn = new javaswingdev.datechooser.DateChooser();
        timePickerCheckIn = new com.raven.swing.TimePicker();
        timePickerCheckOut = new com.raven.swing.TimePicker();
        dateChooserCheckOut = new javaswingdev.datechooser.DateChooser();
        lblNumberRoom = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboName = new javax.swing.JComboBox<>();
        txtPhone = new com.undotech.utils.TextField();
        txtEmail = new com.undotech.utils.TextField();
        txtCMND = new com.undotech.utils.TextField();
        textAreaScroll1 = new com.undotech.utils.TextAreaScroll();
        txtAddress = new com.undotech.utils.TextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDateCheckIn = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        txtTimeCheckIn = new javax.swing.JTextField();
        jToggleButton2 = new javax.swing.JToggleButton();
        txtDateCheckOut = new javax.swing.JTextField();
        txtTimeCheckOut = new javax.swing.JTextField();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        dateChooserCheckIn.setForeground(new java.awt.Color(37, 88, 207));
        dateChooserCheckIn.setTextRefernce(txtDateCheckIn);

        timePickerCheckIn.setDisplayText(txtTimeCheckIn);

        timePickerCheckOut.setDisplayText(txtTimeCheckOut);

        dateChooserCheckOut.setForeground(new java.awt.Color(37, 88, 207));
        dateChooserCheckOut.setTextRefernce(txtDateCheckOut);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblNumberRoom.setFont(new java.awt.Font("Helvetica", 1, 20)); // NOI18N
        lblNumberRoom.setText("Phòng 202");

        jLabel2.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel2.setText("Họ và tên:");

        jLabel3.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel3.setText("Số điện thoại:");

        jLabel4.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel4.setText("Email:");

        jLabel5.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel5.setText("CMND:");

        jLabel6.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel6.setText("Địa chỉ:");

        cboName.setEditable(true);
        cboName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNameItemStateChanged(evt);
            }
        });
        cboName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNameActionPerformed(evt);
            }
        });

        txtPhone.setLabelText("SĐT");

        txtEmail.setLabelText("MAIL");

        txtCMND.setLabelText("CMND/CCCD");

        textAreaScroll1.setBackground(new java.awt.Color(255, 255, 255));

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        txtAddress.setToolTipText("");
        txtAddress.setName(""); // NOI18N
        textAreaScroll1.setViewportView(txtAddress);

        jLabel7.setText("Ngày nhận phòng");

        jLabel8.setText("Ngày trả phòng");

        jToggleButton1.setText("...");
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jToggleButton2.setText("...");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jToggleButton3.setText("...");
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        jToggleButton4.setText("...");
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Đặt Phòng");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textAreaScroll1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                                    .addComponent(txtCMND, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(126, 126, 126))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)
                                .addGap(260, 260, 260)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTimeCheckOut, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDateCheckOut, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTimeCheckIn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addComponent(txtDateCheckIn, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jToggleButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jToggleButton3)
                                    .addComponent(jToggleButton4)))
                            .addComponent(jButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(427, 427, 427)
                        .addComponent(lblNumberRoom)))
                .addGap(189, 189, 189))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblNumberRoom)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboName, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtDateCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtTimeCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton2))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textAreaScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDateCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jToggleButton3))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTimeCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jToggleButton4))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        dateChooserCheckIn.showPopup();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        timePickerCheckIn.showPopup(this, (getWidth() - timePickerCheckIn.getPreferredSize().width) / 2, (getHeight() - timePickerCheckIn.getPreferredSize().height) / 2);
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        timePickerCheckOut.showPopup(this, (getWidth() - timePickerCheckOut.getPreferredSize().width) / 2, (getHeight() - timePickerCheckOut.getPreferredSize().height) / 2);
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        dateChooserCheckOut.showPopup();
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void cboNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNameItemStateChanged
        // khi chọn cbox
        System.out.println("đang chọn");
        if (flagKH == 1) {
            setForm();
        }
    }//GEN-LAST:event_cboNameItemStateChanged

    private void cboNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNameActionPerformed
//        setForm();
    }//GEN-LAST:event_cboNameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clearForm();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        insert();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(DatPhongDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatPhongDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatPhongDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatPhongDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        try {
    UIManager.setLookAndFeel( new FlatLightLaf() );
} catch( Exception ex ) {
    System.err.println( "Failed to initialize LaF" );
}
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                ModelRoom test = new ModelRoom("123","Title",
//            "0.00$",
//            "Description",true,false,true,
//            new ImageIcon(getClass().getResource("/com/undotech/icons/icon8_globe.png")));
//                DatPhongDialog dialog = new DatPhongDialog(new javax.swing.JFrame(), true,test);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboName;
    private javaswingdev.datechooser.DateChooser dateChooserCheckIn;
    private javaswingdev.datechooser.DateChooser dateChooserCheckOut;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JLabel lblNumberRoom;
    private com.undotech.utils.TextAreaScroll textAreaScroll1;
    private com.raven.swing.TimePicker timePickerCheckIn;
    private com.raven.swing.TimePicker timePickerCheckOut;
    private com.undotech.utils.TextArea txtAddress;
    private com.undotech.utils.TextField txtCMND;
    private javax.swing.JTextField txtDateCheckIn;
    private javax.swing.JTextField txtDateCheckOut;
    private com.undotech.utils.TextField txtEmail;
    private com.undotech.utils.TextField txtPhone;
    private javax.swing.JTextField txtTimeCheckIn;
    private javax.swing.JTextField txtTimeCheckOut;
    // End of variables declaration//GEN-END:variables
}
