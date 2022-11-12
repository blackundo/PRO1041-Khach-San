/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

import com.undotech.entity.Phong;
import com.undotech.entity.ThanhToan;
import com.undotech.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blackundo
 */
public class ThanhToanDAO extends QLyKhachSanDAO<ThanhToan, Integer>{
    
    String INSERT_SQL = "INSERT INTO ThanhToan(MaThanhToan, LoaiThanhToan, TongTienThanhToan, MaDanhGia) VALUES(?,?,?,?)";
    String UPDATE_SQL = "UPDATE ThanhToan SET LoaiThanhToan=?, TongTienThanhToan=?, MaDanhGia=? WHERE MaThanhToan=?";
    String DELETE_SQL = "DELETE FROM ThanhToan WHERE MaThanhToan=?";
    String SELECT_ALL_SQL = "SELECT * FROM ThanhToan";
    String SELECT_BY_ID_SQL = "SELECT * FROM ThanhToan WHERE MaThanhToan=?";

    @Override
    public void insert(ThanhToan entity) {
        XJdbc.executeUpdate(INSERT_SQL, 
                    entity.getMaThanhToan(),
                    entity.getLoaiThanhToan(),
                    entity.getTongTienThanhToan(),
                    entity.getMaDanhGia()
                );
    }

    @Override
    public void update(ThanhToan entity) {
        XJdbc.executeUpdate(UPDATE_SQL, 
                    entity.getLoaiThanhToan(),
                    entity.getTongTienThanhToan(),
                    entity.getMaDanhGia(),
                    entity.getMaThanhToan()
                );
    }

    @Override
    public void delete(Integer key) {
        XJdbc.executeUpdate(DELETE_SQL, key);
    }

    @Override
    public List<ThanhToan> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ThanhToan selectById(Integer key) {
        List<ThanhToan> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<ThanhToan> selectBySql(String sql, Object... args) {
        List<ThanhToan> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                ThanhToan entity = new ThanhToan();
                entity.setMaThanhToan(rs.getInt("MaThanhToan"));
                entity.setLoaiThanhToan(rs.getString("LoaiThanhToan"));
                entity.setTongTienThanhToan(rs.getDouble("TongTienThanhToan"));
                entity.setMaDanhGia(rs.getInt("MaDanhGia"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
