/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

import com.undotech.entity.ChietKhau;
import com.undotech.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getPhanTramCK(),
                entity.getNgayBatDau(),
                entity.getNgayKetThuc(),
                entity.getMaPhong()
        );
    }

    @Override
    public void update(ChietKhau entity) {
        XJdbc.executeUpdate(UPDATE_SQL, 
                entity.getPhanTramCK(),
                entity.getNgayBatDau(),
                entity.getNgayKetThuc(),
                entity.getMaChietKhau()
                );
    }

    @Override
    public void delete(Integer key) {
        XJdbc.executeUpdate(DELETE_SQL, key);}

    @Override
    public List<ChietKhau> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ChietKhau selectById(Integer key) {
        List<ChietKhau> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<ChietKhau> selectBySql(String sql, Object... args) {
        List<ChietKhau> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                ChietKhau entity = new ChietKhau();
                entity.setMaChietKhau(rs.getInt("MaChietKhau"));
                entity.setPhanTramCK(rs.getDouble("PhamTramChietKhau"));
                entity.setNgayBatDau(rs.getDate("NgayBatDau"));
                entity.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                entity.setMaPhong(rs.getString("MaPhong"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
