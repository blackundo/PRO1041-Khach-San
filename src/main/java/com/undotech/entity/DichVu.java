
package com.undotech.entity;

/**
 *
 * @author Vox
 */
public class DichVu {
    private int maDV;
    private String tenDV;
    private double giaDV;
    private String moTaDV;

    public DichVu() {
    }

    public DichVu(int maDV, String tenDV, double giaDV, String moTaDV) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.giaDV = giaDV;
        this.moTaDV = moTaDV;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public double getGiaDV() {
        return giaDV;
    }

    public void setGiaDV(double giaDV) {
        this.giaDV = giaDV;
    }

    public String getMoTaDV() {
        return moTaDV;
    }

    public void setMoTaDV(String moTaDV) {
        this.moTaDV = moTaDV;
    }
    
    
}
