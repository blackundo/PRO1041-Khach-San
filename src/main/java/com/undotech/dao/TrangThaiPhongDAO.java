/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

import com.undotech.entity.TrangThaiPhong;
import com.undotech.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blackundo
 */
public class TrangThaiPhongDAO extends QLyKhachSanDAO<TrangThaiPhong, String>{
    
    String INSERT_SQL = "INSERT INTO status_room(room_id, is_clean, is_repair) VALUES(?,?,?)";
    String UPDATE_SQL = "UPDATE status_room SET is_clean=?, is_repair=? WHERE room_id=?";
    String DELETE_SQL = "DELETE FROM status_room WHERE room_id=?";
    String SELECT_ALL_SQL = "SELECT * FROM status_room";
    String SELECT_BY_ID_SQL = "SELECT * FROM status_room WHERE room_id=?";

    @Override
    public void insert(TrangThaiPhong entity) {
        XJdbc.executeUpdate(INSERT_SQL, 
                    entity.getMaPhong(),
                    entity.isDonDep(),
                    entity.isSuaChua()
                );
    }

    @Override
    public void update(TrangThaiPhong entity) {
        XJdbc.executeUpdate(UPDATE_SQL, 
                    entity.isDonDep(),
                    entity.isSuaChua(),
                    entity.getMaPhong()
                );
    }

    @Override
    public void delete(String key) {
        XJdbc.executeQuery(DELETE_SQL, key);
    }

    @Override
    public List<TrangThaiPhong> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public TrangThaiPhong selectById(String key) {
        List<TrangThaiPhong> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<TrangThaiPhong> selectBySql(String sql, Object... args) {
        List<TrangThaiPhong> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                TrangThaiPhong entity = new TrangThaiPhong();
                entity.setMaPhong(rs.getString("room_id"));
                entity.setDonDep(rs.getBoolean("is_clean"));
                entity.setSuaChua(rs.getBoolean("is_repair"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
