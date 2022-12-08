/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

import com.undotech.entity.TienNghi;
import com.undotech.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blackundo
 */
public class TienNghiDAO extends QLyKhachSanDAO<TienNghi, Integer>{
    
    String INSERT_SQL = "INSERT INTO convenient(name, price, description, room_id) VALUES(?,?,?,?)";
    String UPDATE_SQL = "UPDATE convenient SET name=?, price=?, description=?, room_id=? WHERE id=?";
    String DELETE_SQL = "DELETE FROM convenient WHERE id=?";
    String SELECT_ALL_SQL = "SELECT * FROM convenient";
    String SELECT_BY_ROOMID_SQL = "SELECT * FROM convenient WHERE room_id=?";
    String SELECT_BY_ID_SQL = "SELECT * FROM convenient WHERE id=?";
    
    public List<TienNghi> selectByKeyword(String keyword) {
        String SQL = "SELECT * FROM convenient WHERE name LIKE ?";
        return this.selectBySql(SQL, "%" + keyword + "%");
    }
    
    @Override
    public void insert(TienNghi entity) {
        XJdbc.executeUpdate(INSERT_SQL, 
                    entity.getTenTienNghi(),
                    entity.getGia(),
                    entity.getMoTa(),
                    entity.getMaPhong()
                );
    }

    @Override
    public void update(TienNghi entity) {
        XJdbc.executeUpdate(UPDATE_SQL, 
                    entity.getTenTienNghi(),
                    entity.getGia(),
                    entity.getMoTa(),
                    entity.getMaPhong(),
                    entity.getMaTienNghi()
                );
    }

    @Override
    public void delete(Integer key) {
        XJdbc.executeUpdate(DELETE_SQL, key);
    }

    @Override
    public List<TienNghi> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }
    
    public List<TienNghi> selectByRoomID(String key) {
        return this.selectBySql(SELECT_BY_ROOMID_SQL,key);
    }

    @Override
    public TienNghi selectById(Integer key) {
        List<TienNghi> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<TienNghi> selectBySql(String sql, Object... args) {
        List<TienNghi> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                TienNghi entity = new TienNghi();
                entity.setMaTienNghi(rs.getInt("id"));
                entity.setTenTienNghi(rs.getString("name"));
                entity.setGia(rs.getDouble("price"));
                entity.setMoTa(rs.getString("description"));
                entity.setMaPhong(rs.getString("room_id"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
