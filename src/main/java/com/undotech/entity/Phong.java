
package com.undotech.entity;

import java.util.Date;

/**
 *
 * @author Vox
 */
public class Phong {
    private String maPhong;
    private int soPhong;
    private double giaPhong;
    private String kieuPhong;
    private String moTa;
    private int maDatPhong;

    public Phong() {
    }

    public Phong(String maPhong, int soPhong, double giaPhong, String kieuPhong, String moTa, int maDatPhong) {
        this.maPhong = maPhong;
        this.soPhong = soPhong;
        this.giaPhong = giaPhong;
        this.kieuPhong = kieuPhong;
        this.moTa = moTa;
        this.maDatPhong = maDatPhong;
    }

    public Phong(String maPhong, int maDatPhong) {
        this.maPhong = maPhong;
        this.maDatPhong = maDatPhong;
    }

    public Phong(String maPhong) {
        this.maPhong = maPhong;
    }
    
    

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public double getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(double giaPhong) {
        this.giaPhong = giaPhong;
    }

    public String getKieuPhong() {
        return kieuPhong;
    }

    public void setKieuPhong(String kieuPhong) {
        this.kieuPhong = kieuPhong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getMaDatPhong() {
        return maDatPhong;
    }

    public void setMaDatPhong(int maDatPhong) {
        this.maDatPhong = maDatPhong;
    }

    
}
