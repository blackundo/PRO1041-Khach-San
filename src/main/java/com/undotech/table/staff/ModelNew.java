/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.undotech.table.staff;

import javax.swing.Icon;

/**
 *
 * @author blackundo
 */
public class ModelNew extends Object{
    private String hoTen;
    private String hinhAnh;

    public ModelNew() {
    }

    public ModelNew(String hoTen, String hinhAnh) {
        this.hoTen = hoTen;
        this.hinhAnh = hinhAnh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return hoTen+"||"+hinhAnh;
    }

    
    
    
}
