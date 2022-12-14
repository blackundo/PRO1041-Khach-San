
package com.undotech.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Vox
 */
public class DatPhong {
    private int maDatPhong;
    private Date ngayDatPhong = new Date();
    private Timestamp checkIn;
    private Timestamp checkOut;
    private int tongSoPhongDat;
    private int maKH;
    private String maNV;
    private String maPhong;

    public DatPhong() {
    }

    public DatPhong(int maDatPhong, Timestamp checkIn, Timestamp checkOut, int tongSoPhongDat, int maKH, String maNV, String maPhong) {
        this.maDatPhong = maDatPhong;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.tongSoPhongDat = tongSoPhongDat;
        this.maKH = maKH;
        this.maNV = maNV;
        this.maPhong = maPhong;
    }

    public int getMaDatPhong() {
        return maDatPhong;
    }

    public void setMaDatPhong(int maDatPhong) {
        this.maDatPhong = maDatPhong;
    }

    public Date getNgatDatPhong() {
        return ngayDatPhong;
    }

    public void setNgatDatPhong(Date ngatDatPhong) {
        this.ngayDatPhong = ngatDatPhong;
    }

    public Timestamp getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Timestamp checkIn) {
        this.checkIn = checkIn;
    }

    public Timestamp getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Timestamp checkOut) {
        this.checkOut = checkOut;
    }

    public int getTongSoPhongDat() {
        return tongSoPhongDat;
    }

    public void setTongSoPhongDat(int tongSoPhongDat) {
        this.tongSoPhongDat = tongSoPhongDat;
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

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    
}
