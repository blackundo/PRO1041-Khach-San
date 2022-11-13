/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

import com.undotech.entity.Phong;
import com.undotech.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blackundo
 */
public class PhongDAO extends QLyKhachSanDAO<Phong, String>{
    
    String INSERT_SQL = "INSERT INTO room(id, number, price, type, description, booking_id) VALUES(?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE room SET number=?, price=?, type=?, description=?, booking_id=? WHERE id=?";
    String DELETE_SQL = "DELETE FROM room WHERE id=?";
    String SELECT_ALL_SQL = "SELECT * FROM room";
    String SELECT_BY_ID_SQL = "SELECT * FROM room WHERE id=?";

    @Override
    public void insert(Phong entity) {
        XJdbc.executeUpdate(INSERT_SQL, 
                    entity.getMaPhong(),
                    entity.getSoPhong(),
                    entity.getGiaPhong(),
                    entity.getKieuPhong(),
                    entity.getMoTa(),
                    entity.getMaDatPhong()
                );
    }

    @Override
    public void update(Phong entity) {
        XJdbc.executeUpdate(INSERT_SQL, 
                    entity.getSoPhong(),
                    entity.getGiaPhong(),
                    entity.getKieuPhong(),
                    entity.getMoTa(),
                    entity.getMaDatPhong(),
                    entity.getMaPhong()
                );
    }

    @Override
    public void delete(String key) {
        XJdbc.executeUpdate(DELETE_SQL, key);
    }

    @Override
    public List<Phong> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Phong selectById(String key) {
        List<Phong> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<Phong> selectBySql(String sql, Object... args) {
        List<Phong> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                Phong entity = new Phong();
                entity.setMaPhong(rs.getString("id"));
                entity.setSoPhong(rs.getInt("number"));
                entity.setGiaPhong(rs.getDouble("price"));
                entity.setKieuPhong(rs.getString("type"));
                entity.setMoTa(rs.getString("description"));
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
