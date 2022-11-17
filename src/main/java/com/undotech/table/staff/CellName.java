package com.undotech.table.staff;


import com.undotech.dao.NhanVienDAO;
import com.undotech.entity.NhanVien;
import com.undotech.notification.Notification;
import com.undotech.ui.MainJFrame;
import com.undotech.ui.testfrom;
import com.undotech.utils.MsgBox;
import com.undotech.utils.XImage;
import javaswingdev.swing.table.TableCustom;
import javaswingdev.swing.table.cell.TableCustomCell;
import javaswingdev.swing.table.model.TableRowData;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;

public class CellName extends TableCustomCell {

    private String pathImage;

    public CellName() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popup = new javax.swing.JPopupMenu();
        deleteImage = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        imageAvatar1 = new javaswingdev.swing.ImageAvatar();
        jLabel3 = new javax.swing.JLabel();
        cmdSave = new javax.swing.JButton();

        deleteImage.setText("Xoá");
        deleteImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteImageActionPerformed(evt);
            }
        });
        popup.add(deleteImage);

        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Họ và Tên");

        imageAvatar1.setBorderSize(2);
        imageAvatar1.setBorderSpace(1);
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/com/undotech/icons/icon8_globe.png"))); // NOI18N
        imageAvatar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageAvatar1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                imageAvatar1MousePressed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Hình Ảnh");

        cmdSave.setText("Lưu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdSave)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void imageAvatar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageAvatar1MouseClicked
        if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 2) {
            JFileChooser ch = new JFileChooser();
            ch.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File f) {
                    String name = f.getName().toLowerCase();
                    return f.isDirectory() || name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg");
                }

                @Override
                public String getDescription() {
                    return "Image File";
                }
            });
            
            int opt = ch.showOpenDialog(this);
            if (opt == JFileChooser.APPROVE_OPTION) {
                File file = ch.getSelectedFile();
                XImage.save(file);
                ImageIcon icon = XImage.read(file.getName());
                pathImage = ch.getSelectedFile().getAbsolutePath();
                imageAvatar1.setToolTipText(file.getName());
                imageAvatar1.setImage(icon);
            }
        }
    }//GEN-LAST:event_imageAvatar1MouseClicked

    private void deleteImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteImageActionPerformed
        imageAvatar1.setToolTipText(null);
        imageAvatar1.setImage(null);
    }//GEN-LAST:event_deleteImageActionPerformed

    private void imageAvatar1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageAvatar1MousePressed
        if (SwingUtilities.isRightMouseButton(evt)) {
            popup.show(imageAvatar1, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_imageAvatar1MousePressed

    private void addEventSave(TableCustom table) {
        cmdSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                table.stopCellEditing();
                int row = getRow();
                NhanVien data = (NhanVien) table.getModelData(row);
                String user="1"; String pass="1";
                
                String name = table.getValueAt(row, 1).toString();
                String nameHandle = name.substring(0, name.indexOf("||"));
                
                String role = table.getValueAt(row, 2).toString();
                String phone = table.getValueAt(row, 3).toString();
                String email = table.getValueAt(row, 4).toString();
                String address = table.getValueAt(row, 5).toString();
                String img = imageAvatar1.getToolTipText();
                
                if (data.getMaNV()==null) {
                    user = MsgBox.prompt(null, "Tài Khoản");
                    pass = MsgBox.prompt(null, "Mật Khẩu");
                }
                
                NhanVien nvIn = new NhanVien("",nameHandle,role,phone,email,address,user,pass,img);
                NhanVien nvUp = new NhanVien(data.getMaNV(),nameHandle,role,phone,email,address,data.getTaiKhoan(),data.getMatKhau(),img);
                
                try {
                    if (data.getMaNV()==null) {
                        //  Insert
                        new NhanVienDAO().insert(nvIn);
//                        MsgBox.alert(null, "Thêm Thành công");
                    new Notification(MainJFrame.getMain(), Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "Thêm thành công ^^").showNotification();

//                        data.getName().setPath("Image");
                        table.updateModelData(row, nvIn);
                    } else {
                        //  Update
//                        new ServiceStaff().updateStaff(data);
                        new NhanVienDAO().update(nvUp);
//                        data.getName().setPath("Image");
                        table.updateModelData(row, nvUp);
                    }
                } catch (Exception e) {
                    System.err.println(e);
                }
                
                
//                String st = "tên : " + table.getValueAt(row, 0).toString() + " chức vụ : " + table.getValueAt(row, 1);
//                JOptionPane.showMessageDialog(null, st);
            }
        });
    }

    @Override
    public void setData(Object data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    //@Override
    public void setData1(Object o,TableCustom tc,int row) {
        if (o != null) {
//            ModelNew d =  (ModelNew) o;

                //lỗi click 2 lần là ảnh bị null
                String text = o.toString();
                int tenIndex = text.indexOf("||");
//                System.out.println(text);
            NhanVien data = (NhanVien) tc.getModelData(row);
            
                    String s1,s2;
                        
                        if(text.length()>2){
                            if (tenIndex==-1) {
                                //update
                                tenIndex=text.length();
                                 s1 = text.substring(0,tenIndex);
                                s2 = text.substring(0, text.length());

//                                 JOptionPane.showMessageDialog(null,data.getHinhAnh());
//                                 s2 = text.substring(0, text.length());
                            }else{
                                //them
                                 s1 = text.substring(0,tenIndex);
                                 s2 = text.substring(tenIndex+2, text.length());
//                                 System.out.println("s1: "+s1+"s2: "+s2);
                            }
                            
        //                    System.out.println("Name"+s1+"anh"+s2);
                            txtFirstName.setText(s1);
                            imageAvatar1.setImage(XImage.read(s2));
                            pathImage = s2;
                        }
                    
            
//                txtFirstName.setText("123");
                    
            
//            txtFirstName.setText(d.getHoTen());
//            imageAvatar1.setImage(d.getProfile());
//            imageAvatar1.setImage(new ImageIcon(getClass().getResource("/logos/"+d.getHinhAnh())));
//            pathImage = d.getHinhAnh();
        }
    }

    @Override
    public Object getData() {
        String firstName = txtFirstName.getText();
        String hinhAnh = imageAvatar1.getToolTipText();
        NhanVien nv = new NhanVien("", firstName, "", "", "", "", "", "", pathImage);
//        nv.setTenNV(firstName);
//        nv.setHinhAnh("123");
//        return nv;
        return new ModelNew(firstName, hinhAnh);
//        return new ModelName(firstName, imageAvatar1.getImage(), pathImage);
    }

    @Override
    public Component createComponentCellRender(TableCustom table, TableRowData data, int row, int column) {
        NhanVien nv = (NhanVien) data;
//        ModelStaff student = (ModelStaff) data;
        
        return new CellNameRender(nv);
        //return new CellNameRender(student.getName());
    }

    @Override
    public TableCustomCell createComponentCellEditor(TableCustom tc, TableRowData trd, Object o, int i, int i1) {
        CellName cell = new CellName();
        cell.setData1(o,tc,i); // default o
        cell.addEventSave(tc);
        return cell;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdSave;
    private javax.swing.JMenuItem deleteImage;
    private javaswingdev.swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPopupMenu popup;
    private javax.swing.JTextField txtFirstName;
    // End of variables declaration//GEN-END:variables
}
