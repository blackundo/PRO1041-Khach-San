/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

import com.undotech.entity.NhanVien;
import com.undotech.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blackundo
 */
public class NhanVienDAO extends QLyKhachSanDAO<NhanVien, String>{
    
    String INSERT_SQL = "INSERT INTO NhanVien(MaNhanVien, TenNhanVien, ChucVu, SoDT, Email, DiaChi, TaiKhoan, MatKhau) VALUES(?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE NhanVien SET TenNhanVien=?, ChucVu=?, SoDT=?, Email=?, DiaChi=?, TaiKhoan=?, MatKhau=? WHERE MaNhanVien=?";
    String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNhanVien=?";
    String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE TaiKhoan=?";
    String SELECT_ALL_SQL = "SELECT * FROM NHANVIEN";

    @Override
    public void insert(NhanVien entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getMaNV(),
                entity.getTenNV(),
                entity.getChucVu(),
                entity.getSoDT(),
                entity.getEmail(),
                entity.getDiaChi(),
                entity.getTaiKhoan(),
                entity.getMatKhau()
        );
    }

    @Override
    public void update(NhanVien entity) {
        XJdbc.executeUpdate(UPDATE_SQL, 
                entity.getTenNV(),
                entity.getChucVu(),
                entity.getSoDT(),
                entity.getEmail(),
                entity.getDiaChi(),
                entity.getTaiKhoan(),
                entity.getMatKhau(),
                entity.getMaNV()
                );
    }

    @Override
    public void delete(String key) {
        XJdbc.executeUpdate(DELETE_SQL, key);
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectById(String key) {
        List<NhanVien> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNhanVien"));
                entity.setTenNV(rs.getString("TenNhanVien"));
                entity.setChucVu(rs.getString("ChucVu"));
                entity.setSoDT(rs.getString("SoDT"));
                entity.setEmail(rs.getString("Email"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setTaiKhoan(rs.getString("TaiKhoan"));
                entity.setMatKhau(rs.getString("MatKhau"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
