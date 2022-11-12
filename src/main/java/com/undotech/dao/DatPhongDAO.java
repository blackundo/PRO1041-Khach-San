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
    
    String INSERT_SQL = "INSERT INTO DatPhong(NgayDatPhong, Checkin_date, Checkout_date, TongSoPhongDat, MaTT, MaDV, MaKH, MaNV) VALUES(?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE DatPhong SET NgayDatPhong=?, Checkin_date=?, Checkout_date=?, TongSoPhongDat=?, MaTT=?, MaDV=?, MaKH=?, MaNV=? WHERE MaDatPhong=?";
    String DELETE_SQL = "DELETE FROM DatPhong WHERE MaDatPhong=?";
    String SELECT_ALL_SQL = "SELECT * FROM DatPhong";
    String SELECT_BY_ID_SQL = "SELECT * FROM DatPhong WHERE MaDatPhong=?";

    @Override
    public void insert(DatPhong entity) {
        XJdbc.executeUpdate(INSERT_SQL, 
                    entity.getNgatDatPhong(),
                    entity.getCheckIn(),
                    entity.getCheckOut(),
                    entity.getTongSoPhongDat(),
                    entity.getMaTT(),
                    entity.getMaDV(),
                    entity.getMaKH(),
                    entity.getMaNV()
                );
    }

    @Override
    public void update(DatPhong entity) {
        XJdbc.executeUpdate(UPDATE_SQL, 
                    entity.getNgatDatPhong(),
                    entity.getCheckIn(),
                    entity.getCheckOut(),
                    entity.getTongSoPhongDat(),
                    entity.getMaTT(),
                    entity.getMaDV(),
                    entity.getMaKH(),
                    entity.getMaNV(),
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

    @Override
    protected List<DatPhong> selectBySql(String sql, Object... args) {
        List<DatPhong> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                DatPhong entity = new DatPhong();
                entity.setMaDatPhong(rs.getInt("MaDatPhong"));
                entity.setNgatDatPhong(rs.getDate("NgayDatPhong"));
                entity.setCheckIn(rs.getDate("Checkin_date"));
                entity.setCheckOut(rs.getDate("Checkout_date"));
                entity.setTongSoPhongDat(rs.getInt("TongSoPhongDat"));
                entity.setMaTT(rs.getInt("MaTT"));
                entity.setMaDV(rs.getInt("MaDV"));
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setMaNV(rs.getString("MaNV"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
