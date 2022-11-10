
package com.undotech.entity;

import java.util.Date;

/**
 *
 * @author Vox
 */
public class ChietKhau {
    private int maChietKhau;
    private double phanTramCK;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String maPhong;

    public ChietKhau() {
    }

    public ChietKhau(int maChietKhau, double phanTramCK, Date ngayBatDau, Date ngayKetThuc, String maPhong) {
        this.maChietKhau = maChietKhau;
        this.phanTramCK = phanTramCK;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.maPhong = maPhong;
    }

    public int getMaChietKhau() {
        return maChietKhau;
    }

    public void setMaChietKhau(int maChietKhau) {
        this.maChietKhau = maChietKhau;
    }

    public double getPhanTramCK() {
        return phanTramCK;
    }

    public void setPhanTramCK(double phanTramCK) {
        this.phanTramCK = phanTramCK;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }
    
    
}
