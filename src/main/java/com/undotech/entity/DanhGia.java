
package com.undotech.entity;

/**
 *
 * @author Vox
 */
public class DanhGia {
    private int maDanhGia;
    private String loaiDanhGia;

    public DanhGia() {
    }

    public DanhGia(int maDanhGia, String loaiDanhGia) {
        this.maDanhGia = maDanhGia;
        this.loaiDanhGia = loaiDanhGia;
    }

    public int getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(int maDanhGia) {
        this.maDanhGia = maDanhGia;
    }

    public String getLoaiDanhGia() {
        return loaiDanhGia;
    }

    public void setLoaiDanhGia(String loaiDanhGia) {
        this.loaiDanhGia = loaiDanhGia;
    }
}
