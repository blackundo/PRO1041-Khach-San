/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.entity;

/**
 *
 * @author Vox
 */
public class TrangThaiPhong {
    private String maPhong;
    private boolean DonDep;
    private boolean suaChua;

    public TrangThaiPhong() {
    }

    public TrangThaiPhong(String maPhong, boolean DonDep, boolean suaChua) {
        this.maPhong = maPhong;
        this.DonDep = DonDep;
        this.suaChua = suaChua;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public boolean isDonDep() {
        return DonDep;
    }

    public void setDonDep(boolean DonDep) {
        this.DonDep = DonDep;
    }

    public boolean isSuaChua() {
        return suaChua;
    }

    public void setSuaChua(boolean suaChua) {
        this.suaChua = suaChua;
    }
    
    
}
