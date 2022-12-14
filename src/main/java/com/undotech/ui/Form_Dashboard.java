package com.undotech.ui;


import com.undotech.dao.KhachHangDAO;
import com.undotech.entity.DatPhong;
import com.undotech.entity.KhachHang;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javaswingdev.card.ModelCard;
import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;
import org.json.JSONArray;

public class Form_Dashboard extends javax.swing.JPanel {
    
    ArrayList<DatPhong> list;
    KhachHangDAO khdao = new KhachHangDAO();

    public Form_Dashboard() throws IOException, ParseException {
        initComponents();
        init();
        list = new ArrayList<>();
    }

    private void init() throws IOException, ParseException {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.fixTable(jScrollPane1);
//        table.addRow(new Object[]{"1", "Trần Văn Híu", "0905161000", "201870555", "102"});
//        table.addRow(new Object[]{"2", "Trần Phước Khánh Huy", "0905161000", "201870555", "204"});
//        table.addRow(new Object[]{"3", "Đặng Văn Võ", "0905161000", "123123213", "230"});
//        table.addRow(new Object[]{"1", "Trần Văn Híu", "0905161000", "201870555", "102"});
//        table.addRow(new Object[]{"2", "Trần Phước Khánh Huy", "0905161000", "201870555", "204"});
//        table.addRow(new Object[]{"3", "Đặng Văn Võ", "0905161000", "123123213", "230"});
//        table.addRow(new Object[]{"1", "Trần Văn Híu", "0905161000", "201870555", "102"});
//        table.addRow(new Object[]{"2", "Trần Phước Khánh Huy", "0905161000", "201870555", "204"});
//        table.addRow(new Object[]{"3", "Đặng Văn Võ", "0905161000", "123123213", "230"});
//        table.addRow(new Object[]{"1", "Trần Văn Híu", "0905161000", "201870555", "102"});
//        table.addRow(new Object[]{"2", "Trần Phước Khánh Huy", "0905161000", "201870555", "204"});
//        table.addRow(new Object[]{"3", "Đặng Văn Võ", "0905161000", "123123213", "230"});
//        table.addRow(new Object[]{"1", "Trần Văn Híu", "0905161000", "201870555", "102"});
//        table.addRow(new Object[]{"2", "Trần Phước Khánh Huy", "0905161000", "201870555", "204"});
//        table.addRow(new Object[]{"3", "Đặng Văn Võ", "0905161000", "123123213", "230"});
//        table.addRow(new Object[]{"1", "Trần Văn Híu", "0905161000", "201870555", "102"});
//        table.addRow(new Object[]{"2", "Trần Phước Khánh Huy", "0905161000", "201870555", "204"});
//        table.addRow(new Object[]{"3", "Đặng Văn Võ", "0905161000", "123123213", "230"});

        //  init card data
        card1.setData(new ModelCard(null, null, null, "23/100", "Số phòng trống"));
        card2.setData(new ModelCard(null, null, null, "52.320.000đ", "Doanh thu/tháng"));
        card3.setData(new ModelCard(null, null, null, "8/20", "Nhân viên"));
        
        
        String data = getListJson();
        JSONArray items = new JSONArray(data);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        for(int i = 0; i < items.length(); ++i){
            DatPhong dp = null;
            JSONObject rec = items.getJSONObject(i);
            
            
            int id = rec.getInt("id");
            String bk_date = rec.getString("bk_date");
            String checkin = rec.getString("checkin_date");
            String checkout = rec.getString("checkout_date");
            int total = rec.getInt("total_room");
            int customer_id = rec.getInt("customer_id");
            String staff_id = rec.getString("staff_id");
            String room_id = rec.getString("room_id");
            
            KhachHang kh = khdao.selectById(customer_id);
            String tenkh = kh.getTenKH();
            
//            dp = new DatPhong(id, checkin, sdf.parse(checkout), total, customer_id, staff_id,room_id);
//            list.add(dp);

            Object[] rows = {
                id,tenkh,bk_date,checkin,checkout,customer_id,room_id
            };
            model.addRow(rows);
            
        }
        
    }
    
    private String getListJson() throws MalformedURLException, IOException{
        URL url = new URL("http://localhost:8081/booking/");
        URLConnection conn = url.openConnection();
//        HttpURLConnection;
//        HttpsURLConnection;
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String input = in.readLine();
        String output = input.replace("(", " ").replace(")", " ");
        in.close();
        
        return output.trim();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card1 = new javaswingdev.card.Card();
        card2 = new javaswingdev.card.Card();
        card3 = new javaswingdev.card.Card();
        roundPanel1 = new javaswingdev.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
        jLabel1 = new javax.swing.JLabel();

        setOpaque(false);

        card2.setColor1(new java.awt.Color(95, 211, 226));
        card2.setColor2(new java.awt.Color(26, 166, 170));
        card2.setIcon(javaswingdev.GoogleMaterialDesignIcon.PIE_CHART);

        card3.setColor1(new java.awt.Color(95, 243, 140));
        card3.setColor2(new java.awt.Color(3, 157, 27));
        card3.setIcon(javaswingdev.GoogleMaterialDesignIcon.RING_VOLUME);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Tên khách hàng", "Ngày đặt phòng", "Ngày nhận phòng", "Ngày trả phòng", "Mã khách hàng", "Mã phòng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Danh sách khách đặt phòng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(card1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(card2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(card3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javaswingdev.card.Card card1;
    private javaswingdev.card.Card card2;
    private javaswingdev.card.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javaswingdev.swing.table.Table table;
    // End of variables declaration//GEN-END:variables
}
