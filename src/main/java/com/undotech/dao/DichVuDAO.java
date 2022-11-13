/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.dao;

import com.undotech.entity.DichVu;
import com.undotech.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blackundo
 */
public class DichVuDAO extends QLyKhachSanDAO<DichVu, Integer>{
    
    String INSERT_SQL = "INSERT INTO service(name, price, description) VALUES(?,?,?)";
    String UPDATE_SQL = "UPDATE service SET name=?, price=?, description=? WHERE id=?";
    String DELETE_SQL = "DELETE FROM service WHERE id=?";
    String SELECT_ALL_SQL = "SELECT * FROM service";
    String SELECT_BY_ID_SQL = "SELECT * FROM service WHERE id=?";

    @Override
    public void insert(DichVu entity) {
        XJdbc.executeUpdate(INSERT_SQL, 
                    entity.getTenDV(),
                    entity.getGiaDV(),
                    entity.getMoTaDV()
                );
    }

    @Override
    public void update(DichVu entity) {
        XJdbc.executeUpdate(UPDATE_SQL, 
                    entity.getTenDV(),
                    entity.getGiaDV(),
                    entity.getMoTaDV(),
                    entity.getMaDV()
                );
    }

    @Override
    public void delete(Integer key) {
        XJdbc.executeUpdate(DELETE_SQL, key);
    }

    @Override
    public List<DichVu> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public DichVu selectById(Integer key) {
        List<DichVu> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<DichVu> selectBySql(String sql, Object... args) {
        List<DichVu> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                DichVu entity = new DichVu();
                entity.setMaDV(rs.getInt("id"));
                entity.setTenDV(rs.getString("name"));
                entity.setGiaDV(rs.getDouble("price"));
                entity.setMoTaDV(rs.getString("description"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
