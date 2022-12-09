/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

import com.undotech.entity.KhachHang;
import com.undotech.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blackundo
 */
public class KhachHangDAO extends QLyKhachSanDAO<KhachHang, Integer>{
    
    String INSERT_SQL = "INSERT INTO customer(full_name, phone, email, CMND, address) VALUES(?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE customer SET full_name=?, phone=?, email=?, CMND=?, address=? WHERE id=?";
    String DELETE_SQL = "DELETE FROM customer WHERE id=?";
    String SELECT_ALL_SQL = "SELECT * FROM customer";
    String SELECT_TOP1_SQL = "SELECT TOP 1 * FROM customer ORDER BY id DESC";
    String SELECT_BY_ID_SQL = "SELECT * FROM customer WHERE id=?";
    
    public List<KhachHang> selectByKeyword(String keyword) {
        String SQL = "SELECT * FROM customer WHERE full_name LIKE ?";
        return this.selectBySql(SQL, "%" + keyword + "%");
    }

    @Override
    public void insert(KhachHang entity) {
        XJdbc.executeUpdate(INSERT_SQL, 
                    entity.getTenKH(),
                    entity.getSDT(),
                    entity.getEmail(),
                    entity.getCMND(),
                    entity.getDiaChi()
                );
    }

    @Override
    public void update(KhachHang entity) {
        XJdbc.executeUpdate(UPDATE_SQL, 
                    entity.getTenKH(),
                    entity.getSDT(),
                    entity.getEmail(),
                    entity.getCMND(),
                    entity.getDiaChi(),
                    entity.getMaKH()
                );
    }

    @Override
    public void delete(Integer key) {
        XJdbc.executeUpdate(DELETE_SQL, key);
    }

    @Override
    public List<KhachHang> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhachHang selectById(Integer key) {
        List<KhachHang> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    
    public KhachHang selectTop1() {
        List<KhachHang> list = this.selectBySql(SELECT_TOP1_SQL);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getInt("id"));
                entity.setTenKH(rs.getString("full_name"));
                entity.setSDT(rs.getString("phone"));
                entity.setEmail(rs.getString("email"));
                entity.setCMND(rs.getString("CMND"));
                entity.setDiaChi(rs.getString("address"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
