/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

import com.undotech.entity.PhieuDichVu;
import com.undotech.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blackundo
 */
public class PhieuDichVuDAO extends QLyKhachSanDAO<PhieuDichVu, Integer>{
    
    String INSERT_SQL = "INSERT INTO room_service(service_id, room_id, bk_date) VALUES(?,?,?)";
    String UPDATE_SQL = "UPDATE room_service SET service_id=?, room_id=?, bk_date=? WHERE id=?";
    String DELETE_SQL = "DELETE FROM room_service WHERE id=?";
    String DELETE_BY_SER_ID_SQL = "DELETE FROM room_service WHERE service_id=?";
    String SELECT_ALL_SQL = "SELECT * FROM room_service";
    String SELECT_BY_ID_SQL = "SELECT * FROM room_service WHERE id=?";
    String SELECT_BY_SER_ID_SQL = "SELECT * FROM room_service WHERE service_id=?";
    String SELECT_BY_ROOM_ID_SQL = "SELECT * FROM room_service WHERE room_id=?";
    
    String SELECT_DV_PDV = "SELECT pdv.id,dv.name,dv.price,pdv.bk_date FROM room_service pdv INNER JOIN service dv ON pdv.service_id = dv.id";

    @Override
    public void insert(PhieuDichVu entity) {
        XJdbc.executeUpdate(INSERT_SQL, 
                    entity.getMaDichVu(),
                    entity.getMaPhong(),
                    entity.getTgDatPhong()
                );
    }

    @Override
    public void update(PhieuDichVu entity) {
        XJdbc.executeUpdate(UPDATE_SQL, 
                    entity.getMaDichVu(),
                    entity.getMaPhong(),
                    entity.getTgDatPhong(),
                    entity.getMaPhieuDV()
                );
    }

    @Override
    public void delete(Integer key) {
        XJdbc.executeUpdate(DELETE_SQL, key);
    }

   public void deleteBySerID(Integer key) {
        XJdbc.executeUpdate(DELETE_BY_SER_ID_SQL, key);
    }
    
    @Override
    public List<PhieuDichVu> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public List<PhieuDichVu> selectBySerID(Integer key){
         List<PhieuDichVu> list = this.selectBySql(SELECT_BY_SER_ID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return (List<PhieuDichVu>) list.get(0);
     }
    
    public List<PhieuDichVu> selectByRoomID(String key){
        return this.selectBySql(SELECT_BY_ROOM_ID_SQL, key);
     }
    
    @Override
    public PhieuDichVu selectById(Integer key) {
        List<PhieuDichVu> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<PhieuDichVu> selectBySql(String sql, Object... args) {
        List<PhieuDichVu> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                PhieuDichVu entity = new PhieuDichVu();
                entity.setMaPhieuDV(rs.getInt("id"));
                entity.setMaDichVu(rs.getInt("service_id"));
                entity.setMaPhong(rs.getString("room_id"));
                entity.setTgDatPhong(rs.getDate("bk_date"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
