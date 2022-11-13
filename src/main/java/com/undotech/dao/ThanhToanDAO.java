/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

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
    
    String INSERT_SQL = "INSERT INTO payment(payment_type, total_price, rating_id, booking_id) VALUES(?,?,?,?)";
    String UPDATE_SQL = "UPDATE payment SET payment_type=?, total_price=?, rating_id=?, booking_id=? WHERE id=?";
    String DELETE_SQL = "DELETE FROM payment WHERE id=?";
    String SELECT_ALL_SQL = "SELECT * FROM payment";
    String SELECT_BY_ID_SQL = "SELECT * FROM payment WHERE id=?";

    @Override
    public void insert(ThanhToan entity) {
        XJdbc.executeUpdate(INSERT_SQL, 
                    entity.getLoaiThanhToan(),
                    entity.getTongTienThanhToan(),
                    entity.getMaDanhGia(),
                    entity.getMaDatPhong()
                );
    }

    @Override
    public void update(ThanhToan entity) {
        XJdbc.executeUpdate(UPDATE_SQL, 
                    entity.getLoaiThanhToan(),
                    entity.getTongTienThanhToan(),
                    entity.getMaDanhGia(),
                    entity.getMaDatPhong(),
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
                entity.setMaThanhToan(rs.getInt("id"));
                entity.setLoaiThanhToan(rs.getString("payment_type"));
                entity.setTongTienThanhToan(rs.getDouble("total_price"));
                entity.setMaDanhGia(rs.getInt("rating_id"));
                entity.setMaDatPhong(rs.getInt("booking_id"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
