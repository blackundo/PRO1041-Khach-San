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
    
    String INSERT_SQL = "INSERT INTO rating(type) VALUES(?)";
    String UPDATE_SQL = "UPDATE rating SET type=? WHERE id=?";
    String DELETE_SQL = "DELETE FROM rating WHERE id=?";
    String SELECT_ALL_SQL = "SELECT * FROM rating";
    String SELECT_TOP1_SQL = "SELECT TOP 1 * FROM rating ORDER BY id DESC";
    String SELECT_BY_ID_SQL = "SELECT * FROM rating WHERE id=?";

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
    
    public DanhGia selectTop1() {
        List<DanhGia> list = this.selectBySql(SELECT_TOP1_SQL);
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
                entity.setMaDanhGia(rs.getInt("id"));
                entity.setLoaiDanhGia(rs.getString("type"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
