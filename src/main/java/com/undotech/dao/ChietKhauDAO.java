/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

import com.undotech.entity.ChietKhau;
import java.util.List;

/**
 *
 * @author blackundo
 */
public class ChietKhauDAO extends QLyKhachSanDAO<ChietKhau, Integer>{
    
    String INSERT_SQL = "INSERT INTO ChietKhau(PhanTramChietKhau, NgayBatDau, NgayKetThuc, MaPhong) VALUES(?,?,?,?)";
    String UPDATE_SQL = "UPDATE ChietKhau SET PhanTramChietKhau=?, NgayBatDau=?, NgayKetThuc=? WHERE MaChietKhau=?";
    String DELETE_SQL = "DELETE FROM ChietKhau WHERE MaChietKhau=?";
    String SELECT_ALL_SQL = "SELECT * FROM ChietKhau";
    String SELECT_BY_ID_SQL = "SELECT * FROM ChietKhau WHERE MaChietKhau=?";

    @Override
    public void insert(ChietKhau entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(ChietKhau entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChietKhau> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChietKhau selectById(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<ChietKhau> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
