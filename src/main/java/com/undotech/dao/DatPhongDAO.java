/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

import com.undotech.entity.DatPhong;
import com.undotech.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blackundo
 */
public class DatPhongDAO extends QLyKhachSanDAO<DatPhong, Integer>{
    
    String INSERT_SQL = "INSERT INTO booking(bk_date, checkin_date, checkout_date, total_room, customer_id, staff_id, room_id) VALUES(?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE booking SET bk_date=?, checkin_date=?, checkout_date=?, total_room=?, customer_id=?, staff_id=?, room_id=? WHERE id=?";
    String DELETE_SQL = "DELETE FROM booking WHERE id=?";
    String SELECT_ALL_SQL = "SELECT * FROM booking";
    String SELECT_TOP1_SQL = "SELECT TOP 1 * FROM booking ORDER BY ID DESC";
    String SELECT_BY_ID_SQL = "SELECT * FROM booking WHERE id=?";

    @Override
    public void insert(DatPhong entity) {
        XJdbc.executeUpdate(INSERT_SQL, 
                    entity.getNgatDatPhong(),
                    entity.getCheckIn(),
                    entity.getCheckOut(),
                    entity.getTongSoPhongDat(),
                    entity.getMaKH(),
                    entity.getMaNV(),
                    entity.getMaPhong()
                );
    }

    @Override
    public void update(DatPhong entity) {
        XJdbc.executeUpdate(UPDATE_SQL, 
                    entity.getNgatDatPhong(),
                    entity.getCheckIn(),
                    entity.getCheckOut(),
                    entity.getTongSoPhongDat(),
                    entity.getMaKH(),
                    entity.getMaNV(),
                    entity.getMaPhong(),
                    entity.getMaDatPhong()
                );
    }

    @Override
    public void delete(Integer key) {
        XJdbc.executeUpdate(DELETE_SQL, key);
    }

    @Override
    public List<DatPhong> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public DatPhong selectById(Integer key) {
        List<DatPhong> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public DatPhong selectTop1() {
        List<DatPhong> list = this.selectBySql(SELECT_TOP1_SQL);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<DatPhong> selectBySql(String sql, Object... args) {
        List<DatPhong> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                DatPhong entity = new DatPhong();
                entity.setMaDatPhong(rs.getInt("id"));
                entity.setNgatDatPhong(rs.getDate("bk_date"));
                entity.setCheckIn(rs.getTimestamp("checkin_date"));
                entity.setCheckOut(rs.getTimestamp("checkout_date"));
                entity.setTongSoPhongDat(rs.getInt("total_room"));
                entity.setMaKH(rs.getInt("customer_id"));
                entity.setMaNV(rs.getString("staff_id"));
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
