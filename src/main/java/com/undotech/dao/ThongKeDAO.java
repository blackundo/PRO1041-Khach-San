/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

import com.undotech.entity.ModelData;
import com.undotech.utils.XJdbc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import raven.chart.ModelChart;

/**
 *
 * @author blackundo
 */
public class ThongKeDAO {
        public List<ModelData> getdata(){
            try {
            List<ModelData> lists = new ArrayList<>();
            
            String sql = "SELECT MONTH(bk_date) as 'thang', COUNT(total_room) as 'tong' FROM booking GROUP BY MONTH(bk_date) ORDER BY MONTH(bk_date) ASC";
            
            
            
            ResultSet rs = XJdbc.executeQuery(sql);
            while (rs.next()) {
                String month = rs.getString(1);
                int amount = rs.getInt(2);
                ModelData tkp = new ModelData();
                tkp.setMonth(month);
                tkp.setAmount(amount);
                
                lists.add(tkp);
            }
            rs.getStatement().getConnection().close();
            
            return lists;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        public List<ModelData> getdata2(){
            try {
            List<ModelData> lists = new ArrayList<>();
            
            String sql = "select MONTH(bk_date), COUNT(service_id) from room_service GROUP BY MONTH(bk_date) ORDER BY MONTH(bk_date) ASC";
            ResultSet rs = XJdbc.executeQuery(sql);
            while (rs.next()) {
                String month = rs.getString(1);
                int amountService = rs.getInt(2);
                ModelData tkp = new ModelData();
                tkp.setMonth(month);
                tkp.setAmountService(amountService);
                
                lists.add(tkp);
            }
            rs.getStatement().getConnection().close();
            return lists;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
       
}
