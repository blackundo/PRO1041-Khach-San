/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

import com.undotech.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vox
 */
public class ProcDAO {
        private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
        //combobox
    public List<Object[]> getTableVIP() {
        String sql = "{CALL sp_service_used}";
        String[] cols = {"room_id","id", "name", "price", "bk_date"};
        return this.getListOfArray(sql, cols);
    }
    
    
     public String getTableVIPByID(Integer dvid) {
        String sql = "{CALL sp_service_used_id(?)}";
        String[] cols = {"room_id","id", "name", "price", "bk_date"};
        ResultSet rs = XJdbc.executeQuery(sql, dvid);
        String vals = null;
         try {
            while (rs.next()) {
                vals = (String) rs.getObject(cols[0]);
            }
         } catch (Exception e) {
         }
        
        return vals;
    }
}
