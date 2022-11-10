
package com.undotech.entity;

/**
 *
 * @author Vox
 */
public class TienNghi {
    private int maTienNghi;
    private String tenTienNghi;
    private double gia;
    private String moTa;
    private String maPhong;

    public TienNghi() {
    }

    public TienNghi(int maTienNghi, String tenTienNghi, double gia, String moTa, String maPhong) {
        this.maTienNghi = maTienNghi;
        this.tenTienNghi = tenTienNghi;
        this.gia = gia;
        this.moTa = moTa;
        this.maPhong = maPhong;
    }

    public int getMaTienNghi() {
        return maTienNghi;
    }

    public void setMaTienNghi(int maTienNghi) {
        this.maTienNghi = maTienNghi;
    }

    public String getTenTienNghi() {
        return tenTienNghi;
    }

    public void setTenTienNghi(String tenTienNghi) {
        this.tenTienNghi = tenTienNghi;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }
    
    
}
