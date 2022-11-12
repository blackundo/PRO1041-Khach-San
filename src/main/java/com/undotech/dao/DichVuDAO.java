/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

import com.undotech.entity.DatPhong;
import com.undotech.entity.DichVu;
import com.undotech.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blackundo
 */
public class DichVuDAO extends QLyKhachSanDAO<DichVu, Integer>{
    
    String INSERT_SQL = "INSERT INTO DichVu(MaDichVu, TenDichVu, GiaDichVu, MoTaDichVu) VALUES(?,?,?,?)";
    String UPDATE_SQL = "UPDATE DichVu SET TenDichVu=?, GiaDichVu=?, MoTaDichVu=? WHERE MaDichVu=?";
    String DELETE_SQL = "DELETE FROM DichVu WHERE MaDichVu=?";
    String SELECT_ALL_SQL = "SELECT * FROM DichVu";
    String SELECT_BY_ID_SQL = "SELECT * FROM DichVu WHERE MaDichVu=?";

    @Override
    public void insert(DichVu entity) {
        XJdbc.executeUpdate(INSERT_SQL, 
                    entity.getMaDV(),
                    entity.getTenDV(),
                    entity.getGiaDV(),
                    entity.getMoTaDV()
                );
    }

    @Override
    public void update(DichVu entity) {
        XJdbc.executeUpdate(UPDATE_SQL, 
                    entity.getTenDV(),
                    entity.getGiaDV(),
                    entity.getMoTaDV(),
                    entity.getMaDV()
                );
    }

    @Override
    public void delete(Integer key) {
        XJdbc.executeUpdate(DELETE_SQL, key);
    }

    @Override
    public List<DichVu> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public DichVu selectById(Integer key) {
        List<DichVu> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<DichVu> selectBySql(String sql, Object... args) {
        List<DichVu> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                DichVu entity = new DichVu();
                entity.setMaDV(rs.getInt("MaDichVu"));
                entity.setTenDV(rs.getString("TenDichVu"));
                entity.setGiaDV(rs.getDouble("GiaDichVu"));
                entity.setMoTaDV(rs.getString("MoTaDichVu"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
