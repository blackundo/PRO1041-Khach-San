package com.undotech.dao;

import com.undotech.entity.NhanVien;
import com.undotech.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blackundo
 */
public class NhanVienDAO extends QLyKhachSanDAO<NhanVien, String>{
    
    String INSERT_SQL = "INSERT INTO staff(id, full_name, role, phone, email, address, username, pass, img) VALUES(?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE staff SET full_name=?, role=?, phone=?, email=?, address=?, username=?, pass=?, img=? WHERE id=?";
    String DELETE_SQL = "DELETE FROM staff WHERE id=?";
    String SELECT_BY_ID_SQL = "SELECT * FROM staff WHERE id=?";
    String SELECT_BY_USERNAME_SQL = "SELECT * FROM staff WHERE username=?";
    String SELECT_ALL_SQL = "SELECT * FROM staff";

    @Override
    public void insert(NhanVien entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getMaNV(),
                entity.getTenNV(),
                entity.getVaiTro(),
                entity.getSoDT(),
                entity.getEmail(),
                entity.getDiaChi(),
                entity.getTaiKhoan(),
                entity.getMatKhau(),
                entity.getHinhAnh()
        );
    }

    @Override
    public void update(NhanVien entity) {
        XJdbc.executeUpdate(UPDATE_SQL, 
                entity.getTenNV(),
                entity.getVaiTro(),
                entity.getSoDT(),
                entity.getEmail(),
                entity.getDiaChi(),
                entity.getTaiKhoan(),
                entity.getMatKhau(),
                entity.getHinhAnh(),
                entity.getMaNV()
                );
    }

    @Override
    public void delete(String key) {
        XJdbc.executeUpdate(DELETE_SQL, key);
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectById(String key) {
        List<NhanVien> list = this.selectBySql(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    //tai khoan
    public NhanVien selectByUsername(String key){
        List<NhanVien> list = this.selectBySql(SELECT_BY_USERNAME_SQL, key);
            if (list.isEmpty()) {
                return null;
            }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("id"));
                entity.setTenNV(rs.getString("full_name"));
                entity.setVaiTro(rs.getString("role"));
                entity.setSoDT(rs.getString("phone"));
                entity.setEmail(rs.getString("email"));
                entity.setDiaChi(rs.getString("address"));
                entity.setTaiKhoan(rs.getString("username"));
                entity.setMatKhau(rs.getString("pass"));
                entity.setHinhAnh(rs.getString("img"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
