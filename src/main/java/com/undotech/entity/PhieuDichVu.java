/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.entity;

import java.util.Date;

/**
 *
 * @author Vox
 */
public class PhieuDichVu {
    private int maPhieuDV;
    private int maDichVu;
    private String maPhong;
    private Date tgDatPhong = new Date();

    public PhieuDichVu() {
    }

    public PhieuDichVu(int maPhieuDV, int maDichVu, String maPhong) {
        this.maPhieuDV = maPhieuDV;
        this.maDichVu = maDichVu;
        this.maPhong = maPhong;
    }

    

    public int getMaPhieuDV() {
        return maPhieuDV;
    }

    public void setMaPhieuDV(int maPhieuDV) {
        this.maPhieuDV = maPhieuDV;
    }

    public int getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(int maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public Date getTgDatPhong() {
        return tgDatPhong;
    }

    public void setTgDatPhong(Date tgDatPhong) {
        this.tgDatPhong = tgDatPhong;
    }
    
    
}
