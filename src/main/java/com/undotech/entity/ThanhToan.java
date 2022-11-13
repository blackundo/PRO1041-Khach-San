
package com.undotech.entity;

/**
 *
 * @author Vox
 */
public class ThanhToan {
    private int maThanhToan;
    private String loaiThanhToan;
    private double tongTienThanhToan;
    private int maDanhGia;
    private int maDatPhong;

    public ThanhToan() {
    }

    public ThanhToan(int maThanhToan, String loaiThanhToan, double tongTienThanhToan, int maDanhGia, int maDatPhong) {
        this.maThanhToan = maThanhToan;
        this.loaiThanhToan = loaiThanhToan;
        this.tongTienThanhToan = tongTienThanhToan;
        this.maDanhGia = maDanhGia;
        this.maDatPhong = maDatPhong;
    }

    public int getMaThanhToan() {
        return maThanhToan;
    }

    public void setMaThanhToan(int maThanhToan) {
        this.maThanhToan = maThanhToan;
    }

    public String getLoaiThanhToan() {
        return loaiThanhToan;
    }

    public void setLoaiThanhToan(String loaiThanhToan) {
        this.loaiThanhToan = loaiThanhToan;
    }

    public double getTongTienThanhToan() {
        return tongTienThanhToan;
    }

    public void setTongTienThanhToan(double tongTienThanhToan) {
        this.tongTienThanhToan = tongTienThanhToan;
    }

    public int getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(int maDanhGia) {
        this.maDanhGia = maDanhGia;
    }

    public int getMaDatPhong() {
        return maDatPhong;
    }

    public void setMaDatPhong(int maDatPhong) {
        this.maDatPhong = maDatPhong;
    }

    
}
