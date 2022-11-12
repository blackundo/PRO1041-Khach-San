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
    
    String INSERT_SQL = "INSERT INTO KhachHang(MaKhachHang, TenKhachHang, SoDT, Email, CMND, DiaChi) VALUES(?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KhachHang SET TenKhachHang=?, SoDT=?, Email=?, CMND=?, DiaChi=? WHERE MaKhachHang=?";
    String DELETE_SQL = "DELETE FROM KhachHang WHERE MaKhachHang=?";
    String SELECT_ALL_SQL = "SELECT * FROM KhachHang";
    String SELECT_BY_ID_SQL = "SELECT * FROM KhachHang WHERE MaKhachHang=?";

    @Override
    public void insert(KhachHang entity) {
        XJdbc.executeUpdate(INSERT_SQL, 
                    entity.getMaKH(),
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

    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getInt("MaKhachHang"));
                entity.setTenKH(rs.getString("TenKhachHang"));
                entity.setSDT(rs.getString("SoDT"));
                entity.setEmail(rs.getString("Email"));
                entity.setCMND(rs.getString("CMND"));
                entity.setDiaChi(rs.getString("DiaChi"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
