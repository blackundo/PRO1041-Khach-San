/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

import com.undotech.entity.ThanhToan;
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
    
    String INSERT_SQL = "INSERT INTO TienNghi(MaTienNghi, TenTiennghi, Gia, MoTa, MaPhong) VALUES(?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE TienNghi SET TenTienNghi=?, Gia=?, MoTa=?, MaPhong=? WHERE MaTienNghi=?";
    String DELETE_SQL = "DELETE FROM TienNghi WHERE MaTienNghi=?";
    String SELECT_ALL_SQL = "SELECT * FROM TienNghi";
    String SELECT_BY_ID_SQL = "SELECT * FROM TienNghi WHERE MaTienNghi=?";

    @Override
    public void insert(TienNghi entity) {
        XJdbc.executeUpdate(INSERT_SQL, 
                    entity.getMaTienNghi(),
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
        XJdbc.executeQuery(DELETE_SQL, key);
    }

    @Override
    public List<TienNghi> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
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
                entity.setMaTienNghi(rs.getInt("MaTienNghi"));
                entity.setTenTienNghi(rs.getString("TenTienNghi"));
                entity.setGia(rs.getDouble("Gia"));
                entity.setMoTa(rs.getString("Mota"));
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
