/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

import com.undotech.entity.DanhGia;
import com.undotech.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blackundo
 */
public class DanhGiaDAO extends QLyKhachSanDAO<DanhGia, Integer> {
    
    String INSERT_SQL = "INSERT INTO DanhGia(LoaiDanhGia) VALUES(?)";
    String UPDATE_SQL = "UPDATE DanhGia SET LoaiDanhGia=? WHERE MaDanhGia=?";
    String DELETE_SQL = "DELETE FROM DanhGia WHERE MaDanhGia=?";
    String SELECT_ALL_SQL = "SELECT * FROM DanhGia";
    String SELECT_BY_ID_SQL = "SELECT * FROM DanhGia WHERE MaDanhGia=?";

    @Override
    public void insert(DanhGia entity) {
        XJdbc.executeUpdate(INSERT_SQL, 
                    entity.getLoaiDanhGia()
                );
    }

    @Override
    public void update(DanhGia entity) {
        XJdbc.executeUpdate(UPDATE_SQL, 
                    entity.getLoaiDanhGia(),
                    entity.getMaDanhGia()
                );
    }

    @Override
    public void delete(Integer key) {
        XJdbc.executeUpdate(DELETE_SQL,key);
    }

    @Override
    public List<DanhGia> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public DanhGia selectById(Integer key) {
        List<DanhGia> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<DanhGia> selectBySql(String sql, Object... args) {
        List<DanhGia> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                DanhGia entity = new DanhGia();
                entity.setMaDanhGia(rs.getInt("MaDanhGia"));
                entity.setLoaiDanhGia(rs.getString("LoaiDanhGia"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
