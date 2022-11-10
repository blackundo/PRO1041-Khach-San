
package com.undotech.entity;

import java.util.Date;

/**
 *
 * @author Vox
 */
public class DatPhong {
    private int maDatPhong;
    private Date ngatDatPhong = new Date();
    private Date checkIn;
    private Date checkOut;
    private int tongSoPhongDat;
    private int maTT;
    private int maDV;
    private int maKH;
    private String maNV;

    public DatPhong() {
    }

    public DatPhong(int maDatPhong, Date checkIn, Date checkOut, int tongSoPhongDat, int maTT, int maDV, int maKH, String maNV) {
        this.maDatPhong = maDatPhong;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.tongSoPhongDat = tongSoPhongDat;
        this.maTT = maTT;
        this.maDV = maDV;
        this.maKH = maKH;
        this.maNV = maNV;
    }

    public int getMaDatPhong() {
        return maDatPhong;
    }

    public void setMaDatPhong(int maDatPhong) {
        this.maDatPhong = maDatPhong;
    }

    public Date getNgatDatPhong() {
        return ngatDatPhong;
    }

    public void setNgatDatPhong(Date ngatDatPhong) {
        this.ngatDatPhong = ngatDatPhong;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public int getTongSoPhongDat() {
        return tongSoPhongDat;
    }

    public void setTongSoPhongDat(int tongSoPhongDat) {
        this.tongSoPhongDat = tongSoPhongDat;
    }

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    
    
}
