
package com.undotech.entity;
/**
 *
 * @author Vox
 */
public class NhanVien {
    private String maNV;
    private String tenNV;
    private String vaiTro;
    private String soDT;
    private String email;
    private String diaChi;
    private String taiKhoan;
    private String matKhau;
    private String hinhAnh;

    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, String vaiTro, String soDT, String email, String diaChi, String taiKhoan, String matKhau, String hinhAnh) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.vaiTro = vaiTro;
        this.soDT = soDT;
        this.email = email;
        this.diaChi = diaChi;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.hinhAnh = hinhAnh;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    
    
}
