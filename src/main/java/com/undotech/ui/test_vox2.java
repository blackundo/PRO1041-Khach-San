package com.undotech.ui;

import com.undotech.dao.DichVuDAO;
import com.undotech.dao.PhieuDichVuDAO;
import com.undotech.dao.ProcDAO;
import com.undotech.entity.DichVu;
import com.undotech.entity.PhieuDichVu;
import com.undotech.notification.Notification;
import com.undotech.utils.MsgBox;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vox
 */
public class test_vox2 extends javax.swing.JFrame {

    DichVuDAO dvDAO = new DichVuDAO();
    PhieuDichVuDAO pdvDAO = new PhieuDichVuDAO();
    int row = -1;
    int flag = 0;
    boolean isChoice = false;
    int TvaC = 0;

    /**
     * Creates new form test_vox2
     */
    public test_vox2() {
        initComponents();
        this.fillTable();
        this.fillComboBoxMaPhong();
        this.fillComboBoxTenDichVu();
        this.fillComboBoxDateTime();
        this.fillComboBoxGiaDichVu();
    }

    void fillComboBoxMaPhong() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaPhong.getModel();
        model.removeAllElements();
        List<PhieuDichVu> list = pdvDAO.selectAll();
        for (PhieuDichVu pdv : list) {
            model.addElement(pdv.getMaPhong());
        }
    }

    void fillComboBoxTenDichVu() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenDichVu.getModel();
        model.removeAllElements();
        List<DichVu> list = dvDAO.selectAll();
        for (DichVu dv : list) {
            model.addElement(dv.getTenDV());
        }
    }

    void fillComboBoxGiaDichVu() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboPrice.getModel();
        model.removeAllElements();
        List<DichVu> list = dvDAO.selectAll();
        for (DichVu dv : list) {
            model.addElement(dv.getGiaDV() + "");
        }
    }

    void fillComboBoxDateTime() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboDateTime.getModel();
        model.removeAllElements();
        List<PhieuDichVu> list = pdvDAO.selectAll();
        for (PhieuDichVu pdv : list) {
            model.addElement(pdv.getTgDatPhong());
        }
    }

    void cboTDVfillCboPrice() {
        String selectTDV = cboTenDichVu.getSelectedItem() + "";
        DichVu dv = dvDAO.selectByName(selectTDV);
        cboPrice.setSelectedItem(dv.getGiaDV() + "");
    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try {
//            List<DichVu> list = dvDAO.selectAll();
            List<PhieuDichVu> listDV = pdvDAO.selectAll();

            for (PhieuDichVu pdv : listDV) {
                DichVu dv = new DichVuDAO().selectById(pdv.getMaDichVu());
//                    Phong p = new PhongDAO().selectById(pdv.getMaPhong());
                String map;
//                    if(dv.getMaDV() > 0) {
//                        map = pdv.getMaPhong();
//                    } else {
//                        map = "null";
//                    }
                Object[] rows = {
                    dv.getMaDV(),
                    dv.getTenDV(),
                    dv.getGiaDV(),
                    dv.getMoTaDV(),
                    pdv.getMaPhong()
                };
                model.addRow(rows);
            }
        } catch (Exception e) {
        }
    }

    void setForm(DichVu dv) {
//        PhieuDichVu pdv = new PhieuDichVu(); 
        DichVu dvv = new DichVuDAO().selectByName(dv.getTenDV());
        txtID.setText(String.valueOf(dvv.getMaDV()));
        txtName.setText(dvv.getTenDV());
        txtPrice.setText(String.valueOf(dvv.getGiaDV()));
        txtDesc.setText(dvv.getMoTaDV());
//        txtID_Rooms.setText(String.valueOf(pdv.getMaPhong())); // Chọn 1 dịch vụ không đổ Mã Phòng ra (null)
    }

    DichVu getForm() {
        DichVu dv = new DichVu();
        PhieuDichVu phieuDV = new PhieuDichVu();
        if (flag == 0) {
            dv.setMaDV(Integer.parseInt(txtID.getText()));
        }
        dv.setTenDV(txtName.getText());
        dv.setGiaDV(Double.parseDouble(txtPrice.getText()));
        dv.setMoTaDV(txtDesc.getText());
        phieuDV.setMaPhong(txtID_Rooms.getText());
        return dv;
    }

    void insert() {
        flag = 1;
        DichVu dv = getForm();
//        System.out.println("firr");
        try {
            dvDAO.insert(dv);
            fillComboBoxTenDichVu();
            MsgBox.alert(this, "Thêm thành công");
//            new Notification(MainJFrame.getMain(), Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "Thêm thành công ^^").showNotification();
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm thất bại");
//              new Notification(MainJFrame.getMain(), Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Thêm thất bại ><").showNotification();
            System.err.println(e);
        }
    }

    void update() {
        flag = 0;
        DichVu dv = getForm();
        try {
            dvDAO.update(dv);
            fillTable();
            fillComboBoxDateTime();
            fillComboBoxMaPhong();
//            fillComboBoxTenDichVu();
            fillComboBoxGiaDichVu();
            MsgBox.alert(this, "Cập nhật thành công");
//            new Notification(MainJFrame.getMain(), Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "Cập nhật thành công ^^").showNotification();
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại");
//            new Notification(MainJFrame.getMain(), Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Cập nhật thất bại ><").showNotification();
            System.err.println(e);
        }
    }

    void delete() {
//        if (!Auth.role()) {
//            MsgBox.alert(this, "Bạn không có quyền xoá!");
//        } else {
        int maDV = (Integer.parseInt(txtID.getText()));
        if (MsgBox.confirm(this, "Bạn có muốn xoá không?")) {
            try {
                dvDAO.delete(maDV);
//                this.fillTable();
                this.fillComboBoxMaPhong();
//                this.fillComboBoxTenDichVu();//
                this.fillComboBoxDateTime();
                this.clearForm();
                MsgBox.alert(this, "Xoá thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xoá thất bại!");
                System.err.println(e);
            }
        }
//        }
    }

    void edit() {
        int maDV = (Integer) table.getValueAt(this.row, 0);
        DichVu dv = dvDAO.selectById(maDV);
        this.setForm(dv);
    }

    void selectTable() {
        int maDV = (Integer) table.getValueAt(this.row, 0);
        List<Object[]> tb = new ProcDAO().getTableVIP();
        String maPhong = new ProcDAO().getTableVIPByID(maDV);//

        DichVu dv = dvDAO.selectById(maDV);
        PhieuDichVu pdv = pdvDAO.selectById(maDV);
        cboMaPhong.setSelectedItem(maPhong);
        cboTenDichVu.setSelectedItem(dv.getTenDV());
        cboPrice.setSelectedItem(dv.getGiaDV() + "");
        cboDateTime.setSelectedItem(pdv.getTgDatPhong());

//        for(Object[] i : tb){
//            if(i[0].toString()==maDV){
//                System.out.println(i[0]);
//            } 
//            System.out.println(i[0].toString());
////            cboMaPhong.setSelectedItem(i[1]);
//        }
//        List<PhieuDichVu> list = pdvDAO.selectAll();
//        for (PhieuDichVu pdv : list) {
//            DichVu dv = dvDAO.selectById(maDV);
//            
//            cboMaPhong.setSelectedItem(pdv.getMaPhong());
//            cboTenDichVu.setSelectedItem(dv.getTenDV());
////            setComboBox(Integer.parseInt(item.getMaPhong()));
//        }
    }

    void insert_room_service() {
        String maPhong = cboMaPhong.getSelectedItem().toString();
        String tenDV = cboTenDichVu.getSelectedItem().toString();
        Date ngayGio = new Date();
        DichVu dv = dvDAO.selectByName(tenDV);

        PhieuDichVu pdv = new PhieuDichVu(1, dv.getMaDV(), maPhong);
        pdvDAO.insert(pdv);
        fillTable();
        fillComboBoxDateTime();

    }

    void cancel_room_service() {
        
    }

    void clearForm() {
        txtID.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtDesc.setText("");
        txtID_Rooms.setText("");
        this.row = -1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextArea();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtID = new com.undotech.utils.TextField();
        txtName = new com.undotech.utils.TextField();
        txtPrice = new com.undotech.utils.TextField();
        txtID_Rooms = new com.undotech.utils.TextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnCancelDV = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        cboMaPhong = new javax.swing.JComboBox<>();
        cboTenDichVu = new javax.swing.JComboBox<>();
        btnAddServices = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboPrice = new javax.swing.JComboBox<>();
        cboDateTime = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ DỊCH VỤ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(32, 32, 32))
        );

        jPanel2.setBackground(new java.awt.Color(198, 235, 217));

        jLabel6.setText("Ghi chú :");

        txtDesc.setColumns(20);
        txtDesc.setRows(5);
        jScrollPane1.setViewportView(txtDesc);

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Xoá");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Quản lý dịch vụ");

        txtID.setEnabled(false);
        txtID.setLabelText("Mã dịch vụ");

        txtName.setLabelText("Tên dịch vụ");

        txtPrice.setLabelText("Giá dịch vụ");

        txtID_Rooms.setLabelText("Mã phòng");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReset))
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtID_Rooms, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLabel7)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(txtID_Rooms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnReset))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(5, 100));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Phòng đã thuê dịch vụ");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã dịch vụ", "Tên dịch vụ", "Giá dịch vụ", "Mô tả", "Mã phòng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        btnCancelDV.setText("Huỷ DV khỏi phòng");
        btnCancelDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelDVActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Thêm dịch vụ");

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        cboMaPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboTenDichVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTenDichVu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTenDichVuItemStateChanged(evt);
            }
        });
        cboTenDichVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboTenDichVuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboTenDichVuMousePressed(evt);
            }
        });
        cboTenDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenDichVuActionPerformed(evt);
            }
        });

        btnAddServices.setText("Thêm");
        btnAddServices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddServicesActionPerformed(evt);
            }
        });

        jLabel2.setText("Mã phòng :");

        jLabel3.setText("Tên dịch vụ :");

        cboPrice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboPrice.setEnabled(false);

        cboDateTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboDateTime.setEnabled(false);

        jLabel4.setText("Giá dịch vụ :");

        jLabel5.setText("Ngày (giờ) gọi :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(cboTenDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(cboDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(cboPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(81, 81, 81))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(btnAddServices)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTenDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnAddServices)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(223, 223, 223)
                                .addComponent(jLabel9))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(195, 195, 195)
                                .addComponent(jLabel8)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelDV))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelDV)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 31, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        insert();
    }//GEN-LAST:event_btnAddActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (evt.getClickCount() == 1) {
            this.row = table.getSelectedRow();
            if (this.row >= 0) {
                this.selectTable();
                this.edit();
            }
        }
    }//GEN-LAST:event_tableMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddServicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddServicesActionPerformed
        insert_room_service();
    }//GEN-LAST:event_btnAddServicesActionPerformed

    private void cboTenDichVuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTenDichVuItemStateChanged
//        cboTDVfillCboPrice();

    }//GEN-LAST:event_cboTenDichVuItemStateChanged

    private void cboTenDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenDichVuActionPerformed
//        cboTDVfillCboPrice();
//        System.out.println("ra");

        if (isChoice) {
            cboTDVfillCboPrice();
            DichVu dv = dvDAO.selectByName(cboTenDichVu.getSelectedItem() + "");
            setForm(dv);
        }

    }//GEN-LAST:event_cboTenDichVuActionPerformed

    private void cboTenDichVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTenDichVuMouseClicked
//        System.out.println("ra");
        isChoice = true;
    }//GEN-LAST:event_cboTenDichVuMouseClicked

    private void cboTenDichVuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTenDichVuMousePressed
//        System.out.println("ra");
    }//GEN-LAST:event_cboTenDichVuMousePressed

    private void btnCancelDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelDVActionPerformed
        cancel_room_service();
    }//GEN-LAST:event_btnCancelDVActionPerformed

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
            java.util.logging.Logger.getLogger(test_vox2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(test_vox2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(test_vox2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(test_vox2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new test_vox2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddServices;
    private javax.swing.JButton btnCancelDV;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboDateTime;
    private javax.swing.JComboBox<String> cboMaPhong;
    private javax.swing.JComboBox<String> cboPrice;
    private javax.swing.JComboBox<String> cboTenDichVu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table;
    private javax.swing.JTextArea txtDesc;
    private com.undotech.utils.TextField txtID;
    private com.undotech.utils.TextField txtID_Rooms;
    private com.undotech.utils.TextField txtName;
    private com.undotech.utils.TextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
